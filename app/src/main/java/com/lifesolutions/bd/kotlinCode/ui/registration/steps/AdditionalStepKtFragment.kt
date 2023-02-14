package com.lifesolutions.bd.kotlinCode.ui.registration.steps

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.*
import com.lifesolutions.bd.kotlinCode.ui.intro.IntroKtActivity
import com.lifesolutions.bd.kotlinCode.utils.Utils
import com.lifesolutions.bd.kotlinCode.utils.ViewProgressDialog
import com.lifesolutions.bd.kotlinCode.utils.toast
import kotlinx.android.synthetic.main.fragment_additional_step_kt.*
import java.util.*


class AdditionalStepKtFragment : Fragment() {
    private val TAG = "AdditionalStepFragment"

    // Progress Dialog...
    private lateinit var progress: ViewProgressDialog

    // Birthdate Picker..
    private var dateSetListener: OnDateSetListener? = null


    private lateinit var preferences: SharedPreferences

    // Firebase Auth & Firebase DB...
    // private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var dbRef: DatabaseReference
    // Firebase DB
    private lateinit var database: FirebaseDatabase

    // User Input Data..
    private var birthDate: Long? = null
    private var address: String? = null
    private var studyInfo: String? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(com.lifesolutions.bd.R.layout.fragment_additional_step_kt, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Initialize Progress Dialog
        progress = ViewProgressDialog(requireContext())

        // Initialize Auth and Firebase..
        database = FirebaseDatabase.getInstance()
        // firebaseAuth = FirebaseAuth.getInstance()
        dbRef = FirebaseDatabase.getInstance().reference.child("Users")

        preferences = activity?.getSharedPreferences("UserInfo", Context.MODE_PRIVATE)!!


        // On Exit Show Dialog...
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                openAlertDialog()

            }
        })

        dateSetListener = OnDateSetListener { _: DatePicker?, i: Int, i1: Int, i2: Int ->
            Calendar.getInstance().set(i, i1, i2)
            birthDate = Calendar.getInstance().timeInMillis

            if (birthDate != null) {
                tv_birthdate_picked.text = Utils.getNormalDate(birthDate!!)
                text_float_birthdate.visibility = View.VISIBLE
                // tv_birthdate_picked.setTextColor(resources.getColor(com.lifesolutions.bd.R.color.black))
                tv_birthdate_picked.setTextColor(ContextCompat.getColor(requireContext(), com.lifesolutions.bd.R.color.black))
                tv_birthdate_picked.error = null
            }
        }

        val datePicker: LinearLayout = requireView().findViewById(com.lifesolutions.bd.R.id.birthdate_picker_layout)
        datePicker.setOnClickListener {
            val c = Calendar.getInstance()
            val mYear = c[Calendar.YEAR]
            val mMonth = c[Calendar.MONTH]
            val mDay = c[Calendar.DAY_OF_MONTH]

            // android.R.style.Theme_Holo_Light_Dialog_MinWidth,
            val datePickerDialog = DatePickerDialog(
                requireContext(),
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                dateSetListener, mYear, mMonth, mDay
            )

            datePickerDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            datePickerDialog.show()
            datePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setBackgroundColor(Color.TRANSPARENT)
            datePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(Color.WHITE)

        }


        btn_reg_done.setOnClickListener {
            address = et_address_reg_kt.text.toString()
            studyInfo = et_study_reg_kt.text.toString()
            val referCode = et_refer_reg_kt.text.toString()


            if (address!!.isEmpty()) {
                et_address_reg_kt.error = "Address is required"
                et_address_reg_kt.requestFocus()
                return@setOnClickListener
            }

            if (birthDate == null) {
                tv_birthdate_picked.error = "Birthdate is required"
                tv_birthdate_picked.requestFocus()
                Snackbar.make(additional_reg_step_layout, "Birthdate is required", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            progress.showLoadingBar("Please wait...")
            if (referCode.isNotEmpty()) {
                checkReferCode(referCode)
            } else {
                updateUserInfo()
            }


        }

        // Button Skip..
        btn_skip_additional.setOnClickListener {
            // Navigate...
            navigateToIntroActivity()
        }


    }


    private fun updateUserInfo() {
        // val authId = firebaseAuth.currentUser?.uid
        val authId = preferences.getString("uID", null)

        val userFieldMap = mutableMapOf<String, Any>()


        userFieldMap["address"] = address!!
        userFieldMap["studyInfo"] = studyInfo!!
        userFieldMap["birthDate"] = birthDate!!

        if (authId != null) {
            dbRef.child(authId).updateChildren(userFieldMap).addOnSuccessListener {
                activity?.toast("Registration Success.")
                progress.dismissLoadingBar()
                // Navigate...
                navigateToIntroActivity()
            }
        } else {
            progress.dismissLoadingBar()
            activity?.toast("Authentication Error!!")
        }

    }


//    private fun checkReferCode(referCode: String) {
//
//        val currentUserId = preferences.getString("uID", null)
//
//        val query: Query = dbRef.orderByChild("referCode").equalTo(referCode)
//
//        query.addListenerForSingleValueEvent(object : ValueEventListener{
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                if (dataSnapshot.exists()) {
//                    val children = dataSnapshot.children
//                    var referUserInfo: UserKt? = null
//
//                    children.forEach {
//                        referUserInfo = it.getValue(UserKt::class.java)
//                        Log.d(TAG, referUserInfo?.id.toString())
//                    }
//
//                    dbRef.addListenerForSingleValueEvent(object : ValueEventListener{
//
//                        override fun onDataChange(snapshot: DataSnapshot) {
//                            if (snapshot.child(currentUserId!!).child("referable").getValue(Boolean::class.java)!!) {
//                                giveBonus(referUserInfo?.id!!)
//                                getBonus(currentUserId)
//                                updateUserInfo(true)
//                            } else {
//                                Snackbar.make(additional_reg_step_layout, "You are not allow to add refer code", Snackbar.LENGTH_LONG).show()
//                                // requireContext().toast("You are not allow to add refer code")
//                                progress.dismissLoadingBar()
//                            }
//                        }
//
//                        override fun onCancelled(err: DatabaseError) {
//                            Log.d(TAG, err.message)
//                            requireContext().toast("Something went wrong.")
//                            progress.dismissLoadingBar()
//                        }
//
//                    })
//                } else {
//                    progress.dismissLoadingBar()
//                    et_refer_reg_kt.error = "Invalid Refer Code!"
//                    et_refer_reg_kt.requestFocus()
//                    requireContext().toast("You can add this refer code latter")
//                }
//            }
//
//            override fun onCancelled(err: DatabaseError) {
//                progress.dismissLoadingBar()
//                Log.d(TAG, err.message)
//                requireContext().toast("Something went wrong when check refer code. Try again")
//            }
//
//        })
//    }

    private fun checkReferCode(referCode: String) {
        val ref = database.getReference("ReferArea").child(referCode)

        ref.addListenerForSingleValueEvent(object: ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    val referId = dataSnapshot.getValue(String::class.java)
                    giveBonus(referId!!)
                    getBonus()
                    // Update...
                    updateUserInfo()
                } else {
                    et_refer_reg_kt.error = "Invalid Refer Code!"
                    et_refer_reg_kt.requestFocus()
                    progress.dismissLoadingBar()
                    Snackbar.make(additional_reg_step_layout, "This refer code is not valid", Snackbar.LENGTH_LONG).show()
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                progress.dismissLoadingBar()
            }

        })
    }


    private fun giveBonus(refererId: String) {
        var points = 0
        var referred = 0
        dbRef.child(refererId).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                if (dataSnapshot.exists()) {
                    points = dataSnapshot.child("points").getValue(Int::class.java)!!
                    referred = dataSnapshot.child("referred").getValue(Int::class.java)!!

                    points += 25
                    referred += 1
                    dbRef.child(refererId).child("points").setValue(points)
                    dbRef.child(refererId).child("referred").setValue(referred)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(
                    requireContext(),
                    "Something went wrong, Failed to sent bonus points",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    private fun getBonus() {
        val currentUserId = preferences.getString("uID", null)
        var points = 0
        dbRef.child(currentUserId!!).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    points = dataSnapshot.child("points").getValue(Int::class.java)!!
                    points += 50
                    dbRef.child(currentUserId).child("points").setValue(points)
                    dbRef.child(currentUserId).child("useReferCode").setValue(true)

                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(
                    requireContext(),
                    "Something went wrong, Failed to sent bonus points",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }


    private fun openAlertDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Are you want to leave this step?")
            .setMessage("You can add additional information latter.")
            .setNeutralButton("Cancel") { dialog, which ->
                // Respond to neutral button press
            }
            .setPositiveButton("Skip Now") { dialog, which ->
                // Navigate...
                navigateToIntroActivity()

            }
            .show()
    }

    private fun navigateToIntroActivity() {
        Intent(requireContext(), IntroKtActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(this)
        }
    }


}
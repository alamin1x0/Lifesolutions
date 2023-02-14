package com.lifesolutions.bd.kotlinCode.ui.user

import android.app.DatePickerDialog
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.DatePicker
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.lifesolutions.bd.kotlinCode.data.model.UserKt
import com.lifesolutions.bd.kotlinCode.utils.Utils
import com.lifesolutions.bd.kotlinCode.utils.ViewProgressDialog
import com.lifesolutions.bd.kotlinCode.utils.toast
import kotlinx.android.synthetic.main.activity_edit_profile_info_kt.*
import java.util.*


class EditProfileInfoKtActivity : AppCompatActivity() {

    private val TAG = "EditProfileInfoActivity"

    private lateinit var userPreferences: SharedPreferences

    // Progress Dialog...
    private lateinit var progress: ViewProgressDialog

    // Firebase DB...
    private lateinit var dbRef: DatabaseReference
    private var authId: String? = null

    // Birthdate Picker..
    private var dateSetListener: DatePickerDialog.OnDateSetListener? = null
    private var birthDate: Long? = null


    private val GENDERS = arrayOf("Male", "Female", "Others")
    private val BLOODS = arrayOf("A+", "A-","B+", "B-", "O+", "O-", "AB+", "AB-")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.lifesolutions.bd.R.layout.activity_edit_profile_info_kt)

        // Shared Preferences
        userPreferences = getSharedPreferences("com.starnote.CurrentAuthUser", Context.MODE_PRIVATE)!!

        // Initialize Progress Dialog
        progress = ViewProgressDialog(this)

        // Initialize Firebase..
        dbRef = FirebaseDatabase.getInstance().reference.child("Users")
        authId = FirebaseAuth.getInstance().currentUser?.uid

        // Support Action bar..
        supportActionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(this, com.lifesolutions.bd.R.color.white)))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Edit Profile Info"

        // Selectable Data..
        initializeSelectableItem()
        // Date Picker
        initializeDatePicker()

        setExistsUserData()


    }

    // Update Info Button Click..
    fun updateUserInfo(view: View) {
        val firstName = et_first_name_edit_kt.text.toString()
        val lastName = et_last_name_edit_kt.text.toString()
        val address = et_address_edit_kt.text.toString()
        val phoneNumber = et_phone_number_edit_kt.text.toString()
        val email = et_email_edit_kt.text.toString()
        val studyInfo = et_study_edit_kt.text.toString()
        val bio = et_bio_edit_kt.text.toString()
        val bloodGroup = et_dropdown_blood_edit_kt.text.toString()
        val gender = et_dropdown_gender_edit_kt.text.toString()

        if(firstName.isEmpty()) {
            et_first_name_edit_kt.error = "First name is required"
            et_first_name_edit_kt.requestFocus()
            return
        }

        if (lastName.isEmpty()) {
            et_last_name_edit_kt.error = "Last name is required"
            et_last_name_edit_kt.requestFocus()
            return
        }

        progress.showLoadingBar("Please wait...")

        val searchName = "${firstName.toLowerCase(Locale.ROOT)} ${lastName.toLowerCase(Locale.ROOT)}"

        // Update User Info..
        val userFieldMap = mutableMapOf<String, Any?>()

        userFieldMap["firstName"] = firstName
        userFieldMap["lastName"] = lastName
        userFieldMap["address"] = address
        userFieldMap["phone"] = phoneNumber
        userFieldMap["email"] = email
        userFieldMap["studyInfo"] = studyInfo
        userFieldMap["bio"] = bio
        userFieldMap["bloodGroup"] = bloodGroup
        userFieldMap["birthDate"] = birthDate
        userFieldMap["gender"] = gender
        userFieldMap["searchName"] = searchName

        if (authId != null) {
            dbRef.child(authId!!).updateChildren(userFieldMap).addOnSuccessListener{
                toast("Profile information updated.")
                saveUserIdToSharedPref(authId!!, firstName, lastName)
                progress.dismissLoadingBar()
                finish()
            }.addOnFailureListener {
                toast("Something went wrong. Please try again")
                Log.d(TAG, it.message.toString())
            }
        }
    }


    private fun setExistsUserData() {

        dbRef.child(authId!!).addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    val data = dataSnapshot.getValue(UserKt::class.java)
                    setUserDataToEditText(data!!)
                } else {
                    toast("No Data Found")
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                toast("Cancel when updating.")
            }


        })
    }

    private fun setUserDataToEditText(user: UserKt) {
        if (!user.firstName.isNullOrEmpty()) {
            et_first_name_edit_kt.setText(user.firstName)
        }

        if (!user.lastName.isNullOrEmpty()) {
            et_last_name_edit_kt.setText(user.lastName)
        }

        if (!user.address.isNullOrEmpty()) {
            et_address_edit_kt.setText(user.address)
        }

        if (!user.phone.isNullOrEmpty()) {
            et_phone_number_edit_kt.setText(user.phone)

            if (user.registrationType == "phone") {
                et_phone_number_edit_kt.inputType = InputType.TYPE_NULL
            }
        }

        if (!user.studyInfo.isNullOrEmpty()) {
            et_study_edit_kt.setText(user.studyInfo)
        }

        if (!user.bio.isNullOrEmpty()) {
            et_bio_edit_kt.setText(user.bio)
        }

        if (!user.gender.isNullOrEmpty()) {
            et_dropdown_gender_edit_kt.setText(user.gender, false)
        }

        if (!user.bloodGroup.isNullOrEmpty()) {
            et_dropdown_blood_edit_kt.setText(user.bloodGroup, false)
        }

        if (user.birthDate != null) {
            birthDate = user.birthDate
            tv_birthdate_picked_edit.text = Utils.getNormalDate(user.birthDate!!)
            text_float_birthdate_edit.visibility = View.VISIBLE
            tv_birthdate_picked_edit.setTextColor(ContextCompat.getColor(this, com.lifesolutions.bd.R.color.black))
        }

        if (!user.email.isNullOrEmpty()) {
            et_email_edit_kt.setText(user.email)
        }
    }


    private fun initializeDatePicker() {

        birthdate_picker_et_layout.setOnClickListener {
            val c = Calendar.getInstance()
            val mYear = c[Calendar.YEAR]
            val mMonth = c[Calendar.MONTH]
            val mDay = c[Calendar.DAY_OF_MONTH]

            // android.R.style.Theme_Holo_Light_Dialog_MinWidth,
            val datePickerDialog = DatePickerDialog(
                this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                dateSetListener, mYear, mMonth, mDay
            )

            datePickerDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            datePickerDialog.show()
            datePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setBackgroundColor(Color.TRANSPARENT)
            datePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(Color.WHITE)

        }


        dateSetListener = DatePickerDialog.OnDateSetListener { datePicker: DatePicker?, year: Int, month: Int, date: Int ->
            Calendar.getInstance().set(year, month, date)

            val cal = Calendar.getInstance()
            cal[Calendar.DAY_OF_MONTH] = datePicker!!.dayOfMonth
            cal[Calendar.MONTH] = datePicker.month
            cal[Calendar.YEAR] = datePicker.year
            val millis = cal.timeInMillis

            Log.w(TAG, "initializeDatePicker: ---> $millis")

            birthDate = millis


            if (birthDate != null) {
                tv_birthdate_picked_edit.text = Utils.getNormalDate(birthDate!!)
                text_float_birthdate_edit.visibility = View.VISIBLE
                // tv_birthdate_picked.setTextColor(resources.getColor(com.lifesolutions.bd.R.color.black))
                tv_birthdate_picked_edit.setTextColor(
                    ContextCompat.getColor(this, com.lifesolutions.bd.R.color.black)
                )
                tv_birthdate_picked_edit.error = null
            }
        }

    }

    private fun initializeSelectableItem() {
        val mGenderAdapter = ArrayAdapter(
            this,
            com.lifesolutions.bd.R.layout.s_gender_dropdown_view,
            GENDERS
        )

        val mBloodAdapter = ArrayAdapter(
            this,
            com.lifesolutions.bd.R.layout.s_gender_dropdown_view,
            BLOODS
        )

        // Material Dropdown Spinner
        val editTextDropdownGender: AutoCompleteTextView = findViewById(com.lifesolutions.bd.R.id.et_dropdown_gender_edit_kt)
        val editTextDropdownBlood: AutoCompleteTextView = findViewById(com.lifesolutions.bd.R.id.et_dropdown_blood_edit_kt)
        editTextDropdownGender.inputType = InputType.TYPE_NULL
        editTextDropdownBlood.inputType = InputType.TYPE_NULL
        editTextDropdownGender.setAdapter(mGenderAdapter)
        editTextDropdownBlood.setAdapter(mBloodAdapter)

    }

    // On Cancel
    fun onCancelEdit(view: View) {
        finish()
    }

    /**
     * Update Shared Preference
     */

    private fun saveUserIdToSharedPref(uID: String, firstName: String, lastName: String) {
        val editor = userPreferences.edit()
        editor.putString("uID", uID)
        editor.putString("firstName", firstName)
        editor.putString("lastName", lastName)
        editor.apply()
    }
}
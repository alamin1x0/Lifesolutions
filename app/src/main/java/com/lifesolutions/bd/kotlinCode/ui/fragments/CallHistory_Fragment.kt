package com.lifesolutions.bd.kotlinCode.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.tamir7.contacts.Contact
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.lifesolutions.bd.Helpers.InternetCheck
import com.lifesolutions.bd.R
import com.lifesolutions.bd.kotlinCode.data.model.CallLog
import com.lifesolutions.bd.kotlinCode.ui.adapter.CallLogAdapter
import com.lifesolutions.bd.kotlinCode.utils.toast
import kotlinx.android.synthetic.main.fragment_call_history_.*
import kotlinx.android.synthetic.main.fragment_call_history_.btn_delete_call_log
import kotlinx.android.synthetic.main.fragment_call_history_.lottie_animation_call_list
import kotlinx.android.synthetic.main.fragment_call_history_.recycler_view_call_list
import kotlinx.android.synthetic.main.fragment_call_history_.tv_no_call_logs
import kotlinx.android.synthetic.main.fragment_call_list.*


class CallHistory_Fragment : Fragment() {
    private lateinit var db: FirebaseDatabase
    private lateinit var auth: FirebaseAuth
    private lateinit var callLogAdapter: CallLogAdapter
    private lateinit var mLayoutManager: LinearLayoutManager

      val finalContacts: List<Contact> = ArrayList()
     val finalContactsID: List<String> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_call_history_, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        // Feed Adapter
        mLayoutManager = LinearLayoutManager(requireContext())
        callLogAdapter =
            CallLogAdapter(
                requireContext()
            )

        recycler_view_call_list.apply {
            setHasFixedSize(true)
            layoutManager = mLayoutManager
            adapter = callLogAdapter
        }

        getCallLists()

        btn_delete_call_log.setOnClickListener {
            openAlertDialog()
        }
    }



    private fun getCallLists() {
        if (!InternetCheck.checkConnection(requireContext())) {
            requireContext().toast("No Internet Connection")
            return
        }

        auth = FirebaseAuth.getInstance()
        db= FirebaseDatabase.getInstance()
        val currentUserId = auth.currentUser?.uid
        val ref = db.getReference("CallLogs").child(currentUserId!!).limitToLast(100)
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val listOfConversation = ArrayList<CallLog>()
                if (dataSnapshot.exists()) {
                    dataSnapshot.children.forEach {
                        listOfConversation.add(it.getValue(CallLog::class.java)!!)
                    }
                    listOfConversation.reverse()

                    // Recycler Adapter Changed..
                    callLogAdapter.removeAllItem()
                    callLogAdapter.addAllConversation(listOfConversation)
                    callLogAdapter.notifyDataSetChanged()

                    lottie_animation_call_list?.visibility = View.GONE
                    tv_no_call_logs?.visibility = View.GONE
                    btn_delete_call_log?.visibility = View.VISIBLE
                    // isLoading = false
                } else {
                    lottie_animation_call_list?.visibility = View.GONE
                    tv_no_call_logs?.visibility = View.VISIBLE
                    btn_delete_call_log?.visibility = View.GONE
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                requireContext().toast("Something went wrong while loading conversations")
            }


        })

    }
    private fun openAlertDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Are you want to delete call logs?")
            .setNeutralButton("Cancel") { dialog, which ->
                // Respond to neutral button press
            }
            .setNegativeButton("Delete") { dialog, which ->
                deleteCallLogs()
            }
            .show()
    }

    private fun deleteCallLogs() {
        val currentUserId = auth.currentUser?.uid
        val ref = db.getReference("CallLogs").child(currentUserId!!)
        ref.removeValue().addOnSuccessListener {
            callLogAdapter.removeAllItem()
            callLogAdapter.notifyDataSetChanged()
            activity?.toast("Successfully remove all call logs")
        }
    }

}
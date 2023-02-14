package com.lifesolutions.bd.kotlinCode.ui.home.notification

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.lifesolutions.bd.Adapters.NotificationListAdapter
import com.lifesolutions.bd.Models.NotificationInApp
import com.lifesolutions.bd.kotlinCode.ui.home.MainActivity
import com.lifesolutions.bd.kotlinCode.utils.toast
import kotlinx.android.synthetic.main.notification_fragment.*
import java.util.*

class NotificationFragment : Fragment() {

    private val TAG = "NotificationFragment"

    private lateinit var viewModel: NotificationViewModel

    private var list: ArrayList<NotificationInApp>? = null
    var notificationAdapter: NotificationListAdapter? = null

    private var mLayoutManager: LinearLayoutManager? = null

    private lateinit var databaseReference: DatabaseReference
    private var uID: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(com.lifesolutions.bd.R.layout.notification_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NotificationViewModel::class.java)


        uID = FirebaseAuth.getInstance().currentUser?.uid
        databaseReference = FirebaseDatabase.getInstance().reference.child("Notifications")

        list = ArrayList()

        mLayoutManager = LinearLayoutManager(requireContext())
        mLayoutManager!!.reverseLayout = true
        mLayoutManager!!.stackFromEnd = true

        recyclerView_notifications_kt.layoutManager = mLayoutManager
        recyclerView_notifications_kt.setHasFixedSize(true)

        getNotificationList()

        text_btn_clear_notification.setOnClickListener {
            openAlertDialog()
        }
    }


    private fun getNotificationList() {

        val queryRef = databaseReference.child(uID!!).orderByKey().limitToLast(100)

        Log.d(TAG, "$uID")

        if (uID != null) {
            queryRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    list?.clear()

                    for (dataSnapshot1 in dataSnapshot.children) {
                        val notification = dataSnapshot1.getValue(NotificationInApp::class.java)

                        list?.add(notification!!)
                    }
                    if (list?.size == 0) {
                        progressbar_notifications_kt?.visibility = View.GONE
                        text_btn_clear_notification?.visibility = View.GONE
                        tv_no_notification_kt?.visibility = View.VISIBLE
                    } else {
                        progressbar_notifications_kt?.visibility = View.GONE

                        notificationAdapter = NotificationListAdapter(context, list)
                        recyclerView_notifications_kt?.adapter = notificationAdapter
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {}
            })
        }
    }


    private fun clearAllNotifications() {
        databaseReference.child(uID!!).removeValue().addOnSuccessListener {
            (activity as MainActivity?)?.showNotificationBadge(0, false)
            context?.toast("Success! all notification has been removed")
        }
    }


    private fun openAlertDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Clear")
        builder.setCancelable(false)
        builder.setMessage("Are you sure to clear all notification?")
        builder.setPositiveButton(
            "Yes"
        ) { _: DialogInterface?, _: Int ->
            clearAllNotifications()

        }.setNegativeButton(
            "No"
        ) { dialog: DialogInterface, _: Int -> dialog.dismiss() }
        builder.show()
    }

}
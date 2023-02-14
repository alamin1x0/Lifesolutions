package com.lifesolutions.bd.kotlinCode.ui.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.algolia.search.saas.Client
import com.algolia.search.saas.Query
import com.github.tamir7.contacts.Contact
import com.github.tamir7.contacts.Contacts
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.lifesolutions.bd.Adapters.ContactListAdapter
import com.lifesolutions.bd.Helpers.InternetCheck
import com.lifesolutions.bd.R
import com.lifesolutions.bd.kotlinCode.data.model.CallLog
import com.lifesolutions.bd.kotlinCode.ui.adapter.CallLogAdapter
import com.lifesolutions.bd.kotlinCode.utils.toast
import kotlinx.android.synthetic.main.contact_list.*
import kotlinx.android.synthetic.main.fragment_call_list.*
import org.json.JSONException
import java.util.*
import kotlin.collections.ArrayList


class CallListFragment : Fragment() {

    private val TAG = "CallListFragment"

    private lateinit var db: FirebaseDatabase
    private lateinit var auth: FirebaseAuth

    private lateinit var callLogAdapter: CallLogAdapter
    private lateinit var mLayoutManager: LinearLayoutManager


    //adiing new
    var client =
        Client("2NBFB3NK8Q", "a28846e3d5e638812cc8cdc66dd62073")
    var index = client.getIndex("PhoneNos")
    var nos =
        HashMap<String, String>()
    var ids =
        HashMap<String, String>()
    //  val finalContacts: List<Contact> = ArrayList()
    // val finalContactsID: List<String> = ArrayList()

    val finalContacts = ArrayList<Contact>()//Creating an empty arraylist
    val finalContactsID = ArrayList<String>()//Creating an empty arraylist

    var progressBar: ProgressBar? = null
    var recyclerView: RecyclerView? = null
    private val READ_CONTACT_PERMISSION_REQUEST_CODE = 76

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //  return inflater.inflate(R.layout.fragment_call_list, container, false)
        return inflater.inflate(R.layout.contact_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        db = FirebaseDatabase.getInstance()
//        auth = FirebaseAuth.getInstance()

        //adding sync codes
        Contacts.initialize(activity?.applicationContext)




        progressBar = simpleProgressBar
        recyclerView = test_view
        recyclerView!!.layoutManager = LinearLayoutManager(context)




        checkPermission()
        // Feed Adapter
//        mLayoutManager = LinearLayoutManager(requireContext())
//        callLogAdapter =
//            CallLogAdapter(
//                requireContext()
//            )
//
//        recycler_view_call_list.apply {
//            setHasFixedSize(true)
//            layoutManager = mLayoutManager
//            adapter = callLogAdapter
//        }
//
//        getCallLists()
//
//        btn_delete_call_log.setOnClickListener {
//            openAlertDialog()
//        }

    }


    private fun getCallLists() {
        if (!InternetCheck.checkConnection(requireContext())) {
            requireContext().toast("No Internet Connection")
            return
        }

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


    //function for sync
    private fun checkPermission(): Unit {
        if (activity?.applicationContext?.let {
                ContextCompat.checkSelfPermission(
                    it,
                    Manifest.permission.READ_CONTACTS
                )
            } ==
            PackageManager.PERMISSION_GRANTED
        ) {

            // AlgoliaSearch()
            val q =
                Contacts.getQuery()
            q.hasPhoneNumber()
            val contacts = q.find()
            sendInvites(contacts);

        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(
                    arrayOf(Manifest.permission.READ_CONTACTS),
                    READ_CONTACT_PERMISSION_REQUEST_CODE
                )
            }
        }
    }

    private fun sendInvites(contacts: List<Contact>) {
        for (i in contacts.indices) {
            var no = contacts[i].phoneNumbers[0].normalizedNumber
            finalContacts.add(contacts[i])
        }
        progressBar!!.visibility = View.INVISIBLE
        val contactListAdapter =
            ContactListAdapter(activity?.applicationContext, finalContacts, finalContactsID)
        recyclerView!!.adapter = contactListAdapter
    }

    private fun doSync(contacts: List<Contact>) {
        for (i in contacts.indices) {
            var no = contacts[i].phoneNumbers[0].normalizedNumber
            if (no != null) {
                Log.d("num", no)
                if (!no.startsWith("+88")) {
                    no = "+88$no"
                    Log.d(TAG, "got this far3")
                    if (syncContract(no)) {
                        finalContacts.add(contacts[i])
                        ids[no]?.let { finalContactsID.add(it) }
                    }
                } else {
                    Log.d(TAG, "got this far4")
                    if (syncContract(no)) {
                        finalContacts.add(contacts[i])
                        ids[no]?.let { finalContactsID.add(it) }

                    }
                }
            }
        }
    }

    private fun syncContract(no: String): Boolean {
        return if (nos.containsKey(no)) {
            Log.d("num", "$no is found")
            true
        } else {
            Log.d("num", "$no is not found")
            false
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == READ_CONTACT_PERMISSION_REQUEST_CODE
            && grantResults[0] == PackageManager.PERMISSION_GRANTED
        ) {
            AlgoliaSearch()
        }
    }


    private fun AlgoliaSearch() {
        val query1 =
            Query("+88").setAttributesToRetrieve("phoneNo", "id")
                .setHitsPerPage(359)
        index.searchAsync(query1) { content, error ->
            try {
                val hits = content!!.getJSONArray("hits")
                for (i in 0 until hits.length()) {
                    val jsonObject = hits.getJSONObject(i)
                    val phoneNo = jsonObject.getString("phoneNo")
                    val id = jsonObject.getString("id")
                    ids[phoneNo] = id
                    nos[phoneNo] = phoneNo
                    Log.d(TAG, "No added: " + i + " " + nos[phoneNo])
                }
                Log.d(TAG, "got this far 0")
                val q =
                    Contacts.getQuery()
                q.hasPhoneNumber()
                val contacts = q.find()
                Log.d(TAG, "got this far")
                doSync(contacts)
                Log.d(TAG, "got this far2")
                progressBar!!.visibility = View.INVISIBLE
                val contactListAdapter =
                    ContactListAdapter(activity?.applicationContext, finalContacts, finalContactsID)
                recyclerView!!.adapter = contactListAdapter
                if(finalContacts.size==0){
                    none.visibility = View.VISIBLE
                }

                Log.d(TAG, nos.size.toString())
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
    }

    override fun onPause() {
        finalContacts.clear()
        finalContactsID.clear()
        super.onPause()
    }




}
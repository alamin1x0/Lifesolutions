//package com.stardigiinternational.starnotee.kotlinCode.ui.home.chat
//
//import android.content.Intent
//import android.os.Bundle
//import android.text.Editable
//import android.text.TextUtils
//import android.text.TextWatcher
//import android.view.*
//import android.widget.EditText
//import android.widget.Toast
//import androidx.appcompat.widget.Toolbar
//import androidx.fragment.app.Fragment
//import androidx.lifecycle.ViewModelProvider
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.gmail.samehadar.iosdialog.IOSDialog
//import com.google.android.material.floatingactionbutton.FloatingActionButton
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.database.*
//import com.stardigiinternational.starnotee.Adapters.HorizontalUserListAdapter
//import com.stardigiinternational.starnotee.Helpers.InternetCheck
//import com.stardigiinternational.starnotee.Models.LastConversation
//import com.stardigiinternational.starnotee.Models.User
//import com.stardigiinternational.starnotee.R
//import com.stardigiinternational.starnotee.kotlinCode.data.model.UserKt
//import com.stardigiinternational.starnotee.kotlinCode.ui.auth.LoginKtActivity
//import com.stardigiinternational.starnotee.kotlinCode.utils.toast
//import kotlinx.android.synthetic.main.chat_fragment.*
//import java.util.*
//
//class ChatFragment : Fragment() {
//
//    private lateinit var viewModel: ChatViewModel
//
//    private var list: List<String>? = null
//    private var mUsers: ArrayList<UserKt>? = null
//    private var hList:ArrayList<UserKt>? = null
//    private var mAdapterH: HorizontalUserListAdapter? = null
//    private var mReference: DatabaseReference? = null
//    private var reference:DatabaseReference? = null
//    private var uID: String? = null
//    private var count = 0
//    private var recyclerView: RecyclerView? = null
//    private  var recyclerViewH:RecyclerView? = null
//    private var mLayoutManager: LinearLayoutManager? = null
//    private var dialog1: IOSDialog? = null
//    private var mAuth: FirebaseAuth? = null
//    private var edSearch: EditText? = null
//    private var groupButton: FloatingActionButton? = null
//
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        return inflater.inflate(R.layout.chat_fragment, container, false)
//    }
//
//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProvider(this).get(ChatViewModel::class.java)
//
//
//
//        recyclerView = recyclerView_chat_list_kt
//        recyclerViewH = recyclerView_favorites_chats_kt
//        edSearch = searchView_chat_list_kt
//        groupButton = group_chat_button_chat_list_kt
//
//        mLayoutManager = LinearLayoutManager(requireContext())
//        mLayoutManager!!.reverseLayout = false
//        mLayoutManager!!.stackFromEnd = false
//        recyclerView!!.layoutManager = mLayoutManager
//
//        recyclerViewH!!.layoutManager = LinearLayoutManager(
//            requireContext(),
//            LinearLayoutManager.HORIZONTAL,
//            false
//        )
//        count = 0
//
//        mAuth = FirebaseAuth.getInstance()
//        if (mAuth != null) {
//            uID = mAuth!!.uid
//        } else {
//            Intent(requireContext(), LoginKtActivity::class.java).apply {
//                startActivity(this)
//                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//            }
//        }
//
//        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
//
//        list = ArrayList()
//        hList = ArrayList<UserKt>()
//        mUsers = ArrayList()
//
//        dialog1 = IOSDialog.Builder(requireContext())
//            .setDimAmount(3f)
//            .setSpinnerColorRes(R.color.white)
//            .setMessageColorRes(R.color.white)
//            .setTitleColorRes(R.color.colorPrimary)
//            .setMessageContent("Please Wait")
//            .setCancelable(false)
//            .setMessageContentGravity(Gravity.CENTER)
//            .build()
//
//        if (InternetCheck.checkConnection(requireContext())) {
//            dialog1?.show()
//            mReference = FirebaseDatabase.getInstance().getReference("Users")
//            reference = FirebaseDatabase.getInstance().reference
//            reference!!.child("ChatConversations").child(uID!!)
//                .addValueEventListener(object : ValueEventListener {
//                    override fun onDataChange(dataSnapshot: DataSnapshot) {
//                        if (dataSnapshot.exists()) {
//                            (list as ArrayList<String>).clear()
//                            for (dataSnapshot1 in dataSnapshot.children) {
//                                val lastConversation =
//                                    dataSnapshot1.getValue(
//                                        LastConversation::class.java
//                                    )!!
//                                (list as ArrayList<String>).add(lastConversation.userId)
//                            }
//                            if (list!!.isNotEmpty()) {
//                                Collections.reverse(list)
//                                getUserDetails(list as ArrayList<String>)
//                            } else {
//                                Toast.makeText(
//                                    requireContext(),
//                                    "No Conversations",
//                                    Toast.LENGTH_SHORT
//                                ).show()
//                                dialog1?.dismiss()
//                            }
//                            mAdapter = ChatListAdapter(context, mUsers)
//                            mAdapterH = HorizontalUserListAdapter(context, hList)
//                            recyclerView?.adapter = mAdapter
//                            recyclerViewH?.adapter = mAdapterH
//                            dialog1?.dismiss()
//                        } else {
//                            dialog1?.hide()
//                            tv_no_conversation_msg?.visibility = View.VISIBLE
//                        }
//                    }
//
//                    override fun onCancelled(databaseError: DatabaseError) {}
//                })
//        } else {
//            dialog1?.dismiss()
//            requireContext().toast("No Internet Connection")
//        }
//
//        edSearch!!.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(
//                charSequence: CharSequence,
//                i: Int,
//                i1: Int,
//                i2: Int
//            ) {
//            }
//
//            override fun onTextChanged(
//                charSequence: CharSequence,
//                i: Int,
//                i1: Int,
//                i2: Int
//            ) {
//                if (charSequence.length > 0) {
//                    searchUsers(charSequence.toString())
//                } else {
//                    val adapterClass =
//                        ChatListAdapter(requireContext(), mUsers)
//                    recyclerView!!.adapter = adapterClass
//                }
//            }
//
//            override fun afterTextChanged(editable: Editable) {}
//        })
//
//        groupButton?.setOnClickListener { view: View? ->
//            startActivity(Intent(requireContext(), GroupChatListActivity::class.java))
//        }
//
//
//    }
//
//
//    fun searchUsers(str: String) {
//        val myList =
//            ArrayList<UserKt>()
//        for (obj in mUsers!!) {
//            if (!TextUtils.isEmpty(obj.firstName)) {
//                if (obj.firstName?.toLowerCase()?.contains(str.toLowerCase())!!) {
//                    myList.add(obj)
//                }
//            }
//        }
//        val adapterClass = ChatListAdapter(requireContext(), myList)
//        recyclerView!!.adapter = adapterClass
//    }
//
//
//
//    private fun getUserDetails(usersList: List<String>) {
//        hList?.clear()
//        mUsers?.clear()
//        for (i in usersList.indices) {
//            mReference?.child(list!![i])
//                ?.addListenerForSingleValueEvent(object : ValueEventListener {
//                    override fun onDataChange(dataSnapshot: DataSnapshot) {
//                        val user = dataSnapshot.getValue(UserKt::class.java)!!
//                        mUsers?.add(user)
//                        if (hList!!.size < 11) {
//                            hList?.add(user)
//                        }
//                        mAdapter?.notifyDataSetChanged()
//                        mAdapterH?.notifyDataSetChanged()
//                    }
//
//                    override fun onCancelled(databaseError: DatabaseError) {}
//                })
//        }
//    }
//
//}
package com.lifesolutions.bd.kotlinCode.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.lifesolutions.bd.Helpers.InternetCheck
import com.lifesolutions.bd.kotlinCode.data.model.Conversation
import com.lifesolutions.bd.kotlinCode.data.model.UserKt
import com.lifesolutions.bd.kotlinCode.ui.adapter.ConversationsAdapter
import com.lifesolutions.bd.kotlinCode.ui.home.MainActivity
import com.lifesolutions.bd.kotlinCode.ui.user.UserFriendListActivity
import com.lifesolutions.bd.kotlinCode.utils.toast
import kotlinx.android.synthetic.main.fragment_chat_list.*


class ChatListFragment : Fragment() {
    private val TAG = "ChatListFragment"

    private lateinit var db: FirebaseDatabase
    private lateinit var auth: FirebaseAuth

    private lateinit var conversationsAdapter: ConversationsAdapter
    private lateinit var mLayoutManager: LinearLayoutManager

    private var allConversation = ArrayList<Conversation>()

    // Posts
    private var loadItem = 25
    private var loadCount = 1
    private var isScrolling = false
    private var isLoading = false

    private lateinit var userPathRef: DatabaseReference
    var mReference: DatabaseReference? = null
    private var currentUserFIre: UserKt? = null
    private var uID: String? = null
    private var authId: String? = null
    var c: Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(com.lifesolutions.bd.R.layout.fragment_chat_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        db = FirebaseDatabase.getInstance()
        auth = FirebaseAuth.getInstance()


        userPathRef = db.getReference("Users")



         uID = FirebaseAuth.getInstance().currentUser?.uid

        authId = FirebaseAuth.getInstance().currentUser?.uid
        mReference = userPathRef.child(uID!!)

        // Feed Adapter
        mLayoutManager = LinearLayoutManager(requireContext())
        conversationsAdapter =
            ConversationsAdapter(
                requireContext()
            )

        recycler_view_conversations.apply {
            setHasFixedSize(true)
            layoutManager = mLayoutManager
            adapter = conversationsAdapter
        }

        getConversationLists()
       // getUser()

        recycler_view_conversations.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    val visibleItemCount = mLayoutManager.childCount
                    val totalItemCount = mLayoutManager.itemCount
                    val pastVisibleItems = mLayoutManager.findFirstVisibleItemPosition()

                    val a = allConversation.size * 2
                    val b = loadCount * loadItem
                    if (a < b) {
                        return
                    }

                    if(!isLoading && (visibleItemCount + pastVisibleItems) >= totalItemCount) {
                        loadCount++
                        getConversationLists()
                    }
                }
            }
        })


        swipe_to_refresh_chat_conversation.setOnRefreshListener {
            getConversationLists()
        }

        btn_check_friend_list.setOnClickListener {
            Intent(requireContext(), UserFriendListActivity::class.java).apply {
                startActivity(this)
            }
        }



    }

//    private fun checkInfoList() {
//
//        val epicDialog = context?.let { Dialog(it) }
//        epicDialog!!.setContentView(R.layout.address_dialog)
//        // ID....
//        val btnCancel = epicDialog.findViewById<MaterialButton>(R.id.btn_upload_cancel)
//        val btnUpload = epicDialog.findViewById<MaterialButton>(R.id.btn_upload_confirm)
//        val add =   epicDialog.findViewById<EditText>(R.id.enter_address)
//        val name =  epicDialog.findViewById<EditText>(R.id.enter_name)
//
//        Log.d(TAG, "C in dialog = "+ c)
//
//        if(c == 1){
//            add.visibility = View.GONE
//            name.visibility = View.VISIBLE
//        }
//        if(c ==2){
//            add.visibility = View.VISIBLE
//            name.visibility = View.GONE
//        }
//        if(c==3){
//            //add.visibility = View.GONE
//           // name.visibility = View.GONE
//        }
//
//
//
//        epicDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//        epicDialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
//        epicDialog.setCancelable(false)
//
//
//
//        //Events
//        btnCancel.setOnClickListener {
//            epicDialog.hide()
//        }
//
//
//
//        btnUpload.setOnClickListener {
//            val addre  = add.text.toString()
//            val nam = name.text.toString()
//
//            if(c == 1){
//                if (nam.isNullOrEmpty()){
//                    name.error = "Enter your name"
//                    name.requestFocus()
//                    return@setOnClickListener
//                }
//                userPathRef.child(uID!!).child("searchName")
//                    .setValue(nam)
//
//            }
//            if(c == 3){
//                if (nam.isNullOrEmpty()){
//                    name.error = "Enter your name"
//                    name.requestFocus()
//                    return@setOnClickListener
//                } else if (addre.isNullOrEmpty()){
//                    add.error = "Enter your address"
//                    add.requestFocus()
//                    return@setOnClickListener
//                }
//                userPathRef.child(uID!!).child("searchName")
//                    .setValue(nam)
//                userPathRef.child(uID!!).child("address")
//                    .setValue(addre)
//            }
//            if(c == 2){
//                if (addre.isNullOrEmpty()){
//                    add.error = "Enter your address"
//                    add.requestFocus()
//                    return@setOnClickListener
//                }
//                userPathRef.child(uID!!).child("address")
//                    .setValue(addre)
//            }
//
//            btnUpload.isClickable = false
//            epicDialog.hide()
//
//        }
//        if(c != 0){
//            epicDialog.show()
//        }
//
//
//
//    }
//
//    private fun getUser() {
//        val ref = userPathRef.child(authId!!)
//        ref.addListenerForSingleValueEvent(object: ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                Log.w(TAG, "User Info From database" )
//                if (dataSnapshot.child("address").exists()){
//                    currentUserFIre = dataSnapshot.getValue(UserKt::class.java)
//
//                    if(currentUserFIre!!.address!!.isNullOrEmpty()){
//                        c = 2
//                    }
//
//                    /*if(currentUserFIre!!.address!!.isNullOrEmpty() && currentUserFIre!!.searchName!!.isNullOrEmpty()){
//                        c = 3
//                    }
//                    else if(currentUserFIre!!.address!!.isNullOrEmpty()){
//                        c = 2
//                    }
//                    else if(currentUserFIre!!.searchName!!.isNullOrEmpty()){
//                        c = 1
//                    }
//                   // Toast.makeText(context, "ada "+currentUserFIre!!.searchName, Toast.LENGTH_SHORT).show()
//                    Log.d(TAG, "C = "+ c)*/
//                } else {
//                    /*if (dataSnapshot.child("searchName").exists()){
//                        c = 2
//                    } else {
//                        c = 3
//                    }*/
//
//                    c = 2
//                }
//
//                checkInfoList()
//
//            }
//
//
//            override fun onCancelled(databaseError: DatabaseError) {
//                // Getting Post failed, log a message
//                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
//                // ...
//            }
//        })
//
//
//
//
//
//
//    }


    private fun getConversationLists() {
        if (!InternetCheck.checkConnection(requireContext())) {
            requireContext().toast("No Internet Connection")
            return
        }

        isLoading = true
        swipe_to_refresh_chat_conversation.isEnabled = true
        swipe_to_refresh_chat_conversation.isRefreshing = true

        val currentUserId = auth.currentUser?.uid
        Log.w(TAG, "getConversationLists: ${loadItem * loadCount}" )
        val ref = db.getReference("StarnoteConversation").child(currentUserId!!).orderByChild("lastTimestamps").limitToLast(loadItem * loadCount)
        ref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    val listOfConversation = ArrayList<Conversation>()
                    dataSnapshot.children.forEach {
                        listOfConversation.add(it.getValue(Conversation::class.java)!!)
                    }
                    allConversation = listOfConversation
                    listOfConversation.reverse()


                    // Recycler Adapter Changed..
                    conversationsAdapter.removeAllItem()
                    conversationsAdapter.addAllConversation(listOfConversation)
                    conversationsAdapter.notifyDataSetChanged()

                    isLoading = false

                    // Swipe to refresh
                    swipe_to_refresh_chat_conversation?.isRefreshing = false

                    tv_message_chatlist?.visibility = View.GONE
                    lottie_animation_chat_list?.visibility = View.GONE
                    getUnreadConversationCount()

                    Log.w(TAG, "Size"+ allConversation.size.toString() )
                } else {
                    // Swipe to refresh
                    swipe_to_refresh_chat_conversation?.isRefreshing = false
                    lottie_animation_chat_list?.visibility = View.GONE
                    tv_message_chatlist?.visibility = View.VISIBLE
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                requireContext().toast("Something went wrong while loading conversations")
            }


        })

    }


    private fun getUnreadConversationCount() {
        var unSeenConversation = 0
        if (allConversation.size > 0) {

            allConversation.forEach {
                if (it.seen == false) {
                    unSeenConversation++
                }
            }

            (activity as MainActivity?)?.showMessageNotificationBadge(unSeenConversation)

        }
    }

}
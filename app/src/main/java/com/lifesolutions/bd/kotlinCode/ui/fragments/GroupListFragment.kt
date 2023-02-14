package com.lifesolutions.bd.kotlinCode.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.lifesolutions.bd.Helpers.InternetCheck
import com.lifesolutions.bd.R
import com.lifesolutions.bd.kotlinCode.data.model.GroupConversation
import com.lifesolutions.bd.kotlinCode.ui.activities.CreateChatGroupActivity
import com.lifesolutions.bd.kotlinCode.ui.adapter.GroupConversationListAdapter
import com.lifesolutions.bd.kotlinCode.utils.ViewProgressDialog
import com.lifesolutions.bd.kotlinCode.utils.toast
import kotlinx.android.synthetic.main.fragment_group_list.*


class
GroupListFragment : Fragment() {

    private lateinit var progressDialog: ViewProgressDialog
    private lateinit var db: FirebaseDatabase
    private lateinit var uID: String
    private lateinit var groupChatListAdapter: GroupConversationListAdapter
    private lateinit var mLayoutManager: LinearLayoutManager


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_group_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Init
        progressDialog = ViewProgressDialog(requireContext())
        db = FirebaseDatabase.getInstance()
        uID = FirebaseAuth.getInstance().currentUser?.uid!!

        mLayoutManager = LinearLayoutManager(requireContext())
        groupChatListAdapter = GroupConversationListAdapter(requireContext())

        recycler_view_group_list.apply {
            setHasFixedSize(true)
            layoutManager = mLayoutManager
            adapter = groupChatListAdapter
        }

        getGroupList()

        btn_create_group_chat_list.setOnClickListener {
            Intent(requireContext(), CreateChatGroupActivity::class.java).apply {
                startActivity(this)
            }
        }


    }

    /**
     * Get Group List
     */
    private fun getGroupList() {

        if (!InternetCheck.checkConnection(requireContext())) {
            requireContext().toast("No Internet Connection")
            return
        }

        db.getReference("GroupConversations").orderByChild("lastTimestamps").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val listOfConversation = ArrayList<GroupConversation>()
                for (ds in dataSnapshot.children) {
                    if (ds.child("Members").child(uID).exists()) {
                        val groupChat = ds.getValue(GroupConversation::class.java)!!
                        listOfConversation.add(groupChat)
                    }
                }

                if (listOfConversation.size == 0) {
                    tv_no_join_group?.visibility = View.VISIBLE
                } else {
                    tv_no_join_group?.visibility = View.GONE
                }

                listOfConversation.reverse()

                // Recycler Adapter Changed..
                groupChatListAdapter.removeAllItem()
                groupChatListAdapter.addAllConversation(listOfConversation)
                groupChatListAdapter.notifyDataSetChanged()
                lottie_animation_group_list?.visibility = View.GONE
            }

            override fun onCancelled(databaseError: DatabaseError) {
                lottie_animation_group_list?.visibility = View.GONE
            }
        })
    }



}

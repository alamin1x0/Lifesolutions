package com.lifesolutions.bd.kotlinCode.ui.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.lifesolutions.bd.R
import com.lifesolutions.bd.kotlinCode.data.model.FriendData
import com.lifesolutions.bd.kotlinCode.ui.adapter.FriendListConversationAdapter
import com.lifesolutions.bd.kotlinCode.utils.AnimatedLoading
import kotlinx.android.synthetic.main.activity_search_friend.*

class SearchFriendActivity : AppCompatActivity() {

    private val TAG = "SearchFriendActivityKt";

    private lateinit var friendsAdapter: FriendListConversationAdapter
    private lateinit var mLayoutManager: LinearLayoutManager
    private lateinit var db: FirebaseDatabase
    private  var authId: String? = null

    private lateinit var animatedLoading: AnimatedLoading

    private val allFriends = ArrayList<FriendData>()
    private val displayLists = ArrayList<FriendData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_friend)

        setSupportActionBar(toolbar_search_friends)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        db = FirebaseDatabase.getInstance()
        authId = FirebaseAuth.getInstance().currentUser?.uid

        animatedLoading = AnimatedLoading(this)

        search_view_friends?.onActionViewExpanded()

        // Feed Adapter
        mLayoutManager = LinearLayoutManager(this)
        friendsAdapter = FriendListConversationAdapter(this)

        recyclerView_search_friends.apply {
            setHasFixedSize(true)
            layoutManager = mLayoutManager
            adapter = friendsAdapter
        }

        getFriendList()

        search_view_friends.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText!!.isNotEmpty()) {
                    friendsAdapter.removeAllItem()
                    displayLists.clear()
                    val search = newText.toLowerCase()
                    allFriends.forEach {
                        if (it.firstName?.toLowerCase()!!.contains(search) || it.lastName?.toLowerCase()!!.contains(search)) {
                            displayLists.add(it)
                        }
                    }
                    friendsAdapter.addAllFriends(displayLists, authId!!)
                    friendsAdapter.notifyDataSetChanged()
                    if (displayLists.size <= 0) {
                        text_layout_search_friend.visibility = View.VISIBLE
                        recyclerView_search_friends.visibility = View.GONE
                    }
                } else {
                    displayLists.clear()
                    displayLists.addAll(allFriends)
                    friendsAdapter.notifyDataSetChanged()

                    text_layout_search_friend.visibility = View.GONE
                    recyclerView_search_friends.visibility = View.VISIBLE
                }

                return true
            }

        })

    }

    // On Back Pressed

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }


    private fun getFriendList() {
        animatedLoading.showAnimatedLoading()
         val ref = db.getReference("FriendsList").child(authId!!).limitToLast(500)

        ref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    allFriends.clear()
                    for (usersData in dataSnapshot.children) {
                        val user = usersData.getValue(FriendData::class.java)

                        if (user!!.firstName.isNullOrEmpty() && user.lastName.isNullOrEmpty()) {
                            continue
                        }
                        allFriends.add(user)
                    }

//                    allFriends.forEach {
//                        Log.w(TAG, it.toString())
//                    }
                     displayLists.addAll(allFriends)

                    // Recycler Adapter Changed..
                    text_layout_search_friend.visibility = View.GONE
                    recyclerView_search_friends.visibility = View.VISIBLE

                    friendsAdapter.removeAllItem()
                    friendsAdapter.addAllFriends(displayLists, authId!!)
                    friendsAdapter.notifyDataSetChanged()

                    animatedLoading.hideAnimatedLoading()

                } else {
                    animatedLoading.hideAnimatedLoading()
                    text_layout_search_friend.visibility = View.VISIBLE
                    recyclerView_search_friends.visibility = View.GONE
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }


        })

    }



}
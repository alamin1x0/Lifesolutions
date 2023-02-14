package com.lifesolutions.bd.kotlinCode.ui.home.conversations

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.badge.BadgeDrawable
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.lifesolutions.bd.kotlinCode.data.model.ActiveUser
import com.lifesolutions.bd.kotlinCode.ui.activities.SearchFriendActivity
import com.lifesolutions.bd.kotlinCode.ui.adapter.ChatViewPagerAdapter
import com.lifesolutions.bd.kotlinCode.ui.fragments.CallHistory_Fragment
import com.lifesolutions.bd.kotlinCode.ui.fragments.CallListFragment
import com.lifesolutions.bd.kotlinCode.ui.fragments.ChatListFragment
import com.lifesolutions.bd.kotlinCode.ui.fragments.GroupListFragment
import com.lifesolutions.bd.kotlinCode.ui.home.randomCall.adapter.UserOnlineViewAdapter
import com.lifesolutions.bd.kotlinCode.utils.AnimatedLoading
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.conversations_fragment.*
import kotlinx.android.synthetic.main.random_call_fragment.*

class ConversationsFragment : Fragment() {
    private val TAG = "ConversationsFragment"

    private lateinit var viewModel: ConversationsViewModel

    // Fragments
    private lateinit var chatLisFragment: ChatListFragment
    private lateinit var groupListFragment: GroupListFragment
    private lateinit var callListFragment: CallListFragment
    private lateinit var callHistory_Fragment: CallHistory_Fragment

    private lateinit var badgeDrawable: BadgeDrawable

    //active
    private lateinit var animatedLoading: AnimatedLoading
    private lateinit var activeUserRef: DatabaseReference
    private var listenerActiveList: ValueEventListener? = null
    private lateinit var database: FirebaseDatabase
    private var authId: String? = null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(com.lifesolutions.bd.R.layout.conversations_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ConversationsViewModel::class.java)

        // Initialize..
        chatLisFragment = ChatListFragment()
        groupListFragment = GroupListFragment()
       // callListFragment = CallListFragment()
        callHistory_Fragment = CallHistory_Fragment()

        // Tablayout with View Pagger
        tab_layout_chat.setupWithViewPager(view_pager_chat)


        val chatViewPagerAdapter = ChatViewPagerAdapter(childFragmentManager, 0)
        chatViewPagerAdapter.addFragments(chatLisFragment, "Messages")
        chatViewPagerAdapter.addFragments(groupListFragment, "Groups")
        // chatViewPagerAdapter.addFragments(callListFragment, "Calls")
       // chatViewPagerAdapter.addFragments(callListFragment, "Contacts")
        chatViewPagerAdapter.addFragments(callHistory_Fragment, "Call History")
        view_pager_chat.adapter = chatViewPagerAdapter

       // showCountBadgeOnSingleChat(5, true)

        // DB Initialize...
        animatedLoading = AnimatedLoading(requireContext())
        database = FirebaseDatabase.getInstance()
        authId = FirebaseAuth.getInstance().currentUser?.uid
        activeUserRef = database.getReference("ActiveNow")


        search_button_home_kt.setOnClickListener {
            startActivity(Intent(requireContext(), SearchFriendActivity::class.java))
        }

        getActiveUserLists()

    }

    // Show Bottom Notification Badge..
    fun showCountBadgeOnSingleChat(count: Int, visibility: Boolean) {
        badgeDrawable = tab_layout_chat.getTabAt(0)?.orCreateBadge!!
        badgeDrawable.isVisible = visibility
        badgeDrawable.number = count
    }


    //active list section
    private fun getActiveUserLists() {

        animatedLoading.showAnimatedLoading()
        val refLimit = activeUserRef.orderByKey().limitToFirst(8)
        val refCount = activeUserRef.orderByKey()

        val users = ArrayList<ActiveUser>()

        listenerActiveList = refCount.addValueEventListener(object : ValueEventListener {

            @SuppressLint("SetTextI18n")
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    val activeUserCount = dataSnapshot.childrenCount
                    txt_view_online_count_user?.text = "($activeUserCount)"
                    users.clear()
                    for (usersData in dataSnapshot.children) {

                        if (users.size == 8) {
                            break
                        }
                        val user = usersData.getValue(ActiveUser::class.java)

                        if (user?.firstName.isNullOrEmpty()) {
                            continue
                        }
                        users.add(user!!)
                    }
                    initActiveUser(users)
                    animatedLoading.hideAnimatedLoading()
                } else {
                    animatedLoading.hideAnimatedLoading()
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                animatedLoading.hideAnimatedLoading()
                TODO("Not yet implemented")
            }


        })

//        refCount.addValueEventListener(object: ValueEventListener {
//
//            @SuppressLint("SetTextI18n")
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                if (dataSnapshot.exists()) {
//                    val activeUserCount = dataSnapshot.childrenCount
//                    txt_view_title_with_online_count?.text = "Online ($activeUserCount)"
//                }
//            }
//
//            override fun onCancelled(p0: DatabaseError) {
//                TODO("Not yet implemented")
//            }
//
//        })

    }

    private fun initActiveUser(users: List<ActiveUser>) {
        if (users.isNotEmpty()) {
            if (context != null) {
                // Toast.makeText(requireContext(), "${users.size}", Toast.LENGTH_SHORT).show()
                val activeLayoutManager =
                    LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                val activeViewPool = RecyclerView.RecycledViewPool()

                activeLayoutManager.initialPrefetchItemCount = 4
                rv_active_list?.apply {
                    layoutManager = activeLayoutManager
                    setHasFixedSize(true)
                    adapter = UserOnlineViewAdapter(requireContext(), users)
                    setRecycledViewPool(activeViewPool)
                }
            }
        }


    }


}

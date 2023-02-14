package com.lifesolutions.bd.kotlinCode.ui.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.lifesolutions.bd.Activities.FullPostActivity
import com.lifesolutions.bd.Api.ResType
import com.lifesolutions.bd.Api.RetrofitClient
import com.lifesolutions.bd.Helpers.InternetCheck
import com.lifesolutions.bd.R
import com.lifesolutions.bd.kotlinCode.data.model.PostKt
import com.lifesolutions.bd.kotlinCode.data.model.UserKt
import com.lifesolutions.bd.kotlinCode.ui.home.feed.FeedItemOnClickListener
import com.lifesolutions.bd.kotlinCode.ui.home.feed.adapter.FeedAdapterKt
import com.lifesolutions.bd.kotlinCode.utils.AnimatedLoading
import kotlinx.android.synthetic.main.activity_all_user_post.*
import kotlinx.android.synthetic.main.activity_all_user_post.rv_user_post_profile
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AllUserPostActivity : AppCompatActivity(), FeedItemOnClickListener {

    private val TAG = "AllUserPostActivity"

    // Firebase DB
    private lateinit var database: FirebaseDatabase

    // Shared Pref
    private lateinit var userPreferences: SharedPreferences
    private var authId: String? = null
    private var currentUser: UserKt? = null

    // Feed
    private var feedAdapter: FeedAdapterKt? = null
    private val allPosts = ArrayList<PostKt>()

    private lateinit var animatedLoading: AnimatedLoading

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_user_post)

        // Set Actionbar
        setSupportActionBar(toolbar_all_user_post)
        supportActionBar?.title = "All Your Posts"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Initialize Shared Preferences for User Data
        userPreferences = getSharedPreferences("CurrentUser", Context.MODE_PRIVATE)!!
        animatedLoading = AnimatedLoading(this)

        // DB Initialize...
        database = FirebaseDatabase.getInstance()
        authId = FirebaseAuth.getInstance().currentUser?.uid

        feedAdapter = FeedAdapterKt(this, applicationContext)


        rv_user_post_profile.apply {
            setHasFixedSize(true)
            adapter = feedAdapter
        }

        // Get Initial Posts
        getInitPosts()


    }


    // On Back Pressed
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


    private fun getInitPosts() {

        if (!InternetCheck.checkConnection(this)) {
            return
        }
        animatedLoading.showAnimatedLoading()


        val postRef = database.getReference("UserPosts").child(authId!!).limitToLast(50)


        // val query: Query = database.getReference("Feeds").orderByChild("authorID").equalTo(authId).limitToLast(5)

        postRef.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                    val postItems = ArrayList<PostKt>()
                    for (postSnapshot in dataSnapshot.children) {
                        postItems.add(postSnapshot.getValue(PostKt::class.java)!!)
                    }
                    postItems.reverse()
                    allPosts.addAll(postItems)

                    // Recycler Adapter Changed..
                    feedAdapter?.removeAllItem()
                    feedAdapter?.addAllPosts(postItems)
                    feedAdapter?.notifyDataSetChanged()
                }
                animatedLoading.hideAnimatedLoading()

            }

            override fun onCancelled(p0: DatabaseError) {
                animatedLoading.hideAnimatedLoading()
            }

        })
    }

    override fun onRecyclerViewItemClicked(view: View, postItem: PostKt) {
        when (view.id) {

            // Comment Button Event
            R.id.layout_btn_comment_with_image,
            R.id.layout_btn_comment_without_image,
            R.id.layout_btn_comment_with_link,
            R.id.layout_btn_comment_with_big_text -> {
                Intent(this, FullPostActivity::class.java).apply {
                    this.putExtra("Id", postItem.postID)
                    startActivity(this)
                }
            }
            // Share Button Event..
            R.id.share_layout_with_image,
            R.id.share_layout_without_image,
            R.id.share_layout_with_link,
            R.id.share_layout_with_big_text -> {
                try {
                    val i = Intent(Intent.ACTION_SEND)
                    i.type = "text/plain"
                    i.putExtra(
                        Intent.EXTRA_SUBJECT,
                        resources.getString(R.string.app_name)
                    )
                    val shareHint = "https://starnotesocial.com/shared_post/${postItem.postID}"

                    i.putExtra(Intent.EXTRA_TEXT, shareHint)
                    startActivity(Intent.createChooser(i, "Choose one"))
                } catch (e: Exception) {
                    Toast.makeText(this, "" + e.message, Toast.LENGTH_SHORT).show()
                }
            }

            R.id.layout_post_link_img_item -> {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(postItem.linkRaw)))
            }

            R.id.btn_more_on_feed_link,
            R.id.btn_more_on_feed_color_text,
            R.id.btn_more_on_feed_big_text,
            R.id.btn_more_on_feed_image -> {
                showMenu(view, postItem)
            }

        }
    }


    /**
     * Remove Post
     * Delete from Server
     * Image Remove
     * Refresh
     */

    private fun showMenu(v: View, feed: PostKt) {
        val popup = PopupMenu(this, v)
        popup.inflate(R.menu.menu_feed)
        val mAuthInfo = popup.menu.findItem(R.id.item_info)

        mAuthInfo.isVisible = false

        popup.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.item_menu_delete -> {
                    deleteUserPost(feed)
                }
            }
            false
        }
        popup.show()
    }


    private fun deleteUserPost(feed: PostKt) {
        val databaseReference = FirebaseDatabase.getInstance().reference

        databaseReference.child("Feeds").child(feed.postID!!).removeValue().addOnSuccessListener {
            if (feed.postImage != "none") {
                removeServerImage(feed.postImage!!)
            }
            refresh()
            Snackbar.make(layout_all_post_view, "Post Deleted Successfully", Snackbar.LENGTH_LONG)
                .show()
            database.getReference("UserPosts").child(authId!!).child(feed.postID).removeValue()
        }
    }


    private fun removeServerImage(imageLink: String) {

        val filUrl = ResType(imageLink)
        // Retrofit API Call..
        val call = RetrofitClient
            .getInstance()
            .serverApi
            .removeImage(filUrl)

        call.enqueue(object : Callback<ResType?> {
            override fun onFailure(call: Call<ResType?>, t: Throwable) {
                // TODO
            }

            override fun onResponse(call: Call<ResType?>, response: Response<ResType?>) {
                if (response.isSuccessful) {
                    val serverResponse: ResType = response.body()!!
                    Log.e(TAG, "$serverResponse")
                    // requireContext().toast(serverResponse.message)
                } else {
                    Log.e(TAG, "onResponse: Error")
                }
            }
        })

    }

    private fun refresh() {
        feedAdapter!!.removeAllItem()
        feedAdapter!!.notifyDataSetChanged()
        getInitPosts()
    }


}
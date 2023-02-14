package com.lifesolutions.bd.kotlinCode.ui.home.menu

import android.content.ContentValues.TAG
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.facebook.login.LoginManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import com.lifesolutions.bd.Activities.*
import com.lifesolutions.bd.R
import com.lifesolutions.bd.kotlinCode.data.model.UserKt
import com.lifesolutions.bd.kotlinCode.ui.activities.FriendRequestListActivity
import com.lifesolutions.bd.kotlinCode.ui.activities.OwnProfileActivity
import com.lifesolutions.bd.kotlinCode.ui.auth.LoginKtActivity
import com.lifesolutions.bd.kotlinCode.ui.user.EditProfileInfoKtActivity
import com.lifesolutions.bd.kotlinCode.utils.toast
import kotlinx.android.synthetic.main.menu_fragment.*

class MenuFragment : Fragment(), View.OnClickListener {


    private lateinit var viewModel: MenuViewModel

    // Firebase DB
    private lateinit var database: FirebaseDatabase
    private var authId: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        return inflater.inflate(R.layout.menu_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MenuViewModel::class.java)

        // DB Initialize...
        database = FirebaseDatabase.getInstance()
        authId = FirebaseAuth.getInstance().currentUser?.uid
        getUserInfo()

        // Click Event..
        menu_logout.setOnClickListener(this)
        //menu_classroom.setOnClickListener(this)
        menu_group.setOnClickListener(this)
        menu_leaderboard.setOnClickListener(this)
        menu_wallet.setOnClickListener(this)
        menu_friend_request.setOnClickListener(this)
        //  menu_translate.setOnClickListener(this)
        menu_stay_connect.setOnClickListener(this)
        menu_news.setOnClickListener(this)
        //  menu_ecommarce.setOnClickListener(this)
        menu_games.setOnClickListener(this)
        menu_teacher.setOnClickListener(this)
        menu_brand_ambassador.setOnClickListener(this)
        menu_brand_campus_ambassador.setOnClickListener(this)
        menu_feedback.setOnClickListener(this)
        menu_about_us.setOnClickListener(this)
        menu_rate_us.setOnClickListener(this)
        menu_friends.setOnClickListener(this)
        menu_share.setOnClickListener(this)
        //   menu_mini_app.setOnClickListener(this)
        profile_image_user_menu_kt.setOnClickListener(this)


    }


    // All the Click Listener...
    override fun onClick(v: View?) {
        val msg = "This feature is not supported on your device. its available soon for your device"

        when (v?.id) {
            R.id.menu_logout -> {
                onLogoutAlertDialog()
            }

//            R.id.menu_classroom -> {
//                if (Build.VERSION.SDK_INT > 21) {
//                    startActivity(Intent(requireContext(), QuizActivity::class.java))
//                } else {
//                    requireContext().toast(msg)
//                }
//            }

            R.id.menu_group -> {
                view?.findNavController()
                    ?.navigate(R.id.action_menuFragment_to_conversationsFragment)
            }
            R.id.profile_image_user_menu_kt -> {
                startActivity(Intent(requireContext(), OwnProfileActivity::class.java))
            }


            R.id.menu_leaderboard -> {
                val preference =
                    activity?.getSharedPreferences("com.user.permission", Context.MODE_PRIVATE)
                val hasFirstName = preference?.getBoolean("hasFirstName", false)

                if (hasFirstName!!) {
                    startActivity(Intent(requireContext(), LeaderBoardActivity::class.java))
                } else {
                      openProfileCompleteAlertDialog()
                    startActivity(Intent(requireContext(), LeaderBoardActivity::class.java))

                }
            }

            R.id.menu_wallet -> {
                startActivity(Intent(requireContext(), ReferActivity::class.java))
            }

            R.id.menu_stay_connect -> {
               // startActivity(Intent(requireContext(), StayConnectActivity::class.java))
            }

            R.id.menu_friends -> {
                startActivity(Intent(requireContext(), FindFriendsActivity::class.java))
            }

            R.id.menu_friend_request -> {
                startActivity(Intent(requireContext(), FriendRequestListActivity::class.java))
            }

//            R.id.menu_translate -> {
//                if (Build.VERSION.SDK_INT > 21) {
//                    startActivity(Intent(requireContext(), TranslateActivity::class.java))
//                } else {
//                    requireContext().toast(msg)
//                }
//            }

            R.id.menu_news -> {
                if (Build.VERSION.SDK_INT > 21) {
                  // startActivity(Intent(requireContext(), NewsActivity::class.java))
                } else {
                    requireContext().toast(msg)
                }
            }

//            R.id.menu_ecommarce -> {
//                if (Build.VERSION.SDK_INT > 21) {
//                    startActivity(Intent(requireContext(), EcommerceActivity::class.java))
//                } else {
//                    requireContext().toast(msg)
//                }
//            }

            R.id.menu_games -> {
                if (Build.VERSION.SDK_INT > 24) {
                    startActivity(Intent(requireContext(), GameActivity::class.java))
                } else {
                    requireContext().toast(msg)
                }
            }

            R.id.menu_teacher -> {
                startActivity(Intent(requireContext(), ApplyForTeacherActivity::class.java))
            }

            R.id.menu_brand_ambassador -> {
                startActivity(Intent(requireContext(), AmbassadorActivity::class.java))
            }


            R.id.menu_brand_campus_ambassador -> {
                startActivity(Intent(requireContext(), Campuss_AmbassadorActivity::class.java))
            }



            R.id.menu_feedback -> {
                try {
                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("mailto:" + "stardigiinternation@gmail.com")
                    )
                    intent.putExtra(
                        Intent.EXTRA_SUBJECT,
                        resources.getString(R.string.app_name)
                    )
                    intent.putExtra(Intent.EXTRA_TEXT, "Your Feedback : ")
                    startActivity(intent)
                } catch (e: Exception) {
                    Toast.makeText(
                        requireContext(),
                        "Sorry. You don't have any mail app",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            R.id.menu_about_us -> {
                startActivity(Intent(requireContext(), AboutUsActivity::class.java))
            }





            R.id.menu_rate_us -> {
                try {
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("market://details?id=" + activity?.packageName)
                        )
                    );
                } catch (e: java.lang.Exception) {
                    //Exception
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://play.google.com/store/apps/details?id=" + activity?.packageName)
                        )
                    )
                }

            }
            R.id.menu_share -> {

                val sharingIntent = Intent(Intent.ACTION_SEND)
                sharingIntent.type = "text/plain"
                val shareBody = "https://play.google.com/store/apps/details?id=" + activity?.packageName
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Share \"Starnote Social\"")
                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody)
                startActivity(Intent.createChooser(sharingIntent, "Share via"))


            }

//            R.id.menu_mini_app -> {
//                Intent(requireContext(), MiniAppListActivity::class.java).apply {
//                    startActivity(this)
//                }
//            }


        }
    }


    /**
     * On Logout
     */

    private fun onLogoutAlertDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Logout")
        builder.setCancelable(false)
        builder.setMessage("Are you sure to logout ?")
        builder.setPositiveButton(
            "Yes"
        ) { dialog: DialogInterface?, which: Int ->
            // Clear Shared Preferences
            val sharedPreferences: SharedPreferences? =
                activity?.getSharedPreferences("UserInfo", Context.MODE_PRIVATE)
            val sharedUserPref: SharedPreferences? =
                activity?.getSharedPreferences("CurrentUser", Context.MODE_PRIVATE)

            val editor = sharedPreferences?.edit()
            val editorUserPref = sharedUserPref?.edit()
            editor?.clear()
            editor?.apply()
            editorUserPref?.clear()
            editorUserPref?.apply()

            // Remove Status
            removeUserStatus(authId!!)

            FirebaseAuth.getInstance().signOut()
            LoginManager.getInstance().logOut()

            val intent = Intent(requireContext(), LoginKtActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            activity?.finish()
        }.setNegativeButton(
            "No"
        ) { dialog: DialogInterface, which: Int -> dialog.dismiss() }
        builder.show()
    }


    private fun openProfileCompleteAlertDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Your Profile is incomplete!")
            .setMessage("Please add required profile information")
            .setNeutralButton("Cancel") { dialog, which ->
                // Respond to neutral button press
            }
            .setNegativeButton("Update Profile") { dialog, which ->

                Intent(requireContext(), EditProfileInfoKtActivity::class.java).apply {
                    startActivity(this)
                }
            }
            .show()
    }


    /**
     * Get User Info
     */
    private fun getUserInfo() {
        val dbUserRef = database.reference.child("Users")

        dbUserRef.child(authId!!).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // isReceived = true

                if (dataSnapshot.exists()) {
                    val currentUser = dataSnapshot.getValue(UserKt::class.java)


                    // Set User Image Thumbnail
                    if (currentUser?.imageThumbnail != null && currentUser.imageThumbnail != "none") {

                        if (profile_image_user_menu_kt != null) {
                            Picasso.get().load(currentUser.imageThumbnail)
                                .into(profile_image_user_menu_kt)
                        }
                    }
                }

            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.d(TAG, "Something went wrong.Please try again later")
            }
        })
    }

    private fun removeUserStatus(authId: String) {
        val dbUserRef = database.getReference("ActiveNow").child(authId)
        val tokenReference = database.getReference("CloudTokens").child(authId)
        val dbUserStatusRef =
            database.getReference("Users").child(authId).child("userActivity").child("online")
        dbUserRef.removeValue()
        tokenReference.removeValue()
        dbUserStatusRef.setValue(false)
    }


}
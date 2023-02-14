package com.lifesolutions.bd.kotlinCode.ui.home

import android.Manifest
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.ViewGroup
import android.view.Window
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide

import com.facebook.login.LoginManager
import com.google.android.gms.ads.MobileAds
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.iid.FirebaseInstanceId
import com.sinch.android.rtc.PushTokenRegistrationCallback
import com.sinch.android.rtc.SinchError
import com.lifesolutions.bd.Activities.BaseActivity
import com.lifesolutions.bd.Notifications.Token
import com.lifesolutions.bd.Services.SinchService
import com.lifesolutions.bd.Services.SinchService.StartFailedListener
import com.lifesolutions.bd.kotlinCode.data.model.ActiveUser
import com.lifesolutions.bd.kotlinCode.data.model.FriendData
import com.lifesolutions.bd.kotlinCode.data.model.UserKt
import com.lifesolutions.bd.kotlinCode.ui.auth.LoginKtActivity
import com.lifesolutions.bd.kotlinCode.utils.AnimatedLoading
import com.lifesolutions.bd.kotlinCode.utils.UserData.currentUserID
import com.lifesolutions.bd.kotlinCode.utils.UserData.friendList
import com.lifesolutions.bd.kotlinCode.utils.Utils
import com.lifesolutions.bd.kotlinCode.utils.toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.util.*


class MainActivity : BaseActivity(), StartFailedListener, PushTokenRegistrationCallback {

    private val TAG = "MainActivity"

    // private var doubleBackToExitPressedOnce = false
    private lateinit var animatedLoading: AnimatedLoading
    private var authId: String? = null

    // Firebase DB
    private lateinit var database: FirebaseDatabase

    // Shared Preferences
    private lateinit var userPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    private var uID: String? = null
    private var currentUser: UserKt? = null
    private var currentUserFIre: UserKt? = null
    private var mPushTokenIsRegistered = false

    private val PERMISSION_ALL = 1

    private val PERMISSIONS = arrayOf(
        Manifest.permission.READ_PHONE_STATE,
        Manifest.permission.RECORD_AUDIO,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.CAMERA
    )

    // Realtime Listener
    private lateinit var userPathRef: DatabaseReference
    private var listenerUserPermission: ValueEventListener? = null

    var mReference: DatabaseReference? = null

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.lifesolutions.bd.R.layout.activity_main)


        //======================ADMOB INIT============================

        MobileAds.initialize(this) {}
        //Test Ads
       // MediationTestSuite.launch(this@MainActivity)a
        //======================ADMOB INIT============================


        //=================Facebook ads===========================

        // Example for setting the SDK to crash when in debug mode
/*
        AdSettings.IntegrationErrorMode.INTEGRATION_ERROR_CRASH_DEBUG_MODE
        AudienceNetworkAds.initialize(this)
*/



        //=================Facebook ads===========================

        // Status Bar Color for SDK 21 & 22..
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            window.statusBarColor =
                ContextCompat.getColor(applicationContext, com.lifesolutions.bd.R.color.colorPrimaryDark)
        }

        // Initialize
        animatedLoading = AnimatedLoading(this)
        authId = FirebaseAuth.getInstance().currentUser?.uid
        database = FirebaseDatabase.getInstance()
        userPreferences = getSharedPreferences("com.starnote.CurrentAuthUser", Context.MODE_PRIVATE)
        userPathRef = database.getReference("Users")



        uID = FirebaseAuth.getInstance().currentUser?.uid

        mReference = userPathRef.child(uID!!)
        // Static Essential Methods
        getUserInfo()
        checkUserBlock()
        checkUpdateVersion()

        getOverlayPermission()

        // Navigation Controller.
        val navHostFragment =
            supportFragmentManager.findFragmentById(com.lifesolutions.bd.R.id.fragment_main) as NavHostFragment
        // val navController = Navigation.findNavController(this, com.lifesolutions.bd.R.id.fragment_main)
        val navController = navHostFragment.navController
        // NavigationUI.setupWithNavController(bottom_nav, navController)
        findViewById<BottomNavigationView>(com.lifesolutions.bd.R.id.bottom_nav).setupWithNavController(navController)

        if (!Utils.hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL)
        }


        // showMessageNotificationBadge(100,true)

        getFriendLists()

        // checkInfoList()

    }

    private fun checkInfoList() {
        getUser()
        var c: Int = 0
        val epicDialog = Dialog(this@MainActivity)
        epicDialog.setContentView(com.lifesolutions.bd.R.layout.address_dialog)
        // ID....
        val btnCancel = epicDialog.findViewById<Button>(com.lifesolutions.bd.R.id.btn_upload_cancel)
        val btnUpload = epicDialog.findViewById<Button>(com.lifesolutions.bd.R.id.btn_upload_confirm)
        val add = epicDialog.findViewById<EditText>(com.lifesolutions.bd.R.id.enter_address)
        val name = epicDialog.findViewById<EditText>(com.lifesolutions.bd.R.id.enter_name)

        //  val pDesc = epicDialog.findViewById<EditText>(com.lifesolutions.bd.R.id.et_post_desc)

//        if(currentUser!!.searchName.equals(null) || currentUser!!.address.equals(null)){
//            if(currentUser!!.searchName.isNullOrBlank()){
//                add.visibility = View.GONE
//                c = 1
//            }
//            else if(currentUser!!.searchName.equals(null) || currentUser!!.address.equals(null)){
//                c = 2
//            }
//            else if(currentUser!!.address.equals(null)){
//                name.visibility = View.GONE
//                c = 3
//            }
//        }

        epicDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        epicDialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        epicDialog.setCancelable(false)


        //Events
        btnCancel.setOnClickListener {
            epicDialog.hide()
        }



        btnUpload.setOnClickListener {
            val addre = add.text.toString()
            val nam = name.text.toString()

            if (c == 1) {
                userPathRef.child(uID!!).child("searchName")
                    .setValue(nam)

            }
            if (c == 2) {
                userPathRef.child(uID!!).child("searchName")
                    .setValue(nam)
                userPathRef.child(uID!!).child("address")
                    .setValue(addre)
            }
            if (c == 3) {
                userPathRef.child(uID!!).child("address")
                    .setValue(addre)
            }

            btnUpload.isClickable = false
            epicDialog.hide()

        }
        epicDialog.show()
    }

    private fun getUser() {
        mReference!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                currentUserFIre!!.searchName = dataSnapshot.child("searchName")
                    .getValue(String::class.java)
                currentUserFIre!!.address = dataSnapshot.child("address")
                    .getValue(String::class.java)
                Toast.makeText(
                    this@MainActivity,
                    "Something went wrong.Please try again later",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
    }

//    override fun onStart() {
//        super.onStart()
////        userStateChange(true)
//    }

    override fun onDestroy() {
        super.onDestroy()
        // toast("onDestroy() Main Activity")
        if (listenerUserPermission != null) {
            userPathRef.removeEventListener(listenerUserPermission!!)
        }
    }

    override fun onPause() {
        super.onPause()
        // toast("onPause() Main Activity")
    }

    private fun getOverlayPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (isMiUi()) {
                goToXiaomiPermissions(this)
            } else {
                overlayPermission()
            }
        }
    }


    // Show Bottom Notification Badge..
    fun showNotificationBadge(count: Int, visibility: Boolean) {
        bottom_nav.getOrCreateBadge(com.lifesolutions.bd.R.id.notificationFragment).apply {
            backgroundColor = ContextCompat.getColor(applicationContext, com.lifesolutions.bd.R.color.colorMain)
            number = count
            isVisible = visibility
        }
    }

    // Show Bottom Notification Badge..
    fun showMessageNotificationBadge(count: Int) {
        if (count != 0) {
            bottom_nav.getOrCreateBadge(com.lifesolutions.bd.R.id.conversationsFragment).apply {
                isVisible = true
                number = count
                backgroundColor = ContextCompat.getColor(applicationContext, com.lifesolutions.bd.R.color.colorMain)
            }
        } else {
            bottom_nav.getOrCreateBadge(com.lifesolutions.bd.R.id.conversationsFragment).apply {
                isVisible = false
            }
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        if (requestCode == PERMISSION_ALL) {
            if (grantResults.isNotEmpty()) {
                val callRequestAccepted =
                    grantResults[0] == PackageManager.PERMISSION_GRANTED
                val recordRequestAccepted =
                    grantResults[1] == PackageManager.PERMISSION_GRANTED
                if (callRequestAccepted && recordRequestAccepted) {
                    toast("Permission Granted")
                    //  startActivity(Intent(this, RandomCallActivity::class.java))
                } else {
                    toast("Phone call and Record audio permissions are required")
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }


    override fun onStartFailed(error: SinchError) {
        Toast.makeText(this, "" + error.message, Toast.LENGTH_LONG).show()
    }

    override fun onStarted() {}

    override fun onServiceConnected() {

        getSinchServiceInterface().setStartListener(this)

        //Register user
        if (getSinchServiceInterface() != null && !getSinchServiceInterface().isStarted) {
            getSinchServiceInterface().startClient(uID)
        }
        FirebaseApp.initializeApp(this)
        applicationContext.bindService(
            Intent(this, SinchService::class.java), this,
            Context.BIND_AUTO_CREATE
        )
        if (!mPushTokenIsRegistered) {
            getSinchServiceInterface().getManagedPush(uID).registerPushToken(this)
        }

        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(Intent(this, TempService::class.java))
        } else startService(Intent(this, TempService::class.java))*/
    }

    override fun tokenRegistered() {
        mPushTokenIsRegistered = true
    }

    override fun tokenRegistrationFailed(sinchError: SinchError?) {
        mPushTokenIsRegistered = false
        Toast.makeText(
            this,
            "Push token registration failed - incoming calls can't be received!",
            Toast.LENGTH_LONG
        ).show()
    }


    /**
     * User Online Offline
     */
    private fun userActivity(uID: String, firstName: String?, imageThumbnail: String?) {

        val globalActiveConnectionsRef = database.getReference("ActiveNow/${uID}")

        val activeInfo = ActiveUser(uID, firstName, imageThumbnail, true)

        val myConnectionsRef = database.getReference("Users/${uID}/userActivity/online")

        // Stores the timestamp of my last disconnect (the last time I was seen online)
        val lastOnlineRef = database.getReference("/Users/${uID}/userActivity/lastOnline")

        val connectedRef = database.getReference(".info/connected")

        connectedRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val connected = snapshot.getValue(Boolean::class.java) ?: false
                if (connected) {
                    // val con = myConnectionsRef.push()
                    myConnectionsRef.setValue(true)
                    globalActiveConnectionsRef.setValue(activeInfo)

                    // Update Token
                    updateToken(FirebaseInstanceId.getInstance().token!!)

                    // When this device disconnects, remove it
                    // con.onDisconnect().removeValue()
                    myConnectionsRef.onDisconnect().setValue(false)

                    // When I disconnect, update the last time I was seen online
                    lastOnlineRef.onDisconnect().setValue(ServerValue.TIMESTAMP)

                    // Add this device to my connections list
                    // this value could contain info about the device or a timestamp too
                    // con.setValue(java.lang.Boolean.TRUE)
                }
                globalActiveConnectionsRef.onDisconnect().removeValue()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Listener was cancelled at .info/connected")
            }
        })
    }

    /**
     * Token For Cloud Messaging
     */

    private fun updateToken(token: String) {
        val databaseReference = FirebaseDatabase.getInstance().getReference("CloudTokens")
        val tokenInfo = Token(token)
        databaseReference.child(uID!!).setValue(tokenInfo)
    }


    fun isMiUi(): Boolean {
        return getSystemProperty("ro.miui.ui.version.name")?.isNotBlank() == true
    }

    fun isMiuiWithApi28OrMore(): Boolean {
        return isMiUi() && Build.VERSION.SDK_INT >= 28
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun goToXiaomiPermissions(context: Context) {
        if (!Settings.canDrawOverlays(this)) {
            val getPermissionCount = userPreferences.getBoolean("showPermissionDialog", false)
            if (getPermissionCount) {
                showPermissionGifDialogMi(context)
            } else {
                setPermissionCount()
                goMiOverlayActivity(context)
            }
        }
    }

    private fun getSystemProperty(propName: String): String? {
        val line: String
        var input: BufferedReader? = null
        try {
            val p = Runtime.getRuntime().exec("getprop $propName")
            input = BufferedReader(InputStreamReader(p.inputStream), 1024)
            line = input.readLine()
            input.close()
        } catch (ex: IOException) {
            return null
        } finally {
            if (input != null) {
                try {
                    input.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
        return line
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun overlayPermission() {
        if (!Settings.canDrawOverlays(this)) {
            val getPermissionCount = userPreferences.getBoolean("showPermissionDialog", false)
            if (getPermissionCount) {
                showPermissionGifDialog()
            } else {
                toast("Please Check Additional Permission For Voice & Video Call")
                setPermissionCount()
                goNormalOverlayActivity()
            }
        }
    }


    @RequiresApi(Build.VERSION_CODES.M)
    fun showPermissionGifDialogMi(context: Context) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(com.lifesolutions.bd.R.layout.xi_alert_box)

        val gifImageView = dialog.findViewById<ImageView>(com.lifesolutions.bd.R.id.imageView5)
        val text = dialog.findViewById<TextView>(com.lifesolutions.bd.R.id.textView2)
        val but = dialog.findViewById<Button>(com.lifesolutions.bd.R.id.button2)
        but.setOnClickListener {
            dialog.dismiss()
            goMiOverlayActivity(context)
        }
        text.text = getString(com.lifesolutions.bd.R.string.please_allow_all_permission)
        Glide.with(this)
            .load(com.lifesolutions.bd.R.drawable.gif_permission)
            .placeholder(com.lifesolutions.bd.R.drawable.loading_icon)
            .centerCrop()
            .into(gifImageView)

        dialog.show()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun showPermissionGifDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(com.lifesolutions.bd.R.layout.xi_alert_box)

        val gifImageView = dialog.findViewById<ImageView>(com.lifesolutions.bd.R.id.imageView5)
        val text = dialog.findViewById<TextView>(com.lifesolutions.bd.R.id.textView2)
        val but = dialog.findViewById<Button>(com.lifesolutions.bd.R.id.button2)
        but.setOnClickListener {
            dialog.dismiss()
            goNormalOverlayActivity()
        }
        text.text = getString(com.lifesolutions.bd.R.string.please_allow_overlay_permission)
        Glide.with(this)
            .load(com.lifesolutions.bd.R.drawable.overlay_permission_other)
            .placeholder(com.lifesolutions.bd.R.drawable.loading_icon)
            .centerCrop()
            .into(gifImageView)

        dialog.show()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun goNormalOverlayActivity() {
        val intent = Intent(
            Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
            Uri.parse("package:$packageName")
        )
        startActivityForResult(intent, 0)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun goMiOverlayActivity(context: Context) {
        toast("Please Check Additional Permission For Voice & Video Call")
        val intent = Intent("miui.intent.action.APP_PERM_EDITOR")
        intent.setClassName(
            "com.miui.securitycenter",
            "com.miui.permcenter.permissions.PermissionsEditorActivity"
        )
        intent.putExtra("extra_pkgname", context.packageName)
        context.startActivity(intent)
    }


    /**
     * Get Current User Data
     * Check User Block
     * Save User Data to Shared Pref
     */

    private fun getUserInfo() {
        // animatedLoading.showAnimatedLoading()
        val uID = userPreferences.getString("uID", null)
        val firstName = userPreferences.getString("firstName", "")
        val lastName = userPreferences.getString("lastName", "")
        val imageThumbnail = userPreferences.getString("imageThumbnail", null)
        val referCode = userPreferences.getString("referCode", null)
        val hasReferCollection = userPreferences.getBoolean("hasReferCollection", false)
        val hasSearchName = userPreferences.getBoolean("hasSearchName", false)

        if (uID != null) {
            Log.w(TAG, "User Info From Shared Pref $uID -> $firstName")
            userActivity(uID, firstName, imageThumbnail)
            setUserReferCode(uID, referCode!!)
//            if (!hasReferCollection) {
//                setUserReferCode(uID, referCode!!)
//            }
            if (!hasSearchName) {
                setUserSearchName(uID, firstName, lastName)
            }
        } else {
            val ref = userPathRef.child(authId!!)

            ref.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    Log.w(TAG, "User Info From database")
                    if (dataSnapshot.exists()) {
                        currentUser = dataSnapshot.getValue(UserKt::class.java)
                        // Set Important Value...
                        userActivity(
                            currentUser?.uID!!,
                            currentUser?.firstName,
                            currentUser?.imageThumbnail
                        )
                        setUserReferCode(currentUser?.uID!!, currentUser?.referCode!!)
                        setUserSearchName(
                            currentUser?.uID!!,
                            currentUser?.firstName,
                            currentUser?.lastName
                        )
                        // Save Locally...
                        currentUser?.let { saveUserIdToSharedPref(it) }
                        // animatedLoading.hideAnimatedLoading()
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Getting Post failed, log a message
                    Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
                    // ...
                }
            })
        }

    }

    private fun checkUserBlock() {
        listenerUserPermission = userPathRef.child(authId!!).child("permission")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // toast("Check User Block dataSnapshot")
                    if (dataSnapshot.exists()) {
                        val permission = dataSnapshot.getValue(Boolean::class.java)
                        if (permission == false) {
                            FirebaseAuth.getInstance().signOut()
                            LoginManager.getInstance().logOut()
                            toast("You are temporary blocked for unusual activity")

                            Intent(this@MainActivity, LoginKtActivity::class.java).apply {
                                flags =
                                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                startActivity(this)
                                finish()
                            }
                            return
                        }
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Getting Post failed, log a message
                    Log.w(TAG, "databaseError on Main Activity Check user block method")
                }
            })

    }

    /**
     * Save to Shared Preference
     * Set User Refer Code
     */

    private fun saveUserIdToSharedPref(user: UserKt) {
        editor = userPreferences.edit()
        editor.putString("uID", user.uID)
        editor.putString("firstName", user.firstName)
        editor.putString("lastName", user.lastName)
        editor.putString("referCode", user.referCode)
        editor.putString("imageThumbnail", user.imageThumbnail)
        editor.apply()
    }

    private fun setUserReferCode(uid: String, referCode: String) {
        Log.w(TAG, "setUserReferCode()")
        database.getReference("ReferArea").child(referCode)
            .setValue(uid).addOnSuccessListener {
                editor = userPreferences.edit()
                editor.putBoolean("hasReferCollection", true)
                editor.apply()
            }
    }

    private fun setUserSearchName(uid: String, firstName: String?, lastName: String?) {
        Log.w(TAG, "setUserSearchName()")
        val searchName =
            "${firstName?.toLowerCase(Locale.ROOT)} ${lastName?.toLowerCase(Locale.ROOT)}"

        userPathRef.child(uid).child("searchName")
            .setValue(searchName).addOnSuccessListener {
                editor = userPreferences.edit()
                editor.putBoolean("hasSearchName", true)
                editor.apply()
            }
    }

    private fun setPermissionCount() {
        editor = userPreferences.edit()
        editor.putBoolean("showPermissionDialog", true)
        editor.apply()
    }


    private fun checkUpdateVersion() {
        Log.w(TAG, "checkUpdateVersion()")
        // val updateReference = database.getReference("Version")
        val updateReference = database.getReference("versionControl")

        updateReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    // val versionNameDB = dataSnapshot.getValue(String::class.java)!!
                    val forceType = dataSnapshot.child("needForce").getValue(Boolean::class.java)!!
                    val versionNameDB = dataSnapshot.child("version").getValue(String::class.java)!!

                    try {
                        val versionName: String = BuildConfig.VERSION_NAME
                        val versionCode = BuildConfig.VERSION_CODE


                        val pInfo: PackageInfo = this@MainActivity.getPackageManager()
                            .getPackageInfo(this@MainActivity.getPackageName(), 0)
                        val version = pInfo.versionName
                        Log.d(TAG, "version: " + "version  " + version)




                        Log.d(
                            TAG,
                            "onDataChange: " + "Version Name   " + versionName + "Version db   " + versionNameDB + "CODE" + versionCode + "version "
                        )

                        if (versionNameDB != version) {
                            val dialog = Dialog(this@MainActivity)
                            dialog.setContentView(com.lifesolutions.bd.R.layout.dialog_bonus_layout)
                            dialog.setCancelable(!forceType)
                            dialog.window?.attributes?.windowAnimations = com.lifesolutions.bd.R.style.animation_dialog
                            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                            val title = dialog.findViewById<TextView>(com.lifesolutions.bd.R.id.title_dialog_bonus)
                            val details =
                                dialog.findViewById<TextView>(com.lifesolutions.bd.R.id.description_dialog_bonus)
                            val collect = dialog.findViewById<TextView>(com.lifesolutions.bd.R.id.refresh_dialog_bonus)
                            val icon = dialog.findViewById<ImageView>(com.lifesolutions.bd.R.id.image_dialog_bonus)

                            title.text = getString(com.lifesolutions.bd.R.string.new_update_available)
                            details.text = getString(com.lifesolutions.bd.R.string.please_update_app_first)
                            collect.text = getString(com.lifesolutions.bd.R.string.update_now)


                            icon.setImageResource(com.lifesolutions.bd.R.drawable.app_icon)

                            collect.setOnClickListener {
                                try {
                                    startActivity(
                                        Intent(
                                            Intent.ACTION_VIEW,
                                            Uri.parse("market://details?id=" + applicationContext?.packageName)
                                        )
                                    )
                                } catch (e: java.lang.Exception) {
                                    startActivity(
                                        Intent(
                                            Intent.ACTION_VIEW,
                                            Uri.parse("https://play.google.com/store/apps/details?id=" + applicationContext?.packageName)
                                        )
                                    )
                                }
                            }
                            dialog.show()
                        }
                    } catch (e: java.lang.Exception) {
                        e.printStackTrace()
                    }
                } else {
                    Log.d(TAG, "Iam Here...")
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.d(TAG, "):...")
            }
        })
    }

    private fun getFriendLists() {

        val ref = FirebaseDatabase.getInstance().getReference("FriendsList").child(currentUserID!!)
            .orderByKey()

        ref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {

                    friendList!!.clear()
                    for (usersData in dataSnapshot.children) {
                        val user = usersData.getValue(FriendData::class.java)
                        friendList!!.add(user!!._id!!)
                    }
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                toast(p0.message)
            }


        })
    }

}
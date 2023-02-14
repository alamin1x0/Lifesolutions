package com.lifesolutions.bd.kotlinCode.ui.auth

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.provider.Settings
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.chaos.view.PinView
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import com.google.firebase.database.*
import com.lifesolutions.bd.Helpers.InternetCheck
import com.lifesolutions.bd.R
import com.lifesolutions.bd.kotlinCode.data.model.UserKt
import com.lifesolutions.bd.kotlinCode.ui.intro.IntroKtActivity
import com.lifesolutions.bd.kotlinCode.ui.registration.RegistrationKtActivity
import com.lifesolutions.bd.kotlinCode.utils.Utils
import com.lifesolutions.bd.kotlinCode.utils.ViewProgressDialog
import com.lifesolutions.bd.kotlinCode.utils.hideKeyboard
import com.lifesolutions.bd.kotlinCode.utils.toast
import kotlinx.android.synthetic.main.activity_login_kt.*
import java.util.concurrent.TimeUnit


class LoginKtActivity : AppCompatActivity(){
    private val TAG = "LoginKtActivity"
    // Progress Dialog...
    private lateinit var progress: ViewProgressDialog

    // Firebase Auth & Firebase DB...
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var dbRef: DatabaseReference

    // Shared Preferences
    private lateinit var preferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    // Pin Edit Text View..
    private lateinit var pinEtView: PinView

    // Phone Verification..
    private var storedVerificationId: String? = null
    private var resendToken: PhoneAuthProvider.ForceResendingToken? = null

    private var countryCode = "+880"

    // Google Sign In Auth..
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    // Sign In Code
    private val RC_SIGN_IN: Int = 5858
    // Callback Manager..
    private lateinit var mCallbackManager: CallbackManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        // When Screen Height is less then 640dp
        val config = resources.configuration
        if (config.screenHeightDp <= 640) {
            setContentView(R.layout.activity_login_kt_small)
        } else {
            setContentView(R.layout.activity_login_kt)
        }

        // Status Bar Color for SDK 21 & 22..
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            window.statusBarColor = ContextCompat.getColor(applicationContext, R.color.colorPrimaryDark)
        }

        // Initialize Progress Dialog
        progress = ViewProgressDialog(this)

        // Initialize Pin View
        pinEtView = findViewById(R.id.pinView_otp_input)

        // Initialize Auth and Firebase..
        firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.setLanguageCode("en")
        dbRef = FirebaseDatabase.getInstance().reference.child("Users")

        // Initialize Facebook SDK..
        FacebookSdk.sdkInitialize(applicationContext)
        mCallbackManager = CallbackManager.Factory.create()

        // Initialize Google Auth
        configureGoogleSignIn()

        // Initialize Shared Preferences
        preferences = getSharedPreferences("UserInfo", Context.MODE_PRIVATE)!!

        // Terms Layout Click Event...
        terms_text_layout.setOnClickListener {
            //Open Browser Terms Link...
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://starnotesocial.com/terms")
                )
            )
        }

        // Get Country Code Value from Picker..
        country_code_picker.setOnCountryChangeListener {
            countryCode = country_code_picker.selectedCountryCodeWithPlus
        }

    }


    /**
     * Phone Number Auth
     * With Custom Button
     */

    fun sendOtpRequest(view: View) {

        var phoneNumber = et_phone_number.text.toString().trim()

        // Input Condition Check..
        if (phoneNumber.isEmpty()) {
            et_phone_number.error = "Empty field! Please enter your phone number"
            et_phone_number.requestFocus()
            return
        }
        // Internet..
        if (!InternetCheck.checkConnection(this)) {
            toast("No Internet Connection!")
        }


        val firstChar = phoneNumber[0].toString()

        if (countryCode == "+880" && firstChar == "0" && phoneNumber.length == 11) {
            phoneNumber = phoneNumber.substring(1)
        }

        if (countryCode == "+880" && phoneNumber.length != 10) {
            et_phone_number.error = "Invalid Number! Please enter valid number"
            et_phone_number.requestFocus()
            return
        }

        // Hide Keyboard..
        view.hideKeyboard()


        progress.showLoadingBar("Please wait...")

        PhoneAuthProvider.getInstance()
            .verifyPhoneNumber(
                "$countryCode$phoneNumber",
                60,
                TimeUnit.SECONDS,
                this,
                phoneAuthCallbacks
            )
    }

    // Update Phone Otp and Input Field Visibility
    private fun updatePhoneLoginUi() {
        view_area_before_otp.visibility = View.GONE
        view_area_after_otp.visibility = View.VISIBLE
        timer_otp_verification.visibility = View.VISIBLE

        object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timer_otp_verification.text = "${millisUntilFinished / 1000}"
            }

            override fun onFinish() {
                timer_otp_verification.text = "Didn't get code?"
                resend_otp_verification.visibility = View.VISIBLE
                resend_otp_verification.setOnClickListener {

                    view_area_after_otp.visibility = View.GONE
                    view_area_before_otp.visibility = View.VISIBLE
                    timer_otp_verification.visibility = View.GONE
                }
            }
        }.start()
    }


    private val phoneAuthCallbacks =
        object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
                val code = phoneAuthCredential.smsCode

                if (code != null) {
                    pinEtView.setText(code)
                }

                progress.dismissLoadingBar()
                // toast("Verification Success")
                Snackbar.make(login_layout, "Phone Number Successfully Verified ", Snackbar.LENGTH_SHORT).show()
                signInWithPhoneAuthCredential(phoneAuthCredential)
            }

            override fun onVerificationFailed(exception: FirebaseException) {
                when (exception) {
                    is FirebaseAuthInvalidCredentialsException -> {
                        progress.dismissLoadingBar()
                        Snackbar.make(login_layout, "Invalid request", Snackbar.LENGTH_LONG).show()
                        Log.d("loginDDDD", "onVerificationFailed: "+exception)
                    }
                    is FirebaseTooManyRequestsException -> {
                        progress.dismissLoadingBar()
                        Snackbar.make(
                            login_layout,
                            "The verification is blocked for many actions",
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                    else -> {
                        progress.dismissLoadingBar()
                        toast(exception.message!!)
                    }
                }
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                super.onCodeSent(verificationId, token)
                storedVerificationId = verificationId
                resendToken = token

                progress.dismissLoadingBar()
                toast("Verification Code Sent")
                updatePhoneLoginUi()
            }

        }


    // Login with Entered OTP Code..

    fun loginWithEnteredOtp(view: View) {
        val code = pinView_otp_input.text.toString().trim()

        if (code.isEmpty()) {
            pinEtView.error = "Enter Verification Code"
            return
        }

        // Check Code sent or not.
        storedVerificationId?.let {
            val credential = PhoneAuthProvider.getCredential(it, code)

            signInWithPhoneAuthCredential(credential)
        }
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {

        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    progress.showLoadingBar("Please wait...")
                    val uid = firebaseAuth.uid!!
                    initAuthUser(uid, "phone")
                } else {
                    // Sign in failed, display a message and update the UI
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        progress.dismissLoadingBar()
                        pinEtView.error = "Wrong Verification code :)"
                        Snackbar.make(
                            login_layout,
                            "The verification code entered was invalid",
                            Snackbar.LENGTH_LONG
                        ).show()
                    } else {
                        progress.dismissLoadingBar()
                        toast("Sign in failed")
                        Log.d(TAG, task.exception.toString())
                    }
                }
            }
    }


    /**
     * Facebook Auth
     * Start from Here
     * With Custom Button
     */
    // Facebook Login with OnClick()..
    fun onFacebookLogin(view: View) {
        LoginManager.getInstance().logInWithReadPermissions(this, listOf("email", "public_profile"))

        LoginManager.getInstance().registerCallback(mCallbackManager, object :
            FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult?) {
                if (result != null) {
                    handleFacebookAccessToken(result.accessToken)
                };
            }

            override fun onCancel() {
                toast("Facebook:onCancel")
                Log.d(TAG, "Facebook:onCancel")
            }

            override fun onError(error: FacebookException?) {
                Log.d(TAG, "facebook:onError", error);
            }

        })
    }


    // Handle Facebook Auth Token...
    private fun handleFacebookAccessToken(token: AccessToken) {

        progress.showLoadingBar("Please wait...")
        val credential = FacebookAuthProvider.getCredential(token.token)
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val uid = firebaseAuth.currentUser!!.uid
                    initAuthUser(uid, "facebook")
                } else {
                    Log.d(TAG, "signInWithCredential:failure ${task.exception}")
                }

            }
    }

    /**
     * Google Auth
     * Start from Here
     * With Custom Button
     */

    private fun configureGoogleSignIn() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        //    .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
    }

    // OnClick..
    fun onGoogleLogin(view: View) {
        signInWithGoogle()
    }

    private fun signInWithGoogle() {
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    private fun handleGoogleAccessToken(acct: GoogleSignInAccount) {
        progress.showLoadingBar("Please wait...")
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener {task ->
            if (task.isSuccessful) {
                val uid = firebaseAuth.currentUser!!.uid
                initAuthUser(uid, "google")
            } else {
                progress.showLoadingBar("Please wait...")
                Snackbar.make(login_layout, "Google sign in failed:(", Snackbar.LENGTH_LONG).show()
            }
        }
    }

    /**
     * Common On Activity Result
     * For Facebook and Google
     */

    // On AUth Action Activity Result...
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // For Facebook Auth
        mCallbackManager.onActivityResult(requestCode, resultCode, data)


        // For Google Auth
        val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)

        try {
            val account = task.getResult(ApiException::class.java)
            if (account != null) {
                handleGoogleAccessToken(account)
            }
        } catch (e: ApiException) {
            Log.d(TAG, e.toString())
        }
    }



    /**
     * Check Auth
     * Store Logged In User in Firebase
     * Check New Registration
     * Save Data
     */
    private fun initAuthUser(uid: String, loggedInWith: String) {
        // val query: Query = dbRef.orderByChild("uID").equalTo(uid)
        // val ref = dbRef.child(uid)

        dbRef.child(uid).addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    saveUserIdToSharedPref(uid)
                    progress.dismissLoadingBar()
                    toast("Login Success")

                    Intent(this@LoginKtActivity, IntroKtActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(this)
                    }

                } else {
                    // Registration...
                    createNewAccount(uid, loggedInWith)
                }
            }

            override fun onCancelled(err: DatabaseError) {
                progress.dismissLoadingBar()
                toast("Database Error! Please try again)")
                Log.d(TAG, err.message)
            }

        })
    }


    // Create New User..
    private fun createNewAccount(uid: String, registrationWith: String) {
        Log.d(TAG, "createNewAccount")
        // Get Device ID
        @SuppressLint("HardwareIds")
        val deviceId = Settings.Secure.getString(
            this@LoginKtActivity.contentResolver,
            Settings.Secure.ANDROID_ID
        )
        val referable = true
        val permission = true
        val isRandomCall = false

        val newUser = UserKt(
            uid,
            if(registrationWith == "phone")  null else FirebaseAuth.getInstance().currentUser?.displayName,
            "",
            if(registrationWith == "google") FirebaseAuth.getInstance().currentUser?.email else "",
            if(registrationWith == "phone")  FirebaseAuth.getInstance().currentUser?.phoneNumber else "",
            "",
            Utils.generateReferCode(),
            "user",
            if(registrationWith == "phone") "none" else FirebaseAuth.getInstance().currentUser?.photoUrl.toString(),
            if(registrationWith == "phone") "none" else FirebaseAuth.getInstance().currentUser?.photoUrl.toString(),
            if(registrationWith == "phone") "none" else FirebaseAuth.getInstance().currentUser?.photoUrl.toString(),
            registrationWith,
            deviceId,
            Utils.getLocalIpAddress(),
            System.currentTimeMillis(),
            permission,
            isRandomCall,
            referable,
            50,
            0,
            "unnamed"
        )

        // Check Device ID
        // val query: Query = dbRef.orderByChild("deviceId").equalTo(deviceId)

        dbRef.child(uid).setValue(newUser).addOnCompleteListener { task ->
            Log.d(TAG, "Set Value addOnCompleteListener")
            if (task.isSuccessful) {
                // Added extra Field
                dbRef.child(uid).child("uID").setValue(uid)
                progress.dismissLoadingBar()
                // Toast.makeText(this@LoginKtActivity, "Registration Data Added", Toast.LENGTH_SHORT).show()
                openCompleteProfileActivity(newUser)
            }
        }.addOnFailureListener {e ->
            progress.dismissLoadingBar()
            toast("Something went wrong! ${e.message}")
        }
    }

    /**
     * Redirect new User to Profile Complete page
     */
    private fun openCompleteProfileActivity(user: UserKt) {
        // Save to Shared Preferences
        saveUserIdToSharedPref(user.id!!)

        Intent(this, RegistrationKtActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra("Auth_User_Info", user)
            startActivity(this)
        }
    }

    /**
     * Save User ID to shared preference
     */
    private fun saveUserIdToSharedPref(uid: String) {
        Log.d(TAG, uid)
        editor = preferences.edit()
        editor.putString("uID", uid)
        editor.apply()
    }



}
package com.lifesolutions.bd.kotlinCode.utils

import com.google.firebase.auth.FirebaseAuth

object UserData {

    val currentUserID = FirebaseAuth.getInstance().uid
    var friendList: ArrayList<String> = ArrayList()
}
package com.lifesolutions.bd.kotlinCode.data.model

import java.io.Serializable

data class UserKt(
    var id: String? = null,
    var firstName: String? = null,
    var lastName: String? = null,
    var email: String? = null,
    var phone: String? = null,
    var password: String? = null,
    var referCode: String? = null,
    var role: String? = null,
    var imageThumbnail: String? = null,
    var imageMedium: String? = null,
    var imageHD: String? = null,
    var registrationType: String? = null,
    var deviceId: String? = null,
    var ipAddress: String? = null,
    var registeredTime: Long? = 0,
    var permission: Boolean? = false,
    var isRandomCall: Boolean? = false,
    var isReferable: Boolean? = false,
    var points: Int? = 0,
    var referred: Int? = 0,
    var searchName: String? = null,
    var address: String? = null,
    var studyInfo: String? = null,
    var bio: String? = null,
    var bloodGroup: String? = null,
    var gender: String? = null,
    var birthDate: Long? = null,
    var uID: String? = null

): Serializable
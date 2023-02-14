package com.lifesolutions.bd.kotlinCode.data.model
import java.io.Serializable

data class Teacher(
    val _id: String? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val profileImage: String? = null,
    val ratings: Double? = null,
    val rated: Int? = null,
    val talktime: Int? = null

) : Serializable

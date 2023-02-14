package com.lifesolutions.bd.kotlinCode.data.model

import java.io.Serializable

data class Course(
    val _id: String? = null,
    val name: String? = null,
    val category: String? = null,
    val offeredBy: String? = null,
    val price: Int? = null,
    val description: String? = null
): Serializable
package com.lifesolutions.bd.kotlinCode.data.model

import com.lifesolutions.bd.Models.User

data class GroupMemberItem(
    val groupMember: GroupMember,
    val user: User
)
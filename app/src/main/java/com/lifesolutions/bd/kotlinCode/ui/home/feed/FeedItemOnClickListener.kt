package com.lifesolutions.bd.kotlinCode.ui.home.feed

import android.view.View
import com.lifesolutions.bd.kotlinCode.data.model.PostKt

interface FeedItemOnClickListener {
    fun onRecyclerViewItemClicked(view: View, postItem: PostKt)
}
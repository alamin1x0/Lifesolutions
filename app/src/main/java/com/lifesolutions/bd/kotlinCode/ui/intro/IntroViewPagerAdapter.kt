package com.lifesolutions.bd.kotlinCode.ui.intro

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.lifesolutions.bd.kotlinCode.data.model.ScreenItem
import kotlinx.android.synthetic.main.layout_intro_screen.view.*


class IntroViewPagerAdapter(
    private val mContext: Context,
    private val mListScreen: List<ScreenItem>
): PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layoutScreen = LayoutInflater.from(mContext)
            .inflate(com.lifesolutions.bd.R.layout.layout_intro_screen, null, false)

        val imgSlide: ImageView = layoutScreen.intro_img
        val title = layoutScreen.intro_title
        val description = layoutScreen.intro_description

        title.text = mListScreen[position].title
        description.text = mListScreen[position].description
        mListScreen[position].ScreenImg?.let { imgSlide.setImageResource(it) }

        container.addView(layoutScreen)

        return layoutScreen
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int = mListScreen.size

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }


}
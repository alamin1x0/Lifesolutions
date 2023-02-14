package com.lifesolutions.bd.kotlinCode.ui.activities

import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.lifesolutions.bd.R
import com.lifesolutions.bd.kotlinCode.data.model.CourseVideo
import kotlinx.android.synthetic.main.activity_youtube_play.*

class YoutubePlayActivity : AppCompatActivity() {
    private lateinit var video: CourseVideo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_youtube_play)

        video = intent.getSerializableExtra("PLAYBACK_DATA") as CourseVideo

        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        lifecycle.addObserver(youtube_player_view)
        Toast.makeText(this, "Please rotate your phone for full screen video", Toast.LENGTH_LONG).show()

        playVideo()
        setData()

    }

    private fun playVideo() {
        youtube_player_view.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadVideo(video.videoId!!, 0f)
            }
        })
    }

    private fun setData() {
        title_video_play.text = video.title
        description_video_play.text = video.description
    }

    override fun onDestroy() {
        super.onDestroy()
        youtube_player_view.release()
    }

}
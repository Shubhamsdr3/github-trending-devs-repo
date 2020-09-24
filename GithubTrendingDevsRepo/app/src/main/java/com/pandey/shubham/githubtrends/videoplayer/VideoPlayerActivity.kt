package com.pandey.shubham.githubtrends.videoplayer

import android.os.Bundle
import com.longtailvideo.jwplayer.configuration.PlayerConfig
import com.longtailvideo.jwplayer.media.playlists.PlaylistItem
import com.pandey.shubham.githubtrends.R
import com.pandey.shubham.githubtrends.base.BaseActivity
import kotlinx.android.synthetic.main.activity_video_player.*

class VideoPlayerActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startFragment(VideoPlayerFragment.newInstance(), false)
    }
}
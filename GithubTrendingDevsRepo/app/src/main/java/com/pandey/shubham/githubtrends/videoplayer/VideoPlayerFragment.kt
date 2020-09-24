package com.pandey.shubham.githubtrends.videoplayer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.longtailvideo.jwplayer.configuration.PlayerConfig
import com.longtailvideo.jwplayer.media.playlists.PlaylistItem
import com.pandey.shubham.githubtrends.R
import com.pandey.shubham.githubtrends.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_video_player.*

class VideoPlayerFragment : BaseFragment() {

    companion object {
        fun newInstance() : VideoPlayerFragment {
            return VideoPlayerFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_video_player, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        video_player_toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white)
        video_player_toolbar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
        val playListItem =
            PlaylistItem.Builder()
                .file("https://cdn.jwplayer.com/manifests/FKuy1Gdj.m3u8")
                .image("https://dev.farmstock.in/media/images/products/2020/08/scaled_image_picker5476852007814294079.jpg")
                .title("Dhan ki ropai")
                .description("Is video hum btayenge ki dhan in roapi kaise ki jati hai")
                .build()

        val playerList = mutableListOf<PlaylistItem>()
        playerList.add(playListItem)
        playerList.add(playListItem)
        playerList.add(playListItem)

        val playerConfig = PlayerConfig.Builder().playlist(playerList).build()
        video_player_view.setup(playerConfig)
    }

    override fun getLayoutFile(): Int {
        return R.layout.fragment_video_player
    }
}
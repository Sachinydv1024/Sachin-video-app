package com.example.videocallapp.alladapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.videocallapp.databinding.ItemVideoBinding
import com.example.videocallapp.model.VideoDataModel


class VideoAdapter(private val videoList: List<VideoDataModel>) :
    RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemVideoBinding.inflate(inflater, parent, false)
        return VideoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val video = videoList[position]
        holder.bind(video)
    }

    override fun getItemCount(): Int {

        return videoList.size
    }

    inner class VideoViewHolder(private val binding: ItemVideoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(video: VideoDataModel) {

            binding.apply {

            }

            if (video.video_url.isNotEmpty()) {
                binding.progressBar.visibility = View.GONE
            } else {
                binding.progressBar.visibility = View.VISIBLE
            }

            binding.videoView.apply {
                setVideoPath(video.video_url)
                setOnPreparedListener { mp ->
                    mp.start()
                    val videoRatio: Float = mp.videoWidth.toFloat() / mp.videoHeight.toFloat()
                    val screenRatio: Float = this.width.toFloat() / this.height.toFloat()
                    val scale = videoRatio / screenRatio
                    if (scale >= 1F) {
                        this.scaleX = scale
                    } else {
                        this.scaleY = scale
                    }
                }
                setOnCompletionListener {
                    it.start()

                }
            }


// Play button click listener
            // Play button click listener
//            binding.apply {
//                btnPause.visibility = View.VISIBLE
//
//                binding.btnPlay.setOnClickListener(View.OnClickListener {
//                    btnPause.visibility = View.GONE
//                    btnPlay.visibility = View.VISIBLE
//                    binding.videoView.start() // Start playing the video
//                })
//
//                // Pause button click listener
//
//                // Pause button click listener
//                binding.btnPause.setOnClickListener(View.OnClickListener {
//                    binding.videoView.pause() // Pause the video
//                })
//            }
//            binding.userName.text = video.userName
//            binding.videoDescription.text = video.description
//            binding.likeCount.text = video.likeCount.toString()
            // Bind other data here
        }
    }
}


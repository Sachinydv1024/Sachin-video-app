package com.example.videocallapp.ui.allFragmnet

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.videocallapp.alladapter.VideoAdapter
import com.example.videocallapp.api.ApiService
import com.example.videocallapp.api.RetrofitClient
import com.example.videocallapp.databinding.FragmentHomeBinding
import com.example.videocallapp.repo.MemesRepo
import com.example.videocallapp.viewmodel.MemesViewModel
import com.example.videocallapp.viewmodel.MemesViewModelFactory

class HomeFragment() : Fragment() {
    private lateinit var adapter: VideoAdapter
    private lateinit var memesViewModel: MemesViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val apiinterface = RetrofitClient.getInstance().create(ApiService::class.java)

        val memesRepo = MemesRepo(apiinterface)

        memesViewModel =
            ViewModelProvider(this, MemesViewModelFactory(memesRepo))[MemesViewModel::class.java]
        memesViewModel.meme.observe(viewLifecycleOwner) {
            Log.d("TAG", "onCreate: ${it.get(0).video_url}")
            adapter = VideoAdapter(it)
            val snapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(binding.recyclerView)
            binding.recyclerView.layoutManager =
                LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            binding.recyclerView.adapter = adapter

        }

    }
}
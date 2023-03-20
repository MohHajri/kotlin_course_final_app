package com.ddevs.nasaapod

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.signature.ObjectKey
import com.ddevs.nasaapod.databinding.FragmentListBinding
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*



class ListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel:ListViewModel=ViewModelProvider(this).get(ListViewModel::class.java)
        val binding:FragmentListBinding=DataBindingUtil.inflate(inflater,R.layout.fragment_list, container, false)
        val layoutManager= GridLayoutManager(context,3)
        binding.viewModel=viewModel
        binding.recyclerView.layoutManager=layoutManager
        val shimmer = Shimmer.AlphaHighlightBuilder()
                .setDuration(1800)
                .setBaseAlpha(0.7f)
                .setHighlightAlpha(0.6f)
                .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
                .setAutoStart(true)
                .build()

        val shimmerDrawable = ShimmerDrawable().apply {
            setShimmer(shimmer)
        }

        viewModel.todayApod.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if(it!=null){
                Glide.with(requireContext())
                        .load(it.hdUrl)
                        .placeholder(shimmerDrawable)
                        .into(binding.todayPhoto)
            }
        })

        viewModel.listApod.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            val adapter:ListAdapter= ListAdapter(it)
            binding.recyclerView.adapter=adapter
        })

        viewModel.navigate.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if(it!=null) {
                binding.root.findNavController().navigate(ListFragmentDirections.actionListFragmentToDetailFragment(it))
                viewModel.doneNavigating()
            }
        })

        return binding.root
    }

}

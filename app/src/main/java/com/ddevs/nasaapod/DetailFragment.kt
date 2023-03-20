package com.ddevs.nasaapod

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.signature.ObjectKey
import com.ddevs.nasaapod.databinding.FragmentDetailBinding


class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding:FragmentDetailBinding=DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container,false)
        val args=DetailFragmentArgs.fromBundle(requireArguments())
        ViewModelProvider(this,DetailViewModelFactory(args.apod)).get(DetailViewModel::class.java)
        binding.data=args.apod
        Glide.with(this)
                .load(Uri.parse(args.apod.hdUrl))
                .signature(ObjectKey(args.apod.date))
                .into(binding.apodImage)
        return binding.root
    }

}
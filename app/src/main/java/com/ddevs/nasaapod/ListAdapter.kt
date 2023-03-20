package com.ddevs.nasaapod

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.signature.ObjectKey
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable



class ListAdapter(val list:List<Apod>): RecyclerView.Adapter<ListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.ViewHolder {
        val view:View=LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListAdapter.ViewHolder, position: Int) {
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
        if(list[position].mediaType=="video"){
            Glide.with(holder.listImage)
                    .load(list[position].thumbnailUrl)
                    .placeholder(shimmerDrawable)
                    .into(holder.listImage)
        }
        else {
            Glide.with(holder.listImage)
                    .load(list[position].hdUrl)
                    .placeholder(shimmerDrawable)
                    .signature(ObjectKey(list[position].date))
                    .into(holder.listImage)
        }
        holder.listImage.setOnClickListener {
            holder.moveToDetailFragment(list[position])

        }
    }

    override fun getItemCount(): Int= list.size

    class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val listImage: ImageView = itemView.findViewById(R.id.list_image)
        fun moveToDetailFragment(data:Apod){
            itemView.findNavController().navigate(ListFragmentDirections.actionListFragmentToDetailFragment(data))
        }
    }

}



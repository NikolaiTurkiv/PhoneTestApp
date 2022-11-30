package com.test.featue_phone_detail.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.test.featue_phone_detail.databinding.ImageItemBinding

class ImageViewPagerAdapter(
    private val context: Context,
    private val inflater: LayoutInflater,
) : RecyclerView.Adapter<ImageViewPagerAdapter.ImageViewPagerHolder>() {

    val pictures = mutableListOf<String>()

    fun updateList(list: List<String>) {
        pictures.clear()
        pictures.addAll(list)

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewPagerHolder {
        return ImageViewPagerHolder(ImageItemBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ImageViewPagerHolder, position: Int) {
        val item = pictures[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = pictures.size

    class ImageViewPagerHolder(val binding: ImageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            Picasso.get().load(item).into(binding.viewPagerImage)
        }
    }

}
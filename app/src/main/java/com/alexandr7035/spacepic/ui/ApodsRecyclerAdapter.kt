package com.alexandr7035.spacepic.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alexandr7035.spacepic.databinding.ViewApodBinding
import com.bumptech.glide.Glide

class ApodsRecyclerAdapter: RecyclerView.Adapter<ApodsRecyclerAdapter.ViewHolder>() {

    private var items = emptyList<ApodUi>()

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: List<ApodUi>) {
        this.items = items
//        Timber.debug("set items $items")
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewApodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return when(viewType) {
            0 -> ViewHolder.Image(binding)
            1 -> ViewHolder.Video(binding)
            else -> throw RuntimeException("unknown viewholder, implement it")
        }
    }

    override fun getItemViewType(position: Int): Int {
//        return super.getItemViewType(position)
        return when(items[position]) {
            is ApodUi.ImageApod -> 0
            is ApodUi.VideoApod -> 1
            else -> 2
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }


    abstract class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        abstract fun bind(apod: ApodUi)

        class Image(private val binding: ViewApodBinding): ViewHolder(binding.root) {
            override fun bind(apod: ApodUi) {

                val apod = apod as ApodUi.ImageApod
                binding.date.text = apod.date
                binding.title.text = apod.title

                Glide.with(binding.root.context).load(apod.apodUri).into(binding.imageView)
            }
        }


        class Video(private val binding: ViewApodBinding): ViewHolder(binding.root) {
            override fun bind(apod: ApodUi) {
                TODO("Not yet implemented")
            }
        }
    }

}
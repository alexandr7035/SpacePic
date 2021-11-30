package com.alexandr7035.spacepic.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alexandr7035.spacepic.databinding.ViewApodImageBinding
import com.alexandr7035.spacepic.databinding.ViewApodVideoBinding
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
        return when(viewType) {
            ApodViewType.APOD_IMAGE.ordinal -> {
                val binding = ViewApodImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ViewHolder.Image(binding)
            }
            else -> {
                throw RuntimeException("unknown viewholder, implement it")
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
//        return super.getItemViewType(position)
        return when(items[position]) {
            is ApodUi.ImageApod -> ApodViewType.APOD_IMAGE.ordinal
            is ApodUi.VideoApod -> ApodViewType.APOD_VIDEO.ordinal
            else -> throw java.lang.RuntimeException("Unknown viewtype. Implement it")
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }


    abstract class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        abstract fun bind(apod: ApodUi)

        class Image(private val binding: ViewApodImageBinding): ViewHolder(binding.root) {
            override fun bind(apod: ApodUi) {
                val apodCasted = apod as ApodUi.ImageApod
                binding.date.text = apodCasted.date
                binding.title.text = apodCasted.title

                Glide.with(binding.root.context).load(apod.apodUri).into(binding.imageView)
            }
        }

        class Video(private val binding: ViewApodVideoBinding): ViewHolder(binding.root) {
            override fun bind(apod: ApodUi) {
                val apodCasted = apod as ApodUi.VideoApod

                // TODO
            }
        }
    }

}
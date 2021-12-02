package com.alexandr7035.spacepic.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alexandr7035.spacepic.core.extensions.debug
import com.alexandr7035.spacepic.databinding.ViewApodImageBinding
import com.alexandr7035.spacepic.databinding.ViewApodLoadingFooterBinding
import com.alexandr7035.spacepic.databinding.ViewApodVideoBinding
import com.bumptech.glide.Glide
import timber.log.Timber

class ApodsRecyclerAdapter: RecyclerView.Adapter<ApodsRecyclerAdapter.ViewHolder>() {

    private var items = ArrayList<ApodUi>()

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: ArrayList<ApodUi>) {
        this.items = items
//        Timber.debug("set items $items")
        notifyDataSetChanged()
    }

    fun addLoadingFooter() {
        items.add(ApodUi.LoadingFooter())
        notifyItemInserted(items.size-1)
    }

    fun removeLoadingFooter() {
        items.removeAt(items.size - 1)
        notifyItemRemoved(items.size-1)
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

            ApodViewType.APOD_VIDEO.ordinal -> {
                val binding = ViewApodVideoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ViewHolder.Video(binding)
            }

            ApodViewType.LOADING_FOOTER.ordinal -> {
                val binding = ViewApodLoadingFooterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ViewHolder.LoadingFooter(binding)
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
            is ApodUi.LoadingFooter -> ApodViewType.LOADING_FOOTER.ordinal
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

                Glide.with(binding.root.context).load(apod.imageUrl).into(binding.imageView)
            }
        }

        class Video(private val binding: ViewApodVideoBinding): ViewHolder(binding.root) {
            override fun bind(apod: ApodUi) {
                val apodCasted = apod as ApodUi.VideoApod
                binding.date.text = apodCasted.date
                binding.title.text = apodCasted.title

                Timber.debug("url ${apodCasted.videoThumbUrl}")

                Glide.with(binding.root.context).load(apod.videoThumbUrl).into(binding.videoThumbView)
            }
        }

        class LoadingFooter(private val binding: ViewApodLoadingFooterBinding): ViewHolder(binding.root) {
            override fun bind(apod: ApodUi) {

            }
        }
    }

}
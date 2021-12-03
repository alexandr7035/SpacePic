package com.alexandr7035.spacepic.ui.apods_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alexandr7035.spacepic.databinding.ViewApodImageBinding
import com.alexandr7035.spacepic.databinding.ViewApodLoadingFooterBinding
import com.alexandr7035.spacepic.databinding.ViewApodVideoBinding
import com.alexandr7035.spacepic.ui.ApodUi
import com.bumptech.glide.Glide

class ApodsRecyclerAdapter(
    private val imageClickListener: ImageClickListener,
    private val videoClickListener: VideoClickListener
): RecyclerView.Adapter<ApodsRecyclerAdapter.ViewHolder>() {

    var items = ArrayList<ApodUi>()

    fun addItems(insertedItems: ArrayList<ApodUi>) {
        val currentEnd = insertedItems.size
        this.items.addAll(insertedItems)
        notifyItemRangeChanged(currentEnd+1, insertedItems.size)
    }

    fun addLoadingFooter() {
        items.add(ApodUi.LoadingFooter())
        notifyItemInserted(items.size-1)
    }

    fun removeLoadingFooter() {
        items.removeAt(items.size - 1)
        notifyItemRemoved(items.size-1)
    }

    fun getLastItem(): ApodUi? {

        if (items.isNotEmpty()) {
            return items.last()
        }
        else {
            return null
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when(viewType) {
            ApodViewType.APOD_IMAGE.ordinal -> {
                val binding = ViewApodImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ViewHolder.Image(binding, imageClickListener)
            }

            ApodViewType.APOD_VIDEO.ordinal -> {
                val binding = ViewApodVideoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ViewHolder.Video(binding, videoClickListener)
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

        class Image(private val binding: ViewApodImageBinding, private val clickListener: ImageClickListener): ViewHolder(binding.root) {

            override fun bind(apod: ApodUi) {
                val apodCasted = apod as ApodUi.ImageApod

                binding.date.text = apodCasted.date
                binding.title.text = apodCasted.title
                Glide.with(binding.root.context).load(apod.imageUrl).into(binding.imageView)

                binding.root.setOnClickListener { clickListener.onImageApodClicked(apod.date) }

            }
        }

        class Video(private val binding: ViewApodVideoBinding, private val clickListener: VideoClickListener): ViewHolder(binding.root) {
            override fun bind(apod: ApodUi) {
                val apodCasted = apod as ApodUi.VideoApod

                binding.date.text = apodCasted.date
                binding.title.text = apodCasted.title
                Glide.with(binding.root.context).load(apod.videoThumbUrl).into(binding.videoThumbView)

                binding.root.setOnClickListener { clickListener.onVideoApodClicked(apod.date) }
            }
        }

        class LoadingFooter(private val binding: ViewApodLoadingFooterBinding): ViewHolder(binding.root) {
            override fun bind(apod: ApodUi) {

            }
        }
    }

}
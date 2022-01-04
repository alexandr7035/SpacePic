package com.alexandr7035.spacepic.ui.apod_page

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.alexandr7035.spacepic.BuildConfig
import com.alexandr7035.spacepic.MainActivity
import com.alexandr7035.spacepic.R
import com.alexandr7035.spacepic.core.extensions.debug
import com.alexandr7035.spacepic.core.extensions.getStringDateFromUnix
import com.alexandr7035.spacepic.databinding.FragmentImagePageBinding
import com.alexandr7035.spacepic.ui.ApodUi
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.io.File
import java.io.FileOutputStream

@AndroidEntryPoint
class ImagePageFragment : Fragment(), ShareHelper {

    private var binding: FragmentImagePageBinding? = null
    private val viewModel by viewModels<ApodPageViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentImagePageBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.toolbar?.setNavigationOnClickListener {
            (requireActivity() as MainActivity).navigateBack()
        }

        val apodDate = arguments?.getLong(APOD_DATE) ?: System.currentTimeMillis()
        viewModel.init(apodDate)

        viewModel.singleApodLiveData.observe(viewLifecycleOwner, {
            Timber.debug("apod: $it")
            val apod = it as ApodUi.ImageApod

            binding?.titleView?.text = apod.title
            binding?.descriptionView?.text = apod.description
            // FIXME
            binding?.toolbar?.title = apod.date.getStringDateFromUnix("dd MMM yyyy")
            Glide.with(binding!!.root.context).load(apod.imageUrl).into(binding!!.imageView)


            binding?.shareBtn?.setOnClickListener {
                onShareRequested(apod)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


    override fun onShareRequested(apodUi: ApodUi) {

        val apod = apodUi as ApodUi.ImageApod

        Timber.debug("url ${apodUi.imageUrl}")
        val imageUri = Uri.parse(apod.imageUrl)
        Timber.debug("uri $imageUri")

        Glide.with(requireContext()).asBitmap().load(apod.imageUrl).into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {

                // FIXME move logics out of fragment
                val filesDir = requireContext().getExternalFilesDir(null)
                val imagePath = filesDir!!.absolutePath + File.separator + "shared_image.png"

                val outStream = FileOutputStream(imagePath)
                resource.compress(Bitmap.CompressFormat.PNG, 100, outStream)
                outStream.close()

                // We must convert file uri to content uri to make it work
                // Otherwise get FileUriExposedException
                val savedUri = Uri.fromFile(File(imagePath))
                val file = File(savedUri.path!!)
                val contentUri = FileProvider.getUriForFile(requireContext(), BuildConfig.APPLICATION_ID + ".provider", file)

                // Implicit intent
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "image/*"

                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT)

                intent.putExtra(Intent.EXTRA_STREAM, contentUri)

                // TODO add copyright
                intent.putExtra(Intent.EXTRA_TEXT, apodUi.title + "\n ${apod.imageUrl} сравниваем нахуй с оригиналом")
                intent.putExtra(Intent.EXTRA_TEXT, getString(
                    R.string.share_text_template,
                    apodUi.title,
                    BuildConfig.VERSION_NAME
                ))
                startActivity(Intent.createChooser(intent, getString(R.string.share_dialog_text)))
            }

            override fun onLoadCleared(placeholder: Drawable?) {

            }
        })
    }

    companion object {
        private const val APOD_DATE = "APOD_DATE"

        @JvmStatic
        fun newInstance(apodDate: Long) = ImagePageFragment().apply {
            arguments = Bundle().apply {
                putLong(APOD_DATE, apodDate)
            }
        }
    }
}
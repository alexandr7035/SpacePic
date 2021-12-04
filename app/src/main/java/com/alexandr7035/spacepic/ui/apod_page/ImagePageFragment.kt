package com.alexandr7035.spacepic.ui.apod_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.alexandr7035.spacepic.core.extensions.debug
import com.alexandr7035.spacepic.core.extensions.getStringDateFromUnix
import com.alexandr7035.spacepic.databinding.FragmentImagePageBinding
import com.alexandr7035.spacepic.ui.ApodUi
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class ImagePageFragment : Fragment() {

    private var binding: FragmentImagePageBinding? = null
    private val viewModel by viewModels<ApodPageViewModel>()
    private val safeArgs by navArgs<ImagePageFragmentArgs>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentImagePageBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.toolbar?.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        val apodDate = safeArgs.apodDate
        viewModel.init(apodDate)

        viewModel.singleApodLiveData.observe(viewLifecycleOwner, {
            Timber.debug("apod: $it")
            val apod = it as ApodUi.ImageApod

            binding?.titleView?.text = apod.title
            binding?.descriptionView?.text = apod.description
            // FIXME
            binding?.toolbar?.title = apod.date.getStringDateFromUnix("dd MMM yyyy")
            Glide.with(binding!!.root.context).load(apod.imageUrl).into(binding!!.imageView)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
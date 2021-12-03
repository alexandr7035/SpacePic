package com.alexandr7035.spacepic.ui.apod_page

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.alexandr7035.spacepic.R
import com.alexandr7035.spacepic.core.extensions.debug
import com.alexandr7035.spacepic.databinding.FragmentApodPageBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class ApodPageFragment : Fragment() {

    private var binding: FragmentApodPageBinding? = null
    private val viewModel by viewModels<ApodPageViewModel>()
    private val safeArgs by navArgs<ApodPageFragmentArgs>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentApodPageBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val apodDate = safeArgs.apodDate
        viewModel.init(apodDate)

        viewModel.singleApodLiveData.observe(viewLifecycleOwner, {
            Timber.debug("apod: $it")
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
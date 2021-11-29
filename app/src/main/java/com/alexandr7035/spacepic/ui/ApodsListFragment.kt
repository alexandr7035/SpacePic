package com.alexandr7035.spacepic.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.alexandr7035.spacepic.R
import com.alexandr7035.spacepic.databinding.FragmentApodsListBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class ApodsListFragment : Fragment() {

    private var binding: FragmentApodsListBinding? = null
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentApodsListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ApodsRecyclerAdapter()
        binding?.recycler?.adapter = adapter

        val testItmes = listOf<ApodUi>(
            ApodUi.ImageApod(
                title = "Image1",
                apodUri = "link",
                date = "2020-11-01",
                description = "description"
            ),
            ApodUi.ImageApod(
                title = "Image1",
                apodUri = "link",
                date = "2020-11-01",
                description = "description"
            ),
            ApodUi.ImageApod(
                title = "Image1",
                apodUri = "link",
                date = "2020-11-01",
                description = "description"
            )
        )

        viewModel.apodsLiveData.observe(viewLifecycleOwner, { apodsUi ->
            if (apodsUi is ApodsUi.Success) {
                Timber.tag("DEBUG_TAG").d("SUCCESS ${apodsUi.apods}")
                adapter.setItems((apodsUi.apods))
            }
            else if (apodsUi is ApodsUi.Fail) {
                Timber.tag("DEBUG_TAG").d("FAIL ${apodsUi.errorMessage}")
            }
        })

//        adapter.setItems(testItmes)
        viewModel.init()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
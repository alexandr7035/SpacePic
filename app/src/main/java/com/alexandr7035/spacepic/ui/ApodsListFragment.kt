package com.alexandr7035.spacepic.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.alexandr7035.spacepic.R
import com.alexandr7035.spacepic.core.extensions.debug
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

        viewModel.apodsLiveData.value = ApodsUi.Progress()

        viewModel.apodsLiveData.observe(viewLifecycleOwner, { apodsUi ->

            Timber.debug("Update apods $apodsUi")

            when (apodsUi) {
                is ApodsUi.Success -> {
                    binding?.progressView?.visibility = View.GONE
                    binding?.errorView?.visibility = View.GONE
                    binding?.recycler?.visibility = View.VISIBLE
                    adapter.setItems(apodsUi.apods)
                }
                is ApodsUi.Progress -> {
                    binding?.progressView?.visibility = View.VISIBLE
                    binding?.errorView?.visibility = View.GONE
                    binding?.recycler?.visibility = View.GONE
//                    Toast.makeText(requireContext(), "Progress...", Toast.LENGTH_SHORT).show()
                }
                is ApodsUi.Fail -> {
                    binding?.progressView?.visibility = View.GONE
                    binding?.errorView?.visibility = View.VISIBLE
                    binding?.recycler?.visibility = View.GONE
                    binding?.errorView?.text = apodsUi.errorMessage
//                    Toast.makeText(requireContext(), apodsUi.errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
        })

        viewModel.init()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
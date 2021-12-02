package com.alexandr7035.spacepic.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alexandr7035.spacepic.core.extensions.debug
import com.alexandr7035.spacepic.core.extensions.getApodUnixDateFromString
import com.alexandr7035.spacepic.databinding.FragmentApodsListBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class ApodsListFragment : Fragment() {

    private var binding: FragmentApodsListBinding? = null
    private val viewModel by viewModels<MainViewModel>()

    // FIXME global var is not good
    private var isPaginationLoading: Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentApodsListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ApodsRecyclerAdapter()
        val layoutManager = LinearLayoutManager(requireContext())
        binding?.recycler?.layoutManager = layoutManager
        binding?.recycler?.adapter = adapter

        viewModel.apodsLiveData.value = ApodsUi.Progress()

        viewModel.apodsLiveData.observe(viewLifecycleOwner, { apodsUi ->

            Timber.debug("Update apods $apodsUi")

            when (apodsUi) {
                is ApodsUi.Success -> {
                    if (isPaginationLoading) {
                        isPaginationLoading = false
                        adapter.removeLoadingFooter()
                    }

                    binding?.progressView?.visibility = View.GONE
                    binding?.errorView?.visibility = View.GONE
                    binding?.recycler?.visibility = View.VISIBLE
                    adapter.addItems(ArrayList(apodsUi.apods))
                }
                is ApodsUi.Progress -> {
                    if (isPaginationLoading) {
                        isPaginationLoading = false
                        adapter.removeLoadingFooter()
                    }

                    binding?.progressView?.visibility = View.VISIBLE
                    binding?.errorView?.visibility = View.GONE
                    binding?.recycler?.visibility = View.GONE
//                    Toast.makeText(requireContext(), "Progress...", Toast.LENGTH_SHORT).show()
                }
                is ApodsUi.Fail -> {
                    if (isPaginationLoading) {
                        isPaginationLoading = false
                        adapter.removeLoadingFooter()
                    }

                    binding?.progressView?.visibility = View.GONE
                    binding?.errorView?.visibility = View.VISIBLE
                    binding?.recycler?.visibility = View.GONE
                    binding?.errorTextView?.text = apodsUi.errorMessage
//                    Toast.makeText(requireContext(), apodsUi.errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
        })

        viewModel.init()

        binding?.retryBtn?.setOnClickListener {
            // FIXME pagination
            viewModel.init()
        }

        binding?.recycler?.addOnScrollListener(object: RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val visibleItemsCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if (! isPaginationLoading) {
                    if (visibleItemsCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0) {
                        isPaginationLoading = true
                        adapter.addLoadingFooter()
                        loadMoreItems()
                    }
                }
            }

            fun loadMoreItems() {
                Timber.debug("load more...")

                val lastApod = adapter.getLastItem()

                val lastApodDate = when(lastApod) {
                    is ApodUi.ImageApod -> lastApod.date.getApodUnixDateFromString()
                    is ApodUi.VideoApod -> lastApod.date.getApodUnixDateFromString()
                    else -> System.currentTimeMillis()
                }

                viewModel.loadMore(lastApodDate)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
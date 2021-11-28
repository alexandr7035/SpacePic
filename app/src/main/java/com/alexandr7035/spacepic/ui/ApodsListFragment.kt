package com.alexandr7035.spacepic.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alexandr7035.spacepic.R
import com.alexandr7035.spacepic.databinding.FragmentApodsListBinding

class ApodsListFragment : Fragment() {

    private var binding: FragmentApodsListBinding? = null

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

        adapter.setItems(testItmes)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
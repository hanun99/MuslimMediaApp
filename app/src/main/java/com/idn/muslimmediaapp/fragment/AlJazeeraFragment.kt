package com.idn.muslimmediaapp.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.idn.muslimmediaapp.NewsViewModel
import com.idn.muslimmediaapp.adapter.NewsAdapter
import com.idn.muslimmediaapp.databinding.FragmentAlJazeeraBinding

class AlJazeeraFragment : Fragment() {
    private var _binding: FragmentAlJazeeraBinding? = null
    private val binding get() = _binding as FragmentAlJazeeraBinding

    private var _aljazeeraNewsViewModel: NewsViewModel? =null
    private val aljazeeraNewsViewModel get() = _aljazeeraNewsViewModel as NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAlJazeeraBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loadingView.root.visibility = View.VISIBLE
        _aljazeeraNewsViewModel = ViewModelProvider(this)[NewsViewModel::class.java]
        aljazeeraNewsViewModel.alJazeeraNews()
        aljazeeraNewsViewModel.alJazeeraNews.observe(viewLifecycleOwner) {
            val mAdapter = NewsAdapter()
            mAdapter.setData(it.articles)
            Log.i(
                "AljazeeraFragment",
                "onViewCreated: ${it.articles}"
            )
            binding.rvAljazeera.apply {
                adapter = mAdapter
                layoutManager = LinearLayoutManager(view.context)
            }
            binding.loadingView.root.visibility = View.GONE
        }
    }
}
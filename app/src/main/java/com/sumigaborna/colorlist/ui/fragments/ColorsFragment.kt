package com.sumigaborna.colorlist.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.sumigaborna.colorlist.databinding.FragmentColorsBinding
import com.sumigaborna.colorlist.ui.ColorsViewModel
import com.sumigaborna.colorlist.ui.adapters.ColorsAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ColorsFragment : Fragment(), ColorItemListener {

    private val viewModel by viewModel<ColorsViewModel>()
    private lateinit var binding: FragmentColorsBinding
    private val adapter: ColorsAdapter by inject { parametersOf(this) }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.addColors()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentColorsBinding.inflate(layoutInflater)
        binding.rvColors.adapter = adapter
        binding.rvColors.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showProgressBar()
        getColors()
    }

    override fun openColorItem(colorId: Int) {
        val action = ColorsFragmentDirections.actionFirstFragmentToSecondFragment(colorId)
        findNavController().navigate(action)
    }

    private fun getColors() {
        viewModel.databaseInitalize.observe(viewLifecycleOwner, { isDatabaseInitialized ->
            if (isDatabaseInitialized) {
                lifecycleScope.launch {
                    viewModel.colors.collectLatest { data ->
                        hideProgressBar()
                        adapter.submitData(data)
                    }
                }
            } else {
                Snackbar.make(
                    binding.root,
                    "Initializing database, please wait...",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        })
    }

    private fun showProgressBar() {
        binding.pbColors.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.pbColors.visibility = View.GONE
    }
}

interface ColorItemListener {
    fun openColorItem(colorId: Int)
}
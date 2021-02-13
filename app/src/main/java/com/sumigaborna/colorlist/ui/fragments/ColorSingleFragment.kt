package com.sumigaborna.colorlist.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.sumigaborna.colorlist.databinding.FragmentColorSingleBinding
import com.sumigaborna.colorlist.ui.ColorsViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

class ColorSingleFragment : Fragment() {

    private lateinit var binding: FragmentColorSingleBinding
    private val args: ColorSingleFragmentArgs by navArgs()
    private val viewModel by sharedViewModel<ColorsViewModel>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.color.observe(viewLifecycleOwner, {
            binding.ivColor.setBackgroundColor(it.color)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentColorSingleBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }

        viewModel.getColorById(args.colorId)


    }
}
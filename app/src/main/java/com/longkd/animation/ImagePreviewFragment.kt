package com.longkd.animation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.longkd.animation.databinding.FragmentImagePreviewBinding

/**
 * @Author: longkd
 * @Since: 10:04 - 11/11/2023
 */
class ImagePreviewFragment : Fragment() {
    private lateinit var binding: FragmentImagePreviewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentImagePreviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnTake.setOnClickListener {
            binding.imgDemo.takePhoto()
        }
        binding.imgDemo.blockClickUser = {
            binding.btnTake.isClickable = it
        }
    }
}
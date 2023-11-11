package com.longkd.animation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.longkd.animation.databinding.FragmentRectangleBinding

/**
 * @Author: longkd
 * @Since: 10:04 - 11/11/2023
 */
class RectangleFragment : Fragment() {
    private lateinit var binding: FragmentRectangleBinding
    private var currentPos = 0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentRectangleBinding.inflate(inflater, container, false)
        initData()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClick()
    }

    private fun initData() {
        binding.rectangle.updateData(currentPos)
    }

    private fun initClick() {
        binding.btnShuffle.setOnClickListener {
            when (currentPos) {
                0 -> currentPos = 1
                1 -> currentPos = 2
                2 -> currentPos = 0
            }
            binding.rectangle.updateData(currentPos)
        }
    }
}
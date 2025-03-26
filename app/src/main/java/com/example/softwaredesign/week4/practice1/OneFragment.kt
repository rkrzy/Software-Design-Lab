package com.example.softwaredesign.week4.practice1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.softwaredesign.R

class OneFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // TODO : inflater.inflate를 사용하여 fragment_one.xml을 뷰로 반환
        return inflater.inflate(R.layout.week4_ex1_fragment_bind, container, false)
    }
    // TODO : inflater.inflate를 사용하여 fragment_one.xml을 뷰로 반환
}

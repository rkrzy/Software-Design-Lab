package com.example.softwaredesign.week4.practice5

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import com.example.softwaredesign.R

class MainFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.week4_ex5_fragment_main, container, false)

        // ✅ TODO: fragment_main.xml에 있는 버튼을 가져와서 fragButton에 저장
        val fragButton = view.findViewById<AppCompatButton>(R.id.frag_btn)

        val fragmentManager = requireActivity().supportFragmentManager
        var onClick = false

        fragButton.setOnClickListener {
            val transaction = fragmentManager.beginTransaction()
            if (onClick) {
                onClick = false
                // ✅ TODO: fragmentManager를 사용하여 fragment를 제거
                val frag = fragmentManager.findFragmentByTag("inner")
                if (frag != null) {
                    transaction.remove(frag)
                }
            } else {
                onClick = true
                // ✅ TODO: fragmentManager를 사용하여 fragment를 추가
                transaction.add(R.id.fragment_content, Onefragment(), "inner")
            }
            transaction.commit()
        }

        return view
    }
}

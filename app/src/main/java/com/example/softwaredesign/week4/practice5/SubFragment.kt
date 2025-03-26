package com.example.softwaredesign.week4.practice5

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.softwaredesign.R

class SubFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.week4_ex5_fragment_sub, container, false)

        // ✅ TODO: 데이터 생성, item 개수는 20개
        val datas = mutableListOf<String>()
        for (i in 1..20) {
            datas.add("Item $i")
        }

        // ✅ TODO: findViewById를 이용하여 recyclerView 가져오기
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)

        // ✅ TODO: recyclerView의 layout manager, adapter, item decoration 설정
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = MyAdapter(datas)
        recyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

        return view
    }
}

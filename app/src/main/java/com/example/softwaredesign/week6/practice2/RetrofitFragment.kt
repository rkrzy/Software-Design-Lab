package com.example.softwaredesign.week6.practice2

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.softwaredesign.databinding.Week6Ex2FragmentRetrofitBinding
import com.example.softwaredesign.week6.practice2.model.PageListModel
import com.example.softwaredesign.week6.practice2.recycler.MyAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RetrofitFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = Week6Ex2FragmentRetrofitBinding.inflate(inflater, container, false)

        //인터페이스 기반 Callable 객
        val call: Call<PageListModel> = MyApplication.networkService.getList(
            MyApplication.QUERY,
            MyApplication.API_KEY,
            1,
            10
        )

        //enqueue로 통신 수
        call?.enqueue(object : Callback<PageListModel> {
            override fun onResponse(
                call: Call<PageListModel>,
                response: Response<PageListModel>
            ) {

                //콜백 데이터 처리
                if (response.isSuccessful()) {
                    val newsList = response.body()?.articles
                    binding.retrofitRecyclerView.layoutManager = LinearLayoutManager(activity)
                    binding.retrofitRecyclerView.adapter = MyAdapter(requireContext(), newsList)
                }
            }

            //콜백 실패시 데이터 처리
            override fun onFailure(
                call: Call<PageListModel?>,
                t: Throwable
            ) {
            }
        })
        return binding.root
    }
}
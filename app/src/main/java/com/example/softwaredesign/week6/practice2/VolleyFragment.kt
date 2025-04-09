package com.example.softwaredesign.week6.practice2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.softwaredesign.databinding.Week6Ex2FragmentVolleyBinding
import com.example.softwaredesign.week6.practice2.recycler.MyAdapter

import org.json.JSONObject


class VolleyFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = Week6Ex2FragmentVolleyBinding.inflate(inflater, container, false)

        val url =
            MyApplication.BASE_URL + "/v2/everything?q=${MyApplication.QUERY}&apiKey=${MyApplication.API_KEY}&page=1&pageSize=5"

        val queue = Volley.newRequestQueue(activity)
        val jsonRequest =
            object : JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                Response.Listener<JSONObject> {
                    val datas = mutableListOf<ItemModel>()
                    val articles = it.getJSONArray("articles")
                    for (i in 0 until articles.length()) {
                        val article = articles.getJSONObject(i)
                        val item = ItemModel().apply {
                            title = article.getString("title")
                            author = article.optString("author", "Unknown")
                            description = article.optString("description", "")
                            urlToImage = article.optString("urlToImage", "")
                            publishedAt = article.optString("publishedAt", "")
                        }
                        datas.add(item)
                    }

                    binding.volleyRecyclerView.layoutManager = LinearLayoutManager(activity)
                    binding.volleyRecyclerView.adapter = MyAdapter(requireContext(), datas)
                },

                //Request 에러 처리
                Response.ErrorListener { error -> println("error............$error") }){
                override fun getHeaders(): MutableMap<String, String> {
                    val map = mutableMapOf<String, String>(
                        "User-agent" to MyApplication.USER_AGENT
                    )
                    return map
                }
            }

        //Request 전송
        queue.add(jsonRequest)
        return binding.root

    }

}

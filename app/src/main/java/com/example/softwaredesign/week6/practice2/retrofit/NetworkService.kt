package com.example.softwaredesign.week6.practice2

import com.example.softwaredesign.week6.practice2.model.PageListModel

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

//Retrofit Query 구현
interface NetworkService {
    @GET("/v2/everything")
    fun getList(
        @Query("q") q: String?,
        @Query("apiKey") apiKey: String?,
        @Query("page") page: Long,
        @Query("pageSize") pageSize: Int
    ): Call<PageListModel>
}


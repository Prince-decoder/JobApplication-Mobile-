package com.ashu.jobapplication.Service

import com.ashu.jobapplication.Model.JobResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private val reterofit = Retrofit.Builder()
    .baseUrl("http://10.0.2.2:8080/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val ReteroService = reterofit.create(ApiService::class.java)

interface ApiService {
    @GET("jobPosts")
    suspend fun getAllJobs(): JobResponse
}
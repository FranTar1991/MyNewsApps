package com.example.android.mynewsapp.dataLayer.retrofit

import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


private const val BASE_URL="https://newsapi.org/v2/"
private val moshi = Moshi.Builder().build()

private val retrofitServiceCreator = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(
    MoshiConverterFactory.create(moshi)).build()

interface RetrofitService{

    @Headers("X-Api-Key: 76c00c7b59784dd6bf406ed80690beef")
    @GET("top-headlines")
    suspend fun getArticlesFromNetwork(@Query("country") country: String): NetworkedArticleContainer
}

object RetrofitObject{

val RETROFIT_SERVICE_OBJECT: RetrofitService by lazy {
    retrofitServiceCreator.create(RetrofitService::class.java)
}

}
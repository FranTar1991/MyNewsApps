package com.example.android.mynewsapp.allFragments.dataLayer.retrofit

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Source(val id: String? = "id", val name: String? = "name")

@JsonClass(generateAdapter = true)
data class NetworkArticle(val source: Source? = Source(),
                          val author: String? = "no author",
                          val title: String? = "no title",
                          val description: String? = "no description",
                          val url: String? = "",
                          val urlToImage: String? = "",
                          val publishedAt: String? = "",
                          val content: String? = "no content")

@JsonClass(generateAdapter = true)
data class NetworkedArticleContainer(val status: String,
                                     val totalResults: Int,
                                     val articles: Array<NetworkArticle>)
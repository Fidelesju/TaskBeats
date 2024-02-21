package com.comunidadedevspace.taskbeats.data.remote

import com.comunidadedevspace.taskbeats.BuildConfig
import retrofit2.http.GET

interface NewsService {
    //https://api.thenewsapi.com/v1/news/headlines?locale=us&language=en&api_token=uFK08h67qirvUcofXaNqwktX9jHxJDPHK9J18sZU
    @GET("top?api_token=${BuildConfig.API_KEY}&locale=us")
    suspend fun fetchTopNews(): NewsResponse
}
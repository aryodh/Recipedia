package id.ac.ui.cs.mobileprogramming.aryodh.recipedia.network

import id.ac.ui.cs.mobileprogramming.aryodh.recipedia.room.Chef
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class NetworkConfig  {
    private fun getInterceptor() : OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }
    private fun getRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://my-json-server.typicode.com/aryodh/demo/")
            .client(getInterceptor())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    fun getService() = getRetrofit().create(ChefInterface::class.java)
}

interface ChefInterface {
    @GET("chef")
    fun getTips(): Call<List<Chef>>
}
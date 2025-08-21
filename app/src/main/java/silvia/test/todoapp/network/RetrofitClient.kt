package silvia.test.todoapp.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitClient {
    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BASIC
    }

    private val okHttp = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

//    val api: ApiService by lazy {
//        Retrofit.Builder()
//            .baseUrl("https://jsonplaceholder.typicode.com/")
//            .client(okHttp)
//            .addConverterFactory(MoshiConverterFactory.create())
//            .build()
//            .create(ApiService::class.java)
//    }

    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .client(okHttp)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(ApiService::class.java)
    }
}
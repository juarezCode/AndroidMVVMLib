package juarez.roberto.mylibrary.api


import android.util.Log
import juarez.roberto.mylibrary.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object WebService {
    private val client = OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
        level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    })
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(AuthTokenInterceptor())
        .build()

    fun service(): IMasterApi {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(IMasterApi::class.java)
    }
}

class AuthTokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
//        val request: Request = chain.request()
//        val response = chain.proceed(request)
//        Log.d("code", response.code.toString())
//        return response
        val originalRequest = chain.request()
        val requestBuilder = originalRequest.newBuilder()
            .header("Authorization", "AuthToken")
            .build()
        Log.d("requestBuilder", requestBuilder.toString())
        return chain.proceed(requestBuilder)
    }
}
package juarez.roberto.mylibrary.api

import okhttp3.Interceptor
import okhttp3.Response

class RefreshInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val accessToken = ""//our access Token
        val request = chain.request().newBuilder()
            .addHeader("Authorization", accessToken)
            .build()
        val response = chain.proceed(request)

        if (response.code == 401) {
            val newToken: String = ""//fetch from some other source
            if (newToken != null) {
                val newRequest = chain.request().newBuilder()
                    .addHeader("Authorization", newToken)
                    .build()
                return chain.proceed(newRequest)
            }
        }

        return response
    }
}
package juarez.roberto.mylibrary.api

import juarez.roberto.mylibrary.models.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface IMasterApi {

    @Headers("foo: bar")
    @GET("users")
    suspend fun getUsers(): Response<List<User>>
}
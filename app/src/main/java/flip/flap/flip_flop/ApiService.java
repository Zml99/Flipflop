package flip.flap.flip_flop;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

interface ApiService {

    @GET(value = "posts/")
    fun getAllPosts(): Call<List<POST>>

    @GET(value = "posts/{id}")
    fun getPostById(@Path(value = "id") id: Int): Call<POST>

    @POST("posts/{id}")
    fun editPostById(@Path(value = "id") id: Int, @Body POST: POST?): Call<POST>
}

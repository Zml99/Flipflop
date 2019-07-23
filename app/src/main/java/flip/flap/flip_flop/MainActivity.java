package flip.flap.flip_flop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create<ApiService>(ApiService::class.java);

        getAllPosts();
        getPostById();
        editPost();
    }

    fun getAllPosts(){

        service.getAllPosts().enqueue(Object: Callback<List<POST>>{
            override fun onResponse(call: Call<List<POST>>?, response: Response<List<POST>>?) {
                val posts = response?.body()
                Log.i(TAG_LOGS, Gson().toJson(posts))
            }
            override fun onFailure(call: Call<List<POST>>?, t: Throwable?) {
                t?.printStackTrace()
            }
        })
    }

    fun getPostById(){
        var post: Post? = null
        service.getPostById(1).enqueue(object: Callback<Post>{
            override fun onResponse(call: Call<Post>?, response: Response<Post>?) {
                post = response?.body()
                Log.i(TAG_LOGS, Gson().toJson(post))
            }
            override fun onFailure(call: Call<Post>?, t: Throwable?) {
                t?.printStackTrace()
            }
        })
    }

    fun editPost(){
        var post: Post? = Post(1, 1, "Hello k", "body")
        //Editamos los datos por POST
        service.editPostById(1, post).enqueue(Object: Callback<POST>{
            override fun onResponse(call: Call<POST>?, response: Response<POST>?) {
                post = response?.body()
                Log.i(TAG_LOGS, Gson().toJson(post))
            }
            override fun onFailure(call: Call<Post>?, t: Throwable?) {
                t?.printStackTrace()

            }
        })
    }
}


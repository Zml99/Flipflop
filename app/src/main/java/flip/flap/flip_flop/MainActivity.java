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

        getPosts();

    }

    private void getPosts() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://my-json-server.typicode.com/adissonfeliz/APIFAKE/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService postService = retrofit.create(ApiService.class);
        Call<List<Post>> call = postService.getPost();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                for(Post post : response.body()) {
                    titles.add(post.getTitle());
                }
                arrayAdapter.notifyDataSetInvalidated();
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
            }
        });
    }


}


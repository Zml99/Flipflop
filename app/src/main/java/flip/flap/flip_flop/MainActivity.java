package flip.flap.flip_flop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends Activity {

    public GridView gridView;
    int[] cards = { R.drawable.animals, R.drawable.pokemon_card, R.drawable.sports, R.drawable.superstars};
    String[] text = {"Preparate para la aventura por la salvaje selva. Identifica a todos los animales y se el gurú de la naturaleza!",
            "¡Identifícalos a todos! Prepárate a adivinar las diferentes especies de Pokémon, " +
            "desde el famoso Pickachu hasta los que nunca habías oido mencionar",
    "Ha iniciado el partido. Es hora de ejercitarte junto a tus atletas preferidos. Trata de reconocer a todos en esta entretenida partida.",
    "Caminando por la alfombra roja está....Describe a tus amigos esa super estrella de Holliwood que todos conocen y rie con ellos."};
    int[] logos = {R.drawable.animals_png, R.drawable.pokemon_logo, R.drawable.sports_png, R.drawable.superstar};
    String[] color = {"#FFFFFF", "#E51717", "#FFFFFF", "#E5B813"};

    ArrayList<String> titles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getPosts();
        //To hide navigation bar and notifications bar.
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        gridView = findViewById(R.id.gridcards);
        //Agregar elementos al gridview
        GridAdapter gridAdapter = new GridAdapter(this, cards);
        //Asignando el Adapter del Gridview
        gridView.setAdapter(gridAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), Pop.class);
                intent.putExtra("Cards", cards[position]);
                intent.putExtra("words", text[position]);
                intent.putExtra("logos", logos[position]);
                intent.putExtra("btncolors", color[position]);
                intent.putExtra("posts", titles);
                startActivityForResult(intent, 0);
            }
        });
    }

    private void getPosts() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://my-json-server.typicode.com/Zml99/fake_API/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService postService = retrofit.create(ApiService.class);
        Call<List<Post>> call = postService.getPost();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response <List<Post>> response) {
                for(Post post : response.body()) {
                    titles.add(post.getTitle());
                }
                //arrayAdapter.notifyDataSetInvalidated();
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
            }
        });
    }


}


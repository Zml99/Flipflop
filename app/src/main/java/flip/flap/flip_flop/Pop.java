package flip.flap.flip_flop;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.util.DisplayMetrics;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Pop extends Activity {

    //private ImageView imageView;
    RelativeLayout rl;
    private TextView txt;
    private ImageView img;
    int[] cards = { R.drawable.animals, R.drawable.pokemon_card, R.drawable.sports, R.drawable.superstars};

    private Button btnjugar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.popwindow);

        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        rl = findViewById(R.id.popwindow);
        txt = findViewById(R.id.txtText);
        img = findViewById(R.id.imgvLogo);

        btnjugar = findViewById(R.id.btnjugar);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        if (b != null){
            rl.setBackgroundResource(b.getInt("Cards"));
            rl.getBackground().setAlpha(150);
            txt.setText(b.getString("words"));
            img.setImageResource(b.getInt("logos"));
            btnjugar.setBackgroundColor(Color.parseColor(b.getString("btncolors")));
           btnjugar.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent intent_game = new Intent(Pop.this, GameActivity.class);
                   startActivity(intent_game);
               }
           });
        }

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = (int)(dm.widthPixels * 0.8);
        int height = (int)(dm.heightPixels* 0.8);

        getWindow().setLayout(width, height);


    }
}

package flip.flap.flip_flop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Conteo extends AppCompatActivity {

    TextView v;
    TextView f;
    Button btnvol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conteo);

        f = findViewById(R.id.txtf);
        v = findViewById(R.id.txtv);
        btnvol = findViewById(R.id.button);

        final Intent intent = getIntent();
        Bundle b = intent.getExtras();
        if (b != null){
              f.setText(String.valueOf(b.getInt("verdadero")));
              v.setText(String.valueOf(b.getInt("false")));
        }

        btnvol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}

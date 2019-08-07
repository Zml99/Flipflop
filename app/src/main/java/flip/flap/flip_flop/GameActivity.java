package flip.flap.flip_flop;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;


public class GameActivity extends AppCompatActivity {


    public void testMethod(){
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        //aqui iba el codigo del acelerometro...

        // Codigo para el giroscopio
        Sensor gyroscopeSensor =
                sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE_UNCALIBRATED);


        // El listener del giroscopio
        SensorEventListener gyroscopeSensorListiner = new SensorEventListener(){
            @Override
            public void onSensorChanged(SensorEvent sensorEvent){
                gact = findViewById(R.id.gactivity);
                if(sensorEvent.values[1] > 12.0f)
                {
                    random = al.nextInt(lista.size()-1);
                    ranitem = lista.get(random);
                    words = findViewById(R.id.txtwords);
                    words.setText(ranitem);
                    // anticlockwise
                    gact.setBackgroundColor(Color.YELLOW);
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            // yourMethod();
                            gact.setBackgroundColor(Color.BLUE);
                        }
                    }, 500);
                    countf += 1;
                }
                else if(sensorEvent.values[1] < -10.0f)
                {
                    random = al.nextInt(lista.size());
                    ranitem = lista.get(random);
                    words = findViewById(R.id.txtwords);
                    words.setText(ranitem);
                    // clockwise
                    gact.setBackgroundColor(Color.GREEN);
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            // yourMethod();
                            gact.setBackgroundColor(Color.BLUE);
                        }
                    }, 500);
                    countt += 1;
                }
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int i){}
            public Handler handler = new Handler();
        };
        //Registro del Listener
        sensorManager.registerListener(gyroscopeSensorListiner, gyroscopeSensor, SensorManager.SENSOR_DELAY_GAME);



    }
    Random al = new Random();
    RelativeLayout gact;
    ArrayList<String> lista;
    TextView words;
    int random;
    String ranitem;
    private int continic = 5;
    private int contgame = 60;
    TextView timer;
    int countt = 0;
    int countf = 0;

    private  void startGameTimer(){
        new CountDownTimer(61_000, 1_000){



            @Override
            public void onTick(long millisUntilFinished) {
                String remainigTimeText = "Tiempo Restante:";
                timer = findViewById(R.id.txttimer);
                timer.setText(remainigTimeText+ " " + String.valueOf(contgame));
                contgame--;
            }

            @Override
            public void onFinish() {
                timer.setVisibility(View.INVISIBLE);
                Intent intent = new Intent(getApplicationContext(), Conteo.class);
                intent.putExtra("verdadero", countt);
                intent.putExtra("false", countf);
                startActivity(intent);
                finish();
            }
        }.start();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        if (b != null){
            lista = b.getStringArrayList("posts");
        }

        testMethod();
        startGameTimer();
    }
}

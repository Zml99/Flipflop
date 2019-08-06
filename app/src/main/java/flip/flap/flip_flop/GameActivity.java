package flip.flap.flip_flop;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;


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
                if(sensorEvent.values[1] > 15.0f)
                {
                    // anticlockwise
                    gact.setBackgroundColor(Color.YELLOW);
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            // yourMethod();
                            gact.setBackgroundColor(Color.BLUE);
                        }
                    }, 800);
                }
                else if(sensorEvent.values[1] < -10.0f)
                {
                    // clockwise
                    gact.setBackgroundColor(Color.GREEN);
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            // yourMethod();
                            gact.setBackgroundColor(Color.BLUE);
                        }
                    }, 800);
                }
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int i){}
            public Handler handler = new Handler();
        };
        //Registro del Listener
        sensorManager.registerListener(gyroscopeSensorListiner, gyroscopeSensor, SensorManager.SENSOR_DELAY_GAME);



    }

    RelativeLayout gact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        testMethod();
    }
}

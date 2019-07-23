package flip.flap.flip_flop;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class GameActivity extends AppCompatActivity {


    public void testMethod(){
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        //aqui iba el codigo del acelerometro...

        // Codigo para el giroscopio
        Sensor gyroscopeSensor =
                sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        // El listener del giroscopio
        SensorEventListener gyroscopeSensorListiner = new SensorEventListener(){
            @Override
            public void onSensorChanged(SensorEvent sensorEvent){

                if(sensorEvent.values[2] > 0.5f) { // anticlockwise
                    getWindow().getDecorView().setBackgroundColor(Color.BLUE);
                } else if(sensorEvent.values[2] < -0.5f) { // clockwise
                    getWindow().getDecorView().setBackgroundColor(Color.YELLOW);}
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int i){}
        };
        //Registro del Listener

        sensorManager.registerListener(gyroscopeSensorListiner,
                gyroscopeSensor, 2*1000*1000);



    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        testMethod();
    }
}

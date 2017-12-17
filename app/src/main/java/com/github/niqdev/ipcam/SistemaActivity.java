package com.github.niqdev.ipcam;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.github.niqdev.mjpeg.Mjpeg;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

public class SistemaActivity extends AppCompatActivity{

    private static final int TIMEOUT = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sistema);

        TextView control =(TextView)findViewById(R.id.tx_username2);
        control.setText("Control de cerradura");

        ImageButton cerraduraU = (ImageButton) findViewById(R.id.cerraduraU_btn);
        ImageButton cerraduraL = (ImageButton) findViewById(R.id.cerraduraL_btn);

        ImageButton openDoor = (ImageButton) findViewById(R.id.opendoor_btn);
        ImageButton closeDoor = (ImageButton) findViewById(R.id.closedoor_btn);

        openDoor.setVisibility(View.INVISIBLE);

        SharedPreferences prefs = getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = prefs.edit();

        cerraduraU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                control.setText("Control de cerradura");

                Mjpeg.newInstance()
                        .open("http://192.168.1.125:1880/activation/false", TIMEOUT).subscribe();

                cerraduraL.setVisibility(View.VISIBLE);
                cerraduraU.setVisibility(View.INVISIBLE);


                closeDoor.setVisibility(View.VISIBLE);
                openDoor.setVisibility(View.INVISIBLE);

            }
        });

        cerraduraL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Mjpeg.newInstance()
                        .open("http://192.168.1.125:1880/activation/true", TIMEOUT).subscribe();

                control.setText("");

                cerraduraL.setVisibility(View.INVISIBLE);
                cerraduraU.setVisibility(View.VISIBLE);



                closeDoor.setVisibility(View.INVISIBLE);
                openDoor.setVisibility(View.INVISIBLE);



            }
        });

        String string = prefs.getString("flag","");

        closeDoor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Mjpeg.newInstance()
                        .open("http://192.168.1.125:1880/cerradura/1", TIMEOUT).subscribe();

                control.setText("Control de cerradura");

                closeDoor.setVisibility(View.INVISIBLE);
                openDoor.setVisibility(View.VISIBLE);


                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Do something after 5s = 5000ms
                        openDoor.setVisibility(View.INVISIBLE);
                        closeDoor.setVisibility(View.VISIBLE);
                    }
                }, 3000);

            }
        });


    }



}

package com.github.niqdev.ipcam;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.niqdev.mjpeg.DisplayMode;
import com.github.niqdev.mjpeg.Mjpeg;
import com.github.niqdev.mjpeg.MjpegView;
import com.github.niqdev.mjpeg.OnFrameCapturedListener;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

public class LlamadaActivity extends AppCompatActivity{

    private static final int TIMEOUT = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_llamada);

        ImageButton cerraduraU = (ImageButton) findViewById(R.id.cerraduraU_btn);
        ImageButton cerraduraL = (ImageButton) findViewById(R.id.cerraduraL_btn);

        ImageButton openDoor = (ImageButton) findViewById(R.id.opendoor_btn);
        ImageButton closeDoor = (ImageButton) findViewById(R.id.closedoor_btn);



        SharedPreferences prefs = getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = prefs.edit();

        cerraduraU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

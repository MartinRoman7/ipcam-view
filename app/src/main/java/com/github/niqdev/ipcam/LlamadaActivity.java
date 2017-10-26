package com.github.niqdev.ipcam;

import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
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

public class LlamadaActivity extends AppCompatActivity{

    private static final int TIMEOUT = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_llamada);





        ImageButton cerradura = (ImageButton) findViewById(R.id.cerradura_btn);
        cerradura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


               /* URL url;
                HttpURLConnection urlConnection = null;
                try {
                    url = new URL("http://192.168.1.70:1880/activation/true");

                    urlConnection = (HttpURLConnection) url.openConnection();



                    String codigoRespuesta = Integer.toString(urlConnection.getResponseCode());

                    if(codigoRespuesta.equals("200")){

                        finish();
                        System.exit(0);

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                }*/

                Mjpeg.newInstance()
                        .open("http://192.168.1.70:1880/activation/true", TIMEOUT).subscribe();

            }
        });

    }



    }


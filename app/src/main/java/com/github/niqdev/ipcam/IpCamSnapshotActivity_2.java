package com.github.niqdev.ipcam;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.niqdev.mjpeg.DisplayMode;
import com.github.niqdev.mjpeg.Mjpeg;
import com.github.niqdev.mjpeg.MjpegView;
import com.github.niqdev.mjpeg.OnFrameCapturedListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IpCamSnapshotActivity_2 extends AppCompatActivity {

    private Button doVideoCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videocall);

        doVideoCall = (Button) findViewById(R.id.btn_videocall);
        doVideoCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoCall();
            }
        });
    }

    private void videoCall() {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://appr.tc/r/437814897"));
        startActivity(browserIntent);
    }


}
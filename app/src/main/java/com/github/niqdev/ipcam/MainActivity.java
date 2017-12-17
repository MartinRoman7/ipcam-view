package com.github.niqdev.ipcam;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.annotation.BinderThread;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.github.niqdev.ipcam.Fragments.login.LoginFragment;
import com.github.niqdev.ipcam.Fragments.login.RegistroFragment_2;
import com.github.niqdev.ipcam.settings.SettingsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.buttonCamera1)
    Button buttonSnapshot;

    @BindView(R.id.buttonCamera2)
    Button buttonSnapshot_2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_final);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String name = preferences.getString(LoginActivity.USER,"");

        TextView bienvenido =(TextView)findViewById(R.id.tx_username1);

        bienvenido.setText("Bievenido "+name);


        ButterKnife.bind(this);


/*
        ImageButton settings = (ImageButton) findViewById(R.id.setting_btn);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction()
                .add(R.id.contenedor, new RegistroFragment_2()).commit();
            }
        });
*/
        Button csesion = (Button) findViewById(R.id.btn_csesion);
        csesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                System.exit(0);
            }
        });


        // load default values first time
        PreferenceManager.setDefaultValues(this, R.xml.pref_general, false);
        //verifySettings();
    }

    private void verifySettings() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (TextUtils.isEmpty(prefs.getString(SettingsActivity.PREF_IPCAM_URL, ""))) {
            buttonSnapshot.setEnabled(false);
        }

        // TODO disabled
        buttonSnapshot.setEnabled(false);
    }


    @OnClick(R.id.buttonCamera1)
    public void onClickSnapshot() {
        startActivity(new Intent(this, IpCamSnapshotActivity.class));
    }

    @OnClick(R.id.buttonCameraOutput)
    public void onClickSnapshot_Output() {
        startActivity(new Intent(this, IpCamSnapshotActivityOutput.class));
    }

    @OnClick(R.id.buttonCamera2)
    public void onClickSnapshot_2() {
        startActivity(new Intent(this, IpCamSnapshotActivity_2.class));
    }

    @OnClick(R.id.buttonSistema)
    public void onClickLlamada() {
        startActivity(new Intent(this, SistemaActivity.class));
    }

    @OnClick(R.id.contact_btn)
    public void onClickContact() { startActivity(new Intent(this, Contacts.class)); }

    @OnClick(R.id.buttonHelp)
    public void onClickHelp() { startActivity(new Intent(this, AyudaActivity.class)); }

    @OnClick(R.id.setting_btn)
    public void onClickRegistro(){ startActivity(new Intent(this, RegistroActivity.class));}

    @OnClick(R.id.btn_csesion)
    public void onClickCSession(View v) {
        finish();
        System.exit(0);
    }


}

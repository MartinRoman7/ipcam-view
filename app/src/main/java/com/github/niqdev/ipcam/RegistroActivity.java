package com.github.niqdev.ipcam;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.niqdev.ipcam.Fragments.login.LoginFragment;
import com.github.niqdev.ipcam.Fragments.login.RegistroFragment_2;

/**
 * Created by ctin on 03/11/17.
 */

public class RegistroActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_content_layout);

        this.getFragmentManager()
                .beginTransaction()
                .add(R.id.contenedor,new RegistroFragment_2())
                .commit();

    }
}

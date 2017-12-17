package com.github.niqdev.ipcam;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.github.niqdev.mjpeg.Mjpeg;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

public class AddContactActivity extends AppCompatActivity{

    public static final String Contact = "contact";

    private EditText name;
    private EditText number;
    private Button aceptar;
    private Button cancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_contact_layout);

        name = (EditText) findViewById(R.id.name_contact);
        number = (EditText) findViewById(R.id.number_contact);

        aceptar =(Button) findViewById(R.id.aceptar_contact);
        cancelar = (Button) findViewById(R.id.cancelar_contact);

        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String nombre=name.getText().toString();
                String numero=number.getText().toString();

                if(! (nombre.equals("") && numero.equals(""))) {

                    SharedPreferences.Editor editor = getSharedPreferences(Contact, MODE_PRIVATE).edit();

                    editor.putString("name_contact", nombre);
                    editor.putString("number_contact",numero);
                    editor.apply();

                    // Obtener valores
                    //SharedPreferences prefs = getSharedPreferences(Contact, MODE_PRIVATE);
                    //String outputname = prefs.getString("name_contact","");

                    //Log.i("Nombre: ",outputname);

                    startActivity(new Intent(AddContactActivity.this, MainActivity.class));
                }
                else{
                AlertDialog.Builder builder = new AlertDialog.Builder(AddContactActivity.this);
                builder.setTitle("Alerta")
                        .setMessage("Campos vacios")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
                }

            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddContactActivity.this, Contacts.class));
            }
        });


    }




}

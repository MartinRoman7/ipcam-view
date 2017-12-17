package com.github.niqdev.ipcam;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.StringBuilderPrinter;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.Manifest;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ctin on 03/11/17.
 */

public class Contacts extends AppCompatActivity {

    public static final String Contact = "contact";

    private Button call911;
    private Button callCruz;
    private Button callBomberos;
    private Button callContact;
    private  Button addContact;
    private TextView listContacts;

    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;
    private static final int PERMISSIONS_REQUEST_CALL_PHONE_911 = 1;
    private static final int PERMISSIONS_REQUEST_CALL_PHONE_CRUZ = 1;
    private static final int PERMISSIONS_REQUEST_CALL_PHONE_BOMBEROS = 1;
    private static final int PERMISSIONS_REQUEST_CALL_PHONE_CONTACT = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacts_layout);

        listContacts = (TextView) findViewById(R.id.listContacts);
        call911 = (Button) findViewById(R.id.makeCall);
        callCruz =(Button)findViewById(R.id.Cruz);
        callBomberos = (Button)findViewById(R.id.Bomberos);
        callContact = (Button)findViewById(R.id.ContactosConfianza);
        addContact = (Button)findViewById(R.id.addContactosConfianza);

        loadContacts();

        SharedPreferences prefs = getSharedPreferences(Contact, MODE_PRIVATE);
        String name = prefs.getString("name_contact","");

        //Log.i("Nombre: ",name);

        callContact.setText(name);

        call911.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeCall911();
            }
        });

        callCruz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeCallCruz();
            }
        });

        callBomberos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeCallBomberos();
            }
        });

        callContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeCallContact();
            }
        });

        addContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Contacts.this,AddContactActivity.class));
            }
        });

    }

    private void makeCall911() {

        final String TAG = "Call";

        Log.i(TAG,"CALL");

        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:5511431791"));

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, PERMISSIONS_REQUEST_CALL_PHONE_911);
            Log.i(TAG,"IF ");
            return;
        }
        Log.i(TAG,"OUT ");
        startActivity(callIntent);
    }

    private void makeCallCruz() {

        final String TAG = "Call";

        Log.i(TAG,"CALL");

        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:5511431791"));

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, PERMISSIONS_REQUEST_CALL_PHONE_CRUZ);
            Log.i(TAG,"IF ");
            return;
        }
        Log.i(TAG,"OUT ");
        startActivity(callIntent);
    }

    private void makeCallBomberos() {

        final String TAG = "Call";

        Log.i(TAG,"CALL");

        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:5511431791"));

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, PERMISSIONS_REQUEST_CALL_PHONE_BOMBEROS);
            Log.i(TAG,"IF ");
            return;
        }
        Log.i(TAG,"OUT ");
        startActivity(callIntent);
    }

    private void makeCallContact() {

        SharedPreferences prefs = getSharedPreferences(Contact, MODE_PRIVATE);
        String numero = prefs.getString("number_contact","");

        final String TAG = "Call";

        Log.i(TAG,"CALL");

        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:"+numero));

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, PERMISSIONS_REQUEST_CALL_PHONE_911);
            Log.i(TAG,"IF ");
            return;
        }
        Log.i(TAG,"OUT ");
        startActivity(callIntent);
    }

    private void loadContacts() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
            //After this point you wait for callback in onRequestPermissionsResult(int, String[], int[]) overriden method
        } else {


            StringBuilder builder = new StringBuilder();
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)));

                if (hasPhoneNumber > 0) {
                    Cursor cursor2 = contentResolver.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id},
                            null);

                    while (cursor2.moveToNext()) {
                        String phoneNumber = cursor2.getString(cursor2.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        builder.append("Nombre: ").append(name).append("\n").append("Número: ").append(phoneNumber).append("\n\n");
                    }

                    cursor2.close();
                }
            }
        }

        cursor.close();
        listContacts.setText(builder.toString());
    }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted
                loadContacts();
            } else {
                Toast.makeText(this, "Until you grant the permission, we canot display the names", Toast.LENGTH_SHORT).show();
            }
        }

        if (requestCode == PERMISSIONS_REQUEST_CALL_PHONE_911) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted
                makeCall911();
            } else {
                Toast.makeText(this, "Until you grant the permission, we canot display the names", Toast.LENGTH_SHORT).show();
            }
        }

        if (requestCode == PERMISSIONS_REQUEST_CALL_PHONE_CRUZ) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted
                makeCallCruz();
            } else {
                Toast.makeText(this, "Until you grant the permission, we canot display the names", Toast.LENGTH_SHORT).show();
            }
        }

        if (requestCode == PERMISSIONS_REQUEST_CALL_PHONE_BOMBEROS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted
                makeCallBomberos();
            } else {
                Toast.makeText(this, "Until you grant the permission, we canot display the names", Toast.LENGTH_SHORT).show();
            }
        }
    }



}

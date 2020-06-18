package com.example.medicalfile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ClientActivity extends AppCompatActivity {

    Button mButtonContactMedic;
    Button mButtonFisaMedicala;
    Button mButtonLogout;
    Button mButtonInfo;
   TextView mTextViewName;
    DatabaseHelper db ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        mButtonInfo = (Button) findViewById(R.id.button_info);
        mButtonFisaMedicala = (Button) findViewById(R.id.button_fisaMedicala);
        mButtonContactMedic = (Button) findViewById(R.id.button_contactMedic);
        mButtonLogout = (Button) findViewById(R.id.button_logout);
        mTextViewName = (TextView)findViewById(R.id.edittext_TextClientName) ;

        SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = mPreferences.edit();


        String mail = mPreferences.getString(getString(R.string.mail), "");
        //String aux = setName(mail);
        mTextViewName.setText(mail);



        mButtonInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClientActivity.this, InfouserActivity.class);

                startActivity(intent);
            }
        });
        mButtonContactMedic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClientActivity.this, ContactMedicActivity.class);

                startActivity(intent);
            }
        });

        mButtonContactMedic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClientActivity.this, ContactMedicActivity.class);

                startActivity(intent);
            }
        });

        mButtonFisaMedicala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClientActivity.this, Fisa.class);

                startActivity(intent);
            }
        });

        mButtonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClientActivity.this, LoginActivity.class);
                startActivityForResult(intent,2);
            }
        });


    }

    private String setName(String mail) {
        String aux = "";
        Cursor data = db.getName(mail);
        while (data.moveToNext()) {
            //aux = "";
            aux = data.getString(1) ;//+ " " + data.getString(2);
        }
        return aux;
    }


}

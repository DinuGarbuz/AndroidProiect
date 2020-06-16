package com.example.medicalfile;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ClientActivity extends AppCompatActivity {

    Button mButtonContactMedic;
    Button mButtonFisaMedicala;
    Button mButtonLogout;
    Button mButtonInfo;
    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        mButtonInfo = (Button) findViewById(R.id.button_info);
        mButtonFisaMedicala = (Button) findViewById(R.id.button_fisaMedicala);
        mButtonContactMedic = (Button) findViewById(R.id.button_contactMedic);
        mButtonLogout = (Button) findViewById(R.id.button_logout);


        mButtonInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(ClientActivity.this, Fisa.class);

                startActivity(registerIntent);
            }
        });
        mButtonContactMedic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClientActivity.this, ContactMedicActivity.class);

                startActivity(intent);
            }
        });

        mButtonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClientActivity.this, LoginActivity.class);

                startActivity(intent);
            }
        });
    }
}

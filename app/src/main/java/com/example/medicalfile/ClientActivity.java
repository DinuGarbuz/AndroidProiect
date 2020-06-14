package com.example.medicalfile;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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

    Button buttonFisaMedicala;
    Button buttonInfoCloent;
    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        TextView name= findViewById(R.id.textView6);

        userName= this.getIntent().getStringExtra("COL_2");
        name.setText(userName);

        buttonFisaMedicala = (Button) findViewById(R.id.button1);
        buttonFisaMedicala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fisaMedicala = new Intent(ClientActivity.this, FisaMedicala.class);

                startActivity(fisaMedicala);
            }
        });

        buttonInfoCloent = (Button) findViewById(R.id.button3);
        buttonInfoCloent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent infoClient = new Intent(ClientActivity.this, infoClient.class);

                startActivity(infoClient);
            }
        });
    }


}
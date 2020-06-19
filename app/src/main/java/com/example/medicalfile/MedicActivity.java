package com.example.medicalfile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MedicActivity extends AppCompatActivity {

    Button mButtonLogout;
    Button mButtonInformatie;
    Button mButtonSetariCont;
    Button mButtonPacienti;
    DatabaseHelper db ;
    TextView mMedicName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medic);
        mMedicName = (TextView) findViewById(R.id.edittext_medicName);
        db = new DatabaseHelper(this);
        mButtonLogout = (Button) findViewById(R.id.button_logout);
        mButtonInformatie = (Button) findViewById(R.id.button_medicInfo);
        mButtonSetariCont = (Button) findViewById(R.id.button_setariCont);
        mButtonPacienti = (Button) findViewById(R.id.button_pacienti);


        SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = mPreferences.edit();

        String name = mPreferences.getString(getString(R.string.mail), "");
        mMedicName.setText(name);


        mButtonSetariCont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MedicActivity.this, MedicAccountActivity.class);

                startActivity(intent);
            }
        });

        mButtonInformatie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MedicActivity.this, InfoMedicActivity.class);

                startActivity(intent);
            }
        });

        mButtonPacienti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MedicActivity.this, ContactClientActivity.class);

                startActivity(intent);
            }
        });


        mButtonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MedicActivity.this, LoginActivity.class);
                startActivityForResult(intent,2);
            }
        });

    }
}


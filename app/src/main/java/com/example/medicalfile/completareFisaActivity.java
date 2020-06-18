package com.example.medicalfile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class completareFisaActivity extends AppCompatActivity {


    DatabaseHelper db;
    EditText mTextAge;
    EditText mTextSex;
    EditText mTextWeight;
    EditText mTextHeight;
    EditText mTextBlood;
    EditText mTextGeneticDiseases;
    EditText mTextAllergens;
    Button mButtonSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completare_fisa);

        db = new DatabaseHelper(this);
        mTextAge = (EditText)findViewById(R.id.edittext_age);
        //mTextSex = (EditText)findViewById(R.id.edittext_sex);
        mTextHeight = (EditText)findViewById(R.id.edittext_height);
        mTextWeight = (EditText)findViewById(R.id.edittext_weight);
        //mTextBlood = (EditText)findViewById(R.id.edittext_blood);
        mTextGeneticDiseases = (EditText)findViewById(R.id.edittext_geneticDiseases);
        mTextAllergens = (EditText)findViewById(R.id.edittext_allergens);
        mButtonSave = (Button) findViewById(R.id.button_save);

        SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = mPreferences.edit();

        final Spinner mSexSpinner = findViewById(R.id.spinner_sex);
        ArrayAdapter<String> myAdapter1 = new ArrayAdapter<String>(completareFisaActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.sex));
        myAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSexSpinner.setAdapter(myAdapter1);

        final Spinner mBloodSpinner = findViewById(R.id.spinner_blood);
        ArrayAdapter<String> myAdapter2 = new ArrayAdapter<String>(completareFisaActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.blood));
        myAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mBloodSpinner.setAdapter(myAdapter2);

        final String mail = mPreferences.getString(getString(R.string.mail), "");



        mButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String age = mTextAge.getText().toString().trim();
                String sex = mSexSpinner.getSelectedItem().toString().trim();
                String height = mTextHeight.getText().toString().trim();
                String weight = mTextWeight.getText().toString().trim();
                String blood = mBloodSpinner.getSelectedItem().toString().trim();
                String genetic= mTextGeneticDiseases.getText().toString().trim();
                String allergens= mTextAllergens.getText().toString().trim();



                int clientID = getID(mail);
                long val = db.addFisaMedicala(age, sex, height, weight, blood, genetic, allergens, clientID);

                Intent intent = new Intent(completareFisaActivity.this, LoginActivity.class);
                startActivity(intent);
            }

        });


    }
    private int getID(String mail)
    {
        Cursor data = db.getName(mail);
        String id = "";
        int aux = 0;
        while(data.moveToNext())
        {

            id =  data.getString(0);
             aux = Integer.parseInt(id);
            int a =aux;

        }
        return aux;

    }
}

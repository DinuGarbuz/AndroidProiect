package com.example.medicalfile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
        mTextSex = (EditText)findViewById(R.id.edittext_sex);
        mTextHeight = (EditText)findViewById(R.id.edittext_height);
        mTextWeight = (EditText)findViewById(R.id.edittext_weight);
        mTextBlood = (EditText)findViewById(R.id.edittext_blood);
        mTextGeneticDiseases = (EditText)findViewById(R.id.edittext_geneticDiseases);
        mTextAllergens = (EditText)findViewById(R.id.edittext_allergens);
        mButtonSave = (Button) findViewById(R.id.button_save);

        mButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String age = mTextAge.getText().toString().trim();
                String sex = mTextSex.getText().toString().trim();
                String height = mTextHeight.getText().toString().trim();
                String weight = mTextWeight.getText().toString().trim();
                String blood = mTextBlood.getText().toString().trim();
                String genetic= mTextGeneticDiseases.getText().toString().trim();
                String allergens= mTextAllergens.getText().toString().trim();



                long val = db.addFisaMedicala("Dinu", "Garbuz", age, sex, height, weight, blood, genetic, allergens, 1);

                Intent intent = new Intent(completareFisaActivity.this, ClientActivity.class);
                startActivity(intent);
            }

        });
    }
}

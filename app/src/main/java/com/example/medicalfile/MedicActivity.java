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
    Button mButtonInfo;
    DatabaseHelper db ;
    TextView mMedicName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medic);
        mMedicName = (TextView) findViewById(R.id.edittext_medicName);
        db = new DatabaseHelper(this);
        mButtonLogout = (Button) findViewById(R.id.button_logout);
        mButtonInfo = (Button) findViewById(R.id.button_info);

        populateListView();

        SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = mPreferences.edit();

        String name = mPreferences.getString(getString(R.string.mail), "");
        mMedicName.setText(name);


        mButtonInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MedicActivity.this, InfouserActivity.class);

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
    private void populateListView()
    {

       // Cursor data = db.getName(1);
     // mMedicName.setText(data.getString(1));



    }
}

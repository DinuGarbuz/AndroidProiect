package com.example.medicalfile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MedicActivity extends AppCompatActivity {

    DatabaseHelper db ;
    TextView mMedicName;
    ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medic);
        mMedicName = (TextView) findViewById(R.id.edittext_medicName);
        db = new DatabaseHelper(this);

        populateListView();

    }
    private void populateListView()
    {

       // Cursor data = db.getName(1);
     // mMedicName.setText(data.getString(1));



    }
}

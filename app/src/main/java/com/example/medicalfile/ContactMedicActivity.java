package com.example.medicalfile;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ContactMedicActivity extends AppCompatActivity {

    DatabaseHelper db ;

    private ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fisa);

        mListView = (ListView)findViewById(R.id.listView);
        db = new DatabaseHelper(this);

        populateListView();

    }

    private void populateListView()
    {

        Cursor data = db.getMedic();
        ArrayList<String> listData = new ArrayList<>();
        while(data.moveToNext())
        {
            String aux = "";
            aux = data.getString(1) + " " + data.getString(2) + " - "+ data.getString(5);

            listData.add(aux);
        }
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        mListView.setAdapter( adapter);


    }
}

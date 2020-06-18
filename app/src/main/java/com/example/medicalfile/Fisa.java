package com.example.medicalfile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Fisa extends AppCompatActivity {

    DatabaseHelper db ;
    EditText mTextMail;
    private ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fisa);

        mListView = (ListView)findViewById(R.id.listView);
        mTextMail= (EditText)findViewById(R.id.edittext_mail) ;
        db = new DatabaseHelper(this);

        SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = mPreferences.edit();


        String mail = mPreferences.getString(getString(R.string.mail), "");
        int id = Integer.parseInt(getID(mail));
        populateListView(id);
        //getID(mail);


    }

    private void populateListView(int id)
    {
//        mTextMail= (EditText)findViewById(R.id.edittext_mail) ;
//        String asd = mTextMail.getText().toString();
        Cursor data = db.getFisa(id);
        ArrayList<String> listData = new ArrayList<>();
        while(data.moveToNext())
        {
            String aux = "";
            aux = "Age:  " + data.getString(1);
            listData.add(aux);
            aux = "Sex:  " + data.getString(2);
            listData.add(aux);
            aux = "Hight:  " + data.getString(3);
            listData.add(aux);
            aux = "Weight:  " + data.getString(4);
            listData.add(aux);
            aux = "Blood:  " + data.getString(5);
            listData.add(aux);
            aux = "Genetic:  " + data.getString(6);
            listData.add(aux);
            aux = "Allergens:  " + data.getString(7);
            listData.add(aux);
        }
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        mListView.setAdapter( adapter);


    }

    private String getID(String mail)
    {
        Cursor data = db.getName(mail);
        //ArrayList<String> listData = new ArrayList<>();
        String id = "";
        while(data.moveToNext())
        {

            id =  data.getString(0);
            int aux = Integer.parseInt(id);
            int a =aux;


        }
      //  ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
      //  mListView.setAdapter( adapter);
        return id;

    }
}

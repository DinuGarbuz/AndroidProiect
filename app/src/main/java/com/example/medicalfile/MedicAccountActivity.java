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

public class MedicAccountActivity extends AppCompatActivity {

    DatabaseHelper db ;
    EditText mTextMail;
    private ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medic_account);
        mListView = (ListView)findViewById(R.id.listView);
        mTextMail= (EditText)findViewById(R.id.edittext_mail) ;
        db = new DatabaseHelper(this);


        SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = mPreferences.edit();


        String mail = mPreferences.getString(getString(R.string.mail), "");
        populateListView(mail);
    }
    private void populateListView(String mail)
    {
//        mTextMail= (EditText)findViewById(R.id.edittext_mail) ;
//        String asd = mTextMail.getText().toString();
        Cursor data = db.getMedicName(mail);
        ArrayList<String> listData = new ArrayList<>();
        while(data.moveToNext())
        {
            String aux = "";
            aux = "Name: " + data.getString(1);
            listData.add(aux);
            aux = "Last name: " + data.getString(2);
            listData.add(aux);
            aux = "Mail:  " + data.getString(4);
            listData.add(aux);
            aux = "Phone:  " + data.getString(5);
            listData.add(aux);
            aux = "Password:  " + data.getString(3);
            listData.add(aux);
        }
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        mListView.setAdapter( adapter);


    }
}

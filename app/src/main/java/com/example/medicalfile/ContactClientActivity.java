package com.example.medicalfile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactClientActivity extends AppCompatActivity {

    int Id;
    DatabaseHelper db ;
    private ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_client);

        // addMedicImages();
        mListView = (ListView)findViewById(R.id.listView);
        db = new DatabaseHelper(this);

        //populateListView(names, numbers);


        ContactClientActivity.CustomAdaptor customAdaptor = new ContactClientActivity.CustomAdaptor();
        mListView.setAdapter(customAdaptor);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), FisaPtMedicActivity.class);
                Id = position +1;
                intent.putExtra("id", Id);

                startActivity(intent);
            }
        });

    }



    class CustomAdaptor extends BaseAdapter {


        @Override
        public int getCount() {
            Cursor data = db.getClient();
            int i = 0;
            while(data.moveToNext()) {

                i= i+1;
            }

            return i;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            View v=getLayoutInflater().inflate(R.layout.customlayout,null);
            Cursor data = db.getClient();


            ArrayList<String> names = new ArrayList<String>();

            ArrayList<String> numbers = new ArrayList<String>();

            ArrayList<Integer> images = new ArrayList<>();

            ImageView mPoza = v.findViewById(R.id.imageView);
            TextView mNume = v.findViewById(R.id.nume);

            TextView mNrTel = v.findViewById(R.id.specialitatea);



            while(data.moveToNext()) {
                names.add(data.getString(1)+" "+ data.getString(2));
                numbers.add(data.getString(5));

                images.add(R.drawable.usericon1);
            }

            mPoza.setImageResource(images.get(position));
            mNume.setText(names.get(position));
            mNrTel.setText(numbers.get(position));



            return v;
        }

    }
}

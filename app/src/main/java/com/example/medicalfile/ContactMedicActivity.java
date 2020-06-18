package com.example.medicalfile;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactMedicActivity extends AppCompatActivity {

    DatabaseHelper db ;
    private ListView mListView;

   int[] images={R.drawable.medic};

//ArrayList<String> names = new ArrayList<String>();
//    ArrayList<String> numbers = new ArrayList<String>();


    public void addMedicImages()
    {
        Cursor data = db.getMedic();
        int i = 0;
        while(data.moveToNext()) {
            images[i] = R.drawable.medic;
            i++;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fisa);


       // addMedicImages();
        mListView = (ListView)findViewById(R.id.listView);
        db = new DatabaseHelper(this);

        //populateListView(names, numbers);


        CustomAdaptor customAdaptor = new CustomAdaptor();
        mListView.setAdapter(customAdaptor);
    }



    class CustomAdaptor extends BaseAdapter{


        @Override
        public int getCount() {
            Cursor data = db.getMedic();
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
            Cursor data = db.getMedic();

            ArrayList<String> names = new ArrayList<String>();
            ArrayList<String> numbers = new ArrayList<String>();
            ArrayList<Integer> images = new ArrayList<>();

            ImageView mPoza = v.findViewById(R.id.imageView);
            TextView mNume = v.findViewById(R.id.nume);
            TextView mNrTel = v.findViewById(R.id.nrTelefon);



            while(data.moveToNext()) {
                names.add(data.getString(1));
                numbers.add(data.getString(5));
                images.add(R.drawable.medic);
            }
            //names.add("asd");
           // names.add("asdaaa");
          //  numbers.add("1111");
            //numbers.add("2222");

            mPoza.setImageResource(images.get(position));
                mNume.setText(names.get(position));
                mNrTel.setText(numbers.get(position));


            return v;
        }
    }
}

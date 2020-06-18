package com.example.medicalfile;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactMedicActivity extends AppCompatActivity {

    DatabaseHelper db ;

    private ListView mListView;

    int[] images={R.drawable.medic1,R.drawable.medic2,R.drawable.medic3,R.drawable.medic4,R.drawable.medic5,};
    String[] names;//={"Dan Diaconescu", "lica Samadau", "Hdolf Aitler", "Ceausescu", "Stela Popescu"};
    String[] numbers;//={"1234567890", "072547392", "214577642", "1245678765","086124555"};
//ArrayList<String> names = new ArrayList<String>();
//    ArrayList<String> numbers = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fisa);

        mListView = (ListView)findViewById(R.id.listView);
        db = new DatabaseHelper(this);

        //populateListView(names, numbers);


        CustomAdaptor customAdaptor = new CustomAdaptor();
        mListView.setAdapter(customAdaptor);
    }

//    private void populateListView(String[] names, String[] numbers)
//    {
//
//        Cursor data = db.getMedic();
//        int i =0;
//      //  ArrayList<String> listData = new ArrayList<>();
//        while(data.moveToNext())
//        {
//
////            String aux = "";
////            aux = data.getString(1) + " " + data.getString(2) + " - "+ data.getString(5);
////
////            listData.add(aux);
//            names[i] = data.getString(1);
//            numbers[i] =data.getString(5);
//            i++;
//
//        }
//        names[3]= "asdads";
//        names[4]= "asaaaa";
//        numbers[3] = "123132";
//        numbers[4]= "1111";
////        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
////        mListView.setAdapter( adapter);
//    }


    class CustomAdaptor extends BaseAdapter{

        @Override
        public int getCount() {
            return images.length;
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

            ImageView mPoza = v.findViewById(R.id.imageView);
            TextView mNume = v.findViewById(R.id.nume);
            TextView mNrTel = v.findViewById(R.id.nrTelefon);

            mPoza.setImageResource(images[position]);

            while(data.moveToNext()) {

                names.add(data.getString(1));
                numbers.add(data.getString(5));
            }
            names.add("asd");
            names.add("asdaaa");
            numbers.add("1111");
            numbers.add("2222");

                mNume.setText(names.get(position));
                mNrTel.setText(numbers.get(position));


            return v;
        }
    }
}

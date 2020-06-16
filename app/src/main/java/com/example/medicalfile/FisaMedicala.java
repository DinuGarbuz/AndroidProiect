package com.example.medicalfile;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.database.Cursor;
import android.util.Log;
import java.util.ArrayList;


public class FisaMedicala extends Fragment {

    private static final String TAG = "FisaMedicala";

    DatabaseHelper db ;

    private ListView mListView;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fisa_medicala, container, false);


        mListView = (ListView) view.findViewById(R.id.listView);
        //db = new DatabaseHelper(this);

        populateListView();
        return view;
    }

    private void populateListView()
    {
        Log.d(TAG, "Populate Listview: Displaying data in the ListView");
        Cursor data = db.getData();
        ArrayList<String> listData = new ArrayList<>();
        while(data.moveToNext())
        {
            listData.add(data.getString(1));
        }

       ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
               getActivity(),
               android.R.layout.simple_list_item_1,
        listData
       );
        mListView.setAdapter(listViewAdapter);
    }
}

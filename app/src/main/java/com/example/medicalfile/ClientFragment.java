package com.example.medicalfile;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;



public class ClientFragment extends Fragment {


    EditText mTextClientName;
    Button mButtonInfo;
    private Button butonFisaMedicala;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_client, container, false);


        mButtonInfo = view.findViewById(R.id.button_info);
        butonFisaMedicala = view.findViewById(R.id.button_fisa_medicala);
        butonFisaMedicala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
              //  fragmentTransaction.add(R.id.fragment_container, new FisaMedicala());
                fragmentTransaction.commit();
            }
        });



        return view;
    }




}

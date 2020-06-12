package com.example.medicalfile;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FisaMedicala#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FisaMedicala extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private int position;

    public static FisaMedicala newInstance(int position) {
        Bundle args = new Bundle();
        // Pass all the parameters to your bundle
        args.putInt("pos", position);
        FisaMedicala fragment = new FisaMedicala();
        fragment.setArguments(args);
        return fragment;
    }

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FisaMedicala() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FisaMedicala.
     */
    // TODO: Rename and change types and number of parameters
    public static FisaMedicala newInstance(String param1, String param2) {
        FisaMedicala fragment = new FisaMedicala();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.position = getArguments().getInt("pos");
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fisa_medicala, container, false);
    }
}

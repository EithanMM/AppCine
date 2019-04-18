package com.example.proyectocine.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.proyectocine.R;


public class Fragment_horario extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private String idPelicula="";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public Fragment_horario() {
        // Required empty public constructor
    }

    public void setIdPelicula(String idPelicula) {
        this.idPelicula = idPelicula;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Toast.makeText(getContext(),idPelicula,Toast.LENGTH_LONG).show();

        return inflater.inflate(R.layout.fragment_fragment_horario, container, false);
    }



}

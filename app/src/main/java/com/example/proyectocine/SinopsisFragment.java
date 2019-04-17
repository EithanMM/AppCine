package com.example.proyectocine;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class SinopsisFragment extends Fragment {
    private String idPelicula = "";
    private String sinopsis="";

    public SinopsisFragment() {
        // Required empty public constructor
    }

    public void setIdPelicula(String id){
        this.idPelicula = id;
    }

    public void setSinopsis(String s){
        this.sinopsis = s;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_sinopsis, container, false);
        View view =inflater.inflate(R.layout.fragment_sinopsis, container, false);
        TextView MiTextView = (TextView) view.findViewById(R.id.textViewSinopsis);
        MiTextView.setText(sinopsis);

        return view;
    }


}

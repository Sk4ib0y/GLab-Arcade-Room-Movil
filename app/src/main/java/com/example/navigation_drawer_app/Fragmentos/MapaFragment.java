package com.example.navigation_drawer_app.Fragmentos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.navigation_drawer_app.R;

public class MapaFragment extends Fragment implements View.OnClickListener {

    ImageView mapa,vista;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_mapa, container, false);
        mapa=view.findViewById(R.id.mapa);
        vista=view.findViewById(R.id.street);

        mapa.setOnClickListener(this);
        vista.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();

        if(id==mapa.getId())
        {
            mapa.setVisibility(View.INVISIBLE);
            vista.setVisibility(View.VISIBLE);
        }
        else if(id==vista.getId())
        {
            mapa.setVisibility(View.VISIBLE);
            vista.setVisibility(View.INVISIBLE);
        }
    }
}
package com.example.navigation_drawer_app;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;


public class MenuFragment extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener {

    String producto[]={"maruchan", "monster", "doritos", "nachos", "cerveza"}, precio[]={"20","40","25","45","50"};
    ListView listita;
    ImageView mas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_menu,container, false);
        listita=view.findViewById(R.id.listita);
        mas=view.findViewById(R.id.mas);

        listita.setOnItemClickListener(this);
        mas.setOnClickListener(this);
        AdaptadorMenu adaptadorMenu= new AdaptadorMenu(requireContext(), producto, precio, getLayoutInflater());
        listita.setAdapter(adaptadorMenu);



        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(requireContext(), "El producto es "+producto[position] +" y cuesta $"+precio[position], Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        int id= v.getId();
        if(mas.getId()== id)
        {
            Intent intent= new Intent(requireContext(), NoDisponible.class);
            requireContext().startActivity(intent);
        }
    }
}
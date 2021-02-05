package com.johancap115.materialdesignintro.fragment;

import android.content.Intent;
import android.os.Bundle;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.johancap115.materialdesignintro.MainActivity;
import com.johancap115.materialdesignintro.R;
import com.johancap115.materialdesignintro.adaptador.MascotaAdaptador;
import com.johancap115.materialdesignintro.interfaz.ItemListener;
import com.johancap115.materialdesignintro.pojo.Mascota;

import java.util.ArrayList;


public class RecyclerviewFragment extends Fragment {

    private ArrayList<Mascota> pets;
    private ArrayList<Mascota> favoritos=null;
    private RecyclerView listaMascotas;
    MascotaAdaptador adaptador;
    public RecyclerviewFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recyclerview, container, false);


        listaMascotas = (RecyclerView) view.findViewById(R.id.rcvMascotas);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        listaMascotas.setLayoutManager(linearLayoutManager);
        inicializarlistaMascotas();
        inicializarAdaptador();

        return view;
    }

    private void inicializarAdaptador() {
        adaptador = new MascotaAdaptador(pets, new ItemListener() {
            @Override
            public void onClick(Mascota pet) {
                if (pet.isLike()){
                    Toast.makeText(getActivity(),getResources().getString(R.string.likeTrue)+pet.getNombre(),Toast.LENGTH_SHORT).show();
                }else {
                    pet.setRaiting(1);
                    pet.setLike(true);
                    Toast.makeText(getActivity(), getResources().getString(R.string.likeOn) +pet.getNombre(),Toast.LENGTH_SHORT).show();
                    addLastFiveFavoritos(pet);
                    recagarAdaptador();
                }
            }
        });
        listaMascotas.setAdapter(adaptador);
    }

    private void recagarAdaptador(){
        adaptador.notifyDataSetChanged();
    }

    private void inicializarlistaMascotas() {
        pets = new ArrayList<Mascota>();

        pets.add(new Mascota(R.drawable.cerdito1, "peggy"));
        pets.add(new Mascota(R.drawable.cerdito2, "porky"));
        pets.add(new Mascota(R.drawable.cerdito3, "pira"));
        pets.add(new Mascota(R.drawable.cerdito4, "qorqui"));
        pets.add(new Mascota(R.drawable.cerdito5, "floki"));
        pets.add(new Mascota(R.drawable.conejito1, "bonny"));
        pets.add(new Mascota(R.drawable.conejito2, "brincos"));
        pets.add(new Mascota(R.drawable.conejito3, "orejas"));
        pets.add(new Mascota(R.drawable.conejito4, "bugs"));
        pets.add(new Mascota(R.drawable.conejito5, "barry"));
        pets.add(new Mascota(R.drawable.perrito1, "brunno"));
        pets.add(new Mascota(R.drawable.perrito2, "pluto"));
        pets.add(new Mascota(R.drawable.perrito3, "spike"));
        pets.add(new Mascota(R.drawable.perrito4, "yanquee"));
        pets.add(new Mascota(R.drawable.perrito5, "firulais"));
        pets.add(new Mascota(R.drawable.gatito1, "zeus"));
        pets.add(new Mascota(R.drawable.gatito2, "tommy"));
        pets.add(new Mascota(R.drawable.gatito3, "tito"));
        pets.add(new Mascota(R.drawable.gatito4, "muchin"));
        pets.add(new Mascota(R.drawable.gatito5, "toto"));

        calificarMascotas();
    }

    private void calificarMascotas() {
        for (Mascota mascotas: pets) {
            int numeroAleatorio = (int) (Math.random()*25+1);
            mascotas.setRaiting(numeroAleatorio);
        }
    }

    private void addLastFiveFavoritos(Mascota favPet) {
        if (favoritos == null) {
            favoritos = new ArrayList<Mascota>();
            favoritos.add(favPet);
        } else if (favoritos.size() == 5) {
            favoritos.remove(0);
            favoritos.add(favPet);
        } else favoritos.add(favPet);
    }

    public  ArrayList<Mascota> getFavoritos(){
        return  favoritos;
    }


}
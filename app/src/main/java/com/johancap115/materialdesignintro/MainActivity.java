package com.johancap115.materialdesignintro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements  ItemListener{

    ArrayList<Mascota> pets;
    private RecyclerView listaMascotas;
    FloatingActionButton FAB;
    TextView raiting;
    MascotaAdaptador adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FAB = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        raiting = (TextView) findViewById(R.id.tvRaitingPet) ;




        listaMascotas = (RecyclerView) findViewById(R.id.rcvMascotas);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        listaMascotas.setLayoutManager(linearLayoutManager);
        if (pets ==null){
            inicializarlistaMascotas();
        }

        inicializarAdaptador();
    }

    private void inicializarAdaptador() {
        adaptador = new MascotaAdaptador(pets, this);
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

    @Override
    public void onClick(Mascota pet) {
        pet.setRaiting(1);
        Toast.makeText(this, "te gusto: "+pet.getNombre(),Toast.LENGTH_SHORT).show();
        recagarAdaptador();
    }
}
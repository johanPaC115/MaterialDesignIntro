package com.johancap115.materialdesignintro;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import java.util.ArrayList;

public class favoritos extends AppCompatActivity {

    ArrayList<Mascota> favoritosPet;
    private RecyclerView listaFavoritos;
    MascotaAdaptador adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        // Remove default title text
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Get access to the custom title view
        TextView mTitle = (TextView) myToolbar.findViewById(R.id.toolbar_title);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar actionBar = getSupportActionBar();

        // Enable the Up button
        actionBar.setDisplayHomeAsUpEnabled(true);

        inicializarFavoritos();

        listaFavoritos = (RecyclerView) findViewById(R.id.rcvMascotasfav);
        LinearLayoutManager llManager = new LinearLayoutManager(this);
        llManager.setOrientation(LinearLayoutManager.VERTICAL);
        listaFavoritos.setLayoutManager(llManager);

        inicializarAdapter();

    }

    private void inicializarAdapter() {
        adaptador = new MascotaAdaptador(favoritosPet, new ItemListener() {
            @Override
            public void onClick(Mascota pet) {

            }
        });
        listaFavoritos.setAdapter(adaptador);
    }

    private void inicializarFavoritos() {
        Bundle objtetoEnviado= getIntent().getExtras();

        if (objtetoEnviado!=null){
            favoritosPet = (ArrayList<Mascota>)objtetoEnviado.getSerializable(getResources().getString(R.string.favoritos));
        }
    }

    /*Actualiza el Toolbar con el menu de accion peronalizado
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_favoritos, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
package com.johancap115.materialdesignintro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements  ItemListener{

    ArrayList<Mascota> pets, favoritos=null;
    private RecyclerView listaMascotas;
    FloatingActionButton FAB;
    MascotaAdaptador adaptador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);


        // Remueve el titulo por defecto de la App
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        // Obtiene Acceso a Titulo Personalizado
        TextView mTitle = (TextView) myToolbar.findViewById(R.id.toolbar_title);

        FAB = (FloatingActionButton) findViewById(R.id.floatingActionButton);

        listaMascotas = (RecyclerView) findViewById(R.id.rcvMascotas);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        listaMascotas.setLayoutManager(linearLayoutManager);
        inicializarlistaMascotas();
        inicializarAdaptador();
    }

    /*Actualiza el Toolbar con el menu de accion peronalizado
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    /*Acciones del Action view del menu
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.action_favorite:
                if (favoritos==null){
                    Toast.makeText(this, getResources().getString(R.string.sinFavoritos),Toast.LENGTH_SHORT).show();
                }else {
                    //Envio el objeto contacto a la siguiente Activity (ConfirmarDatos)
                    Intent intent = new Intent(MainActivity.this, favoritos.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(getResources().getString(R.string.favoritos), favoritos);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    //finish();
                }
               // Toast.makeText(this, "click en Favoritos", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_settings:
                Toast.makeText(this, R.string.settings, Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
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
        if (pet.isLike()){
            Toast.makeText(this,getResources().getString(R.string.likeTrue)+pet.getNombre(),Toast.LENGTH_SHORT).show();
        }else {
            pet.setRaiting(1);
            pet.setLike(true);
            Toast.makeText(this, getResources().getString(R.string.likeOn) +pet.getNombre(),Toast.LENGTH_SHORT).show();
            this.addLastFiveFavoritos(pet);
            recagarAdaptador();
        }
    }
    /*Metodo para ordenar la  lista
    private  void ordenarLista(){
        Collections.sort(pets, new Comparator<Mascota>() {
            @Override
            public int compare(Mascota o1, Mascota o2) {
                return new Integer(o2.getRaiting()).compareTo(new Integer(o1.getRaiting()));
            }
        });
    }

    public void ordenarLista(View view) {
        this.ordenarLista();
        recagarAdaptador();
    }*/

    private void addLastFiveFavoritos(Mascota favPet) {
        if (favoritos == null) {
            favoritos = new ArrayList<Mascota>();
            favoritos.add(favPet);
        } else if (favoritos.size() == 5) {
            favoritos.remove(0);
            favoritos.add(favPet);
        } else favoritos.add(favPet);
    }
}
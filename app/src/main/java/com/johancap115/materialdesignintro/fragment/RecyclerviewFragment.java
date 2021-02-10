package com.johancap115.materialdesignintro.fragment;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.snackbar.Snackbar;
import com.johancap115.materialdesignintro.AboutDesarrollador;
import com.johancap115.materialdesignintro.Contacto;
import com.johancap115.materialdesignintro.R;
import com.johancap115.materialdesignintro.adaptador.MascotaAdaptador;
import com.johancap115.materialdesignintro.favoritos;
import com.johancap115.materialdesignintro.interfaz.ItemListener;
import com.johancap115.materialdesignintro.pojo.Mascota;

import java.util.ArrayList;


public class RecyclerviewFragment extends Fragment {

    MascotaAdaptador adaptador;
    private ArrayList<Mascota> pets;
    private ArrayList<Mascota> favPets = null;
    private RecyclerView listaMascotas;

    public RecyclerviewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recyclerview, container, false);

        // El fragmento de la barra de la app participa en la propagación del menú de opciones.
        setHasOptionsMenu(true);

        listaMascotas = (RecyclerView) view.findViewById(R.id.rcvMascotas);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        listaMascotas.setLayoutManager(linearLayoutManager);
        inicializarlistaMascotas();
        inicializarAdaptador();

        return view;
    }

    /*Actualiza el Toolbar con el menu de accion peronalizado
     */
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);
    }

    /*Acciones del Action view del menu
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_favorite:
                if (favPets == null) {
                    Snackbar.make(getView(), getResources().getString(R.string.sinFavoritos), Snackbar.LENGTH_LONG).setBackgroundTint(getResources().getColor(R.color.colorAccent)).show();
                } else {
                    Intent intentFav = new Intent(getActivity(), favoritos.class);
                    Bundle bundleFav = new Bundle();
                    bundleFav.putSerializable(getResources().getString(R.string.favoritos), favPets);
                    //Envio de Parametros u objetos a tavez de Fragment Manager
                    getParentFragmentManager().setFragmentResult(getResources().getString(R.string.favoritos), bundleFav);
                    intentFav.putExtras(bundleFav);
                    startActivity(intentFav);
                }
                break;
            case R.id.item_contacto:
                //Toast.makeText(getActivity(), R.string.contacto, Toast.LENGTH_SHORT).show();
                Intent intentContacto = new Intent(getActivity(), Contacto.class);
                startActivity(intentContacto);
                break;
            case R.id.item_about:
                Intent intentAbout = new Intent(getActivity(), AboutDesarrollador.class);
                startActivity(intentAbout);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void inicializarAdaptador() {
        adaptador = new MascotaAdaptador(pets, new ItemListener() {
            @Override
            public void onClick(Mascota pet) {
                if (pet.isLike()) {

                    Snackbar.make(getView(), getResources().getString(R.string.likeTrue) + pet.getNombre(), Snackbar.LENGTH_SHORT).setBackgroundTint(getResources().getColor(R.color.colorSecondaryText)).show();
                } else {
                    pet.setRaiting(1);
                    pet.setLike(true);
                    Snackbar.make(getView(), getResources().getString(R.string.likeOn) + pet.getNombre(), Snackbar.LENGTH_SHORT).setBackgroundTint(getResources().getColor(R.color.colorAccent)).show();
                    addLastFiveFavoritos(pet);
                    recagarAdaptador();
                }
            }
        });
        listaMascotas.setAdapter(adaptador);
    }

    private void recagarAdaptador() {
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
        for (Mascota mascotas : pets) {
            int numeroAleatorio = (int) (Math.random() * 25 + 1);
            mascotas.setRaiting(numeroAleatorio);
        }
    }

    private void addLastFiveFavoritos(Mascota favPet) {
        if (favPets == null) {
            favPets = new ArrayList<Mascota>();
            favPets.add(favPet);
        } else if (favPets.size() == 5) {
            favPets.remove(0);
            favPets.add(favPet);
        } else favPets.add(favPet);
    }

}
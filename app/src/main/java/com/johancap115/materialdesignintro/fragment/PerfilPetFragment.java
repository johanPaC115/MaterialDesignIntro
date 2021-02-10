package com.johancap115.materialdesignintro.fragment;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.GridLayoutManager;
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
import com.johancap115.materialdesignintro.adaptador.PerfilPetAdaptador;
import com.johancap115.materialdesignintro.favoritos;
import com.johancap115.materialdesignintro.pojo.Mascota;
import com.mikhaellopez.circularimageview.CircularImageView;
import java.util.ArrayList;

public class PerfilPetFragment extends Fragment {

    private RecyclerView listaPerfil;
    private ArrayList<Mascota> petPerfil;
    private ArrayList<Mascota> favPets=null;
    PerfilPetAdaptador adaptadorPerfil;
    public PerfilPetFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Recepcion de Parametros enviados de otro Fragment
        getParentFragmentManager().setFragmentResultListener(getResources().getString(R.string.favoritos), this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                Bundle objtetoEnviado= result;
                if (objtetoEnviado!=null){
                    favPets = (ArrayList<Mascota>)objtetoEnviado.getSerializable(getResources().getString(R.string.favoritos));
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewProfile = inflater.inflate(R.layout.fragment_perfil_pet, container, false);

        // El fragmento de la barra de la app participa en la propagación del menú de opciones.
        setHasOptionsMenu(true);

        CircularImageView circularImageView = viewProfile.findViewById(R.id.circularImageView);

        listaPerfil = (RecyclerView) viewProfile.findViewById(R.id.rcvPerfil);
        GridLayoutManager glManager = new GridLayoutManager(getActivity(), 3);
        listaPerfil.setLayoutManager(glManager);
        inicializarPerfil();
        inicializarAdaptador();

        return viewProfile;
    }

    private void inicializarAdaptador() {
        adaptadorPerfil = new PerfilPetAdaptador(petPerfil);
        listaPerfil.setAdapter(adaptadorPerfil);
    }

    private void inicializarPerfil() {
        petPerfil = new ArrayList<Mascota>();
        petPerfil.add(new Mascota(R.drawable.cerdito1, "peggy"));
        petPerfil.add(new Mascota(R.drawable.cerdito2, "porky"));
        petPerfil.add(new Mascota(R.drawable.cerdito3, "pira"));
        petPerfil.add(new Mascota(R.drawable.cerdito4, "qorqui"));
        petPerfil.add(new Mascota(R.drawable.cerdito5, "floki"));
        petPerfil.add(new Mascota(R.drawable.cerdito2, "porky"));
        petPerfil.add(new Mascota(R.drawable.cerdito3, "pira"));
        petPerfil.add(new Mascota(R.drawable.cerdito4, "qorqui"));
        petPerfil.add(new Mascota(R.drawable.cerdito5, "floki"));
        calificarMascotas();

    }
    private void calificarMascotas() {
        for (Mascota mascotas: petPerfil) {
            int numeroAleatorio = (int) (Math.random()*25+1);
            mascotas.setRaiting(numeroAleatorio);
        }
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
}
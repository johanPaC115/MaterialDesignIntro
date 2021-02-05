package com.johancap115.materialdesignintro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.johancap115.materialdesignintro.adaptador.MascotaAdaptador;
import com.johancap115.materialdesignintro.adaptador.PageAdapter;
import com.johancap115.materialdesignintro.fragment.PerfilPetFragment;
import com.johancap115.materialdesignintro.fragment.RecyclerviewFragment;
import com.johancap115.materialdesignintro.interfaz.ItemListener;
import com.johancap115.materialdesignintro.pojo.Mascota;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {


    private ArrayList<Mascota> favoritos=null;
    FloatingActionButton FAB;

    private Toolbar myToolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myToolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        FAB = (FloatingActionButton) findViewById(R.id.floatingActionButton);
       setUpViewpager();
       

        if (myToolbar != null){
            setSupportActionBar(myToolbar);
            // Remueve el titulo por defecto de la App
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            // Obtiene Acceso a Titulo Personalizado
            TextView mTitle = (TextView) myToolbar.findViewById(R.id.toolbar_title);
        }

    }

    private ArrayList<Fragment> addFragments(){
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new RecyclerviewFragment());
        fragments.add(new PerfilPetFragment());
        return fragments;
    }

    private void setUpViewpager(){
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), addFragments()));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home_pet_light);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_profile_pet_light);
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
        switch (item.getItemId()) {

            case R.id.action_favorite:
                if (favoritos == null) {
                    Toast.makeText(this, getResources().getString(R.string.sinFavoritos), Toast.LENGTH_SHORT).show();
                } else {
                    //Envio el objeto contacto a la siguiente Activity (ConfirmarDatos)
                    Intent intent = new Intent(MainActivity.this, favoritos.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(getResources().getString(R.string.favoritos), favoritos);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                break;
            case R.id.item_contacto:
                //Toast.makeText(this, R.string.contacto, Toast.LENGTH_SHORT).show();
                Intent intentContacto = new Intent(MainActivity.this, Contacto.class);
                startActivity(intentContacto);
                break;
            case R.id.item_about:
                Intent intentAbout = new Intent(MainActivity.this, AboutDesarrollador.class);
                startActivity(intentAbout);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void getFavoritos() {
      /*  if (favoritos == null) {
            favoritos = new ArrayList<Mascota>();
            favoritos.add(favPet);
        } else if (favoritos.size() == 5) {
            favoritos.remove(0);
            favoritos.add(favPet);
        } else favoritos.add(favPet);
       */

        RecyclerviewFragment fragment = (RecyclerviewFragment) getSupportFragmentManager().findFragmentById(R.id.viewPager);
        favoritos = fragment.getFavoritos();;

    }



}
package com.johancap115.materialdesignintro;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.johancap115.materialdesignintro.adaptador.PageAdapter;
import com.johancap115.materialdesignintro.fragment.PerfilPetFragment;
import com.johancap115.materialdesignintro.fragment.RecyclerviewFragment;
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


}
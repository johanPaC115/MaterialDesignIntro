package com.johancap115.materialdesignintro;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;
import android.widget.TextView;
import com.mikhaellopez.circularimageview.CircularImageView;

public class AboutDesarrollador extends AppCompatActivity {

    private Toolbar myToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_desarrollador);
        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        CircularImageView circularImageView = findViewById(R.id.civPerfil);
        // Remueve el titulo por defecto de la App
       getSupportActionBar().setDisplayShowTitleEnabled(false);
        // Obtiene Acceso a Titulo Personalizado
        TextView mTitle = (TextView) myToolbar.findViewById(R.id.toolbar_title);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar actionBar = getSupportActionBar();

        // Enable the Up button
        actionBar.setDisplayHomeAsUpEnabled(true);

    }
}
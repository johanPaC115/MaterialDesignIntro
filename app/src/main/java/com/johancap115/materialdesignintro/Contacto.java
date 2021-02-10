package com.johancap115.materialdesignintro;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.android.material.textfield.TextInputEditText;
import com.johancap115.materialdesignintro.Util.JavaMailAPI;



public class Contacto extends AppCompatActivity {

    private Toolbar myToolbar;
    //Inicializar Varibles
    TextInputEditText tiEtnombre, tiEtemail, tiEtmensaje;
    Button btEnviar;
    String sEmail, sPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);
        //Asignar variables
        tiEtnombre = (TextInputEditText) findViewById(R.id.txtNombreContacto);
        tiEtemail = (TextInputEditText) findViewById(R.id.txtEmailContacto);
        tiEtmensaje = (TextInputEditText) findViewById(R.id.txtMensajeContacto);
        btEnviar = (Button) findViewById(R.id.btnEnviarMSN);

        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        // Remueve el titulo por defecto de la App
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        // Obtiene Acceso a Titulo Personalizado
        TextView mTitle = (TextView) myToolbar.findViewById(R.id.toolbar_title);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar actionBar = getSupportActionBar();

        // Enable the Up button
        actionBar.setDisplayHomeAsUpEnabled(true);
        btEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail();
            }
        });
    }

    private void sendMail() {
        String mail = tiEtemail.getText().toString().trim();
        String mensaje = tiEtmensaje.getText().toString();
        String subject = tiEtnombre.getText().toString().trim();

        JavaMailAPI javaMailAPI = new JavaMailAPI(this, mail,subject,mensaje);
        javaMailAPI.execute();
    }

}
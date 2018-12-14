package com.example.adan.tareajuegosclasicos.pantallaprincipal;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.adan.tareajuegosclasicos.R;
import com.example.adan.tareajuegosclasicos.juegos.Solitario;
import com.example.adan.tareajuegosclasicos.login.MainLogin;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;

public class PantallaPrincipal extends AppCompatActivity {
    private Button jugar,opciones,creditos,salir,cerrarSesion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pantalla_principal);
        inicializar();
    }
    private void inicializar(){
        jugar=findViewById(R.id.botonJugar);
        opciones=findViewById(R.id.botonOpciones);
        creditos=findViewById(R.id.botonCreditos);
        salir=findViewById(R.id.botonSalir);
        cerrarSesion = findViewById(R.id.cerrarSesion);
        //listeners
        jugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intent jugar
                Intent i = new Intent(PantallaPrincipal.this, Solitario.class);
                startActivity(i);
            }
        });
        opciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //intent opciones
                Intent i = new Intent(PantallaPrincipal.this,PrefActivity.class);
                startActivity(i);
            }
        });

        creditos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intent creditos
                Intent i = new Intent(PantallaPrincipal.this,Creditos.class);
                startActivity(i);
            }
        });

        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if(cerrarSesion!=null){
            cerrarSesion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        FileOutputStream fos = openFileOutput("email", Context.MODE_PRIVATE);
                        fos.write("".getBytes());
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Intent i = new Intent(PantallaPrincipal.this,MainLogin.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);
                }

            });

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

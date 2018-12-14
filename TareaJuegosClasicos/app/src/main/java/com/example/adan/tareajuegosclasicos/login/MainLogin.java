package com.example.adan.tareajuegosclasicos.login;



import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.adan.tareajuegosclasicos.R;
import com.example.adan.tareajuegosclasicos.controlador.ControladorLogin;
import com.example.adan.tareajuegosclasicos.modelo.Persona;
import com.example.adan.tareajuegosclasicos.pantallaprincipal.PantallaPrincipal;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainLogin extends AppCompatActivity implements OnFragmentInteractionListener {
    FrameLayout layoutsVertical;
    LinearLayout layoutsHorizontal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //leer fichero
        String email=new String();
        try {
            FileInputStream fis = openFileInput("email");
            int bite=0;
            while((bite=fis.read())!=-1)
                email+=(char)bite;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(!email.equals(""))
            lanzarPantallaPrincipal(email);

        LoginFragment login = new LoginFragment();
        layoutsVertical =findViewById(R.id.fragments);
        layoutsHorizontal=findViewById(R.id.fragmentsHorizontal);
        if(layoutsVertical!=null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragments, login)
                    .commit();
        }else{
            layoutsHorizontal.findViewById(R.id.botonCambiarRegistro).setVisibility(View.GONE);
        }

    }

    public void registro(View vista){
        Persona persona = new Persona();
        EditText email,password,nombre;
        if(layoutsVertical!=null) {
            email = layoutsVertical.findViewById(R.id.emailRegistro);
            password = layoutsVertical.findViewById(R.id.passwordRegistro);
            nombre = layoutsVertical.findViewById(R.id.nombreRegistro);
        }else{
            email=layoutsHorizontal.findViewById(R.id.emailRegistro);
            password=layoutsHorizontal.findViewById(R.id.passwordRegistro);
            nombre=layoutsHorizontal.findViewById(R.id.nombreRegistro);
        }
        persona.setEmail(email.getText().toString());
        persona.setPassword(email.getText().toString());
        persona.setNombre(email.getText().toString());
        try {
            if(ControladorLogin.newInstance(this).registro(persona)) {
                Toast.makeText(this, "entrando al menu", Toast.LENGTH_SHORT).show();
                lanzarPantallaPrincipal(persona.getEmail());

            }
            else
                Toast.makeText(this, "No se ha podido añadir el usuario", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void login(View vista){

           Persona persona = new Persona();
           EditText pass;
           EditText email;
           if(layoutsVertical!=null) {
               pass=layoutsVertical.findViewById(R.id.passwordLogin);
               email=layoutsVertical.findViewById(R.id.emailLogin);
           }else{
               pass=layoutsHorizontal.findViewById(R.id.passwordLogin);
               email=layoutsHorizontal.findViewById(R.id.emailLogin);
           }
           persona.setPassword(pass.getText().toString());
           persona.setEmail(email.getText().toString());
           this.login(persona);
    }

    public void login(Persona persona){
        try {
            if(ControladorLogin.newInstance(this).login(persona))
            {
                Toast.makeText(this, "entrando al menu", Toast.LENGTH_SHORT).show();
                lanzarPantallaPrincipal(persona.getEmail());
            }
            else
                Toast.makeText(this, "El usuario o la contraseña son incorrectos", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void cambiarARegistro(View vista) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragments,new RegistroFragment())
                .addToBackStack(null)
                .commit();
    }

    public void lanzarPantallaPrincipal(String email){
        Intent i = new Intent(this,PantallaPrincipal.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        String FILENAME = "email";

        FileOutputStream fos = null;
        try {
            fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            fos.write(email.getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        startActivity(i);
    }
}

package com.example.adan.tareajuegosclasicos.modelo;

import android.net.Uri;

public class Persona {
    private String nombre,email;
    private transient String password;
    Uri perfilUri;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Uri getPerfilUri() {
        return perfilUri;
    }

    public void setPerfilUri(Uri perfilUri) {
        this.perfilUri = perfilUri;
    }


    @Override
    public boolean equals(Object obj) {
        if(this==obj)
            return true;
        if(obj instanceof Persona)
            if(((Persona) obj).getNombre().equals(this.nombre)&&((Persona) obj).getEmail().equals(this.email))
                return true;
            return false;
    }
}

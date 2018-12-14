package com.example.adan.tareajuegosclasicos.juegos;

import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adan.tareajuegosclasicos.R;
import com.example.adan.tareajuegosclasicos.modelo.Carta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solitario extends AppCompatActivity {

    private AdaptadorCartas adaptador;
    private List<Carta> cartas, cartasRestantes;
    private RecyclerView rv;
    private ImageView carta1,carta2,carta3,carta4,cartaActual;
    private Carta cartaPosesion;
    private TextView puntuacion;
    private int sumaPuntuacion=1,restaPuntuacion=1,puntuacionActual=0,numAciertos=0;
    private int reyes=0;
    private boolean carta1Dispoible=true,carta2Dispoible=true,carta3Dispoible=true,carta4Dispoible=true;
    private SharedPreferences preferencias;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solitario);

        preferencias=PreferenceManager.getDefaultSharedPreferences(this);
        sumaPuntuacion=Integer.valueOf(preferencias.getString("suma","1"));
        restaPuntuacion=Integer.valueOf(preferencias.getString("resta","1"));
        cartas= new ArrayList<>();
        rv = findViewById(R.id.linearSolitario).findViewById(R.id.recycler);
        rellenarCartas();
        inicializarCartas();
        adaptador=new AdaptadorCartas(cartas);
        adaptador.setMlistener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //juego
                if(cartaPosesion!=null){
                    if(cartaPosesion.getNumCarta()==rv.getChildAdapterPosition(v)){
                        Toast.makeText(Solitario.this, "acertaste", Toast.LENGTH_SHORT).show();
                        cartaPosesion=cartas.get(rv.getChildAdapterPosition(v));
                        Toast.makeText(Solitario.this, "tienes la carta "+cartaPosesion.getNombre(), Toast.LENGTH_SHORT).show();
                        if(cartaPosesion.isRey()){
                            puntuacionActual-=restaPuntuacion;
                            Toast.makeText(Solitario.this, "has conseguido el rey", Toast.LENGTH_SHORT).show();
                            cartaPosesion=null;
                            reyes++;
                            if(reyes==4)
                                Toast.makeText(Solitario.this, "has perdido", Toast.LENGTH_SHORT).show();
                        }else{
                            numAciertos++;
                            puntuacionActual+=sumaPuntuacion;
                        }
                    }else{
                        puntuacionActual-=restaPuntuacion;
                    }
                }else{
                    Toast.makeText(Solitario.this, "debes seleccionar una carta primero", Toast.LENGTH_SHORT).show();
                }

                puntuacion.setText("puntuacion actual: "+puntuacionActual);
                if(numAciertos==36)
                    Toast.makeText(Solitario.this, "has ganado", Toast.LENGTH_SHORT).show();
            }
        });

        rv.setAdapter(adaptador);
        rv.setLayoutManager(new GridLayoutManager(this,9));
    }

    private void inicializarCartas() {
        carta1=findViewById(R.id.fragment3).findViewById(R.id.carta1);
        carta2=findViewById(R.id.fragment3).findViewById(R.id.carta2);
        carta3=findViewById(R.id.fragment3).findViewById(R.id.carta3);
        carta4=findViewById(R.id.fragment3).findViewById(R.id.carta4);
        cartaActual=findViewById(R.id.fragment3).findViewById(R.id.cartaActual);
        puntuacion=findViewById(R.id.fragment3).findViewById(R.id.puntuacionActual);

        carta1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cartaPosesion==null&&carta1Dispoible){
                    //cartaActual.setImageDrawable(cartasRestantes.get(0).getCarta());
                    //carta1.setImageDrawable(cartasRestantes.get(0).getCarta());
                    cartaPosesion=cartasRestantes.get(0);
                    carta1Dispoible=false;
                    if(cartaPosesion.isRey()){
                        puntuacionActual-=restaPuntuacion;
                        puntuacion.setText("puntuacion actual: "+puntuacionActual);
                        Toast.makeText(Solitario.this, "has conseguido el rey", Toast.LENGTH_SHORT).show();
                        cartaPosesion=null;
                        reyes++;
                        if(reyes==4)
                            Toast.makeText(Solitario.this, "has perdido", Toast.LENGTH_SHORT).show();
                    }
                } else{
                Toast.makeText(Solitario.this, "no está permitida esa accion", Toast.LENGTH_SHORT).show();
            }
            }
        });

        carta2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cartaPosesion==null&&carta2Dispoible){
                    //cartaActual.setImageDrawable(cartasRestantes.get(1).getCarta());
                    //carta2.setImageDrawable(cartasRestantes.get(1).getCarta());
                    carta2Dispoible=false;
                    cartaPosesion=cartasRestantes.get(1);
                    if(cartaPosesion.isRey()){
                        puntuacionActual-=restaPuntuacion;
                        puntuacion.setText("puntuacion actual: "+puntuacionActual);
                        Toast.makeText(Solitario.this, "has conseguido el rey", Toast.LENGTH_SHORT).show();
                        cartaPosesion=null;
                        reyes++;
                        if(reyes==4)
                            Toast.makeText(Solitario.this, "has perdido", Toast.LENGTH_SHORT).show();
                    }
                } else{
                Toast.makeText(Solitario.this, "no está permitida esa accion", Toast.LENGTH_SHORT).show();
            }
            }
        });

        carta3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cartaPosesion==null&&carta3Dispoible){
                    carta3Dispoible=false;
                    //cartaActual.setImageDrawable(cartasRestantes.get(2).getCarta());
                    //carta3.setImageDrawable(cartasRestantes.get(2).getCarta());
                    cartaPosesion=cartasRestantes.get(2);
                    if(cartaPosesion.isRey()){
                        puntuacionActual-=restaPuntuacion;
                        puntuacion.setText("puntuacion actual: "+puntuacionActual);
                        Toast.makeText(Solitario.this, "has conseguido el rey", Toast.LENGTH_SHORT).show();
                        cartaPosesion=null;
                        reyes++;
                        if(reyes==4)
                            Toast.makeText(Solitario.this, "has perdido", Toast.LENGTH_SHORT).show();
                    }
                } else{
                Toast.makeText(Solitario.this, "no está permitida esa accion", Toast.LENGTH_SHORT).show();
            }
            }
        });

        carta4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cartaPosesion==null&&carta4Dispoible){
                    carta4Dispoible=false;
                    //cartaActual.setImageDrawable(cartasRestantes.get(3).getCarta());
                    //carta4.setImageDrawable(cartasRestantes.get(3).getCarta());
                    cartaPosesion=cartasRestantes.get(3);
                    if(cartaPosesion.isRey()){
                        puntuacionActual-=restaPuntuacion;
                        puntuacion.setText("puntuacion actual: "+puntuacionActual);
                        Toast.makeText(Solitario.this, "has conseguido el rey", Toast.LENGTH_SHORT).show();
                        cartaPosesion=null;
                        reyes++;
                        if(reyes==4)
                            Toast.makeText(Solitario.this, "has perdido", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(Solitario.this, "no está permitida esa accion", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cartaActual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cartaPosesion!=null)
                    Toast.makeText(Solitario.this, "Tienes la carta "+cartaPosesion.getNombre(), Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(Solitario.this, "debes coger primero carta", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void rellenarCartas(){
        this.cartas=new ArrayList<>();

        List<Carta> cartasTotales = new ArrayList<>();
        List<String> nombresCartas=
                new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.nombres_cartas)));


        Drawable reverso=getResources().getDrawable(R.drawable.reverso_carta);
        int numCarta=0;
        for (int i = 0; i < 40; i++) {
            Carta cartaAniadir = new Carta();
            cartaAniadir.setReverso(reverso);
            cartaAniadir.setActual(reverso);
            cartaAniadir.setNombre(nombresCartas.get(i));
            cartaAniadir.setNumCarta(numCarta);
            cartasTotales.add(cartaAniadir);
            if(i%10!=0&&i>10){
                numCarta++;
            }else if (i<10){
                numCarta++;
            }
        }
        Collections.shuffle(cartasTotales);
        this.cartasRestantes =new ArrayList<>(cartas);
        for (int i = 0; i < 36; i++) {
            this.cartas.add(cartasTotales.get(i));
        }
        for (int i = 36; i < 40; i++) {
            cartasRestantes.add(cartasTotales.get(i));
        }
    }
}

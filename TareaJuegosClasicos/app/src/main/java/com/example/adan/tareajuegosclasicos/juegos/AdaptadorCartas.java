package com.example.adan.tareajuegosclasicos.juegos;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.adan.tareajuegosclasicos.R;
import com.example.adan.tareajuegosclasicos.modelo.Carta;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorCartas extends RecyclerView.Adapter<AdaptadorCartas.ViewHolder> {


    private List<Carta> cartas;
    private View.OnClickListener mlistener;

    public AdaptadorCartas(List<Carta> cartas) {
        this.cartas = cartas;
    }


    public AdaptadorCartas() {
        this.cartas = new ArrayList<>();
    }

    public View.OnClickListener getMlistener() {
        return mlistener;
    }

    public void setMlistener(View.OnClickListener mlistener) {
        this.mlistener = mlistener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View vistaInView= LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_carta,viewGroup,false);

        vistaInView.setOnClickListener(mlistener);
        return new ViewHolder(vistaInView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Carta carta = cartas.get(i);
        viewHolder.blindCarta(carta);
    }

    @Override
    public int getItemCount() {
        return cartas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imagenCarta;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imagenCarta= itemView.findViewById(R.id.carta);
        }

        public void blindCarta(Carta carta){
            imagenCarta.setImageDrawable(carta.getActual());
        }
    }
}

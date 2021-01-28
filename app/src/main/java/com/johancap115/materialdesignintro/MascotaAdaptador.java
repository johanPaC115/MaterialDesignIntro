package com.johancap115.materialdesignintro;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MascotaAdaptador extends RecyclerView.Adapter<MascotaAdaptador.MascotaViewHolder>{

    ArrayList<Mascota> mascotas;
    ItemListener itemListener;

    public MascotaAdaptador(ArrayList<Mascota> mascotas, ItemListener itemlistener) {
        this.mascotas = mascotas;
        this.itemListener = itemlistener;
    }

    @NonNull
    @Override
    public MascotaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota, parent, false);
        return new MascotaViewHolder(v);
    }

    //Asocia cada elemento de la lista con cada View
    @Override
    public void onBindViewHolder(@NonNull MascotaViewHolder mascotaViewHolder, int position) {
        Mascota mascota = mascotas.get(position);
        mascotaViewHolder.imgFotoCV.setImageResource(mascota.getFoto());
        mascotaViewHolder.txvNombreCV.setText(mascota.getNombre());
        mascotaViewHolder.txvRaitingCV.setText(String.valueOf(mascota.getRaiting()));
        if (!mascota.isLike()) {
            mascotaViewHolder.imgRaitingCV.setImageResource(R.drawable.icons8_hueso);
        } else {
            mascotaViewHolder.imgRaitingCV.setImageResource(R.drawable.icons8_hueso_red);
        }

        mascotaViewHolder.imgRaitingCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemListener.onClick(mascota);
            }
        });
    }

    @Override
    public int getItemCount() { //Cantidad de elementos que contiene mi Lista
        return mascotas.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgFotoCV;
        private TextView txvNombreCV;
        private  TextView txvRaitingCV;
        private ImageView imgRaitingCV;

        public MascotaViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFotoCV    = (ImageView) itemView.findViewById(R.id.imgPet);
            txvNombreCV  = (TextView) itemView.findViewById(R.id.tvNpmbrePet);
            txvRaitingCV = (TextView) itemView.findViewById(R.id.tvRaitingPet);
            imgRaitingCV = (ImageView) itemView.findViewById(R.id.imgLikeHueso);
        }
    }
}

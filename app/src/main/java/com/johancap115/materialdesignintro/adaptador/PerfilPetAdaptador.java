package com.johancap115.materialdesignintro.adaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.johancap115.materialdesignintro.R;
import com.johancap115.materialdesignintro.pojo.Mascota;

import java.util.ArrayList;

public class PerfilPetAdaptador extends RecyclerView.Adapter<PerfilPetAdaptador.PerfilViewHolder> {

    ArrayList<Mascota> mascotas;

    public PerfilPetAdaptador(ArrayList<Mascota> mascotas) {
        this.mascotas = mascotas;
    }

    @NonNull
    @Override
    public PerfilViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vPerfil = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_perfil_pet, parent, false);
        return new PerfilViewHolder(vPerfil);
    }

    @Override
    public void onBindViewHolder(@NonNull PerfilViewHolder holder, int position) {
        Mascota mascota = mascotas.get(position);
        holder.imgPerfilPetCV.setImageResource(mascota.getFoto());
        holder.txvRaitingPerfilCV.setText(String.valueOf(mascota.getRaiting()));
    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public static class PerfilViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imgPerfilPetCV;
        private final TextView txvRaitingPerfilCV;
        private final ImageView imgRaitingPerfilCV;

        public PerfilViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPerfilPetCV = (ImageView) itemView.findViewById(R.id.imgPerfilImg);
            txvRaitingPerfilCV = (TextView) itemView.findViewById(R.id.tvPerfilRaitingPet);
            imgRaitingPerfilCV = (ImageView) itemView.findViewById(R.id.imgPerfilRaiting);
        }

    }
}

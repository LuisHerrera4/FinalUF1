package com.example.finaluf1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class GastoAdapter extends RecyclerView.Adapter<GastoAdapter.GastoViewHolder> {
    private List<Gasto> gastos = new ArrayList<>();
    private List<Gasto> gastosFiltrados = new ArrayList<>();

    @NonNull
    @Override
    public GastoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gasto, parent, false);
        return new GastoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GastoViewHolder holder, int position) {
        Gasto gasto = gastosFiltrados.get(position);
        holder.tvNombre.setText(gasto.getNombre());
        holder.tvCantidad.setText(String.format("$%.2f", gasto.getCantidad())); // Formato con 2 decimales
        holder.tvFecha.setText(gasto.getFecha());
    }

    @Override
    public int getItemCount() {
        return gastosFiltrados.size();
    }

    public void setGastos(List<Gasto> gastos) {
        this.gastos = gastos;
        this.gastosFiltrados = new ArrayList<>(gastos);
        notifyDataSetChanged();
    }

    public void filtrar(String texto) {
        gastosFiltrados.clear();
        if (texto.isEmpty()) {
            gastosFiltrados.addAll(gastos);
        } else {
            for (Gasto gasto : gastos) {
                if (gasto.getNombre().toLowerCase().contains(texto.toLowerCase())) {
                    gastosFiltrados.add(gasto);
                }
            }
        }
        notifyDataSetChanged();
    }

    public Gasto getGastoAt(int position) {
        return gastosFiltrados.get(position);
    }

    public static class GastoViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre, tvCantidad, tvFecha;

        public GastoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvCantidad = itemView.findViewById(R.id.tvCantidad);
            tvFecha = itemView.findViewById(R.id.tvFecha);
        }
    }
}

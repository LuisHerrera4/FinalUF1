package com.example.finaluf1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.SearchView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.widget.TextView;
import java.util.List;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private GastoAdapter gastoAdapter;
    private GastoViewModel gastoViewModel;
    private TextView tvTotalGastos;
    private SearchView searchView;

    public HomeFragment() {
        // Constructor vacío requerido
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewGastos);
        tvTotalGastos = view.findViewById(R.id.tvTotalGastos);
        searchView = view.findViewById(R.id.searchView);
        FloatingActionButton fabAgregarGasto = view.findViewById(R.id.fabAgregarGasto);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        gastoAdapter = new GastoAdapter();
        recyclerView.setAdapter(gastoAdapter);

        gastoViewModel = new ViewModelProvider(this).get(GastoViewModel.class);
        gastoViewModel.getAllGastos().observe(getViewLifecycleOwner(), gastos -> {
            gastoAdapter.setGastos(gastos);
            actualizarTotalGastos(gastos);
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                gastoAdapter.filtrar(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                gastoAdapter.filtrar(newText);
                return false;
            }
        });

        fabAgregarGasto.setOnClickListener(v -> {
            ((MainActivity) requireActivity()).cambiarPagina(1);
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Gasto gastoEliminado = gastoAdapter.getGastoAt(viewHolder.getAdapterPosition());
                gastoViewModel.delete(gastoEliminado);
            }
        }).attachToRecyclerView(recyclerView);

        return view;
    }

    private void actualizarTotalGastos(List<Gasto> gastos) {
        double total = 0;
        for (Gasto gasto : gastos) {
            total += gasto.getCantidad();
        }
        tvTotalGastos.setText(String.format("Total gastado: $%.2f", total));
    }
}

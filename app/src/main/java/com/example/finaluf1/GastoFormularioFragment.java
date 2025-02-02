package com.example.finaluf1;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class GastoFormularioFragment extends Fragment {

    private EditText etNombreGasto, etCantidad;
    private Spinner spinnerCategoria;
    private GastoViewModel gastoViewModel;

    public GastoFormularioFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gasto_formulario, container, false);

        etNombreGasto = view.findViewById(R.id.etNombreGasto);
        etCantidad = view.findViewById(R.id.etCantidad);
        spinnerCategoria = view.findViewById(R.id.spinnerCategoria);
        Button btnGuardar = view.findViewById(R.id.btnGuardar);

        gastoViewModel = new ViewModelProvider(this).get(GastoViewModel.class);

        btnGuardar.setOnClickListener(v -> guardarGasto());

        return view;
    }

    private void guardarGasto() {
        String nombre = etNombreGasto.getText().toString().trim();
        String cantidadTexto = etCantidad.getText().toString().trim();
        String categoria = spinnerCategoria.getSelectedItem().toString();

        if (TextUtils.isEmpty(nombre)) {
            Toast.makeText(getContext(), "Ingrese un nombre para el gasto", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(cantidadTexto)) {
            Toast.makeText(getContext(), "Ingrese una cantidad válida", Toast.LENGTH_SHORT).show();
            return;
        }

        float cantidad;
        try {
            cantidad = Float.parseFloat(cantidadTexto);
            cantidad = Math.round(cantidad * 100) / 100f; // Redondear a 2 decimales
        } catch (NumberFormatException e) {
            Toast.makeText(getContext(), "Ingrese un número válido", Toast.LENGTH_SHORT).show();
            return;
        }

        String fechaActual = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());

        Gasto nuevoGasto = new Gasto(nombre, cantidad, fechaActual, categoria);
        gastoViewModel.insert(nuevoGasto);

        Toast.makeText(getContext(), "Gasto guardado exitosamente", Toast.LENGTH_SHORT).show();

        etNombreGasto.setText("");
        etCantidad.setText("");
        spinnerCategoria.setSelection(0);
    }
}

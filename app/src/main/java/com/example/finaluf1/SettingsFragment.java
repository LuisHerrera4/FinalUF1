package com.example.finaluf1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

public class SettingsFragment extends Fragment {

    private Switch switchModoOscuro;
    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "settings_prefs";
    private static final String DARK_MODE_KEY = "dark_mode";

    public SettingsFragment() {
        // Constructor vacío requerido
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        // Inicializar SharedPreferences
        sharedPreferences = requireActivity().getSharedPreferences(PREFS_NAME, getContext().MODE_PRIVATE);

        // Referencia al switch en el layout
        switchModoOscuro = view.findViewById(R.id.switchModoOscuro);

        // Comprobar el estado guardado del modo oscuro
        boolean isDarkMode = sharedPreferences.getBoolean(DARK_MODE_KEY, false);
        switchModoOscuro.setChecked(isDarkMode);

        // Listener para cambiar el tema
        switchModoOscuro.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }

            // Guardar la preferencia del usuario
            sharedPreferences.edit().putBoolean(DARK_MODE_KEY, isChecked).apply();
        });

        return view;
    }
}

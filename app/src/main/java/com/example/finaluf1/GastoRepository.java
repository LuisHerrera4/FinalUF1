package com.example.finaluf1;


import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GastoRepository {
    private final GastoDao gastoDao;
    private final LiveData<List<Gasto>> allGastos;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public GastoRepository(Application application) {
        GastoDatabase database = GastoDatabase.getInstance(application);
        gastoDao = database.gastoDao();
        allGastos = gastoDao.getAllGastos();
    }

    // Obtener todos los gastos
    public LiveData<List<Gasto>> getAllGastos() {
        return allGastos;
    }

    // Insertar un gasto
    public void insert(Gasto gasto) {
        executorService.execute(() -> gastoDao.insert(gasto));
    }

    // Actualizar un gasto
    public void update(Gasto gasto) {
        executorService.execute(() -> gastoDao.update(gasto));
    }

    // Eliminar un gasto
    public void delete(Gasto gasto) {
        executorService.execute(() -> gastoDao.delete(gasto));
    }

    // Obtener un gasto por ID
    public LiveData<Gasto> getGastoById(int id) {
        return gastoDao.getGastoById(id);
    }
}

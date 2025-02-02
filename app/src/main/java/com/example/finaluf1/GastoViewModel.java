package com.example.finaluf1;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

public class GastoViewModel extends AndroidViewModel {
    private GastoRepository repository;
    private LiveData<List<Gasto>> allGastos;

    public GastoViewModel(Application application) {
        super(application);
        repository = new GastoRepository(application);
        allGastos = repository.getAllGastos();
    }

    public LiveData<List<Gasto>> getAllGastos() {
        return allGastos;
    }

    public void insert(Gasto gasto) {
        repository.insert(gasto);
    }

    public void delete(Gasto gasto) {
        repository.delete(gasto);
    }
}

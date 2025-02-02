package com.example.finaluf1;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface GastoDao {

    @Insert
    void insert(Gasto gasto);

    @Update
    void update(Gasto gasto);

    @Delete
    void delete(Gasto gasto);

    @Query("SELECT * FROM gastos ORDER BY fecha DESC")
    LiveData<List<Gasto>> getAllGastos();

    @Query("SELECT * FROM gastos WHERE id = :id LIMIT 1")
    LiveData<Gasto> getGastoById(int id);
}

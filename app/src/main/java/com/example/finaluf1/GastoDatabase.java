package com.example.finaluf1;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Gasto.class}, version = 1, exportSchema = false)
public abstract class GastoDatabase extends RoomDatabase {

    private static volatile GastoDatabase INSTANCE;

    public abstract GastoDao gastoDao();

    public static GastoDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (GastoDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    GastoDatabase.class, "gasto_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}

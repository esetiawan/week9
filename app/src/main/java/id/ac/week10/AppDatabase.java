package id.ac.week10;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities={Mahasiswa.class},version=1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MahasiswaDAO mahasiswaDAO();
    private static AppDatabase INSTANCE;
    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE==null)
        {
            INSTANCE = Room.databaseBuilder(context, AppDatabase.class,
                    "MahasiswaDB").build();
        }
        return INSTANCE;
    }
}

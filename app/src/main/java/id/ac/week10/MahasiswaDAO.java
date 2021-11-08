package id.ac.week10;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public abstract interface MahasiswaDAO {
    @Query("SELECT * FROM mhs")
    List<Mahasiswa> getAllMhs();

    @Query("SELECT * FROM mhs WHERE mhs_nrp=:id ")
    Mahasiswa getMhs(int id);

    @Insert
    void insert(Mahasiswa m);

    @Update
    void update(Mahasiswa m);

    @Delete
    void delete(Mahasiswa m);
}

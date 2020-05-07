package android.example.recommender_app;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LoginDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCreds(Credentials credentials);

    @Query("select * from credentials")
    LiveData<List<Credentials>> selectCreds();





}

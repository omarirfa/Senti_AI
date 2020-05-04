package android.example.recommender_app;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;


import java.util.List;


@Dao
public interface DiaryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void EnterJournal(DiaryDataSource diaryDataSource);

    @Update
    void UpdateJournal(DiaryDataSource diaryDataSource);

    @Delete
    void DeleteJournal(DiaryDataSource diaryDataSource);

    @Query("DELETE FROM diarydata")
    void deleteAllJournalData();

    @Query("SELECT * FROM diarydata ORDER BY date_posted DESC")
    LiveData<List<DiaryDataSource>> showAll();

}

package android.example.recommender_app;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


@Database(entities = {DiaryDataSource.class},version = 5)


public abstract class DiaryDatabase extends RoomDatabase {

    public static DiaryDatabase instance;

    public abstract DiaryDao diaryDao();

    public static synchronized DiaryDatabase getInstance(Context context){

        Log.d("getInstance is called","instance called");
        if(instance==null){

            Log.d("Instance is null","instance null called");
            instance= Room.databaseBuilder(context,DiaryDatabase.class,"diary_database").fallbackToDestructiveMigration().addCallback(roomCallback).build();

        }
        return instance;
    }

    public static RoomDatabase.Callback roomCallback=new RoomDatabase.Callback() {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            Log.d("check onCreate called","OnCreate called");
            super.onCreate(db);
            Log.d("check database is open","database created");
     //       new PopulateDb(instance).execute();
        }

    };

    /*
      private static class PopulateDb extends AsyncTask<Void, Void, Void> {
            private DiaryDao diaryDao;

            public PopulateDb(DiaryDatabase db) {
                diaryDao = db.diaryDao();
            }

            @Override
            protected Void doInBackground(Void... voids) {

                Log.d("check populate db","populate db is called");
                diaryDao.EnterJournal(new DiaryDataSource(DateConverter.DateTimeStamp(new Date()), "Yo", "awesome day", "happy"));
                diaryDao.EnterJournal(new DiaryDataSource(DateConverter.DateTimeStamp(new Date()), "Sup", "good day", "happy"));
                diaryDao.EnterJournal(new DiaryDataSource(DateConverter.DateTimeStamp(new Date()), "Man", "amazing day", "made myself happy"));
                return null;

            }
        }

    */
}

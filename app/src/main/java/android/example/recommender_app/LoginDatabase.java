package android.example.recommender_app;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.RoomDatabase.Callback;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;



@Database(entities = {Credentials.class},version=1)

public abstract class LoginDatabase extends RoomDatabase {

    public static LoginDatabase instance;

    public abstract LoginDao loginDao();

    public static synchronized LoginDatabase getInstance(Context context){
        if(instance==null){
            instance= Room.databaseBuilder(context,LoginDatabase.class,"login_database").fallbackToDestructiveMigration().addCallback(loginCallback).build();
        }
        return instance;
    }

    public static Callback loginCallback=new Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            Log.d("database_creation","database is created");
        }

    };




}

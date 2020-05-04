package android.example.recommender_app;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import java.util.List;

public class DiaryRepository {

    public DiaryDao diaryDao;
    public LiveData<List<DiaryDataSource>> allStuffs;

    public DiaryRepository(Application application){

        DiaryDatabase diaryDatabase=DiaryDatabase.getInstance(application);
        diaryDao=diaryDatabase.diaryDao();
        allStuffs=diaryDao.showAll();
    }

    public void EnterJournal(DiaryDataSource diaryDataSource){

        new InsertTask(diaryDao).execute(diaryDataSource);

    }

    public void UpdateJournal(DiaryDataSource diaryDataSource){

        new UpdateTask(diaryDao).execute(diaryDataSource);
    }

    public void DeleteJournal(DiaryDataSource diaryDataSource){

        new DeleteTask(diaryDao).execute(diaryDataSource);

    }

    public void DeleteAllJournalData(){
        new DeleteAllTask(diaryDao).execute();
    }

    public LiveData<List<DiaryDataSource>> showAll(){

        return allStuffs;

    }

    public static class InsertTask extends AsyncTask<DiaryDataSource,Void,Void>{

        private DiaryDao diaryDao;


        private InsertTask(DiaryDao diaryDao){

                this.diaryDao=diaryDao;

        }

        @Override
        protected Void doInBackground(DiaryDataSource... diaryDataSources) {
            Log.d("check insert db","insert db is called");

            diaryDao.EnterJournal(diaryDataSources[0]);
            return null;
        }
    }

    public static class UpdateTask extends AsyncTask<DiaryDataSource,Void,Void>{
        private DiaryDao diaryDao;

        private UpdateTask(DiaryDao diaryDao){
            this.diaryDao=diaryDao;
        }

        @Override
        protected Void doInBackground(DiaryDataSource... diaryDataSources) {

            diaryDao.UpdateJournal(diaryDataSources[0]);
            return null;
        }
    }

    private static class DeleteTask extends AsyncTask<DiaryDataSource,Void,Void>{
        private DiaryDao diaryDao;

        private DeleteTask(DiaryDao diaryDao){
            this.diaryDao=diaryDao;
        }

        @Override
        protected Void doInBackground(DiaryDataSource... diaryDataSources) {

            diaryDao.DeleteJournal(diaryDataSources[0]);
            return null;
        }
    }

    private static class DeleteAllTask extends AsyncTask<Void,Void,Void>{
        private DiaryDao diaryDao;

        private DeleteAllTask(DiaryDao diaryDao){
            this.diaryDao=diaryDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            diaryDao.deleteAllJournalData();
            return null;
        }
    }

}

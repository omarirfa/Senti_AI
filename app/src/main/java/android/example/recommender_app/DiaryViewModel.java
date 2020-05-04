package android.example.recommender_app;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class DiaryViewModel extends AndroidViewModel {

    public DiaryRepository diaryRepository;
    public LiveData<List<DiaryDataSource>> allData;



    public DiaryViewModel(Application application) {
        super(application);
        diaryRepository=new DiaryRepository(application);
        allData=diaryRepository.showAll();
    }

    public void EnterJournal(DiaryDataSource diaryDataSource){
        diaryRepository.EnterJournal(diaryDataSource);
    }

    public void UpdateJournal(DiaryDataSource diaryDataSource){
        diaryRepository.UpdateJournal(diaryDataSource);
    }

    public void DeleteJournal(DiaryDataSource diaryDataSource){
        diaryRepository.DeleteJournal(diaryDataSource);
    }

    public void DeleteAllJournalData(){

        diaryRepository.DeleteAllJournalData();
    }

    public LiveData<List<DiaryDataSource>> showAll(){

        return allData;
    }

}

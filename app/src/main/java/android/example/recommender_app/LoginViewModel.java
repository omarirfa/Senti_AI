package android.example.recommender_app;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class LoginViewModel extends AndroidViewModel {

    public LoginRepository loginRepository;
    public LiveData<List<Credentials>> getAllCredentials;

    public LoginViewModel(Application application){
        super(application);
        loginRepository=new LoginRepository(application);
        getAllCredentials=loginRepository.getAllCredentials();

    }

    public void insertCredential(Credentials credentials){
        loginRepository.InsertLogin(credentials);
    }

    public LiveData<List<Credentials>> showAllCredential(){
        return getAllCredentials;
    }

}

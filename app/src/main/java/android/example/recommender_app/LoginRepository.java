package android.example.recommender_app;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.google.gson.internal.$Gson$Preconditions;

import java.util.List;

public class LoginRepository {

    public LoginDao loginDao;
    public LiveData<List<Credentials>> getAllStuffs;

    public LoginRepository(Application application){
        LoginDatabase loginDatabase= LoginDatabase.getInstance(application);
        loginDao=loginDatabase.loginDao();
        getAllStuffs=loginDao.selectCreds();

    }

    public void InsertLogin(Credentials credentials){
        new InsertLogin(loginDao).execute(credentials);
    }

    public LiveData<List<Credentials>> getAllCredentials(){
        return getAllStuffs;
    }
    private class InsertLogin extends AsyncTask<Credentials,Void,Void>{
        private LoginDao loginDao;


        public InsertLogin(LoginDao loginDao){
            this.loginDao=loginDao;
        }


        @Override
        protected Void doInBackground(Credentials... credentials) {
            loginDao.insertCreds(credentials[0]);
            return null;
        }
    }
}

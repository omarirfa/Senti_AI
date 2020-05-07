package android.example.recommender_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText mEmailAddress;
    private EditText mPassWord;
    private FloatingActionButton next_button;
    private LoginViewModel loginViewModel;
    private Button signUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEmailAddress=(EditText)findViewById(R.id.editText2);
        mPassWord=(EditText)findViewById(R.id.editText3);

        loginViewModel=new ViewModelProvider(MainActivity.this).get(LoginViewModel.class);

        signUp=(Button)findViewById(R.id.signupbutton);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,SignupPage.class);
                startActivity(i);
            }
        });

        next_button=(FloatingActionButton)findViewById(R.id.floatingActionButton);
        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginViewModel.showAllCredential().observe(MainActivity.this, new Observer<List<Credentials>>() {
                    @Override
                    public void onChanged(List<Credentials> credentials) {

                        for(int i=0;i<credentials.size();i++) {
                            if (mEmailAddress.getText().toString().trim().equalsIgnoreCase(credentials.get(i).getEmailAddress()) && mPassWord.getText().toString().trim().equals(credentials.get(i).getPassword())) {
                                Intent move = new Intent(MainActivity.this, DiaryStore.class);
                                startActivity(move);
                            }
                        }
                    }
                });
            }
        });





    }


}

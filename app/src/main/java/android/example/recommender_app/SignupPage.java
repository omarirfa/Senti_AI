package android.example.recommender_app;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SignupPage extends AppCompatActivity {
    private LoginViewModel loginViewModel;
    private Credentials credentials;
    private EditText emailText;
    private EditText passwordText;
    private FloatingActionButton signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Signup");
        actionBar.setHomeButtonEnabled(true);
        emailText=(EditText)findViewById(R.id.editText_email);
        passwordText=(EditText)findViewById(R.id.editText_password);
        signupButton=(FloatingActionButton)findViewById(R.id.floatingActionButton_signup);
        loginViewModel=new ViewModelProvider(SignupPage.this).get(LoginViewModel.class);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!emailText.getText().toString().isEmpty() && !passwordText.getText().toString().isEmpty()){

                    credentials=new Credentials(emailText.getText().toString().toLowerCase().trim(),passwordText.getText().toString().trim());
                    loginViewModel.insertCredential(credentials);
                    Intent i=new Intent(SignupPage.this,MainActivity.class);
                    startActivity(i);

                }else{
                    Toast.makeText(SignupPage.this,"Fill Email and password",Toast.LENGTH_SHORT).show();

                }
            }
        });


    }
}

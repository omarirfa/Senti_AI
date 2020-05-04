package android.example.recommender_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText mEmailAddress;
    private EditText mPassWord;
    private FloatingActionButton next_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEmailAddress=(EditText)findViewById(R.id.editText2);
        mPassWord=(EditText)findViewById(R.id.editText3);

        next_button=(FloatingActionButton)findViewById(R.id.floatingActionButton);
        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,DiaryStore.class);
                startActivity(i);
            }
        });

        String email_text=mEmailAddress.getText().toString();
        String password_text=mPassWord.getText().toString();



    }


}

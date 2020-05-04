package android.example.recommender_app;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DiaryEntry extends AppCompatActivity {
    private EditText title_text;
    private EditText journal_text;
    private FloatingActionButton floatingActionButton;
    private String title="";
    private String journal_text_content="";
    public static final String TITLE_CODE="android.example.recommender_app.title";
    public static final String JOURNAL_CODE="android.example.recommender_app.journal";
    public static final String SENTIMENT_CODE="android.example.recommender_app.sentiment";
    public static final String DATE_CODE="android.example.recommender_app.date_posted";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_entry);
        title_text=(EditText)findViewById(R.id.editText4);
        journal_text=(EditText)findViewById(R.id.editText5);
        floatingActionButton=(FloatingActionButton)findViewById(R.id.floatingActionButton2);
        ActionBar actionBar=getSupportActionBar();
        if (null!=actionBar){
            actionBar.setHomeAsUpIndicator(android.R.drawable.ic_menu_close_clear_cancel);
            setTitle("Add Journal Entry");
        }
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit_setup();
            }
        });



    }


    private void saveNote(Intent intent) {

        intent.putExtra(TITLE_CODE,title);
        intent.putExtra(JOURNAL_CODE,journal_text_content);
        setResult(RESULT_OK,intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater= getMenuInflater();
        menuInflater.inflate(R.menu.action_menu,menu);
        return true;
    }

    private void Retrofit_setup(){
        Retrofit retrofit=new Retrofit.Builder().baseUrl("http://10.0.2.2:5000").addConverterFactory(GsonConverterFactory.create()).build();

        JsonParser jsonParser=retrofit.create(JsonParser.class);

        title=title_text.getText().toString();

        journal_text_content=journal_text.getText().toString();

        if(title.trim().isEmpty() || journal_text_content.trim().isEmpty()){
            Toast.makeText(DiaryEntry.this,"Please input your content",Toast.LENGTH_LONG).show();
            return;
        }

        Call<String> listparse=jsonParser.sendContent(journal_text_content);
        listparse.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(!response.isSuccessful()){
                    Log.d("response_code",""+response.code());
                    return;
                }else{

                    Log.d("response_body",""+response.body());
                    String response_sentiment=response.body();
                    Intent intent=new Intent();
                    intent.putExtra(SENTIMENT_CODE,response_sentiment);
                    saveNote(intent);


                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

                Log.d("connection failed",""+t.getMessage());

            }
        });

    }



}

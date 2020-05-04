package android.example.recommender_app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.ActionBar;
import android.app.ActivityOptions;
import android.app.IntentService;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class JournalOverview extends AppCompatActivity {
    public static final int REQUEST_CODE_setup=100;

    private TextView title_current_text;
    private TextView date_current_text;
    private ImageView sentiment_current_image;
    private TextView journal_current_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal_overview);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setHomeAsUpIndicator(android.R.drawable.ic_menu_close_clear_cancel);
        setTitle("Journal Overview");
        Intent data=getIntent();
        String title=data.getStringExtra(DiaryEntry.TITLE_CODE);
        String date_posted=data.getStringExtra(DiaryEntry.DATE_CODE);
        String sentiment=data.getStringExtra(DiaryEntry.SENTIMENT_CODE);
        String journal_content=data.getStringExtra(DiaryEntry.JOURNAL_CODE);

        title_current_text=(TextView)findViewById(R.id.title_posted_current);
        date_current_text=(TextView)findViewById(R.id.date_posted_current);
        sentiment_current_image=(ImageView)findViewById(R.id.sentiment_pic);
        journal_current_text=(TextView)findViewById(R.id.journal_content_current);

        title_current_text.setText(title);
        date_current_text.setText(date_posted);
        if(sentiment.equalsIgnoreCase("Happy")){
            sentiment_current_image.setImageResource(R.drawable.smile);
        }else{
            sentiment_current_image.setImageResource(R.drawable.sad);
        }

        journal_current_text.setText(journal_content);


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.drawable.ic_menu_close_clear_cancel){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}

package android.example.recommender_app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DiaryStore extends AppCompatActivity implements JournalClick{

    public RecyclerView recyclerView;
    public DiaryViewModel diaryViewModel;
    private FloatingActionButton floatingActionButton;
    private DiaryAdapter adapter;
    public static final int request_code=10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_store);

        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        adapter=new DiaryAdapter(this,this);
        recyclerView.setAdapter(adapter);



        floatingActionButton=(FloatingActionButton)findViewById(R.id.floatingActionButton3);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(DiaryStore.this,DiaryEntry.class);
                startActivityForResult(i,request_code);
            }
        });


        diaryViewModel= new ViewModelProvider(DiaryStore.this).get(DiaryViewModel.class);
        diaryViewModel.showAll().observe(DiaryStore.this, new Observer<List<DiaryDataSource>>() {

            @Override
            public void onChanged(List<DiaryDataSource> diaryDataSources) {


                Log.d("check viewmodel","viewmodel is called");

                adapter.setDataSource(diaryDataSources);



            }
        });
        ActionBar actionBar=getSupportActionBar();
        if(null!=actionBar){
            setTitle("Journal Feed");
        }


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT|ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                    diaryViewModel.DeleteJournal(adapter.getItemToDelete(viewHolder.getAdapterPosition()));
                    Toast.makeText(DiaryStore.this,"Journal Deleted",Toast.LENGTH_LONG).show();
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode==request_code && resultCode==RESULT_OK){

            String title=data.getStringExtra(DiaryEntry.TITLE_CODE);
            String jour=data.getStringExtra(DiaryEntry.JOURNAL_CODE);
            String sentiment=data.getStringExtra(DiaryEntry.SENTIMENT_CODE);
            DiaryDataSource d=new DiaryDataSource(DateConverter.DateTimeStamp(new Date()),title,jour,sentiment);
            diaryViewModel.EnterJournal(d);
            Toast.makeText(DiaryStore.this,"Journal saved",Toast.LENGTH_LONG).show();

        }else{
            Toast.makeText(DiaryStore.this,"Journal not saved",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.diarystore_menu,menu);
        return true;

    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.delete_all){

            diaryViewModel.DeleteAllJournalData();
            return true;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void OnJournalClick(int position) {

        String title_set=adapter.dataSource.get(position).getTitle();
        Date date_setup=DateConverter.fromTimestamp(adapter.dataSource.get(position).getDate_posted());
        DateFormat df=new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        String date_value=df.format(date_setup);
        String journal_data_set=adapter.dataSource.get(position).getJournal_data();
        String sentiment_set=adapter.dataSource.get(position).getSentiment();

        Intent i=new Intent(this,JournalOverview.class);
        i.putExtra(DiaryEntry.TITLE_CODE,title_set);
        i.putExtra(DiaryEntry.DATE_CODE,date_value);
        i.putExtra(DiaryEntry.SENTIMENT_CODE,sentiment_set);
        i.putExtra(DiaryEntry.JOURNAL_CODE,journal_data_set);
        startActivity(i);
    }
}

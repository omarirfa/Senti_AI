package android.example.recommender_app;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;


public class DiaryAdapter extends RecyclerView.Adapter<DiaryAdapter.ViewHolder> {


    public Context context;
    public List<DiaryDataSource> dataSource=new ArrayList<DiaryDataSource>();
    private JournalClick mJournalClick;




    public DiaryAdapter(Context context,JournalClick mJournalClick){

        this.context=context;
        this.mJournalClick=mJournalClick;

    }

    public void setDataSource(List<DiaryDataSource> dataSource) {


        this.dataSource = dataSource;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public DiaryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.diary_row,parent,false);

        return new ViewHolder(view,mJournalClick);
    }

    @Override
    public void onBindViewHolder(@NonNull DiaryAdapter.ViewHolder holder, int position) {

            holder.title.setText(dataSource.get(position).getTitle());
            if(dataSource.get(position).getSentiment().equalsIgnoreCase("Happy")){
                holder.sentiment.setImageResource(R.drawable.smile);
            }else{
                holder.sentiment.setImageResource(R.drawable.sad);
            }
            Date date_setup=DateConverter.fromTimestamp(dataSource.get(position).getDate_posted());
            DateFormat df=new SimpleDateFormat("dd/MM/yy HH:mm:ss");
            String date_value=df.format(date_setup);
            holder.date_posted_text.setText(date_value);
    }

    @Override
    public int getItemCount() {

        return dataSource.size();
    }

    public DiaryDataSource getItemToDelete(int position){
        return dataSource.get(position);
    }



    public class ViewHolder extends RecyclerView.ViewHolder{


        private TextView title;
        private TextView date_posted_text;
        private ImageView sentiment;
        private JournalClick mJournalClick;



        public ViewHolder(@NonNull View itemView,JournalClick mJournalClickViewSetup) {

            super(itemView);
            title=itemView.findViewById(R.id.title_content);
            date_posted_text=itemView.findViewById(R.id.date_posted);
            sentiment=itemView.findViewById(R.id.sentiments);
            mJournalClick=mJournalClickViewSetup;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mJournalClick.OnJournalClick(getAdapterPosition());
                }
            });
        }

    }
}

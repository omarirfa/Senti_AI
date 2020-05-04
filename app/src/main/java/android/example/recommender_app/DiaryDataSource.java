package android.example.recommender_app;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;

@Entity(tableName = "diarydata")
public class DiaryDataSource {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "date_posted")
    @TypeConverters(DateConverter.class)
    private Long date_posted;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "journal_data")
    private String journal_data;
    @ColumnInfo(name = "sentiment")
    private String sentiment;


    public DiaryDataSource(Long date_posted, String title, String journal_data, String sentiment) {
        this.date_posted = date_posted;
        this.title = title;
        this.journal_data = journal_data;
        this.sentiment = sentiment;
    }
//
//    @Ignore
//    public DiaryDataSource(Date date_posted, String title, String sentiment) {
//        this.date_posted = date_posted;
//        this.title = title;
//        this.sentiment = sentiment;
//    }

    public void setSentiment(String sentiment) {
        this.sentiment = sentiment;
    }


    public void setId(int id) {
        this.id = id;
    }

    public String getSentiment() {
        return sentiment;
    }

    public void setDate_posted(Long date_posted) {
        this.date_posted = date_posted;
    }


    public int getId() {
        return id;
    }

    public Long getDate_posted() {
        return date_posted;
    }



    public String getTitle() {
        return title;
    }

    public String getJournal_data() {
        return journal_data;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setJournal_data(String journal_data) {
        this.journal_data = journal_data;
    }

}

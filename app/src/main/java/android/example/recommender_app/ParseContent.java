package android.example.recommender_app;

import com.google.gson.annotations.SerializedName;

public class ParseContent {

    private String journal_content;

    private String sentiment;

    public String getJournal_content() {
        return journal_content;
    }

    public void setJournal_content(String journal_content) {
        this.journal_content = journal_content;
    }

    public String getSentiment() {
        return sentiment;
    }

    public void setSentiment(String sentiment) {
        this.sentiment = sentiment;
    }

}

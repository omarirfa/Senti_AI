package android.example.recommender_app;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "credentials")
public class Credentials {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "email")
    public String emailAddress;

    @ColumnInfo(name="password")
    public String password;

    public Credentials(String emailAddress, String password) {
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}


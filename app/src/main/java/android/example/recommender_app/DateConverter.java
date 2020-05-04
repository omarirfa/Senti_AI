package android.example.recommender_app;

import androidx.room.TypeConverter;

import java.util.Date;


public class DateConverter {

    @TypeConverter
    public static Long DateTimeStamp(Date date){

        return date==null ? null : date.getTime();

    }

    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

}

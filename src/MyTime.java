import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyTime {
    private Date date;
    private static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");

    // Constructor
    public MyTime (String dateString) {
        try
        {
            this.date = sdf.parse(dateString);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void setMyTime (String newTime) {
        try
        {
            this.date = sdf.parse(newTime);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public long milliSec () {
        return this.date.getTime();
    }

    public static MyTime milliConvert (long milli) {
        String dateStr = sdf.format(milli);
        MyTime n = new MyTime (dateStr);
        return n;
    }


    @Override
    public String toString() {
            String dateStr = sdf.format(date);
            return dateStr;
    }









}

package util;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Sandra on 16.6.2016.
 */
public class DateUtil {

    public static Date addDays(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }
}

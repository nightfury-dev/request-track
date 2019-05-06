package framgia.co.edu.ftrr.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DatetimeUtils {

    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private static final Logger logger = LoggerFactory.getLogger(DatetimeUtils.class);
    private static final DateFormat datetimeFormat = new SimpleDateFormat(DATETIME_FORMAT);

    public static String timestampToString(Timestamp timestamp) {
        try {
            return datetimeFormat.format(timestamp);
        } catch (Exception e) {
            logger.error("Error in timestampToString: " + e.getMessage());
            return null;
        }
    }

    public static Timestamp stringToTimestamp(String source) {
        try {
            return new Timestamp(datetimeFormat.parse(source).getTime());
        } catch (Exception e) {
            logger.error("Error in stringToTimestamp: " + e.getMessage());
            return null;
        }
    }

    public static String getNextMonth(Integer year, Integer month) {
        if (month == 12) {
            year++;
            month = 1;
        } else {
            month++;
        }

        return year + "-" + month;
    }

    public static boolean isNotValidMonths(Integer... months) {
        boolean result = false;

        for (Integer month : months) {
            if (month == null) {
                result = result || false;
                continue;
            }

            result = result || month < 1 || month > 12;
        }
        return result;
    }

    public static Date getCurrentMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, 1);

        return calendar.getTime();
    }

    public static Date getCurrentTime() {
        return Calendar.getInstance().getTime();
    }
}

package framgia.co.edu.ftrr.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

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

}

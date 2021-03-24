package com.mslabs.tangetco.util.log;

import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;


import com.mslabs.tangetco.BaseApplication;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DataLogs {

    private static final String TAG = DataLogs.class.getSimpleName();
    private static final double AVAILABLE_SPACE = 5.0 * 1024.0 * 1024.0;
    private static DataLogs mInstance;
    private static int mTotalTempLogCount = 0;
    private static boolean mCanLogFile = false;

    private static final String PAID_DATE_RESPONSE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String DATE_DISPLAY_FORMAT = "dd-MM-yyyy hh:mm:ss aa";

    /**
     * {@link BufferedWriter}
     */
    private BufferedWriter customLogFile = null;
    private File logFile;

    /**
     * Default constructor.
     */
    private DataLogs() {

        if (!isLoggable()) {
            return;
        }

        logFile = FileUtils.getLogFile(BaseApplication.getApplication());
        //buffer writer
        try {
            customLogFile = new BufferedWriter(new FileWriter(logFile, true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return
     */
    public static DataLogs getInstance() {
        if (mInstance == null) {
            synchronized (DataLogs.class) {
                mInstance = new DataLogs();
            }
        }

        return mInstance;
    }


    /**
     * @return
     */
    public static boolean hasInstance() {
        return mInstance != null;
    }

    /**
     * @param msg Used to identify the source of a log message. It usually identifies the class
     *            where the log call occurs.
     * @return Formatted string.
     */
    private static String getFormattedMessage(final String msg) {
        StringBuffer buffer = new StringBuffer();
        String date = getFormatDateString();

        buffer.append(date).append("   ");
        buffer.append(msg);
        return buffer.toString();
    }

    @NonNull
    private static String getFormatDateString() {
        String DATE_PATTERN = "dd.MM.yyyy";
        final SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN, Locale.US);
        final String date = sdf.format(new Date(System.currentTimeMillis()));

        String time = DateFormat.getTimeInstance(DateFormat.MEDIUM, Locale.ENGLISH).format(new Date());
        return date + " " + time;
    }

    @NonNull
    public static String getFormatDateLongToString(Long DateInLong) {
        Date date = new Date(DateInLong*1000L);
       // String DATE_PATTERN = "dd/MM/yyyy hh:mm aa";
         String DATE_PATTERN = "hh:mm aa";
        final SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN, Locale.US);
        String dateTime = sdf.format(date);

      //  String time = DateFormat.getTimeInstance(DateFormat.MEDIUM, Locale.ENGLISH).format(new Date());
        return dateTime;
    }

    /**
     * @return
     */
    private static boolean isLoggable() {
        if (isSdCardPresent() && doesSdcardHasEnufSpace(AVAILABLE_SPACE)) {
            return true;
        }

        return false;
    }

    /**
     * @return
     */
    private static boolean isSdCardPresent() {
        boolean result = false;
        String state = Environment.getExternalStorageState();

        if (Environment.MEDIA_MOUNTED.equals(state)) {
            result = true;
        }

        return result;
    }

    /**
     * @param requiredSize
     * @return
     */
    private static boolean doesSdcardHasEnufSpace(double requiredSize) {
        return true;
    }

    private static boolean canLogFile(int length) {
        if (mCanLogFile) {
            mTotalTempLogCount++;

            if (mTotalTempLogCount == 5000) {
                mTotalTempLogCount = 0;

                mCanLogFile = (mInstance != null && doesSdcardHasEnufSpace(length));
            }
        } else {
            mCanLogFile = (mInstance != null && doesSdcardHasEnufSpace(length));
        }

        return mCanLogFile;
    }

    /**
     * create a log file and return the file path
     *
     * @return
     */
    private File getLogFilePath() {
        return FileUtils.getLogFile(BaseApplication.getApplication());
    }

    /**
     * check whether the file is exist or not..
     *
     * @return
     */
    private void isFileExists() {
        //directory
        File logFile = getLogFilePath();

        //buffer writer
        try {
            customLogFile = new BufferedWriter(new FileWriter(logFile, true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     */
    public void logMessage(final String message) {
        //check whether the file is exist or not. if not then create a file..
        isFileExists();
        try {
            if (customLogFile != null) {
                customLogFile.write(message);
                customLogFile.newLine();
                customLogFile.flush();
            }
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }
    }

    public static String convertDateResponseDate(String dateString) {
        if (TextUtils.isEmpty(dateString)) {
            return "";
        }

        SimpleDateFormat fmt = new SimpleDateFormat(PAID_DATE_RESPONSE_FORMAT, Locale.US);
        Date date = null;
        try {
            date = fmt.parse(dateString);
            SimpleDateFormat fmtOut = new SimpleDateFormat(DATE_DISPLAY_FORMAT, Locale.US);
            return fmtOut.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }
}

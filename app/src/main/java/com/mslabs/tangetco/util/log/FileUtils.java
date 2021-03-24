package com.mslabs.tangetco.util.log;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import androidx.annotation.NonNull;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Locale;

/**
 * Created by Kannappan on 12/7/2016.
 */

public class FileUtils {

    public static final String TAG = FileUtils.class.getSimpleName();


    private static String getSystemRootFilePath() {
        File rootFile = Environment.getRootDirectory();
        File parentFile = rootFile.getParentFile();
        return parentFile.getAbsolutePath();
    }

    public static void removeFile(File fileOrDirectory) {

        if (fileOrDirectory.isDirectory())
            for (File child : fileOrDirectory.listFiles())
                removeFile(child);

        fileOrDirectory.delete();

    }

    public static String getFileExt(String fileName) {
        String ext = fileName.substring((fileName.lastIndexOf(".") + 1));
        if (ext.isEmpty()) {
            return "";
        } else {
            return ext.toLowerCase(Locale.US);
        }
    }

    @NonNull
    public static File getExternalLogFilesDir(Context context) {
        // Get the directory for the app's private pictures directory.
        File externalFilesDir = context.getExternalFilesDir(
                Environment.DIRECTORY_DOWNLOADS);
        if (externalFilesDir != null && externalFilesDir.exists()) {
            String rootFolderPath = externalFilesDir.getAbsolutePath() + File.separator + "Logs";
            File rootFolder = new File(rootFolderPath);
            if (rootFolder != null && rootFolder.exists()) {
                return rootFolder;
            } else {
                //create a root folder
                createFolders(externalFilesDir.getAbsolutePath(), "Logs");
                return rootFolder;
            }
        } else if (externalFilesDir == null) {
            throw new RuntimeException("The external storage file not available.");
        }
        return externalFilesDir;
    }

    public static void createFolders(String loc, String dir) {
        File f = new File(loc, dir);
        if (!f.isDirectory()) {
            f.mkdirs();
        }
    }


    public static int getFilesLenth(String filePath, boolean isImage) {
        return getFilesLenth(filePath, isImage, false);
    }

    private static int getFilesLenth(String filePath, boolean isImage, boolean isVideo) {
        int length = 0;
        if (!TextUtils.isEmpty(filePath)) {
            File file = new File(filePath);
            if (file.exists()) {
                File[] lisFiles = file.listFiles();
                if (lisFiles != null) {
                    for (int i = 0; i < lisFiles.length; i++) {
                        if (isVideo) {
                            if (getFileExt(lisFiles[i].getName()).equalsIgnoreCase("mp4")) {
                                length++;
                            }
                        } else {
                            if (getFileExt(lisFiles[i].getName()).equalsIgnoreCase(isImage ? "jpg" : "mp3")) {
                                length++;
                            }
                        }
                    }
                }
            }
        }
        return length;
    }


    public static void CopyRAWtoSDCard(Context context, int id, String path) throws IOException {
        InputStream in = context.getResources().openRawResource(id);
        FileOutputStream out = new FileOutputStream(path);
        byte[] buff = new byte[1024];
        int read = 0;
        try {
            while ((read = in.read(buff)) > 0) {
                out.write(buff, 0, read);
            }
        } finally {
            in.close();
            out.close();
        }
    }

    public static File getLogFile(Context context) {
        File file = getExternalLogFilesDir(context);
        File dstFile = new File(file.getAbsolutePath(), "Devicelog.log");
        return dstFile;
    }


    public static void copy(File sourceLocation, File targetLocation) throws IOException {
        if (sourceLocation.isDirectory()) {
            copyDirectory(sourceLocation, targetLocation);
        } else {
            copyFile(sourceLocation, targetLocation);
        }
    }

    private static void copyDirectory(File source, File target) throws IOException {
        if (!target.exists()) {
            target.mkdir();
        }

        for (String f : source.list()) {
            copy(new File(source, f), new File(target, f));
        }
    }

    private static void copyFile(File source, File target) throws IOException {
        try {
            InputStream in = new FileInputStream(source);
            OutputStream out = new FileOutputStream(target);
            byte[] buf = new byte[1024];
            int length;
            while ((length = in.read(buf)) > 0) {
                out.write(buf, 0, length);
            }
        } catch (Exception e) {
        }
    }

    public static void copyFiles(File src, File dst) throws IOException {
        copyFile(src, dst);
    }

    public static void addlogs(String message) {
        //Commented the logs
        DataLogs.getInstance().logMessage(message);
    }

    /**
     * get exception stack trace..
     *
     * @param ex
     * @return
     */
    private static String getExceptionStackTrace(Throwable ex) {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final PrintWriter printWriter = new PrintWriter(out);
        ex.printStackTrace(printWriter);
        printWriter.flush();
        printWriter.close();
        final byte[] errorBuffer = out.toByteArray();
        return new String(errorBuffer);
    }

    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state);
    }

}

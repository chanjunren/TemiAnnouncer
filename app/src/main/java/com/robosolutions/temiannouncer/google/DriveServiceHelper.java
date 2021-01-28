package com.robosolutions.temiannouncer.google;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.OpenableColumns;
import android.util.Log;
import android.util.Pair;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.api.services.drive.Drive;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DriveServiceHelper {
    private final String TAG = "DrievServiceHelper";
    private final Executor mExecutor = Executors.newSingleThreadExecutor();
    private final Drive mDriveService;
    private static final String IMAGES = "task_images";
    private Context context;

    public DriveServiceHelper(Drive mDriveService, Context context) {
        this.mDriveService = mDriveService;
        this.context = context;
    }

//    /**
//     * Opens the file identified by {@code fileId} and returns a {@link Pair} of its name and
//     * contents.
//     */
//    public Task<Pair<String, String>> readFile(String fileId) {
//        return Tasks.call(mExecutor, () -> {
//            File metadata = mDriveService.files().get(fileId).execute();
//            String name = metadata.getName();
//
//            // Stream file contents into a string
//            try {
//                InputStream is = mDriveService.files().get(fileId).executeMediaAsInputStream();
//                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
//                StringBuilder sb = new StringBuilder();
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    sb.append(line);
//                }
//                String contents = sb.toString();
//                return Pair.create(name, contents);
//            } catch (Exception e) {
//                System.err.println(e);
//                return null;
//            }
//        });
//    }

    /**
     * Returns an {@link Intent} for opening the Storage Access Framework file picker.
     */
    public Intent createImgPickerIntent() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        return intent;
    }

//    /**
//     * Opens the file at the {@code uri} returned by a Storage Access Framework {@link Intent}
//     * created by {@link #createImgPickerIntent()} using the given {@code contentResolver}.
//     */
//    public Task<Pair<String, String>> openFileUsingStorageAccessFramework(
//            ContentResolver contentResolver, Uri uri) {
//        return Tasks.call(mExecutor, () -> {
//            // Retrieve document's display name from metadata
//            String name;
//            try (Cursor cursor = contentResolver.query(uri, null,
//                    null, null, null)) {
//                if (cursor != null && cursor.moveToFirst()) {
//                    int nameIdx = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
//                    name = cursor.getString(nameIdx);
//                } else {
//                    throw new IOException("Empty cursor returned for file");
//                }
//            }
//            // Read the document's contents as a String.
//            String content;
//            try (InputStream is = contentResolver.openInputStream(uri);
//                BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
//                StringBuilder stringBuilder = new StringBuilder();
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    stringBuilder.append(line);
//                }
//                content = stringBuilder.toString();
//            }
//            return Pair.create(name, content);
//        });
//    }

    /**
     * Opens the file at the {@code uri} returned by a Storage Access Framework {@link Intent}
     * created by {@link #createImgPickerIntent()} using the given {@code contentResolver}.
     */
    public Task<Pair<String, String>> downloadFileUsingStorageAccessFramework(
            ContentResolver contentResolver, Uri uri) {
        return Tasks.call(mExecutor, () -> {
            // Retrieve document's display name from metadata
            try {
                String name;
                Cursor cursor = contentResolver.query(uri, null, null, null, null);
                if (cursor != null && cursor.moveToFirst()) {
                    int nameIdx = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                    name = cursor.getString(nameIdx);
                } else {
                    throw new IOException("Empty cursor returned for file");
                }
                InputStream is = contentResolver.openInputStream(uri);
                String outputPath = context.getExternalFilesDir(IMAGES).getPath() + "/" + name;
                Log.i(TAG, "current path: " + outputPath);
                File newImgFile = new File(outputPath);
                if (!newImgFile.exists()) {
                    newImgFile.getParentFile().mkdirs();
                    newImgFile.createNewFile();
                }

                byte[] inputData = getBytes(is);
                writeFile(inputData, outputPath);

                return Pair.create(name, outputPath);

            }catch (Exception e) {
                Log.e(TAG, e.toString());
                return null;
            }
        });
    }

    /** This function puts everything in the provided InputStream into a byte array
     * and returns it to the calling function. */
    public byte[] getBytes(InputStream inputStream) throws IOException {

        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len = 0;

        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }

        return byteBuffer.toByteArray();
    }

    /**   This function rewrites the byte array to the provided filename.
     *
     * Note: A String, NOT a file object, though you could easily tweak it to do
     * that. */
    public void writeFile(byte[] data, String fileName) throws IOException{
        FileOutputStream out = new FileOutputStream(fileName);
        out.write(data);
        out.close();
    }
}

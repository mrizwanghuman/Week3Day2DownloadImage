package com.example.admin.week3day2downloadimage;

import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class MyService extends Service {
    public static final String TAG = "Great";
    private String savedFile;


    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");

        downLoadImage();

        return START_REDELIVER_INTENT;
    }

    public void downLoadImage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
//        if (android.os.Build.VERSION.SDK_INT > 9)
//        {
//            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//            StrictMode.setThreadPolicy(policy);
//        }
                String imageURl = "http://4.bp.blogspot.com/-8MpkNOe73_Y/UQ5sJHgEv-I/AAAAAAAADHY/WFVg6JO1rxk/s1600/glass-3d3.jpg";
                Bitmap bitmap;
                try {
//                    String file_path = Environment.getExternalStorageDirectory().getAbsolutePath() +
//                            "/SavedPic";
//
//                    File dir = new File(file_path);
//                    if (!dir.exists()) {
//                        dir.mkdir();
//                    }
//                    Log.d(TAG, "onCreate: " + file_path);
//                    InputStream inputStream = new URL(imageURl).openStream();
//
//                    bitmap = BitmapFactory.decodeStream(inputStream);
//                    //image.setImageBitmap(bitmap);
//                    // Log.d(TAG, "onCreate: "+bitmap.toString());
//                   // inputStream.close();
//
//                    File fileImage = new File(dir, "sketchpad.jpg");
//                    FileOutputStream fOut = new FileOutputStream(fileImage);
//
//
//                    bitmap.compress(Bitmap.CompressFormat.JPEG, 85, fOut);
//                    Log.d(TAG, "onCreate: " + fileImage.getAbsolutePath());
//                    String savedFile = fileImage.getAbsolutePath();
//                    fOut.flush();
//                    fOut.close();
                    String path = Environment.getExternalStorageDirectory().toString();
                    OutputStream outputStream = null;
                    int counter =0;
                    File imgFile= new File(path,"newimage"+counter+".jpg");
                    outputStream= new FileOutputStream(imgFile);
                    InputStream inputStream = new URL(imageURl).openStream();
                   bitmap = BitmapFactory.decodeStream(inputStream);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 85, outputStream);
                    outputStream.flush(); // Not really required
                    outputStream.close();
                    savedFile = imgFile.getAbsolutePath();
                    Log.d(TAG, "run: "+savedFile);
                    Intent broadcastIntent = new Intent();
                    broadcastIntent.setAction(MainActivity.mBroadcastStringAction);
                    broadcastIntent.putExtra("from",savedFile );
                    sendBroadcast(broadcastIntent);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}

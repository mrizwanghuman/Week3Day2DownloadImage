package com.example.admin.week3day2downloadimage;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView image = findViewById(R.id.imageview);
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        String imageURl="http://4.bp.blogspot.com/-8MpkNOe73_Y/UQ5sJHgEv-I/AAAAAAAADHY/WFVg6JO1rxk/s1600/glass-3d3.jpg";
        Bitmap bitmap;
        try {
            String file_path = Environment.getExternalStorageDirectory().getAbsolutePath()+
                    "/SavedPic";

            File dir = new File(file_path);
            if(!dir.exists()){
                dir.mkdir();
            }
            Log.d(TAG, "onCreate: "+file_path);
            InputStream inputStream = new URL(imageURl).openStream();

            bitmap= BitmapFactory.decodeStream(inputStream);
            image.setImageBitmap(bitmap);
           // Log.d(TAG, "onCreate: "+bitmap.toString());
            inputStream.close();

            File file = new File(dir, "sketchpad.jpg");
            FileOutputStream fOut = new FileOutputStream(file);


            bitmap.compress(Bitmap.CompressFormat.JPEG, 85, fOut);
            Log.d(TAG, "onCreate: "+file.getAbsolutePath());
            fOut.flush();
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

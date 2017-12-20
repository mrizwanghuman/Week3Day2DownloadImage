package com.example.admin.week3day2downloadimage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
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

    private static final String TAG = "mainTest";
public static final String mBroadcastStringAction="Training";
    private IntentFilter intentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ImageView image = findViewById(R.id.imageview);
//
        intentFilter = new IntentFilter();
        intentFilter.addAction(mBroadcastStringAction);
        Intent serviceIntent = new Intent(this, MyService.class);
        startService(serviceIntent);
        //registerReceiver(broadcastReceiver, intentFilter);
   }
   BroadcastReceiver broadcastReceiver= new BroadcastReceiver() {
       @Override
       public void onReceive(Context context, Intent intent) {
           if (intent.getAction().equals(mBroadcastStringAction)){
               Log.d(TAG, "onReceive: "+intent.getStringExtra("from"));
           }
       }
   };

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        registerReceiver(broadcastReceiver, intentFilter);
    }
}

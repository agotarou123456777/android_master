package com.example.lifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("LifeCycleSample", "Main onCreate() called.");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButtonClickMain(View view) {
        Intent intent = new Intent(MainActivity.this, SubActivity.class); // インテントオブジェクトを用意。
        startActivity(intent); // アクティビティを起動。
    }

    @Override
    public void onStart() {
        Log.i("LifeCycleSample", "Main onStart() called.");
        super.onStart();
    }

    @Override
    public void onRestart() {
        Log.i("LifeCycleSample", "Main onRestart() called.");
        super.onRestart();
    }

    @Override
    public void onResume() {
        Log.i("LifeCycleSample", "Main onResume() called.");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.i("LifeCycleSample", "Main onPause() called.");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.i("LifeCycleSample", "Main onStop() called.");
        super.onStop();
    }

    @Override
    public void onDestroy() {
        Log.i("LifeCycleSample", "Main onDestory() called.");
        super.onDestroy();
    }

}
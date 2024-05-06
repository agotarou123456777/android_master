package com.example.toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Color;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ツールバー設定
        Toolbar toolbar = findViewById(R.id.toolbar);   // ツールバーを取得
        toolbar.setLogo(R.mipmap.ic_launcher);          // ロゴ設定
        toolbar.setTitle(R.string.toolbar_title);       // タイトル設定
        toolbar.setTitleTextColor(Color.WHITE);         // タイトル文字色設定
        toolbar.setSubtitle(R.string.toolbar_subtitle); // サブタイトル設定
        toolbar.setSubtitleTextColor(Color.LTGRAY);     // サブタイトル文字色設定
        setSupportActionBar(toolbar);                   // アクションバーにツールバーを設定
    }
}
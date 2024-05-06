package com.example.intentchangeactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btNext = findViewById(R.id.btNext);
        btNext.setOnClickListener(new ClickListener());
    }


    private class ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            String text = "test";
            Intent intent = new Intent(MainActivity.this, SubActivity.class);
            intent.putExtra("text", text);
            startActivity(intent);
        }
    }
}
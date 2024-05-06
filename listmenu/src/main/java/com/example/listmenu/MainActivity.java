package com.example.listmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.list_view);
        List<MyDataModel> myList = fetchData();  // データソースを取得
        MyListAdapter adapter = new MyListAdapter(this, myList);
        listView.setAdapter(adapter);
    }
}
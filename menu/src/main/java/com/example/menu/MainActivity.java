package com.example.menu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static TextView tvText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sample_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        tvText = findViewById(R.id.tvText);
        int itemId = item.getItemId();

        if (itemId == R.id.menu1) {
            tvText.setText("direct set text at menu1");
            return true;
        } else if (itemId == R.id.menu2) {
            tvText.setText(item.getTitle());
            return true;
        } else if (itemId == R.id.menu3) {
            tvText.setText("This is title "+item.getTitle());
            return true;
        }

        return false;
    }
}
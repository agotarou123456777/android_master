package com.example.customview1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bt = (Button) findViewById(R.id.show_current_time_button);
        bt.setOnClickListener(new Listener());
    }

    private class Listener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            CustomTextView cb = (CustomTextView) findViewById(R.id.customtext);
            cb.setUnixEpoch(System.currentTimeMillis());
        }
    }
}
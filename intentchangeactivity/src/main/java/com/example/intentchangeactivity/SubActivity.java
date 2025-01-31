package com.example.intentchangeactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SubActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        Intent intent = getIntent();

        String text = intent.getStringExtra("text");
        TextView tvText = findViewById(R.id.tvText);
        tvText.setText(text);

        Button btBack = findViewById(R.id.btBack);
        btBack.setOnClickListener(new ButtonClickListener());

    }

    private class ButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view){
            finish();
        }
    }
}

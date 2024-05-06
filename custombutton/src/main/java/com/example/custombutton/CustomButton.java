package com.example.custombutton;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatTextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class CustomButton extends LinearLayout {
    private Button bt1, bt2, bt3;

    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        View.inflate(context, R.layout.selector_view, this);

        bt1 = findViewById(R.id.bt1);
        bt2 = findViewById(R.id.bt2);
        bt3 = findViewById(R.id.bt3);

        bt1.setOnClickListener(new ClickListener());
        bt2.setOnClickListener(new ClickListener());
        bt3.setOnClickListener(new ClickListener());
    }

    private class ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            int id = view.getId();

            bt1.setTextColor(Color.BLACK);
            bt2.setTextColor(Color.BLACK);
            bt3.setTextColor(Color.BLACK);

            if (id == R.id.bt1){
                bt1.setTextColor(Color.RED);
            } else if (id == R.id.bt2) {
                bt2.setTextColor(Color.RED);
            } else if (id == R.id.bt3) {
                bt3.setTextColor(Color.RED);
            }
        }
    }


}
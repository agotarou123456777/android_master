package com.example.customview1;

import android.content.Context;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class CustomTextView extends AppCompatTextView {
    public CustomTextView(Context context) {
        super(context);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setUnixEpoch(long unixEpoch) {
        setText(getUnixEpochString(unixEpoch));
    }

    private static String getUnixEpochString(long unixEpoch) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd\nHH:mm:ss.SSS", Locale.US);
        formatter.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"));
        return formatter.format(new Date(unixEpoch));
    }
}
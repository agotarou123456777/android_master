package com.example.saveimage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSave = (Button) findViewById(R.id.btn_save);
        btnSave.setOnClickListener(new ButtonClick());
    }

    private class ButtonClick implements View.OnClickListener{
        @Override
        public void onClick(View view){
            saveImageToGallery(getApplicationContext());
        }
    }

    private void saveImageToGallery(Context context) {

        String SaveDir = "test2"; // 保存先のディレクトリ名
        long currentTimeMillis = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmssSSS");
        Date date = new Date(currentTimeMillis);
        String formattedDate = sdf.format(date);
        //String SaveFileName = "Image_" + formattedDate + ".jpg";
        String SaveFileName = "test.jpg";

        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.screenshot_from_2024_04_08_05_52_26);

        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, SaveFileName);
        contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg");
        contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + File.separator + SaveDir);

        TextView tv = findViewById(R.id.tvPath);
        tv.setText(Environment.DIRECTORY_PICTURES + File.separator + SaveDir);


        ContentResolver resolver = context.getContentResolver();
        Uri uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);

        try (OutputStream stream = resolver.openOutputStream(uri)) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            Toast.makeText(context, "Image saved to gallery", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Toast.makeText(context, "Error saving image: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

}
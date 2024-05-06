package com.example.loadimage;

import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import android.Manifest;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btLoad;

    private ImageView imageView;

    private final String TAG="MainActivity";
    private static final int REQUEST_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btLoad = (Button) findViewById(R.id.btLoad);
        btLoad.setOnClickListener(new ButtonClick());

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE);
        }

        imageView = findViewById(R.id.viewImage);

    }

    private class ButtonClick implements View.OnClickListener{
        @Override
        public void onClick(View view){
            String FileName = "test.jpg";
            String DirName = "test2";
            Bitmap bitmap = loadImage(DirName, FileName);
            imageView.setImageBitmap(bitmap);
        }
    }

    public Bitmap loadImage(String dir_name, String file_name) {
        // ファイルの絶対パスを作成
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File saveDir = new File(storageDir, dir_name);  // SaveDirはサブフォルダの名前
        File imageFile = new File(saveDir, file_name);
        if(!imageFile.exists()){
            Toast.makeText(this, "File Not Exist", Toast.LENGTH_LONG).show();
        }

        if(imageFile.canRead()) {
            Log.i(TAG,"Data Load Success");
        }
        else {
            Log.i(TAG,"Error:open file");
            Toast.makeText(this, "File Not Open", Toast.LENGTH_LONG).show();
        }
        Bitmap bitmap = BitmapFactory.decodeFile(imageFile.getPath()); // Bitmapとして画像を読み込む
        return bitmap;
    }

    //なぜかファイル開けない
    public Bitmap loadImage2(String dir_name, String file_name) {

        Bitmap bitmap = null;
        ContentValues values = new ContentValues();

        values.put(MediaStore.Images.Media.DISPLAY_NAME, file_name);
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        values.put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + File.separator + dir_name);
        Uri uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        try {
            InputStream inputStream = getContentResolver().openInputStream(uri);
            bitmap = BitmapFactory.decodeStream(inputStream);
            inputStream.close(); // ストリームを閉じる
        }
        catch (FileNotFoundException e) {
            Log.i(TAG,"Error:open file(FileNotFoundException)"+Environment.DIRECTORY_PICTURES + File.separator + dir_name);
            e.printStackTrace();
        }
        catch (IOException e) {
            Log.i(TAG,"Error:open file(IOException)");
            e.printStackTrace();
        }

        return bitmap;
    }
}
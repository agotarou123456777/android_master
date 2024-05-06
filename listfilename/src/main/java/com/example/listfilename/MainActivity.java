package com.example.listfilename;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    private static final int REQUEST_CODE = 1;
    private static final String TAG = "MainActivity";
    private List<String> mStrLs_FileNameList;
    private String mStr_ProjectName="";
    private ListView mLsView_FileNameList;
    private EditText mEt_ProjectName;
    private ImageView mImView_Image;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE);
        }

        mLsView_FileNameList = findViewById(R.id.lvFileNameList);
        mEt_ProjectName = findViewById(R.id.etProjectName);
        mImView_Image = findViewById(R.id.imageView);

        Button btGetFileList = findViewById(R.id.btGetFileList);
        btGetFileList.setOnClickListener(new ButtonClickListener());

        mLsView_FileNameList.setOnItemClickListener(new ListViewItemClickListener());
    }

    //=======================================================================================================
    // ↓　↓　Listener Class ↓　↓　
    //=======================================================================================================
    private class ButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            int id = view.getId();

            if (id == R.id.btGetFileList) {
                setProjectName();
                mStrLs_FileNameList = getImageFileNames(mStr_ProjectName);
                ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, mStrLs_FileNameList);
                mLsView_FileNameList.setAdapter(adapter);
            }
        }
    }

    private class ListViewItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String item = (String) parent.getItemAtPosition(position);
            Bitmap bitmap = loadImage(mStr_ProjectName, item);
            mImView_Image.setImageBitmap(bitmap);
        }
    }



    //=======================================================================================================
    // ↓　↓　Private Methods ↓　↓　
    //=======================================================================================================
    private void setProjectName(){
        mStr_ProjectName = mEt_ProjectName.getText().toString();
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

    public List<String> getImageFileNames(String sub_dir) {
        List<String> fileNames = new ArrayList<>();

        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File saveDir = new File(storageDir, sub_dir);

        Log.i("Main",saveDir.getAbsolutePath());
        File[] files = saveDir.listFiles();
        if (files != null) {
            for (File file : files) {
                Log.i(TAG,"Get File Name : "+file.getName());
                fileNames.add(file.getName());
            }
        }
        else {
            Log.i(TAG,"Folder Cold not Open");
            Toast.makeText(this, "Folder Cold not Open", Toast.LENGTH_LONG).show();
        }

        return fileNames;
    }

}
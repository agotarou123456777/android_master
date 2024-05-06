package com.example.cameraintent;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    ActivityResultLauncher<Intent> m_cameraLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallbackFromCamera());

    private Uri m_imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 画像部分がタップされたときの処理メソッド。
     */
    public void onCameraImageClick(View view) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date now = new Date(System.currentTimeMillis()); // 現在の日時を取得。
        String nowStr = dateFormat.format(now);
        String fileName = "CameraIntentSamplePhoto_" + nowStr +".jpg";

        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, fileName);
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg"); // 画像ファイルの種類を設定

        ContentResolver resolver = getContentResolver();

        m_imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values); // ContentResolverを使ってURIオブジェクトを生成
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, m_imageUri);

        m_cameraLauncher.launch(intent); // アクティビティを起動。
    }


    private class ActivityResultCallbackFromCamera implements ActivityResultCallback<ActivityResult> {
        @Override
        public void onActivityResult(ActivityResult result) {

            if(result.getResultCode() == RESULT_OK) { // 撮影成功の場合


                ImageView ivCamera = findViewById(R.id.ivCamera);

                //1. 画像をUriから取得
                ivCamera.setImageURI(m_imageUri); // 画像URIをImageViewに設定


                //2. ビットマップから取得
				/*Intent data = result.getData(); // 画像のビットマップデータを取得
				Bitmap bitmap;
				if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
					bitmap = data.getParcelableExtra("data", Bitmap.class);
				}
				else {
					bitmap = data.getParcelableExtra("data");
				}
				ivCamera.setImageBitmap(bitmap); // 撮影された画像をImageViewに設定。*/


            }
        }
    }
}
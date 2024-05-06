package com.example.master;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btClick = findViewById(R.id.btClick); // 表示ボタンを取得
        btClick.setOnClickListener(new HelloListener()); // リスナを設定


        Button btClear = findViewById(R.id.btClear); // クリアボタンを取得。
        btClear.setOnClickListener(new HelloListener());
    }

    private class HelloListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            EditText input = findViewById(R.id.etName);
            TextView output = findViewById(R.id.tvOutput);

            int id = view.getId(); // viewからタップされたボタンのidを取得。

            if(id == R.id.btClick){
                String inputStr = input.getText().toString(); //メッセージ表示
                output.setText(inputStr + "さん、こんにちは!");
            } else if (id == R.id.btClear) {
                input.setText("");
                output.setText(""); // メッセージ表示欄を空文字に設定
            }
        }
    }
}
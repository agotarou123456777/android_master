package com.example.simplelistmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lvMenu = findViewById(R.id.lvMenu); // ListViewオブジェクトを取得。
        List<String> menuList = new ArrayList<>(); // リストビューに表示するリストデータ用Listオブジェクトを作成。

        // リストデータの登録。
        menuList.add("から揚げ定食");
        menuList.add("ハンバーグ定食");
        menuList.add("生姜焼き定食");
        menuList.add("ステーキ定食");
        menuList.add("野菜炒め定食");
        menuList.add("とんかつ定食");
        menuList.add("ミンチかつ定食");
        menuList.add("チキンカツ定食");
        menuList.add("コロッケ定食");
        menuList.add("回鍋肉定食");
        menuList.add("麻婆豆腐定食");
        menuList.add("青椒肉絲定食");
        menuList.add("八宝菜定食");
        menuList.add("酢豚定食");
        menuList.add("豚の角煮定食");
        menuList.add("焼き鳥定食");
        menuList.add("焼き魚定食");
        menuList.add("焼肉定食");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, menuList); // アダプタオブジェクトを生成。
        lvMenu.setAdapter(adapter); // リストビューにアダプタオブジェクトを設定。
        lvMenu.setOnItemClickListener(new ListItemClickListener()); // リストビューにリスナを設定。
    }

    private class ListItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String item = (String) parent.getItemAtPosition(position);
            Toast.makeText(MainActivity.this, item,Toast.LENGTH_LONG).show();
        }
    }
}
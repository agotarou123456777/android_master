package com.example.webview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private List<Map<String, String>> list;
    private WebView wvSite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = createList();


        ListView lvSiteList = findViewById(R.id.lvSiteList);
        String[] from = {"name", "url"};
        int[] to = {android.R.id.text1, android.R.id.text2};
        SimpleAdapter adapter = new SimpleAdapter(MainActivity.this, list, android.R.layout.simple_list_item_2, from, to);
        lvSiteList.setAdapter(adapter);
        lvSiteList.setOnItemClickListener(new ListItemClickListener());
    }

    private List<Map<String, String>> createList() {
        List<Map<String, String>> list = new ArrayList<>();

        Map<String, String> map = new HashMap<>();
        map.put("name", "Youtube");
        map.put("url", "https://www.youtube.com/");
        list.add(map);
        map = new HashMap<>();
        map.put("name", "EnterpriseZine");
        map.put("url", "https://www.enterprisezine.jp/");
        list.add(map);
        map = new HashMap<>();
        map.put("name", "MarkeZine");
        map.put("url", "https://markezine.jp/");
        list.add(map);
        map = new HashMap<>();
        map.put("name", "ECzine");
        map.put("url", "https://eczine.jp/");
        list.add(map);
        return list;
    }

    private class ListItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Map<String, String> item = list.get(position);
            String url = item.get("url");
            wvSite = findViewById(R.id.wvSite);
            WebSettings webSettings = wvSite.getSettings();
            webSettings.setJavaScriptEnabled(true);
            wvSite.stopLoading();
            wvSite.setWebChromeClient(new WebChromeClient());
            wvSite.loadUrl(url);
        }
    }
}
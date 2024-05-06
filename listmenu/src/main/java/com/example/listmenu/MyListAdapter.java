package com.example.listmenu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class MyListAdapter extends ArrayAdapter<?> {
    public MyListAdapter(Context context, List<?> objects){
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // リストアイテムを取得
        MyDataModel item = getItem(position);

        // ビューを再利用するか、新しく作成する
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.new_list, parent, false);
        }

        // 各テキストビューにデータをセット
        TextView text1 = convertView.findViewById(R.id.text1);
        TextView text2 = convertView.findViewById(R.id.text2);
        TextView text3 = convertView.findViewById(R.id.text3);

        text1.setText(item.getData1());
        text2.setText(item.getData2());
        text3.setText(item.getData3());

        return convertView;
    }

}

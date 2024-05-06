package com.example.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class FragmentSample2 extends Fragment {

    public FragmentSample2() {
        super(R.layout.fragment_sample2);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle extras = getArguments();  // このフラグメントに埋め込まれた引き継ぎデータを取得
        String txSample1 = "";

        if(extras != null) { // 引き継ぎデータ(Bundleオブジェクト)が存在する場合
            txSample1 = extras.getString("Sample1Text");
        }

        TextView tvSample2 = view.findViewById(R.id.tvSample2);
        tvSample2.setText(txSample1);// FragmentSample2のテキストにFragmentSample1のエディタ入力値を表示

        Button btBackButton = view.findViewById(R.id.btSample2Back);
        btBackButton.setOnClickListener(new ButtonClickListener());
    }

    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            FragmentManager manager = getParentFragmentManager(); // フラグメントマネージャーを取得
            Activity parentActivity = getActivity();  // このフラグメントが所属するアクティビティオブジェクトを取得

            View frgm_Main = parentActivity.findViewById(R.id.fragmentMainContainer); // アクティビティからfragmentMainContainerを取得
            View frgm_Sample2 = parentActivity.findViewById(R.id.fragmentSample2Container); // アクティビティからfragmentSample2Containerを取得

            // fragmentMainContainerが存在する場合（スマホ画面の場合）
            if(frgm_Main != null) {
                manager.popBackStack(); // バックスタックのひとつ前の状態に戻る
            }
            // fragmentSample2Containerが存在する場合（タブレット画面の場合）
            else if(frgm_Sample2 != null) {
                FragmentTransaction transaction = manager.beginTransaction(); // フラグメントトランザクションの開始
                transaction.setReorderingAllowed(true); // フラグメントトランザクションが正しく動作するように設定
                transaction.remove(FragmentSample2.this); // 自分自身を削除
                transaction.commit(); // フラグメントトランザクションのコミット
            }
        }
    }
}

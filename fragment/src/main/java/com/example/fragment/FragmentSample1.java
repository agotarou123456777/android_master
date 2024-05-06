package com.example.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class FragmentSample1 extends Fragment {

    private EditText etSample1;

    public FragmentSample1() {
        super(R.layout.fragment_sample1);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etSample1 = view.findViewById(R.id.etSample1);
        Button btSample1 = view.findViewById(R.id.btSample1);
        btSample1.setOnClickListener(new ButtonListener());
    }

    private class ButtonListener implements View.OnClickListener {
        @Override
        public void onClick (View view) {

            String tx = etSample1.getText().toString();
            Bundle bundle = new Bundle(); // 引き継ぎデータをまとめて格納できるBundleオブジェクト生成
            bundle.putString("Sample1Text", tx); // Bundleオブジェクトに引き継ぎデータを格納

            FragmentManager manager = getParentFragmentManager(); // フラグメントマネージャーの取得
            FragmentTransaction transaction = manager.beginTransaction(); // フラグメントトランザクションの開始
            transaction.setReorderingAllowed(true); // フラグメントトランザクションが正しく動作するように設定
            Activity parentActivity = getActivity(); // このフラグメントが所属するアクティビティオブジェクトを取得

            View frgm_Main = parentActivity.findViewById(R.id.fragmentMainContainer); // アクティビティからfragmentMainContainerを取得
            View frgm_Sample2 = parentActivity.findViewById(R.id.fragmentSample2Container); // アクティビティからfragmentSample2Containerを取得

            // fragmentMainContainerが存在する場合（スマホ画面の場合）
            if(frgm_Main != null) {
                transaction.addToBackStack("Only List"); // 現在の表示内容をバックスタックに追加
                transaction.replace(R.id.fragmentMainContainer, FragmentSample2.class, bundle); // fragmentMainContainerのフラグメントをFragmentSample2に置き換え
            }
            // fragmentSample2Containerが存在する場合（タブレット画面の場合）
            else if(frgm_Sample2 != null) {
                transaction.replace(R.id.fragmentSample2Container, FragmentSample2.class, bundle); // fragmentSample2ContainerのフラグメントをFragmentSample2に置き換え
            }
            transaction.commit(); // フラグメントトランザクションのコミット
        }
    }
}

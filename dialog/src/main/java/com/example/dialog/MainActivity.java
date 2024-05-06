package com.example.dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btDialog = findViewById(R.id.button);
        btDialog.setOnClickListener(new Listen());
    }

    private class Listen implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            OrderConfirmDialogFragment dialogFragment = new OrderConfirmDialogFragment();
            dialogFragment.show(getFragmentManager(), "OrderConfirmDialogFragment");
        }
    }


}
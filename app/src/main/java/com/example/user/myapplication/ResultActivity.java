package com.example.user.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by User on 2018/5/7.
 */

public class ResultActivity extends AppCompatActivity {
    private Button btnBack;
    private EditText mEdtCountSet,
            mEdtCountPlayerWin,
            mEdtCountComWin,
            mEdtCountDraw;
    private int miCountSet = 0,
            miCountPlayerWin = 0,
            miCountComWin = 0,
            miCountDraw = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Bundle bundle = this.getIntent().getExtras();
        miCountSet = bundle.getInt("CountSet");
        miCountPlayerWin = bundle.getInt("CountPlayerWin");
        miCountComWin = bundle.getInt("CountComWin");
        miCountDraw = bundle.getInt("CountDraw");

        mEdtCountSet = (EditText) findViewById(R.id.edtCountSet);
        mEdtCountPlayerWin = (EditText) findViewById(R.id.edtCountPlayerWin);
        mEdtCountComWin = (EditText) findViewById(R.id.edtCountComWin);
        mEdtCountDraw = (EditText) findViewById(R.id.edtCountDraw);
        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(btnBackOnclick);

        mEdtCountSet.setText(Integer.toString(miCountSet));
        mEdtCountDraw.setText(Integer.toString(miCountDraw));
        mEdtCountComWin.setText(Integer.toString(miCountComWin));
        mEdtCountPlayerWin.setText(Integer.toString(miCountPlayerWin));
    }
    private View.OnClickListener btnBackOnclick = new View.OnClickListener() {
        public void onClick(View v){
            Intent intent = new Intent();
            intent.setClass(ResultActivity.this, MainActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("CountSet", miCountSet);
            bundle.putInt("CountPlayerWin", miCountPlayerWin);
            bundle.putInt("CountComWin", miCountComWin);
            bundle.putInt("CountDraw", miCountDraw);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    };
}

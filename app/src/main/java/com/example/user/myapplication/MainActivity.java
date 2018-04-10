package com.example.user.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView mTxtComPlay,mTxtResult;
    private Button mBtnScissors,mBtnStone,mBtnPaper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTxtComPlay = (TextView) findViewById(R.id.txtComPlay);
        mTxtResult = (TextView) findViewById(R.id.txtResult);
        mBtnScissors = (Button) findViewById(R.id.btnScissors);
        mBtnStone = (Button) findViewById(R.id.btnStone);
        mBtnPaper = (Button) findViewById(R.id.btnPaper);

        mBtnScissors.setOnClickListener(btnScissorsOnclick);
        mBtnStone.setOnClickListener(btnStoneOnClick);
        mBtnPaper.setOnClickListener(btnPaperOnClick);
    }

    private View.OnClickListener btnScissorsOnclick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            onClick onClick = new onClick();
            int iComPlay = (int)(Math.random()*3+1);

            mTxtComPlay.setText(onClick.onClick(iComPlay));

            String comPlay = mTxtComPlay.getText().toString();

            if(comPlay.equals("剪刀")){
                mTxtResult.setText("判斷輸贏：雙方平手！");
            }
            if(comPlay.equals("石頭")){
                mTxtResult.setText("判斷輸贏：很可惜，你輸了！");
            }
            if(comPlay.equals("布")){
                mTxtResult.setText("判斷輸贏：恭喜，你贏了！");
            }

        }
    };

    private  View.OnClickListener btnStoneOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            onClick onClick = new onClick();
            int iComPlay = (int) (Math.random() * 3 + 1);

            mTxtComPlay.setText(onClick.onClick(iComPlay));

            String comPlay = mTxtComPlay.getText().toString();

            if (comPlay.equals("剪刀")) {
                mTxtResult.setText("判斷輸贏：恭喜，你贏了！");
            }
            if (comPlay.equals("石頭")) {
                mTxtResult.setText("判斷輸贏：雙方平手！");
            }
            if (comPlay.equals("布")) {
                mTxtResult.setText("判斷輸贏：很可惜，你輸了！");
            }
        }
    };

    private View.OnClickListener btnPaperOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            onClick onClick = new onClick();
            int iComPlay = (int) (Math.random() * 3 + 1);

            mTxtComPlay.setText(onClick.onClick(iComPlay));

            String comPlay = mTxtComPlay.getText().toString();

            if (comPlay.equals("剪刀")) {
                mTxtResult.setText("判斷輸贏：很可惜，你輸了！");
            }
            if (comPlay.equals("石頭")) {
                mTxtResult.setText("判斷輸贏：恭喜，你贏了！");
            }
            if (comPlay.equals("布")) {
                mTxtResult.setText("判斷輸贏：雙方平手！");
            }
        }
    };

}

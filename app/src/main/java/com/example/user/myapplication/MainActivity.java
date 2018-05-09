package com.example.user.myapplication;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {

    private Button btnStart,btnResult;
    private ImageView rollDice;
    private int miCountSet = 0,
            miCountPlayerWin = 0,
            miCountComWin = 0,
            miCountDraw = 0;


    private static class StaticHandler extends Handler {
        private final WeakReference<MainActivity> mActivity;

        public StaticHandler(MainActivity activity) {
            mActivity = new WeakReference<MainActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            MainActivity activity = mActivity.get();
            if (activity == null) return;

            int iRand = (int) (Math.random() * 6 + 1);
            String s = "";
            activity.miCountSet++;
            if (iRand > 4) {
                s += "很可惜，你輸了！";
                activity.miCountComWin++;
            } else if (iRand >= 3 && iRand <= 4) {
                s += "雙方平手！";
                activity.miCountDraw++;
            } else {
                s += "恭喜，你贏了！";
                activity.miCountPlayerWin++;
            }
            Toast.makeText(activity,s,Toast.LENGTH_LONG).show();
            switch (iRand) {
                case 1:
                    activity.rollDice.setImageResource(R.drawable.dice01);
                    break;
                case 2:
                    activity.rollDice.setImageResource(R.drawable.dice02);
                    break;
                case 3:
                    activity.rollDice.setImageResource(R.drawable.dice03);
                    break;
                case 4:
                    activity.rollDice.setImageResource(R.drawable.dice04);
                    break;
                case 5:
                    activity.rollDice.setImageResource(R.drawable.dice05);
                    break;
                case 6:
                    activity.rollDice.setImageResource(R.drawable.dice06);
                    break;
            }
        }
    }

    public final StaticHandler mHandler = new StaticHandler(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle bundle =this.getIntent().getExtras();
        if(bundle!=null)
        {
            miCountSet = bundle.getInt("CountSet");
            miCountPlayerWin = bundle.getInt("CountPlayerWin");
            miCountComWin = bundle.getInt("CountComWin");
            miCountDraw = bundle.getInt("CountDraw");
        }
        rollDice = (ImageView) findViewById(R.id.imageViewDice);
        btnStart = (Button) findViewById(R.id.btnStart);
        btnResult = (Button) findViewById(R.id.btnShowResult1);


        btnStart.setOnClickListener(btnStartOnclick);
        btnResult.setOnClickListener(btnResultOnclick);
    }
    private View.OnClickListener btnStartOnclick = new View.OnClickListener() {
        public void onClick(View v){

            // 從程式資源中取得動畫檔，設定給ImageView物件，然後開始播放。
            Resources res = getResources();
            final AnimationDrawable animDraw =
                    (AnimationDrawable) res.getDrawable(R.drawable.anim_roll_dice);
            rollDice.setImageDrawable(animDraw);
            animDraw.start();

            // 啟動background thread進行計時。
            new Thread(new Runnable() {

                @Override
                public void run() {
                    try {
                        Thread.sleep(5000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    animDraw.stop();
                    mHandler.sendMessage(mHandler.obtainMessage());
                }
            }).start();
        }
    };
    private View.OnClickListener btnResultOnclick = new View.OnClickListener() {
        public void onClick(View v){
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, ResultActivity.class);
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

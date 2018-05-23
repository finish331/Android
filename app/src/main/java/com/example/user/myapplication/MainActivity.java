package com.example.user.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int MENU_MUSIC = Menu.FIRST,
                            MENU_PLAY_MUSIC = Menu.FIRST + 1,
                            MENU_STOP_PLAYING_MUSIC = Menu.FIRST + 2,
                            MENU_ABOUT = Menu.FIRST + 3,
                            MENU_EXIT = Menu.FIRST + 4;
    private RelativeLayout mRelativeLayout;

    private Spinner itemSpinner;
    private EditText edtDate,edtMoney;
    private DatePicker chooseDate;
    private Button btnInput,btnResult;
    private int itemNumber;
    private String date,money,selectItem,temp;
    private ArrayList<String> result = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle bundle=this.getIntent().getExtras();
        if(bundle!=null){
            result = bundle.getStringArrayList("result");
        }
        mRelativeLayout = (RelativeLayout) findViewById(R.id.relative);
        registerForContextMenu(mRelativeLayout);
        itemSpinner = (Spinner) findViewById(R.id.itemSpinner);
        edtDate = (EditText) findViewById(R.id.edtDate);
        edtMoney = (EditText) findViewById(R.id.edtMoney);
        chooseDate = (DatePicker) findViewById(R.id.datePicker);
        btnInput = (Button) findViewById(R.id.btnBack);
        btnResult = (Button) findViewById(R.id.btnResult);

        resizePikcer(chooseDate);

        btnInput.setOnClickListener(inputOnClick);
        btnResult.setOnClickListener(resultOnClick);
        itemSpinner.setOnItemSelectedListener(OnItemSet);

        int year = chooseDate.getYear();
        int month = chooseDate.getMonth() + 1;
        int day = chooseDate.getDayOfMonth();
        chooseDate.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                monthOfYear += 1;
                date = year + "/" + monthOfYear + "/" + dayOfMonth;
                edtDate.setText(date);
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        SubMenu subMenu = menu.addSubMenu(0,MENU_MUSIC,0,"背景音樂")
                .setIcon(android.R.drawable.ic_media_ff);
        subMenu.add(0,MENU_PLAY_MUSIC,0,"播放背景音樂");
        subMenu.add(0,MENU_STOP_PLAYING_MUSIC,1,"停止播放背景音樂");
        menu.add(0,MENU_ABOUT,1,"關於這個程式...").setIcon(android.R.drawable.ic_dialog_info);
        menu.add(0,MENU_EXIT,2,"結束").setIcon(android.R.drawable.ic_menu_close_clear_cancel);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case MENU_ABOUT:
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("關於這個程式")
                        .setMessage("選單範例程式")
                        .setCancelable(false)
                        .setIcon(android.R.drawable.star_big_on)
                        .setPositiveButton("確定",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                })
                        .show();
                return  true;
            case MENU_EXIT:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu,View v,ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu,v,menuInfo);
        if(v == mRelativeLayout){
            if(menu.size() == 0){
                SubMenu subMenu = menu.addSubMenu(0,MENU_MUSIC,0,"背景音樂");
                subMenu.add(0,MENU_PLAY_MUSIC,0,"播放背景音樂");
                subMenu.add(0,MENU_STOP_PLAYING_MUSIC,1,"停止播放音樂");
                menu.add(0,MENU_ABOUT,1,"關於這個程式...");
                menu.add(0,MENU_EXIT,2,"結束");
            }
        }
    }
    @Override
    public boolean onContextItemSelected(MenuItem item){
        onOptionsItemSelected(item);
        return super.onContextItemSelected(item);
    }


    private void resizePikcer(FrameLayout tp){
        List<NumberPicker> npList = findNumberPicker(tp);
        for(NumberPicker np:npList){
            resizeNumberPicker(np);
        }
    }
    private List<NumberPicker> findNumberPicker(ViewGroup viewGroup){
        List<NumberPicker> npList = new ArrayList<NumberPicker>();
        View child = null;
        if(null != viewGroup){
            for(int i = 0;i<viewGroup.getChildCount();i++){
                child = viewGroup.getChildAt(i);
                if(child instanceof NumberPicker){
                    npList.add((NumberPicker)child);
                }
                else if(child instanceof LinearLayout){
                    List<NumberPicker> result = findNumberPicker((ViewGroup)child);
                    if(result.size()>0){
                        return result;
                    }
                }
            }
        }
        return npList;
    }
    private void resizeNumberPicker(NumberPicker np){
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(100, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(10, 0, 10, 0);
        np.setLayoutParams(params);
    }




    private AdapterView.OnItemSelectedListener OnItemSet = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            selectItem = parent.getSelectedItem().toString();

            switch (selectItem){
                case "Breakfast":
                    selectItem = "Breakfast";
                    break;
                case "Lunch":
                    selectItem = "Lunch";
                    break;
                case "Dinner":
                    selectItem = "Dinner";
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };
    private View.OnClickListener inputOnClick = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            money = edtMoney.getText().toString();
            String temp = Integer.toString(itemNumber);
            Toast.makeText(MainActivity.this,edtMoney.getText(),Toast.LENGTH_LONG).show();
            if(selectItem == "Breakfast") {
                temp = "項目" + itemNumber + "      " + date + "        " + selectItem + "      " + money;
            }
            else if(selectItem == "Lunch") {
                temp = "項目" + itemNumber + "       " + date + "       " + selectItem + "           " + money;
            }
            else if(selectItem == "Dinner") {
                temp = "項目" + itemNumber + "       " + date + "       " + selectItem + "          " + money;
            }
            result.add(temp);
            itemNumber++;
        }
    };

    private View.OnClickListener resultOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this,secondActivity.class);
            Bundle bundle = new Bundle();
            bundle.putStringArrayList("result",result);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    };
}

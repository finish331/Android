package com.example.user.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
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

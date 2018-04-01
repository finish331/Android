package com.example.user.myapplication;

import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private RadioGroup mRadGrp;
    private RadioButton mRadBtnMale;
    private RadioButton mRadBtnfemale;
    private Spinner mSpnAge;
    private CheckBox mInterest1;
    private CheckBox mInterest2;
    private CheckBox mInterest3;
    private CheckBox mInterest4;
    private CheckBox mInterest5;
    private CheckBox mInterest6;
    private CheckBox mInterest7;
    private CheckBox mInterest8;
    private CheckBox mInterest9;
    private CheckBox mInterest10;
    private Button mBtnOK;
    private TextView mTxtSug;
    private TextView mTxtInterset;
    private String selectedAge;

    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRadGrp =(RadioGroup) findViewById(R.id.radGrpSex);
        mRadBtnMale = (RadioButton) findViewById(R.id.radBtnSex1);
        mRadBtnfemale = (RadioButton) findViewById(R.id.radBtnSex2);
        mSpnAge = (Spinner) findViewById(R.id.spnAge);
        mInterest1 = (CheckBox) findViewById(R.id.interest1);
        mInterest2 = (CheckBox) findViewById(R.id.interest2);
        mInterest3 = (CheckBox) findViewById(R.id.interest3);
        mInterest4 = (CheckBox) findViewById(R.id.interest4);
        mInterest5 = (CheckBox) findViewById(R.id.interest5);
        mInterest6 = (CheckBox) findViewById(R.id.interest6);
        mInterest7 = (CheckBox) findViewById(R.id.interest7);
        mInterest8 = (CheckBox) findViewById(R.id.interest8);
        mInterest9 = (CheckBox) findViewById(R.id.interest9);
        mInterest10 = (CheckBox) findViewById(R.id.interest10);
        mBtnOK = (Button) findViewById(R.id.btnOK);
        mTxtSug = (TextView) findViewById(R.id.txtSug);
        mTxtInterset = (TextView) findViewById(R.id.txtInterest);
        mBtnOK.setOnClickListener(btnOKOnClick);
    }
    /*private AdapterView.OnItemSelectedListener spnOnItemSelect = new AdapterView.OnItemSelectedListener(){
        @Override
        public  void onItemSelected(AdapterView<?> parent, View view , int position, long id){
            selectedAge = parent.getSelectedItem().toString();
        }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };-*/
    private View.OnClickListener btnOKOnClick = new View.OnClickListener(){
        @Override
        public  void onClick(View v){
            String strAge = mSpnAge.getSelectedItem().toString();
            String s = "您的興趣：";
            switch (mRadGrp.getCheckedRadioButtonId()) {
                case R.id.radBtnSex1:
                    if(strAge.equals("(男)小於30歲 / (女)小於28歲")){
                        mTxtSug.setText("婚姻建議：還不急");
                    }
                    if(strAge.equals("(男)30~40歲 / (女)28歲~35歲")){
                        mTxtSug.setText("婚姻建議：趕快結婚");
                    }
                    if(strAge.equals("(男)40歲以上 / (女)大於35歲")){
                        mTxtSug.setText("婚姻建議：開始找對象");
                    }
                    break;
                case R.id.radBtnSex2:
                    if(strAge.equals("(男)小於30歲 / (女)小於28歲")){
                        mTxtSug.setText("婚姻建議：還不急");
                    }
                    if(strAge.equals("(男)30~40歲 / (女)28歲~35歲")){
                        mTxtSug.setText("婚姻建議：趕快結婚");
                    }
                    if(strAge.equals("(男)40歲以上 / (女)大於35歲")){
                        mTxtSug.setText("婚姻建議：開始找對象");
                    }
                    break;
            }
            if(mInterest1.isChecked()){
                s += mInterest1.getText().toString();
            }
            if(mInterest2.isChecked()){
                s += mInterest2.getText().toString();
            }
            if(mInterest3.isChecked()){
                s += mInterest3.getText().toString();
            }if(mInterest4.isChecked()){
                s += mInterest4.getText().toString();
            }
            if(mInterest5.isChecked()){
                s += mInterest5.getText().toString();
            }
            if(mInterest6.isChecked()){
                s += mInterest6.getText().toString();
            }if(mInterest7.isChecked()){
                s += mInterest7.getText().toString();
            }
            if(mInterest8.isChecked()){
                s += mInterest8.getText().toString();
            }
            if(mInterest9.isChecked()){
                s += mInterest9.getText().toString();
            }
            if(mInterest10.isChecked()){
                s += mInterest10.getText().toString();
            }
            mTxtInterset.setText(s);

        }
    };

}

package com.example.user.myapplication;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private MainActivity mMainActivity;
    private Spinner mSpnSex;
    private RadioGroup mRadGrp;
    private RadioButton mRadBtnAgeRange1;
    private RadioButton mRadBtnAgeRange2;
    private RadioButton mRadBtnAgeRange3;
    private TextView mTxtNumFamily;
    private NumberPicker mNumPkrFamily;
    private Button mBtnOK;
    private TextView mTxtSug;


    public MainActivityTest() {
        super(MainActivity.class);
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();

        // initialize
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());

        mMainActivity = (MainActivity) getActivity();
        mSpnSex = (Spinner) mMainActivity.findViewById(R.id.spnSex);
//        mRadGrp = (RadioGroup) mMainActivity.findViewById(R.id.radGrpAge);
        mRadBtnAgeRange1 = (RadioButton) mMainActivity.findViewById(R.id.radBtnAgeRange1);
        mRadBtnAgeRange2 = (RadioButton) mMainActivity.findViewById(R.id.radBtnAgeRange2);
        mRadBtnAgeRange3 = (RadioButton) mMainActivity.findViewById(R.id.radBtnAgeRange3);
//        mTxtNumFamily = (TextView) mMainActivity.findViewById(R.id.txtNumFamily);
        mNumPkrFamily = (NumberPicker) mMainActivity.findViewById(R.id.numPkrFamply);
        mBtnOK = (Button) mMainActivity.findViewById(R.id.btnOK);
        mTxtSug = (TextView) mMainActivity.findViewById(R.id.txtSug);
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.user.myapplication", appContext.getPackageName());
    }


    @Test
    public void maleAgeRangeFamily1(){
        mMainActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mSpnSex.setSelection(0);
                mRadBtnAgeRange1.setChecked(true);
                mNumPkrFamily.setValue(12);
                mBtnOK.performClick();
            }
        });

        try{
            Thread.sleep(2000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        assertEquals("建議："+"還不急",mTxtSug.getText().toString());
    }
    @Test
    public void maleAgeRangeFamily2(){
        mMainActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mSpnSex.setSelection(0);
                mRadBtnAgeRange2.setChecked(true);
                mNumPkrFamily.setValue(5);
                mBtnOK.performClick();
            }
        });

        try{
            Thread.sleep(2000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        assertEquals("建議："+"開始找對象",mTxtSug.getText().toString());
    }
    @Test
    public void maleAgeRangeFamily3(){
        mMainActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mSpnSex.setSelection(0);
                mRadBtnAgeRange3.setChecked(true);
                mNumPkrFamily.setValue(3);
                mBtnOK.performClick();
            }
        });

        try{
            Thread.sleep(2000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        assertEquals("建議："+"開始找對象",mTxtSug.getText().toString());
    }
    @Test
    public void maleAgeRangeFamily4(){
        mMainActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mSpnSex.setSelection(1);
                mRadBtnAgeRange1.setChecked(true);
                mNumPkrFamily.setValue(3);
                mBtnOK.performClick();
            }
        });

        try{
            Thread.sleep(2000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        assertEquals("建議："+"趕快結婚",mTxtSug.getText().toString());
    }
    @Test
    public void maleAgeRangeFamily5(){
        mMainActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mSpnSex.setSelection(1);
                mRadBtnAgeRange2.setChecked(true);
                mNumPkrFamily.setValue(5);
                mBtnOK.performClick();
            }
        });

        try{
            Thread.sleep(2000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        assertEquals("建議："+"開始找對象",mTxtSug.getText().toString());
    }
    @Test
    public void maleAgeRangeFamily6(){
        mMainActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mSpnSex.setSelection(1);
                mRadBtnAgeRange3.setChecked(true);
                mNumPkrFamily.setValue(8);
                mBtnOK.performClick();
            }
        });

        try{
            Thread.sleep(2000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        assertEquals("建議："+"趕快結婚",mTxtSug.getText().toString());
    }
}

package com.a105590030.user.myapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final int MENU_ADD = Menu.FIRST;
    private static final String DB_FILE = "phone.db",
            DB_TABLE = "phone";
    private SQLiteDatabase phoneDb;

    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;

    String StoreInfo,name,phone,type;
    ArrayList<String> allPhone = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PhoneStore phoneStore = new PhoneStore(MainActivity.this,DB_FILE,null,1);
        phoneDb = phoneStore.getWritableDatabase();

        Cursor cursor = phoneDb.rawQuery(
                "select DISTINCT tbl_name from sqlite_master where tbl_name = '" +
                        DB_TABLE + "'", null);

        if(cursor != null) {
            if(cursor.getCount() == 0)	// 沒有資料表，要建立一個資料表。
                phoneDb.execSQL("CREATE TABLE " + DB_TABLE + " (" +
                        "_id INTEGER PRIMARY KEY," +
                        "name TEXT NOT NULL," +
                        "sex TEXT," +
                        "address TEXT);");

            cursor.close();
        }

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());


        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(mViewPager);

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        phoneDb.close();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        SearchView searchView = (SearchView)menu.findItem(R.id.menuItemSearch).getActionView();
        searchView.setOnQueryTextListener(searchViewOnQueryTextLis);
        return true;
    }
    private SearchView.OnQueryTextListener searchViewOnQueryTextLis = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            String temp;
            Cursor c = phoneDb.query(true, DB_TABLE, new String[]{"name", "sex",
                    "address"}, "name=" + "\"" + query
                    + "\"", null, null, null, null, null);
            if (c == null) {
                Toast.makeText(MainActivity.this, "請輸入搜尋資料", Toast.LENGTH_LONG).show();
                return false;
            }

            if (c.getCount() == 0) {
                Toast.makeText(MainActivity.this, "沒有這筆資料", Toast.LENGTH_LONG)
                        .show();
            } else {
                c.moveToFirst();
                temp = (c.getString(0) + "        " + c.getString(1)  + "        " + c.getString(2)) + "     is find";
                Toast.makeText(MainActivity.this,temp,Toast.LENGTH_LONG).show();
            }
            return true;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            return false;
        }
    };
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menuItemAdd:
                name=((add_new_contact) getSupportFragmentManager().getFragments().get(0)).name.getText().toString();
                phone=((add_new_contact) getSupportFragmentManager().getFragments().get(0)).phone.getText().toString();
                type=((add_new_contact) getSupportFragmentManager().getFragments().get(0)).storeType;
                StoreInfo = name + "   " + phone + "   " + type;

                ContentValues newRow = new ContentValues();
                newRow.put("name", name);
                newRow.put("sex",phone);
                newRow.put("address", type);
                phoneDb.insert(DB_TABLE, null, newRow);

                Cursor c = phoneDb.query(true, DB_TABLE, new String[]{"name", "sex",
                        "address"}, 	null, null, null, null, null, null);

                if (c == null)
                    return false;

                if (c.getCount() == 0) {
                    Toast.makeText(MainActivity.this, "沒有資料", Toast.LENGTH_LONG)
                            .show();
                }
                else {
                    Toast.makeText(MainActivity.this,"1122",Toast.LENGTH_LONG).show();
                    c.moveToFirst();
                    while (c.moveToNext()) {
                        allPhone.add( c.getString(0) + "        " + c.getString(1)  + "        " +
                                c.getString(2));
                        ArrayAdapter adapter = new ArrayAdapter(MainActivity.this,android.R.layout.simple_expandable_list_item_1,allPhone);
                        ((search_contact) getSupportFragmentManager().getFragments().get(1)).listView.setAdapter(adapter);
                    }
                }

                Toast.makeText(MainActivity.this,StoreInfo,Toast.LENGTH_LONG).show();
                return  true;
        }
        return super.onOptionsItemSelected(item);
    }



    private class SectionsPagerAdapter extends FragmentPagerAdapter{
        public SectionsPagerAdapter(FragmentManager fm){
            super(fm);
        }
        @Override
        public Fragment getItem(int position){
            Fragment fragment = null;

            switch (position){
                case  0:
                    fragment = new add_new_contact();
                    break;
                case 1:
                    fragment = new search_contact();
                    break;
            }
            return  fragment;
        }
        @Override
        public int getCount() {
            return 2;
        }
        @Override
        public CharSequence getPageTitle(int position){
            switch (position){
                case 0:
                    return "建立聯絡人";
                case 1:
                    return "搜尋聯絡人";
                default:
                    return null;
            }
        }

    }
}

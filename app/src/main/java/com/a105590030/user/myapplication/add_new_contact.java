package com.a105590030.user.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class add_new_contact extends Fragment {
    private static final int MENU_ADD = Menu.FIRST;

    public EditText name,phone;
    private Spinner type;

    public String storeName,storePhone,storeType,StoreInfo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_new_contact, container, false);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);

        name = (EditText)getView().findViewById(R.id.inputName);
        phone = (EditText)getView().findViewById(R.id.inputPhone);
        type = (Spinner)getView().findViewById(R.id.type);



        type.setOnItemSelectedListener(spnOnItemSelect);



    }

    private AdapterView.OnItemSelectedListener spnOnItemSelect = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String selectedType = parent.getSelectedItem().toString();
            switch (selectedType){
                case "mobile":
                    storeType = "mobile";
                    break;
                case "home":
                    storeType = "home";
                    break;
                case "office":
                    storeType = "office";
                    break;
            }
        }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };




}

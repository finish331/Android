package com.example.user.myapplication;

/**
 * Created by User on 2018/4/6.
 */

public class onClick {
    String onClick(int iComPlay){
        if(iComPlay == 1){
            return "剪刀";
        }
        else if(iComPlay == 2){
            return "石頭";
        }
        else if(iComPlay == 3){
            return "布";
        }
        return "error";
    }
}

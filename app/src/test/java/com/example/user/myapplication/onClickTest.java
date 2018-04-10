package com.example.user.myapplication;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by User on 2018/4/6.
 */

public class onClickTest {
    private onClick on;

    @Before public void setUp() {
        on = new onClick();
    }
    @After public void tearDown(){
        on = null;
    }

    @Test
    public  void testOnClick(){
        assertEquals("剪刀",on.onClick(1));
        assertEquals("石頭",on.onClick(2));
        assertEquals("布",on.onClick(3));
        assertEquals("error",on.onClick(4));
    }
}

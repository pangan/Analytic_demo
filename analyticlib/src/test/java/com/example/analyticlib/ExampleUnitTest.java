package com.example.analyticlib;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.analyticlib.MainLibClass;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void my_method_isCorrect() throws Exception {
        MainLibClass a = new MainLibClass();
        int num_1 = (int)(Math.random()*100);
        int num_2 = (int)(Math.random()*100);

        assertEquals(a.MyTestMethod(num_1, num_2), num_1+num_2);


    }
}
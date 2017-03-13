package com.example.analyticlib;


import android.os.Build;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by pangan on 28/02/2017.
 *
 */

public class MainLibClass {

    private static Long StartTime;
    private static Class ParentClass;

    private static MainLibClass self = new MainLibClass();
    public static MainLibClass get() { return self; }
    public void init(Class pClass){
        ParentClass = pClass;
        StartTime = System.currentTimeMillis();
    }

    public int MyTestMethod(int num_1, int num_2){
        return num_1+num_2;
    }

    public int SendStatics(){

        new ConnectionTask().execute("http://10.1.1.6:8080/amirone/api/hello", GetStaticData());
        return 0;
    }


    private String GetAppName(){
        String cm = ParentClass.getPackage().getName();
        return cm.toString();

    }

    public JSONObject GetStaticData(){
        JSONObject jo = new JSONObject();
        try {
            jo.put("app_name", GetAppName());
            jo.put("app_ver", ParentClass.getPackage().getImplementationVersion());
            jo.put("start_time", StartTime);
            jo.put("end_time", System.currentTimeMillis());
            jo.put("device", GetDevice());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jo;
    }

    private JSONObject GetDevice(){
        JSONObject jo = new JSONObject();
        try {
            jo.put("manufactorer", Build.MANUFACTURER);
            jo.put("model", Build.MODEL);
            jo.put("os", Build.VERSION.RELEASE);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jo;
    }



}
package com.example.analyticlib;


import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by pangan on 28/02/2017.
 *
 */

public class MainLibClass {

    private static Long StartTime;
    private static Context ParentContext;

    private static MainLibClass self = new MainLibClass();
    public static MainLibClass get() { return self; }
    public void init(Context pContext){
        ParentContext = pContext;
        StartTime = System.currentTimeMillis();
    }

    public int MyTestMethod(int num_1, int num_2){
        return num_1+num_2;
    }

    public int SendStatics(){

        new ConnectionTask().execute("http://10.1.1.6:8080/amirone/api/hello", GetStaticData());
        return 0;
    }


    private JSONObject GetAppInfo(){
        JSONObject jo = new JSONObject();
        try {
            PackageInfo pInfo = ParentContext.getPackageManager().getPackageInfo(
                    ParentContext.getPackageName(), 0);
            jo.put("name", ParentContext.getPackageName());
            jo.put("version", pInfo.versionName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jo;

    }

    public JSONObject GetStaticData(){

        JSONObject jo = new JSONObject();
        try {
            jo.put("application", GetAppInfo());
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
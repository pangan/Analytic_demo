package com.example.analyticlib;


import android.os.Build;

/**
 * Created by pangan on 28/02/2017.
 *
 */

public class MainLibClass {
    private Class ParentClass;
    private Long StartTime;
    public MainLibClass(Class pClass){
        ParentClass = pClass;
        StartTime = System.currentTimeMillis();
    }

    public int MyTestMethod(int num_1, int num_2){
        return num_1+num_2;
    }

    public int SendStatics(){
        return 0;
    }

    public String GetStaticData(){
        String ret_static = GetXMLTag("app_name", GetAppName());
        ret_static += GetXMLTag("start_time", StartTime.toString());
        ret_static += GetXMLTag("device", GetDevice());
        return ret_static;
    }

    private String GetXMLTag(String tag_name, String value){
        return String.format("<%s>%s</%s>", tag_name, value, tag_name);
    }
    private String GetAppName(){
        String cm = ParentClass.getPackage().getName();
        return cm.toString();

    }

    private String GetDevice(){
        String ret_device = GetXMLTag("manufacturer", Build.MANUFACTURER);
        return ret_device;
    }


}
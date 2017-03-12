package com.example.analyticlib;


import android.os.Build;

import org.json.JSONException;
import org.json.JSONObject;

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
        /*int rescode = 0;
        try {
            URL url = new URL("http://localhost:8080/amirone/api/hello");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setInstanceFollowRedirects(false);
            connection.setRequestMethod("PUT");
           // connection.setRequestProperty("Content-Type", "application/xml");

            OutputStream os = connection.getOutputStream();
            os.write(2);
            os.flush();
            //jaxbContext.createMarshaller().marshal("test", os);
            //os.flush();

            rescode = connection.getResponseCode();
            connection.disconnect();
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
        return rescode;*/

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
            jo.put("start_time", StartTime.toString());
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
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
        ret_device += GetXMLTag("model", Build.MODEL);

        return ret_device;
    }


}
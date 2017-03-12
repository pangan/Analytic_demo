package com.example.analyticlib;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by pangan on 09/03/2017.
 */

class ConnectionTask extends AsyncTask<Object, Void, Void> {

    @Override
    protected Void doInBackground(Object... params) {

        String requesturl = (String)params[0];
        JSONObject dataToSend = (JSONObject)params[1];
        int rescode = 0;

        URL url=null;
        try {
            url = new URL(requesturl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        HttpURLConnection conn;

        InputStream in;
        int http_status;
        try {
            conn = (HttpURLConnection) url.openConnection();
            // this opens a connection, then sends GET & headers
            conn.setRequestMethod("PUT");
            conn.setDoOutput(true);
            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
            out.write(dataToSend.toString());
            out.close();
            in = conn.getInputStream();

            // can't get status before getInputStream.  If you try, you'll
            //  get a nasty exception.
            http_status = conn.getResponseCode();

            // better check it first
            if (http_status / 100 != 2) {
                // redirects, server errors, lions and tigers and bears! Oh my!
            }
            conn.disconnect();
        } catch (Exception e) {

        }

        return null;
    }


}
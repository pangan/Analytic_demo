package com.example.analyticlib;

import android.os.AsyncTask;

import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by pangan on 09/03/2017.
 */

class ConnectionTask extends AsyncTask<String, Void, Void> {

    @Override
    protected Void doInBackground(String... params) {
        String requestedurl = params[0];
        String dataToSend = params[1];
        int rescode = 0;

        URL url=null;
        try {
            url = new URL(requestedurl);
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
            out.write(dataToSend);
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

/*
        try {
            URL url = new URL("http://127.0.0.1:8080/amirone/api/hello");
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            //httpCon.setDoOutput(true);
            httpCon.setRequestMethod("PUT");
            OutputStreamWriter out = new OutputStreamWriter(
                    httpCon.getOutputStream());
            out.write("Resource content");
            out.close();
            httpCon.getInputStream();
            //httpCon.getResponseCode();
        } catch(Exception e) {
            throw new RuntimeException(e);
        }*/
        return null;
    }


}
package com.brioal;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by null on 15-11-4.
 *
 */
public class GetLzu {



    public static Info Post(Info info) {
        Info result = new Info();
        HttpURLConnection connection = null ;
        URL url;
        try {
         
            url = new URL(info.getUrl());
            connection = (HttpURLConnection) url.openConnection();//????Connection????
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            if (info.getCookie() != null) {
                connection.setRequestProperty("Cookie", "JSESSIONID="+info.getCookie());
            }
            connection.getOutputStream().write(info.getData().getBytes());
            connection.connect(); //????
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                String s =  GetLzu.getHtml(connection.getInputStream(), "UTF-8");
                result.setData(s);
                int a = s.lastIndexOf("jsessionid=");
                int b = s.indexOf("rel");
                if (info.getCookie() == null) {
                    result.setCookie(s.substring(a + 11, b - 2));

                }
                connection.disconnect();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  result;
    }

    //
    public static String Get(Info info) {
        String result = null;
        try {
            URL url = new URL(info.getUrl());
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setRequestProperty("Cookie", "JSESSIONID="+info.getCookie());
                httpURLConnection.connect();
                result = GetLzu.getHtml(httpURLConnection.getInputStream(), "UTF-8");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return  result;
    }


    public static String getHtml(InputStream inputStream, String encode) {
        InputStream is = inputStream;
        String code = encode;
        BufferedReader reader = null;
        StringBuffer sb = null;
        try {
            reader = new BufferedReader(new InputStreamReader(inputStream, encode));
            sb = new StringBuffer();
            String str = null;
            while ((str = reader.readLine()) != null) {
                if (str.isEmpty()) {

                } else {
                    sb.append(str);
                    sb.append("\n");
                }
            }
            reader.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

}

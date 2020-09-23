package com.kish2.mvpdemo.util;

import android.util.Log;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpUtils {

    private static String propertyName = "/assets/requestConfig.properties";

    private static Properties properties = new Properties();

    private static String listUrl;

    static {
        try {
            InputStream resourceAsStream = OkHttpUtils.class.getResourceAsStream(propertyName);
            properties.load(resourceAsStream);
            listUrl = properties.getProperty("url");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static String getList() {
        String string = null;
        try {
            // 创建OkHttpClient对象
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(listUrl).build();
            Response response = client.newCall(request).execute();
            string = Objects.requireNonNull(response.body()).string().trim();
            //  if (response.isSuccessful()) {
            //      Log.d("toString", Objects.requireNonNull(response.body()).toString());
            //      Log.d("string", string);
            //      Log.d("code", String.valueOf(response.code()));
            //      Log.d("message", response.message());
            //      Log.d("string", string);
            //  }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return string;
    }


}

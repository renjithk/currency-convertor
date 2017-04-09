package com.currency.convertor.ui.helper;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

/**
 * Helper class for view related functionality
 *
 * Created by Renjith Kandanatt on 09/04/2017.
 */

public class ViewUtils {
    public static String getJsonFor(String fileName, Context context) {
        String resultJson = null;
        InputStream inputStream = null;
        try {
            InputStream is = context.getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            resultJson = new String(buffer, "UTF-8");
        } catch(Exception ex) {
            resultJson = null;
        } finally {
            if(null != inputStream) try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultJson;
    }
}

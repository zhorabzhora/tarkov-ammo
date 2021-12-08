package com.zhora.sheetsexample;

import android.app.*;
import android.os.*;

import androidx.annotation.*;

import org.json.*;

import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;

public class ReadJson {

    public static JSONArray ReadThing(Activity activity, String arrayName) {


        String stringy = null;

        try{
            InputStream inputStream = activity.getAssets().open("ammo.json");
            int size = inputStream.available();
            byte[] byteArray = new byte[size];
            inputStream.read(byteArray);
            inputStream.close();
            stringy = new String(byteArray, "UTF-8");
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }


        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(stringy);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONArray array = null;
        try {
            array = jsonObject.getJSONArray(arrayName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject arrayObject = null;



        return array;
    }

    @NonNull
    @RequiresApi(api = Build.VERSION_CODES.O)
    static String readFiles(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
}

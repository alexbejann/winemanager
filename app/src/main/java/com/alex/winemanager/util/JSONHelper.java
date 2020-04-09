package com.alex.winemanager.util;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.alex.winemanager.model.Wine;
import com.alex.winemanager.model.WineAdmin;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class JSONHelper {

    private static final String FILE_NAME="wines.json";
    public static final String TAG="JSONHelper";

    public static void readFromJSON(WineAdmin wineAdmin, InputStream inputStream) { // Read and import from JSON file
        String json;
        try {
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");
            //JSONArray jsonArray = new JSONArray(json);
            Gson gson = new Gson();
            WineItem item = gson.fromJson(json, WineItem.class);

            for (int i = 0; i < item.wines.size(); i++) {

                wineAdmin.addWineObject(item.wines.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean exportToJSON(Context context, List<Wine> wines){
        WineItem wineItem=new WineItem();
        wineItem.setWines(wines);

        Gson gson=new Gson();
        String jsonString =gson.toJson(wineItem);

        FileOutputStream outputStream=null;
        File file=new File(context.getFilesDir()+"/"+FILE_NAME);
        Log.i(TAG,file.toString()+"  FilePath");
        try {
            outputStream=new FileOutputStream(file);

            outputStream.write(jsonString.getBytes());
            return true;

        } catch (IOException e) {
            Toast.makeText(context,"File Exception: "+e.getMessage(),Toast.LENGTH_SHORT);
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                Toast.makeText(context,"File Exception: "+e.getMessage(),Toast.LENGTH_SHORT);
            }
        }
        return false;
    }
    public static void importFromJSON(Context context) throws IOException {

        Log.i(TAG,"Came here to fetch data");
        File file=new File(context.getFilesDir()+"/"+FILE_NAME);
        WineAdmin wineAdmin = WineAdmin.getInstance();
        if (file.exists()){
            readFromJSON(wineAdmin,new FileInputStream(file));
        }else{
            InputStream inputStream = context.getAssets().open("wines.json");
            readFromJSON(wineAdmin, inputStream);
        }
    }

    public static class WineItem{
        List<Wine> wines;

        List<Wine> getWines() {
            return wines;
        }

        void setWines(List<Wine> wines) {
            this.wines = wines;
        }
    }
}

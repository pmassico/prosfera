package com.example.prosfera;

import android.content.Context;
import android.content.res.AssetManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ItemList {

    // An array of Items
    private static final List<Item> ITEMS = new ArrayList<>();

    // Constructor
    public ItemList() {
        // Add some sample items.
        for (int i = 0; i < 3; i++) {
            ITEMS.add(createDummyItem(i, 5, "Item "+i, "Dummy item", i, 10));
        }
    }

    private static Item createDummyItem(int itID, int currQty, String nm, String desc, int prce,
                                        int thresh){

        return new Item(itID, currQty, nm, desc, prce, thresh);
    }

    public Item getItem(int pos) {
        return ITEMS.get(pos);
    }

    public int getSize(){ return ITEMS.size(); }


    // This method is used to read in the items.json file and create a list of Item objects.
    //**IMPORTANT: See comment on loadJSONFromAsset() method below on passed context parameter**
    public ArrayList<Item> initItemList(Context context){
        try {
            //List to hold the Item objects
            ArrayList<Item> itemList = new ArrayList<>();

            //Assuming that the default current quantity on startup is 1
            int default_qty = 1;

            JSONObject obj = new JSONObject(loadJSONFromAsset("items.json", context));
            JSONArray itemsArray = obj.getJSONArray("items");

            for (int i=0; i < itemsArray.length(); i++){

                JSONObject item = itemsArray.getJSONObject(i);
                String name = item.getString("name");
                String desc = item.getString("description");
                int price = item.getInt("price");
                String image = item.getString("image");
                int threshold = item.getInt("threshold");

                Item itm = new Item(i+1, default_qty, name, desc, price, threshold, image);
                //For testing:
                //System.out.println(itm);
                itemList.add(itm);
            }

            return itemList;
        }
        catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }


    //**IMPORTANT: getAssets() worked in mainActivity, but now it needs to be passed a context since it is in a different class**
    //This method can be used to read a JSON string from a file in the assets folder.
    //See https://stackoverflow.com/questions/19945411/android-java-how-can-i-parse-a-local-json-file-from-assets-folder-into-a-listvi/19945484#19945484
    public String loadJSONFromAsset(String assetName, Context context){
        String json;
        try {
            InputStream in = context.getAssets().open(assetName);
            int size = in.available();
            byte[] buffer = new byte[size];
            in.read(buffer);
            in.close();

            json = new String(buffer,"UTF-8");
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return json;
    }
}


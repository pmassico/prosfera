package com.example.prosfera;

import android.content.res.Resources;
import android.os.Bundle;
import java.io.*;
import org.json.*;
import java.util.ArrayList;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.TypedValue;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Item> items = initItemList(); //Initializing a list of items from items.json

        // I am leaving this for now, I don't want to delete Phil's stuff - Christine
        ItemList il = new ItemList();

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ItemFragment iF;

        ft.add(R.id.fragContainer, new ItemFragment(), "fragment").commit();
        iF = (ItemFragment) fm.findFragmentByTag("fragment");

        /**
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });**/

    }

    // Gets called to fill information
    public void fillFragments(ItemList il) {

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ItemFragment iF;


        //iF = (ItemFragment) fm.findFragmentByTag("fragment" +i);
        //iF.fillDetails(il.getItem(i));

        /**
         // For (size of ItemList), fill fragments with items
         for (int i = 0; i <= il.getSize(); i++) {

         }**/


    }

    public int getPixels(int dpToConvert) {

        Resources r = getResources();
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpToConvert, r.getDisplayMetrics());

        return px;
      
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    // This method is used to read in the items.json file and create a list of Item objects.
    public ArrayList<Item> initItemList(){
        try {
            //List to hold the Item objects
            ArrayList<Item> itemList = new ArrayList<>();

            //Assuming that the default current quantity on startup is 0 (maybe 1?)
            int default_qty = 0;

            JSONObject obj = new JSONObject(loadJSONFromAsset("items.json"));
            JSONArray itemsArray = obj.getJSONArray("items");

            for (int i=0; i < itemsArray.length(); i++){

                JSONObject item = itemsArray.getJSONObject(i);
                String name = item.getString("name");
                String desc = item.getString("desc");
                int price = item.getInt("price");
                String image = item.getString("image");
                int threshold = item.getInt("threshold");

                Item itm = new Item(i, default_qty, name, desc, price, threshold, image);
                //For testing:
                System.out.println(itm);
                itemList.add(itm);
            }

            return itemList;
        }
        catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }


    //This method can be used to read a JSON string from a file in the assets folder.
    //See https://stackoverflow.com/questions/19945411/android-java-how-can-i-parse-a-local-json-file-from-assets-folder-into-a-listvi/19945484#19945484
    public String loadJSONFromAsset(String assetName){
        String json = null;
        try {
            InputStream in = getAssets().open(assetName);
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

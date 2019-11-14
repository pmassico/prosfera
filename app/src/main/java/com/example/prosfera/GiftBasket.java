package com.example.prosfera;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GiftBasket extends AppCompatActivity {

    private static final String TAG = "GiftBasket";

    private int    basketID,
                   donorID,
                   basketItemID;
    private double total;

    // temp vars for testing
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private ArrayList<String> mPrices = new ArrayList<>();
    private int[] mProgress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO: upon merge, change reference to XML file on this line
        setContentView(R.layout.activity_basket);

        //Bundle itemData = getIntent().getExtras();
        ItemList il = new ItemList();

        // Populates basket
        initImageBitmaps();

        // handles if data returned is null
        /**
        if (itemData == null) {
            return;
        }**/

        // get info from itemData bundle
        //int itemId = itemData.getInt("itemId");

        // use itemId to reference item and get its name
        //String itemName = il.getItem(itemId).getName();

        // instantiate views and set text
        //final TextView detailsTitle = findViewById(R.id.detailsTitle);
        //detailsTitle.setText(itemName);
    }
//database connection here

    /*
        Constructor Class to initialize variables.
     */
    public GiftBasket() {
        // Blank constructor required by activity
        // args int dID, int bIID
        //this.basketID = 001; //this would actually be pulled from the database and incremented as needed
        //this.donorID = dID;
        //this.basketItemID = bIID;
        //this.total = 0;
    }

    /*
        Getters for basketID, donorID, basketItemID, and total.
     */
    public int getBasketID() { return this.basketID; }
    public int getDonorID() { return this.donorID; }
    public int getBasketItemID() { return this.basketItemID; }
    public double getTotal() { return this.total; }

    /*
        Setters for basketID, donorID, basketItemID, and total.
     */
    public void setDonorID(int dID) { this.donorID = dID; } //might not need
    public void setBasketItemID(int bIID) { this.basketItemID = bIID; } //might not need

    private void initBasketRecyclerView(){
        Log.d(TAG, "initBasketRecyclerView: called.");

        RecyclerView rv1 = findViewById(R.id.basketRecycler);

        // TODO: Conditionally load data structures (wishlistNames or basketNames)
        // TODO: Add progress bar and prices to constructor
        RecyclerViewAdapter adapter1 = new RecyclerViewAdapter(this, mNames, mImageUrls);
        rv1.setAdapter(adapter1);
        rv1.setLayoutManager(new LinearLayoutManager(this));
        rv1.setHasFixedSize(true);
    }

    // FOR TESTING
    // This is how the basket is being populated
    private void initImageBitmaps(){
        Log.d(TAG, "initImageBitmaps: preparing wishlist bitmaps.");


        //TODO: add reference to items
        mImageUrls.add("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg");
        mNames.add("Havasu Falls");

        mImageUrls.add("https://i.redd.it/tpsnoz5bzo501.jpg");
        mNames.add("Trondheim");

        mImageUrls.add("https://i.redd.it/qn7f9oqu7o501.jpg");
        mNames.add("Portugal");

        mImageUrls.add("https://i.redd.it/j6myfqglup501.jpg");
        mNames.add("Rocky Mountain National Park");

        /**
        mImageUrls.add("https://i.redd.it/0h2gm1ix6p501.jpg");
        mNames.add("Mahahual");

        mImageUrls.add("https://i.redd.it/k98uzl68eh501.jpg");
        mNames.add("Frozen Lake");

        mImageUrls.add("https://i.redd.it/glin0nwndo501.jpg");
        mNames.add("White Sands Desert");

        mImageUrls.add("https://i.redd.it/obx4zydshg601.jpg");
        mNames.add("Austrailia");

        mImageUrls.add("https://i.imgur.com/ZcLLrkY.jpg");
        mNames.add("Washington");
        **/
        initBasketRecyclerView();

    }

}

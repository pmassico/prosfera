package com.example.prosfera;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.util.TypedValue;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    //vars
    private ItemList il = new ItemList();
    private Item featuredItem;
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Log that activity started
        Log.d(TAG, "onCreate: started.");

        // TODO: IMPORTANT! app does not run because itemlist needs to be instantiated
        initFeaturedItem(il.getItem(0));
        initImageBitmaps();

        /**
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });**/

    }


    public void onFeaturedClick(View view){
        Log.d(TAG, "onFeaturedClick: card clicked.");
        Intent i = new Intent(this, Featured.class);

        // instantiate view from which to grab data
        //final TextView applesInput = findViewById(R.id.applesInput);

        // convert data to string
        //String usermessage = applesInput.getText().toString();

        // passes this id of featured item to activity
        // uses itemlist to grab item based on Id
        i.putExtra("itemId", featuredItem.getItemID());

        startActivity(i);
    }

    // Populates featured item card with information
    private void initFeaturedItem(Item item) {
        // Note: Should featured item be removed from regular wishlist?
        this.featuredItem = item;


        // initialize views
        final TextView featuredDescription = findViewById(R.id.featuredDescription);
        final TextView featuredTitle = findViewById(R.id.featuredTitle);
        //final ImageView featuredImage = findViewById(R.id.featuredPic);

        // set their contents
        featuredTitle.setText(item.getName());
        featuredDescription.setText(item.getDescription());
        //featuredImage.setImageResource();

        // Should we dispose of the text/image views after we're done using them? Probably?
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

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: ");

        RecyclerView rv = findViewById(R.id.recyclerView);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mNames, mImageUrls);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }

    // Set which images/data to load
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

        initRecyclerView();

    }

}

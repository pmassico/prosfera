package com.example.prosfera;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Featured extends AppCompatActivity {

    // grab item
    // set fields to item fields
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO: upon merge, change reference to XML file on this line
        setContentView(R.layout.activity_featured);

        Bundle itemData = getIntent().getExtras();
        ItemList il = new ItemList();

        // handles if data returned is null
        if (itemData == null) {
            return;
        }

        // get info from itemData bundle
        int itemId = itemData.getInt("itemId");

        // use itemId to reference item and get its name
        String itemName = il.getItem(itemId).getName();

        // instantiate views and set text
        final TextView detailsTitle = findViewById(R.id.detailsTitle);
        detailsTitle.setText(itemName);
    }
}

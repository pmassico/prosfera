package com.example.prosfera;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

// See https://larntech.net/android-custom-listview-that-open-new-activity-to-display-clicked-item/

public class ItemDetails extends AppCompatActivity {

    private static final String TAG = "ItemDetails";
    TextView details_item_Name, details_description;
    ImageView details_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        Log.d(TAG, "onCreate: started.");

        details_item_Name = findViewById(R.id.details_item_name);
        details_description = findViewById(R.id.details_description);
        details_image =  findViewById(R.id.details_image);

        try {
            Intent intent = this.getIntent();
            Bundle bundle = intent.getExtras();

            Item receivedItem = (Item) bundle.getSerializable("itemObj");

            String receivedName = receivedItem.getName();
            String receivedDesc = receivedItem.getDescription();
            String receivedImage = receivedItem.getImageFile();

            details_item_Name.setText(receivedName);
            details_description.setText(receivedDesc);

            int resID = getResources().getIdentifier(receivedImage, "drawable", getPackageName());
            details_image.setImageResource(resID);
        }
        catch(NullPointerException e){
            System.out.println("ItemDetails.java: Could not get item details");
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}

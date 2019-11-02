package com.example.prosfera;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class ItemFragment extends Fragment {

    private TextView itemName, itemPrice;
    private ProgressBar bar;
    private ImageView pic;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemFragment() {
    }

    // Summon TextViews, map values to TextViews
    // In future, this will be a loop with a reference to a List<Item>
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Point to fragment xml file
        View view = inflater.inflate(R.layout.wishlist_item, container, false);

        // Point to elements in fragment
        // TODO: Need to add ID so that new fragments can reference their own components
        itemName = (TextView) view.findViewById(R.id.itemName);
        itemPrice = (TextView) view.findViewById(R.id.itemPrice);
        bar = (ProgressBar) view.findViewById(R.id.progressBar); //progress, max

        return view;
    }

    // Not sure if above constructor needs to be empty or not??
    public void fillDetails(Item item) {
        itemName.setText(item.getName());
        itemPrice.setText(item.getPrice());
        bar.setMax(item.getThreshold());
        bar.setProgress(item.getCurrentQty());
        /**
        setText_itemName(item);
        setText_itemPrice(item);
        setMax_bar(item);
        setProgress_bar(item);**/
    }

    public String getText_itemName(){
        return (String)itemName.getText();
    }
    // Made setters just in case
    public void setText_itemName(Item item){
        itemName.setText(item.getName());
    }

    public void setText_itemPrice(Item item){
        itemPrice.setText(item.getPrice());
    }

    public void setMax_bar(Item item){
        itemName.setText(item.getName());
    }

    public void setProgress_bar(Item item){
        itemName.setText(item.getName());
    }

}

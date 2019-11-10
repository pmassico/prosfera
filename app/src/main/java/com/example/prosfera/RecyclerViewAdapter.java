package com.example.prosfera;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.PopupWindow;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.zip.Inflater;

import de.hdodenhof.circleimageview.CircleImageView;

// Recycler view class taken from "RecyclerView" tutorial by CodingWithMitch
// src: https://www.youtube.com/watch?v=Vyqz_-sJGFk

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";
    private ItemList mItemList;
    private ArrayList<String> mImageNames = new ArrayList<>();
    private ArrayList<Integer> mImages = new ArrayList<>(); //changed to be resIDs
    private ArrayList<Integer> mPrices = new ArrayList<>();
    private ArrayList<Integer> mProgress = new ArrayList<>();
    private Context mContext;
    private PopupWindow popup;

    // TODO: Change objects in constructor, add progressbar and price
    public RecyclerViewAdapter(Context mContext, ArrayList<String> mImageNames, ArrayList<Integer> mImages, ArrayList<Integer> mPrices, ArrayList<Integer> mProgress) {
        this.mImageNames = mImageNames;
        this.mImages = mImages;
        this.mPrices = mPrices;
        this.mContext = mContext;
        this.mProgress = mProgress;
         this.mItemList = new ItemList(mContext);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // If page == Basket, load recycler view with the session items
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");
        //Glide.with(mContext)
         //       .asBitmap()
         //       .load(mImages.get(position))
         //       .into(holder.image);

        holder.image.setImageResource(mImages.get(position));
        holder.imageName.setText(mImageNames.get(position));
        holder.price.setText(Integer.toString(mPrices.get(position)));
        holder.progress.setMax(100);
        holder.progress.setProgress(mProgress.get(position));

        final Item clickedItem = mItemList.getItem(position);

        holder.rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG, "onClick: clicked on: " + mImageNames.get(position));

                // Inflates 'layout_listitem_popup.xml' as a centered popup window
                View container = LayoutInflater.from(mContext).inflate(R.layout.layout_listitem_popup, null);
                popup = new PopupWindow(container, ViewPager.LayoutParams.MATCH_PARENT, ViewPager.LayoutParams.WRAP_CONTENT);
                popup.showAtLocation(v, Gravity.CENTER, 0, 0);

                // Find elements in the popup container
                TextView name = container.findViewById(R.id.image_name);
                final EditText qty = container.findViewById(R.id.itemQty);
                TextView price = container.findViewById(R.id.itemPrice);
                ProgressBar progress = container.findViewById(R.id.progressView);
                CircleImageView img = container.findViewById(R.id.image);

                // Set data of popup window to match clicked element
                name.setText(clickedItem.getName());
                qty.setText("1");
                price.setText(Integer.toString(clickedItem.getPrice())); //calculated total?
                progress.setProgress(clickedItem.getCalculatedPerc());
                img.setImageResource(clickedItem.getImageResID(mContext));

                // Find button elements of popup container
                TextView details = container.findViewById(R.id.button_details);
                CircleImageView qty_increment = container.findViewById(R.id.button_qty_increment);
                CircleImageView qty_decrement = container.findViewById(R.id.button_qty_decrement);
                CircleImageView okay_button = container.findViewById(R.id.button_ok);
                CircleImageView exit_button =container.findViewById(R.id.button_exit);

                //TODO: implement click handlers for buttons

                //test change quantity

                details.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v){
                        Log.d(TAG, "onClick: clicked on details button");
                        Intent intent = new Intent(mContext, ItemDetails.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("itemObj", clickedItem);
                        intent.putExtras(bundle);

                        mContext.startActivity(intent);
                        }
                });

                qty_increment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v){
                        Log.d(TAG, "onClick: clicked on increment button");
                        int qty_text = Integer.parseInt(qty.getText().toString().trim());
                        qty_text++;
                        qty.setText(Integer.toString(qty_text));
                    }
                });


                container.setOnTouchListener(new View.OnTouchListener(){
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        popup.dismiss();
                        return true;
                    }
                });
            }
        });
    }

    // size of item list = number of items loaded into recyclerV iew
    @Override
    public int getItemCount() { return mImageNames.size(); }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView image;
        TextView imageName;
        TextView price;
        ProgressBar progress;
        RelativeLayout rl;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            imageName = itemView.findViewById(R.id.image_name);
            price = itemView.findViewById(R.id.itemPrice);
            progress = itemView.findViewById(R.id.progressView);
            rl = itemView.findViewById(R.id.parent_layout);
        }
    }
}


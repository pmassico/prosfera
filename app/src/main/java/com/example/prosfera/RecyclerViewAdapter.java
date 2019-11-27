package com.example.prosfera;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.PopupWindow;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import de.hdodenhof.circleimageview.CircleImageView;

// Recycler view class taken from "RecyclerView" tutorial by CodingWithMitch
// src: https://www.youtube.com/watch?v=Vyqz_-sJGFk

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";
    private ItemList mItemList;
    private ArrayList<String> mImageNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private ArrayList<Integer> mPrices = new ArrayList<>();
    private ArrayList<Integer> mProgress = new ArrayList<>();
    private ArrayList<Integer> mQuantities = new ArrayList<>();
    private Context mContext;
    private PopupWindow popup;

    // TODO: Change objects in constructor, add progressbar and price
    public RecyclerViewAdapter(Context mContext, ArrayList<String> mImageNames, ArrayList<String> mImageUrls, ArrayList<Integer> mPrices, ArrayList<Integer> mProgress,
                               ArrayList<Integer> mQuantities) {
        this.mImageNames = mImageNames;
        this.mImageUrls = mImageUrls;
        this.mPrices = mPrices;
        this.mContext = mContext;
        this.mProgress = mProgress;
        this.mQuantities = mQuantities;
         this.mItemList = new ItemList(mContext);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // TODO: Add condition: if loaded via this button or page -> inflate this item
        // If loaded by wishlist, inflate layout_listitem
        // If loaded by activity_basket, inflate layout_basketitem

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called with 2 parameters.");
        Glide.with(mContext)
                .asBitmap()
                .load(mImageUrls.get(position))
                .into(holder.image);

        bindFunc(holder, position);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position, List<Object> qtyArray) {
        Log.d(TAG, "onBindViewHolder: called with 3 parameters.");
        if(!qtyArray.isEmpty()) {
            if (qtyArray.get(0) instanceof String) {
                holder.quantity.setText(String.valueOf(qtyArray.get(0)));
            }
            //ArrayList<String> newQty = new ArrayList<String>();
            //for (int i = 0; i < qtyArray.size(); i++) {
            //    newQty.add(qtyArray.get(i).toString());
            //}
            //holder.quantity.setText(newQty.get(position));
            //bindFunc(holder, position);
        } else {
            super.onBindViewHolder(holder,position, qtyArray);
        }
    }

    private void bindFunc(@NonNull ViewHolder holder, final int position){

        //holder.image.setImageResource(mImageUrls.get(position));
        holder.imageName.setText(mImageNames.get(position));
        holder.price.setText(Integer.toString(mPrices.get(position)));
        holder.quantity.setText(Integer.toString(mQuantities.get(position)));
        holder.progress.setMax(100);
        holder.progress.setProgress(mProgress.get(position));

        final Item clickedItem = mItemList.getItem(position);

        holder.rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG, "onClick: clicked on: " + mImageNames.get(position));

                ViewGroup root = (ViewGroup) v.getRootView();
                final ScrollView sv = (ScrollView) root.findViewById(R.id.scroll);
                sv.setSmoothScrollingEnabled(true);

                // Inflates 'layout_listitem_popup.xml' as a centered popup window
                final View container = LayoutInflater.from(mContext).inflate(R.layout.layout_listitem_popup, root, false);
                popup = new PopupWindow(container, ViewPager.LayoutParams.MATCH_PARENT, ViewPager.LayoutParams.WRAP_CONTENT);

                // Closes the popup window when touched outside.
                popup.setOutsideTouchable(true);
                popup.setFocusable(true);
                // Removes default background.
                popup.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                final int[] screenPos = new int[2];
                v.getLocationOnScreen(screenPos);

                popup.showAsDropDown(v, 0, -v.getHeight() + container.getHeight()-150);

                // This gets the height of the popup (before it has been drawn)
                container.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
                final int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
                final int popupHeight = container.getMeasuredHeight();

                Log.d(TAG, "Screen height is " + screenHeight);
                Log.d(TAG, "Clicked item is at " + screenPos[0] +" and "+screenPos[1] );
                Log.d(TAG, "Popup height is " + popupHeight );

                final int[] vertical_scroll = new int[]{0};

                //if the item it too far down to entirely show the popup, scroll down
                if(screenPos[1] > (screenHeight-popupHeight) && !(screenPos[1] < popupHeight)) {
                    vertical_scroll[0] = popupHeight - (screenHeight - screenPos[1]);
                }
                //if the item it high up to entirely show the popup, scroll up
                else if(!(screenPos[1] > (screenHeight-popupHeight)) && (screenPos[1] < popupHeight)) {
                    vertical_scroll[0] = screenPos[1]-popupHeight + v.getHeight();
                }

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        sv.smoothScrollBy(0, vertical_scroll[0]);
                    } }, 200);

                View parent;
                if (android.os.Build.VERSION.SDK_INT > 22) {
                    parent = (View) popup.getContentView().getParent().getParent();
                }else{
                    parent = (View) popup.getContentView().getParent();
                }
                //dim the window in the background
                WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
                WindowManager.LayoutParams p = (WindowManager.LayoutParams) parent.getLayoutParams();
                p.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
                p.dimAmount = 0.4f;
                wm.updateViewLayout(parent, p);


                // Find elements in the popup container
                TextView name = container.findViewById(R.id.image_name);
                final EditText qty = container.findViewById(R.id.itemQty);
                TextView price = container.findViewById(R.id.itemPrice);
                ProgressBar progress = container.findViewById(R.id.progressView);
                CircleImageView img = container.findViewById(R.id.image);

                // Set data of popup window to match clicked element
                name.setText(clickedItem.getName());
                //qty.getText().toString().trim();
                qty.setText("1");
                price.setText(Integer.toString(clickedItem.getPrice())); //calculated total?
                progress.setProgress(clickedItem.getCalculatedPerc());
                Glide.with(mContext)
                        .asBitmap()
                        .load(mImageUrls.get(position))
                        .into(img);

                // Find button elements of popup container
                TextView details = container.findViewById(R.id.button_details);
                ImageButton qty_increment = container.findViewById(R.id.button_qty_increment);
                ImageButton qty_decrement = container.findViewById(R.id.button_qty_decrement);
                ImageButton okay_button = container.findViewById(R.id.button_ok);
                ImageButton exit_button =container.findViewById(R.id.button_exit);

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

                qty_decrement.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v){
                        Log.d(TAG, "onClick: clicked on decrement button");
                        int qty_text = Integer.parseInt(qty.getText().toString().trim());
                        if(qty_text > 1){
                            qty_text--;
                        }
                        qty.setText(Integer.toString(qty_text));
                    }
                });

                okay_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v){
                        Log.d(TAG, "onClick: clicked on okay button");
                        //mQuantities.set(position, Integer.parseInt(qty.getText().toString().trim()));
                        //RecyclerViewAdapter.this.notifyItemChanged(position, mQuantities);
                        RecyclerViewAdapter.this.notifyItemChanged(position, qty.getText().toString().trim());
                        popup.dismiss();
                    }
                });

                exit_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v){
                        Log.d(TAG, "onClick: clicked on exit button");
                        popup.dismiss();
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
        EditText quantity;
        RelativeLayout rl;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            imageName = itemView.findViewById(R.id.image_name);
            price = itemView.findViewById(R.id.itemPrice);
            progress = itemView.findViewById(R.id.progressView);
            quantity = itemView.findViewById(R.id.itemQty);
            rl = itemView.findViewById(R.id.parent_layout);
        }
    }
}


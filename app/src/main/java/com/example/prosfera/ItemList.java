package com.example.prosfera;

import java.util.ArrayList;
import java.util.List;

public class ItemList {

    // An array of Items
    private static final List<Item> ITEMS = new ArrayList<>();

    // Constructor
    public ItemList() { }

    public void addItem(Item item) {
        ITEMS.add(item);
    }

    private static Item createDummyItem(int itID, int currQty, String nm, String desc, int prce,
                                        int thresh){

        return new Item(itID, currQty, nm, desc, prce, thresh);
    }

    public Item getItem(int pos) {
        return ITEMS.get(pos);
    }

    public int getSize(){ return ITEMS.size(); }


}


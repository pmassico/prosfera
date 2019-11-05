package com.example.prosfera;

import java.util.ArrayList;
import java.util.List;

public class ItemList {

    // An array of Items
    // Should be common among all itemlist instantiations
    private static final List<Item> ITEMS = new ArrayList<>();

    // Constructor
    public ItemList() { }

    public void addItem(Item item) {
        ITEMS.add(item);
    }

    public Item getItem(int pos) { return ITEMS.get(pos); }

    public int getSize(){ return ITEMS.size(); }

}


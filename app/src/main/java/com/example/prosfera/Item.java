package com.example.prosfera;

import java.io.Serializable; //This is needed in order to pass objects using intents (i.e. in ItemDetails activity)

public class Item implements Serializable{

    private int itemID, currentQty, price, threshold, calculatedPerc;
    private String name, description;
    private String imageFile; //not sure how an image will be stored
    //visible (flag)
    //featured/promoted (flag)

    public Item(int itID, int currQty, String nm, String desc, int prce,
                int thresh) {
        this.itemID      = itID;
        this.currentQty  = currQty;
        this.name        = nm;
        this.description = desc;
        this.price       = prce;
        this.threshold   = thresh;
        this.imageFile   = null;
        updatePercentage(); //constructor doesn't really need to update the percentage
    }

    //Overloading constructor to include image parameter (temporarily?)
    public Item(int itID, int currQty, String nm, String desc, int prce,
                int thresh, String image) {
        this.itemID      = itID;
        this.currentQty  = currQty;
        this.name        = nm;
        this.description = desc;
        this.price       = prce;
        this.threshold   = thresh;
        this.imageFile   = image;
        updatePercentage(); //constructor doesn't really need to update the percentage
    }

    public int getItemID()
    {
        return itemID;
    }

    public int getCurrentQty()
    {
        return currentQty;
    }

    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }

    public int getPrice() { return price; }

    public int getThreshold() { return threshold; }

    public String getImageFile()
    {
        return imageFile;
    }

    public int getCalculatedPerc()
    {
        return calculatedPerc;
    }


    public void setItemID(int iID)
    {
        itemID = iID;
    }

    public void setCurrentQTY(int cQty)
    {
        currentQty = cQty;
    }

    public void setName(String n)
    {
        name = n;
    }

    public void setDescription(String d)
    {
        description = d;
    }

    public void setPrice(int p)
    {
        price = p;
    }

    public void setThreshold(int t)
    {
        threshold = t;
    }

    public void setImageFile(String res_name)
    {
        imageFile = res_name;
    }

    public void updatePercentage() { this.calculatedPerc = this.currentQty/this.threshold; }
}

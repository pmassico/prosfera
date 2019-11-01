package com.example.prosfera;

public class GiftBasket {
    private int    basketID,
                   donorID,
                   basketItemID;
    private double total;

    //database connection here

    /*
        Constructor Class to initialize variables.
     */
    public GiftBasket(int dID, int bIID) {
        this.basketID = 001; //this would actually be pulled from the database and incremented as needed
        this.donorID = dID;
        this.basketItemID = bIID;
        this.total = 0;
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
}

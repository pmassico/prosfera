package com.example.prosfera;

public class BasketItem {
    private int    basketID,
                   itemID,
                   quantity;    //quantity of item in activity_basket
    private double price,       //price per unit of item in activity_basket
                   priceTotal;  //total price of item x quantity in activity_basket

    /*
        Constructor class.
    */
    public BasketItem (int bID, int iID, int qty, double p, double pTotal) {
        this.basketID = bID;
        this.itemID = iID;
        this.quantity = qty;
        this.price = p;
        this.priceTotal = setPriceTotal( qty, p);
    }

    /*
        Getters for quantity and priceTotal.
     */
    public int getQuantity() { return quantity; }
    public double getPriceTotal() { return priceTotal; }

    /*
        Setters for quantity and priceTotal.
     */
    public void setQuantity(int q) {
        this.quantity = q;
        setPriceTotal( q, priceTotal);
    }

    public double setPriceTotal(int q, double p) {
        this.priceTotal = q * p;
        return priceTotal;
    }

    public String toString() {
        return "BasketID: " + this.basketID + "\nItemID: " + this.itemID +
                "\nQuantity: " + this.quantity + "\nPrice: " + this.price +
                "\nPrice Total: " + this.priceTotal;
    }
}

package com.example.prosfera;
import java.util.*;

public class Receipt {
    private Date dateReceived; // The date in which the Charity received the receipt
    private Date dateIssued; // The date in which the receipt was issued to the donor
    private String uniqueSerialNum; //The unique serial number associated with the charity
    private int basketID; //The ID of the Basket (class) that stores the receipt items



    public Receipt(Date received, Date issued, String serial, int bID) {
        this.dateReceived = received;
        this.dateIssued = issued;
        this.uniqueSerialNum = serial;
        this.basketID = bID;
    }

    // Getters
    public Date getDateReceived() {
        return this.dateReceived;
    }

    public Date getDateIssued() {
        return this.dateIssued;
    }

    public String getUniqueSerialNum() {
        return this.uniqueSerialNum;
    }

    public int getBasketID() {
        return this.basketID;
    }

    // Setters
    public void setDateReceived(Date received) { this.dateReceived = received; }

    public void setDateIssued(Date issued) {
        this.dateIssued = issued;
    }

    public void setUniqueSerialNum(String serial) {
        this.uniqueSerialNum = serial;
    }

    public void setBasketID(int bID) {
        this.basketID = bID;
    }

    //Overwriting the toString method. This may need changing later on
    public String toString() {
        return "Date Received: " + this.dateReceived
                + "\nDate Issued: " + this.dateIssued
                + "\nCharity Registration Number: " + this.uniqueSerialNum

                //The basket ID will likely not be needed here, but I'm including it for
                // potential testing purposes. Data from the Basket class would take its place
                + "\nBasket ID: " + this.basketID;
    }
}

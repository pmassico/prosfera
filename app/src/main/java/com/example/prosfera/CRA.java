package com.example.prosfera;

public class CRA {
    private String websiteAddress;
    private String StmtOfOfficialReceipt;

    public CRA(String webAdd)
    {
        this.websiteAddress = webAdd;
        this.StmtOfOfficialReceipt = "This is an official receipt.";
    }

    public String getWebsiteAddress() { return websiteAddress; }

    public void setWebsiteAddress(String wa) { websiteAddress = wa; }
}

package com.example.prosfera;

public class Branding {
    private String primaryColor, secColor, logoFull, logoSmall, header, body;

    public Branding(String pColor, String sColor, String lFull, String lSmall, String head, String bod)
    {
        this.primaryColor = pColor;
        this.secColor = sColor;
        this.logoFull = lFull;
        this.logoSmall = lSmall;
        this.header = head;
        this.body = bod;
    }

    public String getPrimaryColor() { return primaryColor; }
    public void setPrimaryColor(String pc) { primaryColor = pc; }

    public String getSecColor(){return secColor;}
    public void setSecColor(String sc){secColor = sc;}

    public String getLogoFull() { return logoFull;}
    public void setLogoFull(String lf) {logoFull = lf;}

    public String getLogoSmall() {return logoSmall;}
    public void setLogoSmall(String ls) {logoSmall = ls;}

    public String getHeader() {return header;}
    public void setHeader(String h) {header = h;}

    public String getBody() {return body;}
    public void setBody(String b) {body = b;}
}

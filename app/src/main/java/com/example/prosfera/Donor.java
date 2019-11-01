package com.example.prosfera;

public class Donor {
    private int donorID;
    private String firstName, middleName, lastName;
    private String addLine1,addLine2, city, province, postalCode;
    private String email;
    private int phoneNum;
    private String password;//not sure what would be the type

    public Donor(int dID, String fName, String mName, String lName,
                 String aL1, String aL2, String c, String p, String pC,
                 String em, int phone, String pass)
    {
        this.donorID = dID;
        this.firstName = fName;
        this.middleName = mName;
        this.lastName = lName;
        this.addLine1 = aL1;
        this.addLine2 = aL2;
        this.city = c;
        this.province = p;
        this.postalCode = pC;
        this.email = em;
        this.phoneNum = phone;
        this.password = pass;
    }

    public int getDonorID()
    {
        return donorID;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getMiddleName()
    {
        return middleName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getAddLine1()
    {
        return addLine1;
    }

    public String getAddLine2()
    {
        return addLine2;
    }

    public String getCity()
    {
        return city;
    }

    public String getProvince()
    {
        return province;
    }

    public String getPostalCode()
    {
        return postalCode;
    }

    public String getEmail()
    {
        return email;
    }

    public int getPhoneNum()
    {
        return phoneNum;
    }

    public String getPassword()
    {
        return password;
    }

    public void setDonorID(int donoID)
    {
        donorID = donoID;
    }

    public void setFirstName(String fn)
    {
        firstName = fn;
    }

    public void setMiddleName(String mn)
    {
        middleName = mn;
    }

    public void setLastName(String ln)
    {
        lastName = ln;
    }

    public void setAddLine1(String aline1)
    {
        addLine1 = aline1;
    }

    public void setAddLine2(String aline2)
    {
        addLine2 = aline2;
    }

    public void setCity(String cty)
    {
        city = cty;
    }

    public void setProvince(String prv)
    {
        province = prv;
    }

    public void setPostalCode(String postCode)
    {
        postalCode = postCode;
    }

    public void setEmail(String eml)
    {
        email = eml;
    }

    public void setPhoneNum(int ph)
    {
        phoneNum = ph;
    }

    public void setPassword(String pwd)
    {
        password = pwd;
    }
}

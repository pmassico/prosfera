package com.example.prosfera;

public class Charity {
    private String firstName, middleName, lastName, address;
    private int CRARegNum;

    public Charity(String fName, String mName, String lName, int CRANum)
    {
        this.firstName = fName;
        this.middleName = mName;
        this.lastName = lName;
        this.CRARegNum = CRANum;
    }

    public String getFirstName() { return firstName; }

    public String getMiddleName() { return middleName; }

    public String getLastName() { return lastName; }

    public int getCRARegNum() { return CRARegNum; }

    public void setFirstName(String fn) { firstName = fn; }

    public void setMiddleName(String mn) { middleName = mn; }

    public void setLastName(String ln) { lastName = ln; }

    public void setCRARegNum(int cra) { CRARegNum = cra; }
}

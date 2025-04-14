package com.java.crime.model;

import java.sql.Date;

public class Suspects 
{
    private int SuspectID;
    private String FirstName;
    private String LastName;
    private Date DateOfBirth;
    private String Gender;
    private String ContactInfo;

    public Suspects() {}

    public Suspects(int SuspectID, String FirstName, String LastName, Date DateOfBirth, String Gender, String ContactInfo) 
    {
        this.SuspectID = SuspectID;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.DateOfBirth = DateOfBirth;
        this.Gender = Gender;
        this.ContactInfo = ContactInfo;
    }

    public int getSuspectID() 
    { return SuspectID; }
    public void setSuspectID(int SuspectID)
    { this.SuspectID = SuspectID; }

    public String getFirstName() 
    { return FirstName; }
    public void setFirstName(String FirstName) 
    { this.FirstName = FirstName; }
    
    public String getLastName() 
    { return LastName; }
    public void setLastName(String LastName) 
    { this.LastName = LastName; }

    public Date getDateOfBirth() 
    { return DateOfBirth; }
    public void setDateOfBirth(Date DateOfBirth) 
    { this.DateOfBirth = DateOfBirth; }

    public String getGender() 
    { return Gender; }
    public void setGender(String Gender) 
    { this.Gender = Gender; }

    public String getContactInfo() 
    { return ContactInfo; }
    public void setContactInfo(String ContactInfo) 
    { this.ContactInfo = ContactInfo; }

    @Override
    public String toString() 
    {
        return "Suspect [SuspectID=" + SuspectID + ", FirstName=" + FirstName + ", LastName=" + LastName
                + ", DateOfBirth=" + DateOfBirth + ", Gender=" + Gender + ", ContactInfo=" + ContactInfo + "]";
    }
}

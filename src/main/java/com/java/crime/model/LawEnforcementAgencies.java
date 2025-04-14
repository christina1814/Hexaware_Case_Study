package com.java.crime.model;

public class LawEnforcementAgencies 
{
    private int AgencyID;
    private String AgencyName;
    private String Jurisdiction;
    private String ContactInfo;

    public LawEnforcementAgencies() {}

    public LawEnforcementAgencies(int AgencyID, String AgencyName, String Jurisdiction, String ContactInfo) 
    {
        this.AgencyID = AgencyID;
        this.AgencyName = AgencyName;
        this.Jurisdiction = Jurisdiction;
        this.ContactInfo = ContactInfo;
    }

    public int getAgencyID() 
    { return AgencyID; }
    public void setAgencyID(int AgencyID) 
    { this.AgencyID = AgencyID; }

    public String getAgencyName() 
    { return AgencyName; }
    public void setAgencyName(String AgencyName) 
    { this.AgencyName = AgencyName; }

    public String getJurisdiction() 
    { return Jurisdiction; }
    public void setJurisdiction(String Jurisdiction) 
    { this.Jurisdiction = Jurisdiction; }

    public String getContactInfo() 
    { return ContactInfo; }
    public void setContactInfo(String ContactInfo) 
    { this.ContactInfo = ContactInfo; }

    @Override
    public String toString() 
    {
        return "LawEnforcementAgencies [AgencyID=" + AgencyID + ", AgencyName=" + AgencyName +
               ", Jurisdiction=" + Jurisdiction + ", ContactInfo=" + ContactInfo + "]";
    }
}

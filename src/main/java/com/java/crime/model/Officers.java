package com.java.crime.model;

public class Officers 
{
    private int OfficerID;
    private String FirstName;
    private String LastName;
    private String BadgeNumber;
    private String RankID;
    private int AgencyID;
    private String ContactInfo;

    public Officers() {}

    public Officers(int OfficerID, String FirstName, String LastName, String BadgeNumber, String RankID, String ContactInfo, int AgencyID) 
    {
        this.OfficerID = OfficerID;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.BadgeNumber = BadgeNumber;
        this.RankID = RankID;
        this.ContactInfo = ContactInfo;
        this.AgencyID = AgencyID;
    }

    public int getOfficerID() 
    { return OfficerID; }
    public void setOfficerID(int OfficerID) 
    { this.OfficerID = OfficerID; }

    public String getFirstName() 
    { return FirstName; }
    public void setFirstName(String FirstName) 
    { this.FirstName = FirstName; }

    public String getLastName() 
    { return LastName; }
    public void setLastName(String LastName) 
    { this.LastName = LastName; }

    public String getBadgeNumber() 
    { return BadgeNumber; }
    public void setBadgeNumber(String BadgeNumber) 
    {this.BadgeNumber = BadgeNumber; }

    public String getRankID() 
    { return RankID; }
    public void setRankID(String RankID) 
    { this.RankID = RankID;  }

    public String getContactInfo() 
    { return ContactInfo; }
    public void setContactInfo(String ContactInfo) 
    { this.ContactInfo = ContactInfo; }

    public int getAgencyID() 
    { return AgencyID; }
    public void setAgencyID(int AgencyID)
    { this.AgencyID = AgencyID; }

    @Override
    public String toString()
    {
        return "Officers [OfficerID=" + OfficerID + ", FirstName=" + FirstName + ", LastName=" + LastName +
               ", BadgeNumber=" + BadgeNumber + ", RankID=" + RankID + ", ContactInfo=" + ContactInfo +
               ", AgencyID=" + AgencyID + "]";
    }
}

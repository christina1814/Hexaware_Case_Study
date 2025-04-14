package com.java.crime.model;

public class Evidence 
{
    private int EvidenceID;
    private int IncidentID;
    private String Description;
    private String LocationFound;

    public Evidence() {}

    public Evidence(int EvidenceID, int IncidentID, String Description, String LocationFound)
    {
        this.EvidenceID = EvidenceID;
        this.IncidentID = IncidentID;
        this.Description = Description;
        this.LocationFound = LocationFound;
    }

    public int getEvidenceID() 
    { return EvidenceID; }
    public void setEvidenceID(int EvidenceID) 
    { this.EvidenceID = EvidenceID; }

    public int getIncidentID() 
    { return IncidentID; }
    public void setIncidentID(int IncidentID) 
    { this.IncidentID = IncidentID; }

    public String getDescription() 
    { return Description; }
    public void setDescription(String Description) 
    { this.Description = Description; }

    public String getLocationFound() 
    { return LocationFound; }
    public void setLocationFound(String LocationFound) 
    { this.LocationFound = LocationFound; }

    @Override
    public String toString() 
    {
        return "Evidence [EvidenceID=" + EvidenceID + ", IncidentID=" + IncidentID + ", Description=" + Description +
               ", LocationFound=" + LocationFound + "]";
    }
}

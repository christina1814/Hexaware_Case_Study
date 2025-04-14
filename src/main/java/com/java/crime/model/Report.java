package com.java.crime.model;

import java.sql.Date;

public class Report {
    private int ReportID;
    private int IncidentID;
    private int ReportingOfficer;
    private Date ReportDate;
    private String ReportDetails;
    private String Status;

    // No-arg constructor
    public Report() {
    }

    // All-arg constructor
    public Report(int ReportID, int IncidentID, int ReportingOfficer, Date ReportDate, String ReportDetails, String Status) {
        this.ReportID = ReportID;
        this.IncidentID = IncidentID;
        this.ReportingOfficer = ReportingOfficer;
        this.ReportDate = ReportDate;
        this.ReportDetails = ReportDetails;
        this.Status = Status;
    }

    // Getters and Setters
    public int getReportID() {
        return ReportID;
    }

    public void setReportID(int ReportID) {
        this.ReportID = ReportID;
    }

    public int getIncidentID() {
        return IncidentID;
    }

    public void setIncidentID(int IncidentID) {
        this.IncidentID = IncidentID;
    }

    public int getReportingOfficer() {
        return ReportingOfficer;
    }

    public void setReportingOfficer(int ReportingOfficer) {
        this.ReportingOfficer = ReportingOfficer;
    }

    public Date getReportDate() {
        return ReportDate;
    }

    public void setReportDate(Date ReportDate) {
        this.ReportDate = ReportDate;
    }

    public String getReportDetails() {
        return ReportDetails;
    }

    public void setReportDetails(String ReportDetails) {
        this.ReportDetails = ReportDetails;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    @Override
    public String toString() {
        return "Report [ReportID=" + ReportID + ", IncidentID=" + IncidentID + ", ReportingOfficer=" + ReportingOfficer
                + ", ReportDate=" + ReportDate + ", ReportDetails=" + ReportDetails + ", Status=" + Status + "]";
    }
}

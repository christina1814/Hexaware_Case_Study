package com.java.crime.dao;

import java.sql.*;
import java.util.*;
import java.sql.Date;

import com.java.crime.model.*;
import com.java.crime.util.DBConnection;
import com.java.crime.exception.*;


public class CrimeAnalysisServiceImpl implements ICrimeAnalysisService 
{
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    @Override
    public Boolean createIncident(Incidents incident) throws ClassNotFoundException, SQLException 
    {
        con = DBConnection.getConnection();
        ps = con.prepareStatement(
            "INSERT INTO Incidents (IncidentType, IncidentDate, Location, Description, Status, VictimID, SuspectID, OfficerID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
        );

        ps.setString(1, incident.getIncidentType());
        ps.setDate(2, incident.getIncidentDate());
        ps.setString(3, incident.getLocation());
        ps.setString(4, incident.getDescription());
        ps.setString(5, incident.getStatus());
        ps.setInt(6, incident.getVictimID());
        ps.setInt(7, incident.getSuspectID());
        ps.setInt(8, incident.getOfficerID());

        int rows = ps.executeUpdate();
        return rows > 0;
    }

    @Override
    public int addVictim(Victim victim) throws Exception 
    {
        con = DBConnection.getConnection();
        String sql = "INSERT INTO Victims (FirstName, LastName, DateOfBirth, Gender, ContactInfo) VALUES (?, ?, ?, ?, ?)";
        ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        ps.setString(1, victim.getFirstName());
        ps.setString(2, victim.getLastName());
        ps.setDate(3, victim.getDateOfBirth());                
        ps.setString(4, victim.getGender());
        ps.setString(5, victim.getContactInfo());

        ps.executeUpdate();

        rs = ps.getGeneratedKeys();
        if (rs.next()) 
        {
            return rs.getInt(1);
        } 
        else 
        {
            throw new SQLException("Failed to retrieve generated VictimID.");
        }
    }

    @Override
    public int addSuspect(Suspects suspect) throws Exception 
    {
        con = DBConnection.getConnection();
        String sql = "INSERT INTO Suspects (FirstName, LastName, DateOfBirth, Gender, ContactInfo) VALUES (?, ?, ?, ?, ?)";
        ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        ps.setString(1, suspect.getFirstName());
        ps.setString(2, suspect.getLastName());
        ps.setDate(3, suspect.getDateOfBirth());                
        ps.setString(4, suspect.getGender());
        ps.setString(5, suspect.getContactInfo());

        ps.executeUpdate();

        rs = ps.getGeneratedKeys();
        if (rs.next()) 
        {
            return rs.getInt(1);
        } 
        else 
        {
            throw new SQLException("Failed to retrieve generated SuspectID.");
        }
    }

    @Override
    public int addOfficer(Officers officer) throws Exception 
    {
        con = DBConnection.getConnection();
        String sql = "INSERT INTO Officers (FirstName, LastName, BadgeNumber, RankID, ContactInfo, AgencyID) VALUES (?, ?, ?, ?, ?, ?)";
        ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        ps.setString(1, officer.getFirstName());
        ps.setString(2, officer.getLastName());
        ps.setString(3, officer.getBadgeNumber());
        ps.setString(4, officer.getRankID());
        ps.setString(5, officer.getContactInfo());
        ps.setInt(6, officer.getAgencyID());

        ps.executeUpdate();

        rs = ps.getGeneratedKeys();
        if (rs.next()) 
        {
            return rs.getInt(1);
        } 
        else 
        {
            throw new SQLException("Failed to retrieve generated OfficerID.");
        }
    }

    @Override
    public Boolean updateIncidentStatus(String status, int incidentID) throws Exception, IncidentNotFoundException 
    {
        con = DBConnection.getConnection();
        ps = con.prepareStatement("SELECT 1 FROM Incidents WHERE IncidentID = ?");
        ps.setInt(1, incidentID);
        rs = ps.executeQuery();
        if (!rs.next()) 
        {
            throw new IncidentNotFoundException("Incident ID not found.");
        }

        ps = con.prepareStatement("UPDATE Incidents SET Status = ? WHERE IncidentID = ?");
        ps.setString(1, status);
        ps.setInt(2, incidentID);
        return ps.executeUpdate() > 0;
    }
    
    @Override
    public Boolean searchIncidentById(int incidentID) throws SQLException, ClassNotFoundException, IncidentNotFoundException 
    {
        con = DBConnection.getConnection();
        ps = con.prepareStatement("SELECT 1 FROM Incidents WHERE IncidentID = ?");
        ps.setInt(1, incidentID);
        rs = ps.executeQuery();
        
        if (!rs.next()) 
        {
            throw new IncidentNotFoundException("Incident ID not found.");
        }
        
        return rs.next(); 
    }

    @Override
    public Collection<Incidents> getIncidentsInDateRange(String startDate, String endDate) throws ClassNotFoundException, SQLException 
    {
        List<Incidents> list = new ArrayList<>();
        con = DBConnection.getConnection();
        ps = con.prepareStatement("SELECT * FROM Incidents WHERE IncidentDate BETWEEN ? AND ?");
        ps.setDate(1, Date.valueOf(startDate));
        ps.setDate(2, Date.valueOf(endDate));
        rs = ps.executeQuery();

        while (rs.next()) 
        {
            list.add(new Incidents(
                rs.getInt("IncidentID"),
                rs.getString("IncidentType"),
                rs.getDate("IncidentDate"),
                rs.getString("Location"),
                rs.getString("Description"),
                rs.getString("Status"),
                rs.getInt("VictimID"),
                rs.getInt("SuspectID"),
                rs.getInt("OfficerID")
            ));
        }

        return list;
    }

    @Override
    public Collection<Incidents> searchIncidents(String incidentType) throws ClassNotFoundException, SQLException 
    {
        List<Incidents> list = new ArrayList<>();
        con = DBConnection.getConnection();
        ps = con.prepareStatement("SELECT * FROM Incidents WHERE IncidentType = ?");
        ps.setString(1, incidentType);
        rs = ps.executeQuery();

        while (rs.next()) 
        {
            list.add(new Incidents(
                rs.getInt("IncidentID"),
                rs.getString("IncidentType"),
                rs.getDate("IncidentDate"),
                rs.getString("Location"),
                rs.getString("Description"),
                rs.getString("Status"),
                rs.getInt("VictimID"),
                rs.getInt("SuspectID"),
                rs.getInt("OfficerID")
            ));
        }

        return list;
    }

    @Override
    public Report generateIncidentReport(Incidents incident) throws ClassNotFoundException, SQLException, ReportNotGeneratedException 
    {
        con = DBConnection.getConnection();

        int officerID = 0;
        String description = null;
        String incidentStatus = null;

        ps = con.prepareStatement("SELECT OfficerID, Description, Status FROM Incidents WHERE IncidentID = ?");
        ps.setInt(1, incident.getIncidentID());
        rs = ps.executeQuery();
        if (rs.next()) 
        {
            officerID = rs.getInt("OfficerID");
            description = rs.getString("Description");
            incidentStatus = rs.getString("Status");
        } 
        else 
        {
            throw new ReportNotGeneratedException("Incident ID not found.");
        }

        String reportStatus;
        if ("Closed".equalsIgnoreCase(incidentStatus)) 
        {
            reportStatus = "Finalized";
        } 
        else 
        {
            reportStatus = "Draft";
        }

        String reportDetails = "Incident Description: " + description + "\nReported by Officer #" + officerID;
        Date reportDate = new Date(System.currentTimeMillis());

        ps = con.prepareStatement(
            "INSERT INTO Reports (IncidentID, ReportingOfficer, ReportDate, ReportDetails, Status) VALUES (?, ?, ?, ?, ?)",
            Statement.RETURN_GENERATED_KEYS
        );

        ps.setInt(1, incident.getIncidentID());
        ps.setInt(2, officerID);
        ps.setDate(3, reportDate);
        ps.setString(4, reportDetails);
        ps.setString(5, reportStatus);

        int rows = ps.executeUpdate();

        if (rows > 0) 
        {
            rs = ps.getGeneratedKeys();
            if (rs.next()) 
            {
                int reportId = rs.getInt(1);
                return new Report(reportId, incident.getIncidentID(), officerID, reportDate, reportDetails, reportStatus);
            }
        }

        throw new ReportNotGeneratedException("Failed to generate report.");
    }


    @Override
    public Victim searchVictim(int victimID) throws Exception, VictimNotFoundException 
    {
        con = DBConnection.getConnection();
        ps = con.prepareStatement("SELECT * FROM Victims WHERE VictimID = ?");
        ps.setInt(1, victimID);
        rs = ps.executeQuery();

        if (rs.next()) 
        {
            return new Victim(
                rs.getInt("VictimID"),
                rs.getString("FirstName"),
                rs.getString("LastName"),
                rs.getDate("DateOfBirth"),
                rs.getString("Gender"),
                rs.getString("ContactInfo")
            );
        } 
        else 
        {
        	throw new VictimNotFoundException("Victim not found with ID: " + victimID);
        }
    }

    @Override
    public Suspects searchSuspect(int suspectID) throws Exception, SuspectNotFoundException 
    {
        con = DBConnection.getConnection();
        ps = con.prepareStatement("SELECT * FROM Suspects WHERE SuspectID = ?");
        ps.setInt(1, suspectID);
        rs = ps.executeQuery();

        if (rs.next()) 
        {
            return new Suspects(
                rs.getInt("SuspectID"),
                rs.getString("FirstName"),
                rs.getString("LastName"),
                rs.getDate("DateOfBirth"),
                rs.getString("Gender"),
                rs.getString("ContactInfo")
            );
        } 
        else 
        {
        	throw new SuspectNotFoundException("Suspect not found with ID: " + suspectID);
        }
    }

    @Override
    public Officers searchOfficer(int officerID) throws Exception, OfficerNotFoundException 
    {
        con = DBConnection.getConnection();
        ps = con.prepareStatement("SELECT * FROM Officers WHERE OfficerID = ?");
        ps.setInt(1, officerID);
        rs = ps.executeQuery();

        if (rs.next()) 
        {
            return new Officers(
                rs.getInt("OfficerID"),
                rs.getString("FirstName"),
                rs.getString("LastName"),
                rs.getString("BadgeNumber"),
                rs.getString("RankID"),
                rs.getString("ContactInfo"),
                rs.getInt("AgencyID")
            );
        } 
        else 
        {
        	throw new OfficerNotFoundException("Officer not found with ID: " + officerID);
        }
    }
}

package com.java.crime.dao;

import java.sql.SQLException;
import java.util.Collection;

import com.java.crime.exception.*;
import com.java.crime.model.*;

public interface ICrimeAnalysisService {

    Boolean createIncident(Incidents incident) throws ClassNotFoundException, SQLException;
    int addVictim(Victim victim) throws Exception;
    int addSuspect(Suspects suspect) throws Exception;
    int addOfficer(Officers officer) throws Exception;

    Boolean updateIncidentStatus(String status, int incidentID) throws ClassNotFoundException, SQLException, Exception, IncidentNotFoundException;
	Boolean searchIncidentById(int incidentID) throws SQLException, ClassNotFoundException, IncidentNotFoundException;

    
    Collection<Incidents> getIncidentsInDateRange(String startDate, String endDate) throws ClassNotFoundException, SQLException;

    Collection<Incidents> searchIncidents(String incidentType) throws ClassNotFoundException, SQLException;

    Report generateIncidentReport(Incidents incident) throws ClassNotFoundException, SQLException, ReportNotGeneratedException;
  
    Victim searchVictim(int victimID) throws Exception, VictimNotFoundException;

    Suspects searchSuspect(int suspectID) throws Exception, SuspectNotFoundException;

    Officers searchOfficer(int officerID) throws Exception, OfficerNotFoundException;
}

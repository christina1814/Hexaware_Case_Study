package com.java.crime.junit;

import static org.junit.Assert.*;
import org.junit.Test;

import com.java.crime.dao.*;
import com.java.crime.model.*;

import java.sql.Date;
import java.util.Collection;

public class JunitTest 
{

    @Test
    public void testCreateIncident() throws Exception 
    {
        ICrimeAnalysisService dao = new CrimeAnalysisServiceImpl();
        Incidents incident = new Incidents(0, "Robbery", Date.valueOf("2024-01-01"), "Downtown", "Wallet stolen", "Open", 1, 1, 1);
        boolean result = dao.createIncident(incident);
        assertTrue(result);
    }

    @Test
    public void testUpdateIncidentStatus() throws Exception 
    {
        ICrimeAnalysisService dao = new CrimeAnalysisServiceImpl();
        boolean result = dao.updateIncidentStatus("Closed", 1);
        assertTrue(result); 
    }
    

    @Test
    public void testGetIncidentsInDateRange() throws Exception 
    {
        ICrimeAnalysisService dao = new CrimeAnalysisServiceImpl();
        Collection<Incidents> list = dao.getIncidentsInDateRange("2024-01-01", "2024-12-31");
        assertNotNull(list);
    }

    @Test
    public void testSearchIncidentsByType() throws Exception 
    {
        ICrimeAnalysisService dao = new CrimeAnalysisServiceImpl();
        Collection<Incidents> result = dao.searchIncidents("Robbery");
        assertNotNull(result);
    }

    @Test
    public void testGenerateIncidentReport() throws Exception 
    {
        ICrimeAnalysisService dao = new CrimeAnalysisServiceImpl();
        Incidents incident = new Incidents();
        incident.setIncidentID(1); // Use valid ID
        Report report = dao.generateIncidentReport(incident);
        assertNotNull(report);
    }
}

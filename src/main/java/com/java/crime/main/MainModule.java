package com.java.crime.main;

import com.java.crime.dao.*;
import com.java.crime.model.*;
import com.java.crime.exception.*;

import java.sql.Date;
import java.util.*;

public class MainModule {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ICrimeAnalysisService dao = new CrimeAnalysisServiceImpl();
        int choice;

        do {
            System.out.println("\n========= Crime Reporting & Analysis =========");
            System.out.println("1. Create New Incident");
            System.out.println("2. Update Incident Status");
            System.out.println("3. Get Incidents by Date Range");
            System.out.println("4. Search Incident by Type");
            System.out.println("5. Generate Incident Report");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();  // Clear buffer

            switch (choice) {
                case 1:
                    try {
                        System.out.print("Incident Type: ");
                        String type = sc.nextLine();
                        System.out.print("Incident Date (yyyy-mm-dd): ");
                        Date date = Date.valueOf(sc.nextLine());
                        System.out.print("Location: ");
                        String location = sc.nextLine();
                        System.out.print("Description: ");
                        String desc = sc.nextLine();
                        System.out.print("Status (Open/Closed/Under Investigation): ");
                        String status = sc.nextLine();

                        // ===== Victim =====
                        System.out.println("Victim:\n1. Use Existing\n2. Add New");
                        int vChoice = Integer.parseInt(sc.nextLine());
                        int vid = 0;
                        if (vChoice == 1) {
                        	try {
                                System.out.print("Enter Victim ID: ");
                                vid = Integer.parseInt(sc.nextLine());
                                Victim victim = dao.searchVictim(vid);
                                System.out.println("Victim Found: " + victim);
                            } catch (VictimNotFoundException e) {
                                System.out.println("Error: " + e.getMessage());
                            }
                        } else {
                            Victim v = getVictimDetailsFromUser(sc);
                            vid = dao.addVictim(v);
                            System.out.println("New Victim ID: " + vid);
                        }

                        // ===== Suspect =====
                        System.out.println("Suspect:\n1. Use Existing\n2. Add New");
                        int sChoice = Integer.parseInt(sc.nextLine());
                        int sid = 0;
                        if (sChoice == 1) {
                        	try {
                                System.out.print("Enter Suspect ID: ");
                                sid = Integer.parseInt(sc.nextLine());
                                Suspects suspect = dao.searchSuspect(sid);
                                System.out.println("Suspect Found: " + suspect);
                            } catch (SuspectNotFoundException e) {
                                System.out.println("Error: " + e.getMessage());
                            }
                        } else {
                            Suspects s = getSuspectDetailsFromUser(sc);
                            sid = dao.addSuspect(s);
                            System.out.println("New Suspect ID: " + sid);
                        }

                        // ===== Officer =====
                        System.out.println("Officer:\n1. Use Existing\n2. Add New");
                        int oChoice = Integer.parseInt(sc.nextLine());
                        int oid = 0;
                        if (oChoice == 1) {
                        	try {
                                System.out.print("Enter Officer ID: ");
                                oid = Integer.parseInt(sc.nextLine());
                                Officers officer = dao.searchOfficer(oid);
                                System.out.println("Officer Found: " + officer);
                            } catch (OfficerNotFoundException e) {
                                System.out.println("Error: " + e.getMessage());
                            }
                        } else {
                            Officers o = getOfficerDetailsFromUser(sc);
                            oid = dao.addOfficer(o);
                            System.out.println("New Officer ID: " + oid);
                        }

                        // ===== Create Incident =====
                        Incidents incident = new Incidents(0, type, date, location, desc, status, vid, sid, oid);
                        if (dao.createIncident(incident)) {
                            System.out.println("✅ Incident created successfully.");
                        } else {
                            System.out.println("❌ Failed to create incident.");
                        }

                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 2: // Update Status
                    try {
                        System.out.print("Enter Incident ID: ");
                        int iid = sc.nextInt();
                        sc.nextLine(); // clear buffer

                        // Check if the incident exists before continuing
                        if (!dao.searchIncidentById(iid)) {
                            throw new IncidentNotFoundException("Incident ID not found.");
                        }

                        // Now safe to ask for the status
                        System.out.print("Enter New Status: ");
                        String newStatus = sc.nextLine();

                        if (dao.updateIncidentStatus(newStatus, iid)) {
                            System.out.println("Status updated successfully.");
                        }
                    } catch (IncidentNotFoundException e) {
                        System.out.println("Error: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Database Error: " + e.getMessage());
                    }
                    break;


                case 3: // Incidents in Date Range
                    try {
                        System.out.print("Start Date (yyyy-mm-dd): ");
                        String start = sc.nextLine();
                        System.out.print("End Date (yyyy-mm-dd): ");
                        String end = sc.nextLine();

                        Collection<Incidents> incidents = dao.getIncidentsInDateRange(start, end);
                        if (incidents.isEmpty()) {
                            throw new IncidentNotFoundException("No incidents found in given date range.");
                        }
                        incidents.forEach(System.out::println);
                    } catch (IncidentNotFoundException e) {
                        System.out.println("Error: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 4: // Search by Type
                    try {
                        System.out.print("Enter Incident Type to search: ");
                        String t = sc.nextLine();
                        Collection<Incidents> list = dao.searchIncidents(t);

                        if (list.isEmpty()) {
                            throw new IncidentNotFoundException("No incidents found for type: " + t);
                        }

                        list.forEach(System.out::println);
                    } catch (IncidentNotFoundException e) {
                        System.out.println("Error: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 5: // Generate Report
                    try {
                        System.out.print("Enter Incident ID: ");
                        int iid = sc.nextInt();

                        Incidents dummy = new Incidents();
                        dummy.setIncidentID(iid);

                        Report report = dao.generateIncidentReport(dummy);
                        if (report != null) {
                            System.out.println("Report Generated:\n" + report);
                        } else {
                            throw new ReportNotGeneratedException("Failed to generate report.");
                        }
                    } catch (ReportNotGeneratedException e) {
                        System.out.println("Error: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 6:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 6);

        sc.close();
    }

    private static Victim getVictimDetailsFromUser(Scanner sc) {
        System.out.print("First Name: ");
        String firstName = sc.nextLine();
        System.out.print("Last Name: ");
        String lastName = sc.nextLine();
        System.out.print("Date of Birth (yyyy-mm-dd): ");
        Date dob = Date.valueOf(sc.nextLine());
        System.out.print("Gender (Male/Female/Other): ");
        String gender = sc.nextLine();
        System.out.print("Contact Info: ");
        String contact = sc.nextLine();

        // Updated constructor according to the implementation in the model class
        return new Victim(0, firstName, lastName, dob, gender, contact); // VictimID is set to 0 (auto-generated)
    }

    private static Suspects getSuspectDetailsFromUser(Scanner sc) {
        System.out.print("First Name: ");
        String firstName = sc.nextLine();
        System.out.print("Last Name: ");
        String lastName = sc.nextLine();
        System.out.print("Date of Birth (yyyy-mm-dd): ");
        Date dob = Date.valueOf(sc.nextLine());
        System.out.print("Gender (Male/Female/Other): ");
        String gender = sc.nextLine();
        System.out.print("Contact Info: ");
        String contact = sc.nextLine();

        // Updated constructor according to the implementation in the model class
        return new Suspects(0, firstName, lastName, dob, gender, contact); // SuspectID is set to 0 (auto-generated)
    }

    private static Officers getOfficerDetailsFromUser(Scanner sc) {
        System.out.print("First Name: ");
        String firstName = sc.nextLine();
        System.out.print("Last Name: ");
        String lastName = sc.nextLine();
        System.out.print("Badge Number: ");
        String badge = sc.nextLine();
        System.out.print("Rank ID: ");
        String rank = sc.nextLine();
        System.out.print("Contact Info: ");
        String contact = sc.nextLine();
        System.out.print("Agency ID: ");
        int agencyId = Integer.parseInt(sc.nextLine());

        // Updated constructor according to the implementation in the model class
        return new Officers(0, firstName, lastName, badge, rank, contact, agencyId); // OfficerID is set to 0 (auto-generated)
    }

}

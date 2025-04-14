create database crime_rps;

use crime_rps;

-- 1. Incidents Table
CREATE TABLE Incidents (
    IncidentID INT PRIMARY KEY AUTO_INCREMENT,
    IncidentType VARCHAR(50),
    IncidentDate DATE,
    Location VARCHAR(255),
    Description TEXT,
    Status ENUM('Open', 'Closed', 'Under Investigation'),
    VictimID INT,
    SuspectID INT,
    OfficerID INT,
    FOREIGN KEY (VictimID) REFERENCES Victims(VictimID),
    FOREIGN KEY (SuspectID) REFERENCES Suspects(SuspectID),
    FOREIGN KEY (OfficerID) REFERENCES Officers(OfficerID)
);

-- 2. Victims Table
CREATE TABLE Victims (
    VictimID INT PRIMARY KEY AUTO_INCREMENT,
    FirstName VARCHAR(50),
    LastName VARCHAR(50),
    DateOfBirth DATE,
    Gender ENUM('Male', 'Female', 'Other'),
    ContactInfo VARCHAR(255)
);

-- 3. Suspects Table
CREATE TABLE Suspects (
    SuspectID INT PRIMARY KEY AUTO_INCREMENT,
    FirstName VARCHAR(50),
    LastName VARCHAR(50),
    DateOfBirth DATE,
    Gender ENUM('Male', 'Female', 'Other'),
    ContactInfo VARCHAR(255)
);

-- 4. Law Enforcement Agencies Table
CREATE TABLE LawEnforcementAgencies (
    AgencyID INT PRIMARY KEY AUTO_INCREMENT,
    AgencyName VARCHAR(100),
    Jurisdiction VARCHAR(100),
    ContactInfo VARCHAR(255)
);

-- 5. Officers Table
CREATE TABLE Officers (
    OfficerID INT PRIMARY KEY AUTO_INCREMENT,
    FirstName VARCHAR(50),
    LastName VARCHAR(50),
    BadgeNumber VARCHAR(20) UNIQUE,
    RankID VARCHAR(50),
    ContactInfo VARCHAR(255),
    AgencyID INT,
    FOREIGN KEY (AgencyID) REFERENCES LawEnforcementAgencies(AgencyID)
);

-- 6. Evidence Table
CREATE TABLE Evidence (
    EvidenceID INT PRIMARY KEY AUTO_INCREMENT,
    Description TEXT,
    LocationFound VARCHAR(255),
    IncidentID INT,
    FOREIGN KEY (IncidentID) REFERENCES Incidents(IncidentID)
);

-- 7. Reports Table
CREATE TABLE Reports (
    ReportID INT PRIMARY KEY AUTO_INCREMENT,
    IncidentID INT,
    ReportingOfficer INT,
    ReportDate DATE,
    ReportDetails TEXT,
    Status ENUM('Draft', 'Finalized'),
    FOREIGN KEY (IncidentID) REFERENCES Incidents(IncidentID),
    FOREIGN KEY (ReportingOfficer) REFERENCES Officers(OfficerID)
);

-- Insert into Victims Table
INSERT INTO Victims (FirstName, LastName, DateOfBirth, Gender, ContactInfo) VALUES
('John', 'Doe', '1985-06-15', 'Male', '123 Main St, NY'),
('Alice', 'Smith', '1990-08-22', 'Female', '456 Oak Ave, LA'),
('Bob', 'Johnson', '1982-03-11', 'Male', '789 Pine Rd, TX'),
('Emma', 'Brown', '1995-12-30', 'Female', '321 Cedar St, FL'),
('Charlie', 'Davis', '1987-07-07', 'Male', '654 Birch Ln, IL'),
('Sophia', 'Wilson', '1993-09-18', 'Female', '987 Elm St, CA'),
('David', 'Miller', '1980-01-05', 'Male', '159 Maple Ave, WA'),
('Lily', 'Taylor', '1989-11-21', 'Female', '753 Walnut St, NV'),
('Ethan', 'Anderson', '1997-05-14', 'Male', '852 Willow Ln, CO'),
('Olivia', 'Thomas', '1984-04-28', 'Female', '369 Chestnut Rd, AZ');

-- Insert into Suspects Table
INSERT INTO Suspects (FirstName, LastName, DateOfBirth, Gender, ContactInfo) VALUES
('Mike', 'White', '1975-10-05', 'Male', '321 Elm St, NY'),
('Sarah', 'Harris', '1988-06-25', 'Female', '654 Pine Ave, LA'),
('Tom', 'Clark', '1980-02-14', 'Male', '987 Oak Ln, TX'),
('Jessica', 'Lewis', '1992-12-03', 'Female', '258 Maple St, FL'),
('Robert', 'Walker', '1983-08-19', 'Male', '369 Walnut Rd, IL'),
('Anna', 'Hall', '1991-07-12', 'Female', '741 Birch Ln, CA'),
('James', 'Allen', '1978-11-30', 'Male', '852 Willow St, WA'),
('Emily', 'Young', '1996-05-06', 'Female', '159 Chestnut Ave, NV'),
('Daniel', 'King', '1985-09-28', 'Male', '357 Cedar Ln, CO'),
('Laura', 'Wright', '1982-03-22', 'Female', '456 Spruce Rd, AZ');

-- Insert into Law Enforcement Agencies Table
INSERT INTO LawEnforcementAgencies (AgencyName, Jurisdiction, ContactInfo) VALUES
('NY Police Dept', 'New York', '100 Police Plaza, NY'),
('LA Police Dept', 'Los Angeles', '200 LAPD St, LA'),
('TX State Police', 'Texas', '300 TX St, TX'),
('FL Sheriff Office', 'Florida', '400 FL Blvd, FL'),
('IL Highway Patrol', 'Illinois', '500 IL Ave, IL'),
('CA Crime Bureau', 'California', '600 CA St, CA'),
('WA State Troopers', 'Washington', '700 WA Ln, WA'),
('NV Metro Police', 'Nevada', '800 NV Rd, NV'),
('CO Crime Investigators', 'Colorado', '900 CO Blvd, CO'),
('AZ Public Safety', 'Arizona', '1000 AZ St, AZ');

-- Insert into Officers Table
INSERT INTO Officers (FirstName, LastName, BadgeNumber, RankID, ContactInfo, AgencyID) VALUES
('Mark', 'Brown', '12345', 'Sergeant', 'mark@police.com', 1),
('Lisa', 'Davis', '67890', 'Lieutenant', 'lisa@police.com', 2),
('Kevin', 'Johnson', '11111', 'Detective', 'kevin@police.com', 3),
('Emma', 'Williams', '22222', 'Captain', 'emma@police.com', 4),
('Chris', 'Martinez', '33333', 'Officer', 'chris@police.com', 5),
('Sophia', 'Garcia', '44444', 'Lieutenant', 'sophia@police.com', 6),
('Daniel', 'Rodriguez', '55555', 'Sergeant', 'daniel@police.com', 7),
('Olivia', 'Lopez', '66666', 'Detective', 'olivia@police.com', 8),
('Ethan', 'Hernandez', '77777', 'Officer', 'ethan@police.com', 9),
('Charlotte', 'Moore', '88888', 'Captain', 'charlotte@police.com', 10);

-- Insert into Incidents Table
INSERT INTO Incidents (IncidentType, IncidentDate, Location, Description, Status, VictimID, SuspectID, OfficerID) VALUES
('Robbery', '2023-01-15', 'Downtown NY', 'Bank robbery reported', 'Open', 1, 1, 1),
('Homicide', '2023-02-10', 'Central Park', 'Murder case investigation', 'Under Investigation', 2, 2, 2),
('Theft', '2023-03-05', 'Supermarket, TX', 'Shoplifting case', 'Closed', 3, 3, 3),
('Burglary', '2023-04-20', 'Residential Area, LA', 'House break-in reported', 'Open', 4, 4, 4),
('Assault', '2023-05-18', 'Street, FL', 'Physical altercation', 'Under Investigation', 5, 5, 5),
('Drug Possession', '2023-06-25', 'Highway, IL', 'Illegal drugs found', 'Closed', 6, 6, 6),
('Fraud', '2023-07-14', 'Bank, CA', 'Credit card fraud case', 'Open', 7, 7, 7),
('Kidnapping', '2023-08-29', 'Park, WA', 'Child missing case', 'Under Investigation', 8, 8, 8),
('Vandalism', '2023-09-12', 'School, NV', 'Graffiti on school walls', 'Closed', 9, 9, 9),
('Arson', '2023-10-08', 'Warehouse, CO', 'Intentional fire reported', 'Open', 10, 10, 10);

-- Insert into Evidence Table
INSERT INTO Evidence (Description, LocationFound, IncidentID) VALUES
('Fingerprint found', 'Vault door', 1),
('Blood sample', 'Crime scene', 2),
('Security footage', 'Supermarket camera', 3),
('Broken window', 'House entrance', 4),
('Weapon found', 'Near assault site', 5),
('Drug packets', 'Car trunk', 6),
('Forged documents', 'Bank office', 7),
('Child’s toy', 'Park bench', 8),
('Spray paint can', 'School dumpster', 9),
('Burned matchstick', 'Warehouse debris', 10);


select * from Incidents;
select * from victims;
select * from suspects;
select * from officers;
select * from reports;


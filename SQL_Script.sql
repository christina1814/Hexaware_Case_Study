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
('Amit', 'Sharma', '1985-06-15', 'Male', '45 MG Road, Delhi'),
('Priya', 'Iyer', '1990-08-22', 'Female', '12 Anna Nagar, Chennai'),
('Rahul', 'Verma', '1982-03-11', 'Male', '67 BTM Layout, Bangalore'),
('Sneha', 'Patel', '1995-12-30', 'Female', '101 Navrangpura, Ahmedabad'),
('Arjun', 'Reddy', '1987-07-07', 'Male', '89 Banjara Hills, Hyderabad'),
('Kavita', 'Menon', '1993-09-18', 'Female', '33 Fort Kochi, Kochi'),
('Sanjay', 'Mehta', '1980-01-05', 'Male', '58 Sector 17, Chandigarh'),
('Anjali', 'Desai', '1989-11-21', 'Female', '76 Koregaon Park, Pune'),
('Rohan', 'Singh', '1997-05-14', 'Male', '110 Hazaribagh Road, Ranchi'),
('Neha', 'Joshi', '1984-04-28', 'Female', '25 Jubilee Hills, Hyderabad');


-- Insert into Suspects Table
INSERT INTO Suspects (FirstName, LastName, DateOfBirth, Gender, ContactInfo) VALUES
('Rakesh', 'Kumar', '1975-10-05', 'Male', '12 Daryaganj, Delhi'),
('Meena', 'Rao', '1988-06-25', 'Female', '18 Mylapore, Chennai'),
('Vikram', 'Naik', '1980-02-14', 'Male', '20 JP Nagar, Bangalore'),
('Pooja', 'Kapoor', '1992-12-03', 'Female', '14 Satellite, Ahmedabad'),
('Manoj', 'Yadav', '1983-08-19', 'Male', '77 Kukatpally, Hyderabad'),
('Lalitha', 'Pillai', '1991-07-12', 'Female', '33 MG Road, Kochi'),
('Ajay', 'Thakur', '1978-11-30', 'Male', '23 Sector 22, Chandigarh'),
('Divya', 'Shetty', '1996-05-06', 'Female', '40 FC Road, Pune'),
('Nikhil', 'Roy', '1985-09-28', 'Male', '89 Harmu Colony, Ranchi'),
('Suman', 'Pandey', '1982-03-22', 'Female', '60 Ameerpet, Hyderabad');


-- Insert into Law Enforcement Agencies Table
INSERT INTO LawEnforcementAgencies (AgencyName, Jurisdiction, ContactInfo) VALUES
('Delhi Police', 'Delhi', 'Police HQ, ITO, Delhi'),
('Chennai Police', 'Chennai', 'Commissioner Office, Egmore, Chennai'),
('Bangalore Police', 'Bangalore', 'Infantry Road HQ, Bangalore'),
('Ahmedabad Police', 'Ahmedabad', 'Shahibaug HQ, Ahmedabad'),
('Hyderabad Police', 'Hyderabad', 'Basheerbagh, Hyderabad'),
('Kochi City Police', 'Kochi', 'Marine Drive HQ, Kochi'),
('Chandigarh Police', 'Chandigarh', 'Sector 9, Chandigarh'),
('Pune Police', 'Pune', 'Commissioner Office, Shivajinagar, Pune'),
('Ranchi Police', 'Ranchi', 'Doranda HQ, Ranchi'),
('Telangana CID', 'Telangana', 'CID Complex, Nampally, Hyderabad');


-- Insert into Officers Table
INSERT INTO Officers (FirstName, LastName, BadgeNumber, RankID, ContactInfo, AgencyID) VALUES
('Rajesh', 'Shukla', 'IN12345', 'Inspector', 'rajesh.shukla@delhipolice.in', 1),
('Lakshmi', 'Venkatesan', 'IN67890', 'Sub-Inspector', 'lakshmi.venkatesan@chennaipolice.in', 2),
('Karthik', 'Rao', 'IN11111', 'DSP', 'karthik.rao@blrcitypolice.in', 3),
('Nidhi', 'Deshmukh', 'IN22222', 'ACP', 'nidhi.deshmukh@ahdpolice.in', 4),
('Arvind', 'Rao', 'IN33333', 'SI', 'arvind.rao@hydpolice.in', 5),
('Shalini', 'Menon', 'IN44444', 'ACP', 'shalini.menon@kochicitypolice.in', 6),
('Harsh', 'Singh', 'IN55555', 'Inspector', 'harsh.singh@chandigarhpolice.in', 7),
('Preeti', 'Kulkarni', 'IN66666', 'DSP', 'preeti.kulkarni@punepolice.in', 8),
('Abhishek', 'Verma', 'IN77777', 'SI', 'abhishek.verma@ranchipolice.in', 9),
('Deepika', 'Rathi', 'IN88888', 'ACP', 'deepika.rathi@telanganacid.in', 10);

-- Insert into Incidents Table
INSERT INTO Incidents (IncidentType, IncidentDate, Location, Description, Status, VictimID, SuspectID, OfficerID) VALUES
('Robbery', '2023-01-15', 'Karol Bagh, Delhi', 'Jewelry store robbery reported', 'Open', 1, 1, 1),
('Homicide', '2023-02-10', 'Marina Beach, Chennai', 'Homicide investigation', 'Under Investigation', 2, 2, 2),
('Theft', '2023-03-05', 'Forum Mall, Bangalore', 'Mobile phone theft case', 'Closed', 3, 3, 3),
('Burglary', '2023-04-20', 'Maninagar, Ahmedabad', 'Home burglary reported', 'Open', 4, 4, 4),
('Assault', '2023-05-18', 'Charminar, Hyderabad', 'Street fight case', 'Under Investigation', 5, 5, 5),
('Drug Possession', '2023-06-25', 'Vytilla Junction, Kochi', 'Narcotics found in vehicle', 'Closed', 6, 6, 6),
('Fraud', '2023-07-14', 'Bank of India, Chandigarh', 'ATM fraud complaint', 'Open', 7, 7, 7),
('Kidnapping', '2023-08-29', 'Camp Area, Pune', 'Child kidnapping case', 'Under Investigation', 8, 8, 8),
('Vandalism', '2023-09-12', 'St. Xavier’s School, Ranchi', 'School walls vandalized', 'Closed', 9, 9, 9),
('Arson', '2023-10-08', 'Industrial Area, Hyderabad', 'Warehouse set on fire', 'Open', 10, 10, 10);


-- Insert into Evidence Table
INSERT INTO Evidence (Description, LocationFound, IncidentID) VALUES
('Gold chain recovered', 'Back alley, Karol Bagh', 1),
('Knife with blood stains', 'Near Marina Beach', 2),
('CCTV footage', 'Forum Mall security room', 3),
('Broken lock', 'Main door, Maninagar house', 4),
('Iron rod used in fight', 'Charminar street', 5),
('Brown powder packets', 'Car boot, Vytilla', 6),
('Fake ATM card', 'Bank CCTV footage', 7),
('Child’s shoe', 'Park near Camp Area', 8),
('Spray cans', 'School grounds', 9),
('Burnt documents', 'Warehouse floor', 10);



select * from Incidents;
select * from victims;
select * from suspects;
select * from officers;
select * from reports;



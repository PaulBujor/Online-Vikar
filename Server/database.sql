Create Schema sep2db;

SET Schema 'sep2db';


/* address id - when adding any address, the system will have to check if the database already has same country,city,street,zip .
If it does then it will return the adress with the existing ID else will create a new row*/
Create Table address
(
    addressID SERIAL PRIMARY KEY,
    country   VARCHAR(100) NOT NULL,
    city      VARCHAR(100) NOT NULL,
    street    VARCHAR(100) NOT NULL,
    zip       VARCHAR(50)  NOT NULL
);

Create Table employer
(
    cvr         VARCHAR(100) PRIMARY KEY NOT NULL,
    password    VARCHAR(256)             NOT NULL,
    companyName VARCHAR(200)             NOT NULL,
    email       VARCHAR(200)             NOT NULL,
    phone       VARCHAR(200)             NOT NULL,
    address     int,
    FOREIGN KEY (address) REFERENCES address (addressID)
);

Create Table worker
(
    cpr         VARCHAR(100) PRIMARY KEY,
    password    VARCHAR(256) NOT NULL,
    firstName   VARCHAR(100) NOT NULL,
    lastName    VARCHAR(100) NOT NULL,
    gender      VARCHAR(50)  NOT NULL,
    birthday    DATE         NOT NULL,
    taxCard     VARCHAR(100) NOT NULL,
    email       VARCHAR(200) NOT NULL,
    phone       VARCHAR(200) NOT NULL,
    languages   VARCHAR(1000),
    description VARCHAR(3000),
    address     int,
    FOREIGN KEY (address) REFERENCES address (addressID)
);

CREATE Table job
(
    jobID         SERIAL PRIMARY KEY,
    jobTitle      VARCHAR(200)  NOT NULL,
    description   VARCHAR(2000),
    salary        decimal(5, 2) NOT NULL,
    workersNeeded int           NOT NULL,
    shiftStart    TIMESTAMP     NOT NULL,
    shiftEnd       TIMESTAMP     NOT NULL,
    status        VARCHAR(200)  NOT NULL,
    address       int,
    cvr           VARCHAR(100),
    FOREIGN KEY (cvr) REFERENCES employer (cvr),
    FOREIGN KEY (address) REFERENCES address (addressID)
);

CREATE Table works
(
    cpr   VARCHAR(100),
    jobID int,
    FOREIGN KEY (cpr) REFERENCES worker (cpr),
    FOREIGN KEY (jobID) REFERENCES job (jobID),
    PRIMARY KEY (cpr, jobID)
);

CREATE TABLE applied
(
    cpr   VARCHAR(100),
    jobID int,
    FOREIGN KEY (cpr) REFERENCES worker (cpr),
    FOREIGN KEY (jobID) REFERENCES job (jobID),
    PRIMARY KEY (cpr, jobID)
);

CREATE Table license
(
    cpr           VARCHAR(100),
    FOREIGN KEY (cpr) REFERENCES worker (cpr),
    licenseNumber VARCHAR(200) NOT NULL,
    PRIMARY KEY (cpr, licenseNumber),
    typee         VARCHAR(200),
    category      VARCHAR(200) NOT NULL,
    issueDate     TIMESTAMP    NOT NULL,
    expiryDate    TIMESTAMP    NOT NULL
);
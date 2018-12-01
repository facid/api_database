CREATE TABLE IF NOT EXISTS Organization (
    id          INTEGER  PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(10) NOT NULL,
    full_name   VARCHAR(50) UNIQUE NOT NULL,
    address     VARCHAR(50) NOT NULL,
    phone       VARCHAR(15) UNIQUE,
    inn         VARCHAR(10) UNIQUE NOT NULL,
    kpp         VARCHAR(9) UNIQUE NOT NULL,
    is_active   BOOLEAN
);

CREATE TABLE IF NOT EXISTS Office (
    id         INTEGER PRIMARY KEY AUTO_INCREMENT,
    org_id     INTEGER NOT NULL,
    name       VARCHAR(50) UNIQUE NOT NULL,
    phone      VARCHAR(15) UNIQUE,
    address    VARCHAR(50) NOT NULL,
    is_active  BOOLEAN
);

CREATE TABLE IF NOT EXISTS Employee (
    id               INTEGER PRIMARY KEY AUTO_INCREMENT,
    office_id        INTEGER NOT NULL,
    first_name       VARCHAR(50) NOT NULL,
    second_name      VARCHAR(50),
    last_name        VARCHAR(50),
    position         VARCHAR(50) NOT NULL,
    phone            VARCHAR(15) UNIQUE,
    doc_code         VARCHAR(2),
    doc_number       VARCHAR(10) UNIQUE,
    doc_date         DATE,
    citizenship_code VARCHAR(3),
    is_identified    BOOLEAN
);

CREATE TABLE IF NOT EXISTS Document_Type (
    id     INTEGER PRIMARY KEY AUTO_INCREMENT,
    name   VARCHAR(40),
    code   VARCHAR(2)
);

CREATE TABLE IF NOT EXISTS Country (
    id     INTEGER PRIMARY KEY AUTO_INCREMENT,
    name   VARCHAR(50),
    code   VARCHAR(3)
);

CREATE INDEX UX_Organization_Full_Name ON Organization (full_name);
CREATE INDEX UX_Organization_INN ON Organization (inn);
CREATE INDEX IX_Organization_Is_Active ON Organization (is_active);

CREATE INDEX UX_Office_Name ON Office (name);
CREATE INDEX UX_Office_Phone ON Office (phone);
CREATE INDEX IX_Office_Is_Active ON Office (is_active);

CREATE INDEX IX_Employee_First_Name ON Employee (first_name);
CREATE INDEX IX_Employee_Second_Name ON Employee (second_name);
CREATE INDEX IX_Employee_Last_Name ON Employee (last_name);
CREATE INDEX IX_Employee_Position ON Employee (position);

CREATE INDEX IX_Office_Org_Id ON Office (org_id);
ALTER TABLE Office ADD FOREIGN KEY (org_id) REFERENCES Organization (id);

CREATE INDEX IX_Employee_Office_Id ON Employee (office_id);
ALTER TABLE Employee ADD FOREIGN KEY (office_id) REFERENCES Office (id);

CREATE INDEX IX_Employee_Doc_Code ON Document_Type (code);
ALTER TABLE Employee ADD FOREIGN KEY (doc_code) REFERENCES Document_Type (code);

CREATE INDEX IX_Employee_Citizenship_Code ON Employee (citizenship_code);
ALTER TABLE Employee ADD FOREIGN KEY (citizenship_code) REFERENCES Country (code);

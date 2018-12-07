CREATE TABLE IF NOT EXISTS Organization (
    id          INTEGER                      COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    version     INTEGER NOT NULL             COMMENT 'Служебное поле hibernate',
    name        VARCHAR(10) NOT NULL         COMMENT 'Имя',
    full_name   VARCHAR(50) UNIQUE NOT NULL  COMMENT 'Полное имя',
    address     VARCHAR(50) NOT NULL         COMMENT 'Адрес',
    phone       VARCHAR(15) UNIQUE           COMMENT 'Телефон',
    inn         VARCHAR(10) UNIQUE NOT NULL  COMMENT 'ИНН',
    kpp         VARCHAR(9) UNIQUE NOT NULL   COMMENT 'КПП',
    is_active   BOOLEAN                      COMMENT 'Активность'
);
COMMENT ON TABLE Organization IS 'Организация';

CREATE TABLE IF NOT EXISTS Office (
    id         INTEGER                      COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    version    INTEGER NOT NULL             COMMENT 'Служебное поле hibernate',
    org_id     INTEGER NOT NULL             COMMENT 'Уникальный идентификатор огранизации',
    name       VARCHAR(50) UNIQUE NOT NULL  COMMENT 'Название',
    phone      VARCHAR(15) UNIQUE           COMMENT 'Телефон',
    address    VARCHAR(50) NOT NULL         COMMENT 'Адрес',
    is_active  BOOLEAN                      COMMENT 'Активность'
);
COMMENT ON TABLE Office IS 'Офис';

CREATE TABLE IF NOT EXISTS Employee (
    id               INTEGER              COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    version          INTEGER NOT NULL     COMMENT 'Служебное поле hibernate',
    office_id        INTEGER NOT NULL     COMMENT 'Уникальный идентификатор офиса',
    doc_data_id      INTEGER NOT NULL     COMMENT 'Уникальный идентификатор данных документа',
    country_id       INTEGER NOT NULL     COMMENT 'Уникальный идентификатор страны',
    first_name       VARCHAR(50) NOT NULL COMMENT 'Имя',
    second_name      VARCHAR(50)          COMMENT 'Отчество',
    last_name        VARCHAR(50)          COMMENT 'Фамилия',
    position         VARCHAR(50) NOT NULL COMMENT 'Должность',
    phone            VARCHAR(15) UNIQUE   COMMENT 'Телефон',
    is_identified    BOOLEAN              COMMENT 'Идентификация'
);
COMMENT ON TABLE Employee IS 'Работник';

CREATE TABLE IF NOT EXISTS Document_Type (
    id          INTEGER             COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    version     INTEGER NOT NULL    COMMENT 'Служебное поле hibernate',
    name        VARCHAR(50)         COMMENT 'Название',
    code        VARCHAR(2)          COMMENT 'Код'
);
COMMENT ON TABLE Document_Type IS 'Тип документа';

CREATE TABLE IF NOT EXISTS Document_Data(
    id              INTEGER             COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    version         INTEGER NOT NULL    COMMENT 'Служебное поле hibernate',
    doc_type_id     INTEGER NOT NULL    COMMENT 'Уникальный идентификатор типа документа',
    doc_number      VARCHAR(10) UNIQUE  COMMENT 'Номер',
    doc_date        DATE                COMMENT 'Дата'
);
COMMENT ON TABLE Document_Data IS 'Данные документа';

CREATE TABLE IF NOT EXISTS Country (
    id        INTEGER           COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    version   INTEGER NOT NULL  COMMENT 'Служебное поле hibernate',
    name      VARCHAR(50)       COMMENT 'Название',
    code      VARCHAR(3)        COMMENT 'Код'
);
COMMENT ON TABLE Country IS 'Страна';

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

CREATE INDEX IX_Document_Data_Doc_Type_Id ON Document_Data (doc_type_id);
ALTER TABLE Document_Data ADD FOREING KEY (doc_type_id) REFERENCES Document_Type (id);

CREATE INDEX IX_Employee_Doc_Data_Id ON Employee (doc_data_id);
ALTER TABLE Employee ADD FOREIGN KEY (doc_data_id) REFERENCES Document_Data (id);

CREATE INDEX IX_Employee_Citizenship_Code ON Employee (country_id);
ALTER TABLE Employee ADD FOREIGN KEY (country_id) REFERENCES Country (id);
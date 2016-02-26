#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------


#------------------------------------------------------------
# Table: CITY
#------------------------------------------------------------

CREATE TABLE CITY(
        CITY_ID        int (11) Auto_increment  NOT NULL ,
        CITY_NAME      Varchar (255) NOT NULL ,
        CITY_STU_COUNT Int NOT NULL ,
        PRIMARY KEY (CITY_ID )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: INSTITUTION
#------------------------------------------------------------

CREATE TABLE INSTITUTION(
        INST_ID   int (11) Auto_increment  NOT NULL ,
        INST_NAME Varchar (255) NOT NULL ,
        INST_TYPE Int NOT NULL ,
        PRIMARY KEY (INST_ID )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: SCHOOL
#------------------------------------------------------------

CREATE TABLE SCHOOL(
        SCHOOL_ID     int (11) Auto_increment  NOT NULL ,
        SCHOOL_NAME   Varchar (255) NOT NULL ,
        SCHOOL_REPUTE Int NOT NULL ,
        SCHOOL_COST   Int NOT NULL ,
        SCHOOL_SEATS  Int NOT NULL ,
        CITY_ID       Int NOT NULL ,
        INST_ID       Int NOT NULL ,
        PRIMARY KEY (SCHOOL_ID )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: POLE
#------------------------------------------------------------

CREATE TABLE POLE(
        POLE_ID         int (11) Auto_increment  NOT NULL ,
        POLE_CAT        Int NOT NULL ,
        POLE_SEATS_RATE Int NOT NULL ,
        POLE_COST       Int NOT NULL ,
        POLE_COND       Int NOT NULL ,
        POLE_SCHOOL     Int NOT NULL ,
        SCHOOL_ID       Int NOT NULL ,
        PRIMARY KEY (POLE_ID )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: STUDENT
#------------------------------------------------------------

CREATE TABLE STUDENT(
        STUDENT_ID       int (11) Auto_increment  NOT NULL ,
        STUDENT_NAME     Varchar (255) NOT NULL ,
        STUDENT_BIRTH    Date NOT NULL ,
        STUDENT_CITY     Int NOT NULL ,
        STUDENT_STUDISME Int NOT NULL ,
        STUDENT_PCTN     Int NOT NULL ,
        STUDENT_FLAWS    Int NOT NULL ,
        CITY_ID          Int NOT NULL ,
        PRIMARY KEY (STUDENT_ID )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: TEACHER
#------------------------------------------------------------

CREATE TABLE TEACHER(
        TEACHER_ID         int (11) Auto_increment  NOT NULL ,
        TEACHER_NAME       Varchar (255) NOT NULL ,
        TEACHER_BIRTH      Date NOT NULL ,
        TEACHER_ENTERING   Date NOT NULL ,
        TEACHER_CHARISMA   Int NOT NULL ,
        TEACHER_PNTY       Int NOT NULL ,
        TEACHER_SKILL_RATE Int NOT NULL ,
        POLE_ID            Int NOT NULL ,
        PRIMARY KEY (TEACHER_ID )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: CATEGORY
#------------------------------------------------------------

CREATE TABLE CATEGORY(
        CAT_ID      int (11) Auto_increment  NOT NULL ,
        CAT_NAME    Varchar (255) NOT NULL ,
        CAT_ATTRACT Int NOT NULL ,
        PRIMARY KEY (CAT_ID )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: INTEREST
#------------------------------------------------------------

CREATE TABLE INTEREST(
        INTEREST_RATE Int NOT NULL ,
        CAT_ID        Int NOT NULL ,
        STUDENT_ID    Int NOT NULL ,
        PRIMARY KEY (CAT_ID ,STUDENT_ID )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: GRADUATE
#------------------------------------------------------------

CREATE TABLE GRADUATE(
        GRADUATE_GRADE Int ,
        GRADUATE_YEAR  Date ,
        STUDENT_ID     Int NOT NULL ,
        POLE_ID        Int NOT NULL ,
        PRIMARY KEY (STUDENT_ID ,POLE_ID )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: TEACH
#------------------------------------------------------------

CREATE TABLE TEACH(
        TEACHER_ID Int NOT NULL ,
        CAT_ID     Int NOT NULL ,
        PRIMARY KEY (TEACHER_ID ,CAT_ID )
)ENGINE=InnoDB;

ALTER TABLE SCHOOL ADD CONSTRAINT FK_SCHOOL_CITY_ID FOREIGN KEY (CITY_ID) REFERENCES CITY(CITY_ID);
ALTER TABLE SCHOOL ADD CONSTRAINT FK_SCHOOL_INST_ID FOREIGN KEY (INST_ID) REFERENCES INSTITUTION(INST_ID);
ALTER TABLE POLE ADD CONSTRAINT FK_POLE_SCHOOL_ID FOREIGN KEY (SCHOOL_ID) REFERENCES SCHOOL(SCHOOL_ID);
ALTER TABLE STUDENT ADD CONSTRAINT FK_STUDENT_CITY_ID FOREIGN KEY (CITY_ID) REFERENCES CITY(CITY_ID);
ALTER TABLE TEACHER ADD CONSTRAINT FK_TEACHER_POLE_ID FOREIGN KEY (POLE_ID) REFERENCES POLE(POLE_ID);
ALTER TABLE INTEREST ADD CONSTRAINT FK_INTEREST_CAT_ID FOREIGN KEY (CAT_ID) REFERENCES CATEGORY(CAT_ID);
ALTER TABLE INTEREST ADD CONSTRAINT FK_INTEREST_STUDENT_ID FOREIGN KEY (STUDENT_ID) REFERENCES STUDENT(STUDENT_ID);
ALTER TABLE GRADUATE ADD CONSTRAINT FK_GRADUATE_STUDENT_ID FOREIGN KEY (STUDENT_ID) REFERENCES STUDENT(STUDENT_ID);
ALTER TABLE GRADUATE ADD CONSTRAINT FK_GRADUATE_POLE_ID FOREIGN KEY (POLE_ID) REFERENCES POLE(POLE_ID);
ALTER TABLE TEACH ADD CONSTRAINT FK_TEACH_TEACHER_ID FOREIGN KEY (TEACHER_ID) REFERENCES TEACHER(TEACHER_ID);
ALTER TABLE TEACH ADD CONSTRAINT FK_TEACH_CAT_ID FOREIGN KEY (CAT_ID) REFERENCES CATEGORY(CAT_ID);

#------------------------------------------------------------
#        Script SQLite  
#------------------------------------------------------------


#------------------------------------------------------------
# Table: CITY
#------------------------------------------------------------
CREATE TABLE CITY(
	CITY_ID         INTEGER autoincrement NOT NULL ,
	CITY_NAME       TEXT NOT NULL ,
	CITY_STU_COUNT  INTEGER NOT NULL ,
	PRIMARY KEY (CITY_ID)
);


#------------------------------------------------------------
# Table: INSTITUTION
#------------------------------------------------------------
CREATE TABLE INSTITUTION(
	INST_ID    INTEGER autoincrement NOT NULL ,
	INST_NAME  TEXT NOT NULL ,
	INST_TYPE  INTEGER NOT NULL ,
	PRIMARY KEY (INST_ID)
);


#------------------------------------------------------------
# Table: SCHOOL
#------------------------------------------------------------
CREATE TABLE SCHOOL(
	SCHOOL_ID      INTEGER autoincrement NOT NULL ,
	SCHOOL_NAME    TEXT NOT NULL ,
	SCHOOL_REPUTE  INTEGER NOT NULL ,
	SCHOOL_COST    INTEGER NOT NULL ,
	SCHOOL_SEATS   INTEGER NOT NULL ,
	CITY_ID        INTEGER NOT NULL ,
	INST_ID        INTEGER NOT NULL ,
	PRIMARY KEY (SCHOOL_ID) ,
	
	FOREIGN KEY (CITY_ID) REFERENCES CITY(CITY_ID),
	FOREIGN KEY (INST_ID) REFERENCES INSTITUTION(INST_ID)
);


#------------------------------------------------------------
# Table: POLE
#------------------------------------------------------------
CREATE TABLE POLE(
	POLE_ID          INTEGER autoincrement NOT NULL ,
	POLE_CAT         INTEGER NOT NULL ,
	POLE_SEATS_RATE  INTEGER NOT NULL ,
	POLE_COST        INTEGER NOT NULL ,
	POLE_COND        INTEGER NOT NULL ,
	POLE_SCHOOL      INTEGER NOT NULL ,
	SCHOOL_ID        INTEGER NOT NULL ,
	PRIMARY KEY (POLE_ID) ,
	
	FOREIGN KEY (SCHOOL_ID) REFERENCES SCHOOL(SCHOOL_ID)
);


#------------------------------------------------------------
# Table: STUDENT
#------------------------------------------------------------
CREATE TABLE STUDENT(
	STUDENT_ID        INTEGER autoincrement NOT NULL ,
	STUDENT_NAME      TEXT NOT NULL ,
	STUDENT_BIRTH     NUMERIC NOT NULL ,
	STUDENT_CITY      INTEGER NOT NULL ,
	STUDENT_STUDISME  INTEGER NOT NULL ,
	STUDENT_PCTN      INTEGER NOT NULL ,
	STUDENT_FLAWS     INTEGER NOT NULL ,
	CITY_ID           INTEGER NOT NULL ,
	PRIMARY KEY (STUDENT_ID) ,
	
	FOREIGN KEY (CITY_ID) REFERENCES CITY(CITY_ID)
);


#------------------------------------------------------------
# Table: TEACHER
#------------------------------------------------------------
CREATE TABLE TEACHER(
	TEACHER_ID          INTEGER autoincrement NOT NULL ,
	TEACHER_NAME        TEXT NOT NULL ,
	TEACHER_BIRTH       NUMERIC NOT NULL ,
	TEACHER_ENTERING    NUMERIC NOT NULL ,
	TEACHER_CHARISMA    INTEGER NOT NULL ,
	TEACHER_PNTY        INTEGER NOT NULL ,
	TEACHER_SKILL_RATE  INTEGER NOT NULL ,
	POLE_ID             INTEGER NOT NULL ,
	PRIMARY KEY (TEACHER_ID) ,
	
	FOREIGN KEY (POLE_ID) REFERENCES POLE(POLE_ID)
);


#------------------------------------------------------------
# Table: CATEGORY
#------------------------------------------------------------
CREATE TABLE CATEGORY(
	CAT_ID       INTEGER autoincrement NOT NULL ,
	CAT_NAME     TEXT NOT NULL ,
	CAT_ATTRACT  INTEGER NOT NULL ,
	PRIMARY KEY (CAT_ID)
);


#------------------------------------------------------------
# Table: INTEREST
#------------------------------------------------------------
CREATE TABLE INTEREST(
	INTEREST_RATE  INTEGER NOT NULL ,
	CAT_ID         INTEGER NOT NULL ,
	STUDENT_ID     INTEGER NOT NULL ,
	PRIMARY KEY (CAT_ID,STUDENT_ID) ,
	
	FOREIGN KEY (CAT_ID) REFERENCES CATEGORY(CAT_ID),
	FOREIGN KEY (STUDENT_ID) REFERENCES STUDENT(STUDENT_ID)
);


#------------------------------------------------------------
# Table: GRADUATE
#------------------------------------------------------------
CREATE TABLE GRADUATE(
	GRADUATE_GRADE  INTEGER ,
	GRADUATE_YEAR   NUMERIC ,
	STUDENT_ID      INTEGER NOT NULL ,
	POLE_ID         INTEGER NOT NULL ,
	PRIMARY KEY (STUDENT_ID,POLE_ID) ,
	
	FOREIGN KEY (STUDENT_ID) REFERENCES STUDENT(STUDENT_ID),
	FOREIGN KEY (POLE_ID) REFERENCES POLE(POLE_ID)
);


#------------------------------------------------------------
# Table: TEACH
#------------------------------------------------------------
CREATE TABLE TEACH(
	TEACHER_ID  INTEGER NOT NULL ,
	CAT_ID      INTEGER NOT NULL ,
	PRIMARY KEY (TEACHER_ID,CAT_ID) ,
	
	FOREIGN KEY (TEACHER_ID) REFERENCES TEACHER(TEACHER_ID),
	FOREIGN KEY (CAT_ID) REFERENCES CATEGORY(CAT_ID)
);
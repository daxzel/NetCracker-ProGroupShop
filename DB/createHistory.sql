
CREATE TABLE HISTORY (
             ID_HIS NUMBER(10) NOT NULL,
             ID_USER NUMBER(10) ,			 
             NAME_TABLE VARCHAR2(30),
			 STATUS VARCHAR2 (1000),
			 DATE_UPDATE DATE,
			 ID_OBJ NUMBER(10) NOT NULL,
            FOREIGN KEY ("ID_USER") REFERENCES "USER" ("ID_USER") ON DELETE SET NULL			 
);
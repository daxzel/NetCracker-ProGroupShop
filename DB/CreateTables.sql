
CREATE TABLE ROLE (
  ID_ROLE NUMBER(10) NOT NULL,
  NAME VARCHAR2(20) NOT NULL,
  CONSTRAINT ID_ROLE_ID PRIMARY KEY (ID_ROLE)
  );

CREATE TABLE "USER" (
  ID_USER   NUMBER(10) NOT NULL,
  NAME    VARCHAR2(50) NOT NULL,
  SURNAME          VARCHAR2(50) NOT NULL,
  OTCHESTVO  VARCHAR2(50) NOT NULL,
  NIK VARCHAR2(20) NOT NULL,
  PASSWORD VARCHAR2(50) NOT NULL,
  BORN     DATE  ,
  PHONE    VARCHAR2(11),
  EMAIL    VARCHAR2(25),
  REGISTRATION_DATE     DATE  ,
  ID_ROLE    NUMBER(10)  NOT NULL,
  CONSTRAINT ID_USER_PK PRIMARY KEY (ID_USER),
  FOREIGN KEY (ID_ROLE) REFERENCES ROLE (ID_ROLE),
  CONSTRAINT NIK_USER_UNIQUE UNIQUE (NIK)  
);
CREATE TABLE "CATALOG" ( 
     ID_CATALOG NUMBER (10)  NOT NULL , 
     ID_PARENT NUMBER (10) , 
     NAME VARCHAR2 (200) NOT NULL,
     CONSTRAINT ID_CATALOG_PK PRIMARY KEY (ID_CATALOG),
	 FOREIGN KEY (ID_PARENT) REFERENCES "CATALOG" (ID_CATALOG) ON DELETE CASCADE,
	 CONSTRAINT NAME_CATALOG_UNIQUE UNIQUE (NAME)
);
CREATE TABLE PRODUCT ( 
     DESCRIPTION VARCHAR2 (2000) , 
     ID_PRODUCT NUMBER (10) NOT NULL , 
     ID_CATALOG NUMBER (10)  NOT NULL , 
	 NAME VARCHAR2 (200)  NOT NULL , 
     PRICE DECIMAL (18,2), 
     CONSTRAINT ID_PRODUCT_PK PRIMARY KEY (ID_PRODUCT),
	 FOREIGN KEY (ID_CATALOG) REFERENCES "CATALOG" (ID_CATALOG) ON DELETE CASCADE,
	 CONSTRAINT UNIQUE_NAME_PRODUCT UNIQUE (NAME)
);
CREATE TABLE OPINION (
             ID_OPINION NUMBER(10) NOT NULL,
             ID_PRODUCT NUMBER(10) NOT NULL,
             ID_USER NUMBER(10) ,  
             TEXT VARCHAR2(2000) NOT NULL,
             CONSTRAINT ID_OPINION_PK PRIMARY KEY (ID_OPINION),
             FOREIGN KEY (ID_PRODUCT) REFERENCES PRODUCT (ID_PRODUCT)ON DELETE CASCADE,
             FOREIGN KEY (ID_USER) REFERENCES "USER" (ID_USER) ON DELETE SET NULL
);
CREATE TABLE IMAGE (
             ID_IMG NUMBER(10) NOT NULL,
             ID_PRODUCT NUMBER(10) NOT NULL,
             NAME VARCHAR2(50) NOT NULL,
             WIDTH NUMBER(4) NOT NULL,
             HEIGHT NUMBER (4) NOT NULL, 
             CONSTRAINT ID_IMG_PK PRIMARY KEY (ID_IMG), 
			 FOREIGN KEY (ID_PRODUCT) REFERENCES PRODUCT (ID_PRODUCT)ON DELETE CASCADE
);
CREATE TABLE "ORDER" (
			ID_ORDER NUMBER(10) NOT NULL,
			ID_USER NUMBER(10) NOT NULL,
			ID_PRODUCT NUMBER(10) NOT NULL,
			STATUS NUMBER(1,0) NOT NULL,
			KOL_VO NUMBER(10,0) NOT NULL,
			ORDER_BY_DATE     DATE  ,
			CONSTRAINT ID_ORDER PRIMARY KEY(ID_ORDER),
			FOREIGN KEY ("ID_USER") REFERENCES "USER" ("ID_USER") ON DELETE CASCADE , 
			FOREIGN KEY (ID_PRODUCT) REFERENCES PRODUCT (ID_PRODUCT)ON DELETE CASCADE
);
CREATE TABLE HISTORY (
             ID_HIS NUMBER(10) NOT NULL,
             ID_USER NUMBER(10) ,			 
             NAME_TABLE VARCHAR2(30),
			 STATUS VARCHAR2 (1000),
			 DATE_UPDATE DATE,
			 ID_OBJ NUMBER(10),
             FOREIGN KEY ("ID_USER") REFERENCES "USER" ("ID_USER") ON DELETE SET NULL			 
);


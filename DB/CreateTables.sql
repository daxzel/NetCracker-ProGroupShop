DROP TABLE IMAGE;
DROP TABLE OPINION;
DROP TABLE  "CATALOG";
DROP TABLE "ORDER";
DROP TABLE PRODUCT;
DROP TABLE "USER";
DROP TABLE ROLE;
CREATE TABLE ROLE (
  ID_ROLE NUMBER(10) NOT NULL,
  NAME VARCHAR2(20) NOT NULL,
  CONSTRAINT ID_ROLE_ID PRIMARY KEY (ID_ROLE)
  );
  
CREATE TABLE "USER" (
  ID_USER   NUMBER(10) NOT NULL,
  NAME    VARCHAR2(20) NOT NULL,
  SURNAME          VARCHAR2(25) NOT NULL,
  OTCHESTVO  VARCHAR2(20) NOT NULL,
  BORN     DATE        NOT NULL,
  PHONE    VARCHAR2(11),
  EMAIL    VARCHAR2(25),
  ID_ROLE    NUMBER(10),
  CONSTRAINT ID_USER_PK PRIMARY KEY (ID_USER),
 FOREIGN KEY (ID_ROLE) REFERENCES ROLE (ID_ROLE)   
);

CREATE TABLE PRODUCT 
    ( 
     DESCRIPTION VARCHAR2 (2000) , 
     ID_PRODUCT NUMBER (10) NOT NULL , 
    ID_CATALOG NUMBER (10)  NOT NULL , 
	NAME VARCHAR2 (30)  NOT NULL , 
     PRICE DECIMAL (18,2), 
     CONSTRAINT ID_PRODUCT_PK PRIMARY KEY (ID_PRODUCT),
	 FOREIGN KEY (ID_CATALOG) REFERENCES "CATALOG" (ID_CATALOG) 
    )  
;

CREATE TABLE OPINION (
             ID_OPINION NUMBER(10) NOT NULL,
             ID_PRODUCT NUMBER(10) NOT NULL,
             ID_USER NUMBER(10) NOT NULL,
             TEXT VARCHAR2(2000) NOT NULL,
             CONSTRAINT ID_OPINION_PK PRIMARY KEY (ID_OPINION),
             FOREIGN KEY (ID_PRODUCT) REFERENCES PRODUCT (ID_PRODUCT),
             FOREIGN KEY (ID_USER) REFERENCES "USER" (ID_USER)
);

CREATE TABLE "CATALOG"
    ( 
     ID_CATALOG NUMBER (10)  NOT NULL , 
     ID_PARENT NUMBER (10)  , 
     NAME VARCHAR2 (200) ,
     CONSTRAINT ID_CATALOG_PK PRIMARY KEY (ID_CATALOG)
     
    )
;

CREATE TABLE IMAGE (
             ID_IMG NUMBER(10) NOT NULL,
             ID_PRODUCT NUMBER(10) NOT NULL,
             NAME VARCHAR2(20) NOT NULL,
             IMAGE BLOB NOT NULL,
             WIDTH NUMBER(4) NOT NULL,
             HEIGHT NUMBER (4) NOT NULL, 
             EXTENSION VARCHAR2(4) NOT NULL,
             CONSTRAINT ID_IMG_PK PRIMARY KEY (ID_IMG),
             FOREIGN KEY (ID_PRODUCT) REFERENCES PRODUCT (ID_PRODUCT)
);
CREATE TABLE "ORDER" (
			ID_ORDER NUMBER(10) NOT NULL,
			ID_USER NUMBER(10) NOT NULL,
			ID_PRODUCT NUMBER(10) NOT NULL,
			STATUS NUMBER(1,0) NOT NULL,
			KOL_VO NUMBER(10,0) NOT NULL,
			CONSTRAINT ID_ORDER PRIMARY KEY(ID_ORDER),
			FOREIGN KEY ("ID_USER") REFERENCES "USER" ("ID_USER"),
			FOREIGN KEY (ID_PRODUCT) REFERENCES PRODUCT (ID_PRODUCT)
);


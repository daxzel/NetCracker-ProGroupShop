CREATE SEQUENCE MY_SEQ_O
	INCREMENT BY 1
  	START WITH 1
  	;
CREATE OR REPLACE TRIGGER MY_TRIGGER_O
BEFORE INSERT ON OPINION
FOR EACH ROW
BEGIN
SELECT MY_SEQ_O.NEXTVAL INTO :NEW.ID_OPINION FROM dual;
END;
/

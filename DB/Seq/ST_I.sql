CREATE SEQUENCE MY_SEQ_I
	INCREMENT BY 1
  	START WITH 1
  	;
CREATE OR REPLACE TRIGGER MY_TRIGGER_I
BEFORE INSERT ON IMAGE
FOR EACH ROW
BEGIN
SELECT MY_SEQ_I.NEXTVAL INTO :NEW.ID_IMG FROM dual;
END;
/


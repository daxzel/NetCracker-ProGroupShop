INSERT INTO "CATALOG" (ID_CATALOG,"NAME")   VALUES(1,'�������');

       INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME")   VALUES(2,1,'������� �������');
       
              INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME")   VALUES(3,2,'������������');
              INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME")   VALUES(4,2,'���������� ������');
              INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME")   VALUES(5,2,'������� ������');
              INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME")   VALUES(6,2,'������������� ����');
              
       INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME")   VALUES(7,1,'�����������');
       
              INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME") VALUES(8,7,'����������');
              INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME") VALUES(9,7,'����������');
              INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME") VALUES(10,7,'��������');
              INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME") VALUES(11,7,'�����������');
       
 INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(0,3,'LG GW-B207 FVQA',33090,
         '����:	 �������
        ������, ��:	 175
        ������, ��:	 89
        �������, ��:	 72,5
        ����� �����, �:	 511
        ����� ����������� ������, �:	 346
        ����� ����������� ������, �:	 165
        ��� ����������:	 �����������
        �������������� ����������� ������:	 No Frost
        �������������� ����������� ������:	 No Frost
        ���������� ������������:	 1
        ����� �����������������:	 A
        ��������:	 3 ����');
  INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(1,3,'Siemens KA 58NA45RU',62820,
        '����:	 ����������� �����
        ������, ��:	 180
        ������, ��:	 90
        �������, ��:	 67.5
        ����� �����, �:	 531
        ����� ����������� ������, �:	 356
        ����� ����������� ������, �:	 175
        ��� ����������:	 �����������
        �������������� ����������� ������:	 No Frost
        �������������� ����������� ������:	 No Frost
        ���������� ������������:	 1
        ����� �����������������:	 �+');
    
       
COMMIT;
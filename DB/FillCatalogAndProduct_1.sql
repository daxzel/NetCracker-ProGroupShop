INSERT INTO "CATALOG" (ID_CATALOG,"NAME")   VALUES(1,'�������');

       INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME")   VALUES(2,1,'������� �������');
       
              INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME")   VALUES(3,2,'������������');
              INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME")   VALUES(4,2,'���������� ������');
              INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME")   VALUES(5,2,'��������');
              INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME")   VALUES(6,2,'������������� ����');
              
       INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME")   VALUES(7,1,'����������, �����, �����');
       
              INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME") VALUES(8,7,'����������');
			  
			         INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME") VALUES(9,8,'�� ����������');
					 INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME") VALUES(10,8,'���������� ����������');
					 INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME") VALUES(11,8,'LED ����������');
					 INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME") VALUES(12,8,'3D-����������');
					 
			  INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME") VALUES(13,7,'MP3-������');
			  INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME") VALUES(14,7,'����������� �����');
			  INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME") VALUES(15,7,'�������� ����������');
			  INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME") VALUES(16,7,'����������� ������');
			  INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME") VALUES(17,7,'DVD-������');
					
       INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME") VALUES(18,1,'���������� � ��������');
	   
		      INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME") VALUES(19,18,'��������');
			  INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME") VALUES(20,18,'���������� ����������');
					        
				     INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME") VALUES(21,20,'��������� ����');  
                     INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME") VALUES(22,20,'������������� � ��');
							
							INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME") VALUES(23,22,'����������� �����');
							INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME") VALUES(24,22,'����������');
							INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME") VALUES(25,22,'������ ������');
							INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME") VALUES(26,22,'����������');
							INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME") VALUES(27,22,'������� �����');
							INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME") VALUES(28,22,'���� �������');
							INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME") VALUES(29,22,'�������');
							INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME") VALUES(30,22,'��������');
							INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME") VALUES(31,22,'������������ ����');
							INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME") VALUES(32,22,'����������');
      	  
				INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME") VALUES(33,18,'���������� ����������');
				INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME") VALUES(34,18,'���������');
					 
	   INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME") VALUES(35,1,'��������');
	   
	          INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME") VALUES(36,35,'�������� ��������');
			  INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME") VALUES(37,35,'������� ��������');
	   
	   INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME") VALUES(38,1,'���� � �����');
	   
	          INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME") VALUES(39,38,'�������� ������������');
			  INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME") VALUES(40,38,'�������� �����������');
			         
                    
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(1,3,'����������� ����������� Liebherr KX 1021-21', 6990,
         '���������� ������� (�*�*�): 63*55*62 ��, ����� ����������� ������: 86 �, ����� ����������� ������: 6 �, ����� �����������������: A, �������� ����� �� �����: �������');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(2,3,'����������� ����������� Nord ��-403-010', 7990,
         '���������� ������� (�*�*�): 85*50*52 ��, ����� ����������� ������: 100 �, ����� ����������� ������: 10 �, ����� �����������������: A');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(3,3,'����������� ����������� Indesit TT-85.001-WT', 8490,
         '���������� ������� (�*�*�): 85*60*62 ��, ����� ����������� ������: 105 �, ����� ����������� ������: 14 �, ����� �����������������: B');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(4,3,'����������� ����������� Indesit TT-85.005-T', 9290,
         '���������� ������� (�*�*�): 85*60*62 ��, ����� ����������� ������: 105 �, ����� ����������� ������: 14 �, ����� �����������������: B');

INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(5,4,'���������� ������ INDESIT WIUN 103', 10499,
         '�����: ����� ������ - A, ��� ���������� - ����������-������������; ��������: ������������ �������� - 3.5 ��; �����: ����. �������� ������ - 1000 ��/���; ��������: ������� - 33 ��, ������ - 59.5 ��; ���� - �����');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(6,4,'���������� ������ SAMSUNG WF8500NHW', 9999,
         '�����: ����� ������ - A, ��� ���������� - �����������; ��������: ������������ �������� - 5 ��; �����: ����. �������� ������ - 1000 ��/���; ���������: ������� - ����; ��������: ������� - 45 ��, ������ - 60 ��; ���� - �����');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(7,4,'���������� ������ BOSCH WLX 24463 OE', 20999,
         '�����: ����� ������ - A, ��� ���������� - ����������-������������; ��������: ������������ �������� - 5 ��; �����: ����. �������� ������ - 1200 ��/���; ���������: ������� - ����; ��������: ������� - 40 ��, ������ - 60 ��; ���� - �����');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(8,4,'���������� ������ SAMSUNG WFE509NZW', 11999,
         '�����: ����� ������ - A, ��� ���������� - �����������; ��������: ������������ �������� - 5 ��; �����: ����. �������� ������ - 900 ��/���; ���������: ������� - ����; ��������: ������� - 45 ��, ������ - 60 ��; ���� - �����');
		 
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(9,5,'������� SAMSUNG VCC47S5H35', 4499,
         '���� ����: ������� ����� ���� - �����������; �������: ������ ������ ������� - HEPA11; ����������: ��� - �����������; ��������: ����. ������������ - 1800 ��; ��������: ��� - 4.6 ��');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(10,5,'������� SAMSUNG SC6570', 4199,
         '���� ����: ������� ����� ���� - �����������; �������: ������ ������ ������� - HEPA11; ����������: ��� - �����������; ��������: ����. ������������ - 1800 ��; ��������: ��� - 5.2 ��');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(11,5,'������� SAMSUNG SC8853', 20999,
         '���� ����: ������� ����� ���� - �����������; �������: ������ ������ ������� - HEPA12; ����������: ��� - �����������; ��������: ����. ������������ - 2200 ��; ��������: ��� - 5.7 ��');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(12,5,'������� DYSON DC32 EXCLUSIVE', 23999,
         '���� ����: ������� ����� ���� - �����������; �������: ������ ������ ������� - HEPA; ����������: ��� - ������������; ��������: ����. ������������ - 1400 ��; ��������: ��� - 8.7 ��');
		 
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(13,6,'������������� ���� LG MS-1947W', 2799,
         '�����: ��� ���������� - ���������; ������: ����� - 19 �; ��������: �������� ��������� - 700 ��; ���� - �����');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(14,6,'������������� ���� SUPRA MWS�1720', 1199,
         '�����: ��� ���������� - ������������; ������: ����� - 17 �; ��������: �������� ��������� - 700 ��; ���� - �����');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(15,6,'������������� ���� DAEWOO KOC-8H6T', 5999,
         '�����: ��������� - ����, ����� - ����, ��� ���������� - �����������; ������: ����� - 24 �; ��������: �������� ��������� - 900 ��; ���� - ������, �����������');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(16,6,'������������� ���� GORENJE MO20DW II', 3199,
         '�����: ��� ���������� - �����������; ������: ����� - 20 �; ��������: �������� ��������� - 800 ��; ���� - �����');
		 
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(17,9,'��-��������� 19" Philips 19PFL3606H/60', 7990,
         '������: 29 ��, ������: 46 ��, �������: 5.7 ��, ���������: 19"(48.2 ��), ���������� ������: 1366x768 ���� (HD Ready), �������� ��������������: ��, �������� �� ������ DVB-T/DVB-C: ��');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(18,9,'��-��������� 19" Samsung LE-19 D451G3W', 8990,
         '������: 32 ��, ������: 48 ��, �������: 6.3 ��, ���������: 19"(48.2 ��), ���������� ������: 1366x768 ���� (HD Ready), �������� ��������������: ��, �������� �� ������ DVB-T/DVB-C: ��');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(19,9,'��-��������� 19" LG 19 LD320', 8990,
         '������: 35 ��, ������: 46 ��, �������: 17 ��, ���������: 19"(48.2 ��), ���������� ������: 1366x768 ���� (HD Ready), �������� �� ����� DVB-T: ��');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(20,9,'��-��������� 19"/DVD combo Sharp LC-19 DV200 RU', 11990,
         '������: 36 ��, ������: 47 ��, �������: 20 ��, ���������: 19"(48.2 ��), ���������� ������: 1366x768 ���� (HD Ready), ���������� DVD �����: ��, �������� �� ����� DVB-T: ��');

INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(21,10,'���������� ��������� 42"-49" LG 42 PT351', 17990,
         '���������� ������: 1024x768 ����, ������: ������, ������ ��������� ������: 42"(106.6 ��), ���������� 600 ��: ��, �������� ��������������: ��, �������� �� ������ DVB-T/DVB-C: ��');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(22,10,'���������� ��������� 42"-49" Panasonic TX-PR42 C3', 18990,
         '���������� ������: 1024x768 ����, ������: �����, ������ ��������� ������: 42"(106.6 ��), ���������� 600 ��: ��, �������� ��������������: ��, �������� �� ������ DVB-T/DVB-C: ��');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(23,10,'���������� ��������� 50"-51" Panasonic TX-PR50 C3', 32990,
         '���������� ������: 1024x768 ����, ������: �����, ������ ��������� ������: 50"(126.9 ��), ���������� 600 ��: ��, �������� ��������������: ��, �������� �� ������ DVB-T/DVB-C: ��');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(24,10,'���������� ��������� Samsung PS-43 D452 A5W', 18990,
         '���������� ������: 1024x768 ����, ������: ������, ������ ��������� ������: 43"(109.2 ��), ���������� 600 ��: ��, ����: SRS TheaterSound, ����� ��� �����. �����������: ���.�����');		 
		 
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(25,11,'LED-��������� 19"-20" Vestel V19-LE990 HD', 6590,
         '������ ��������� ������: 19"(48.2 ��), ���������� ������: 1366x768 ���� (HD Ready), �������� ��������������: ��, �������� �� ����� DVB-T: ��, ����: NICAM ������');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(26,11,'LED-��������� 19"-20" Akai LEA19H03P', 6990,
         '������ ��������� ������: 19"(48.2 ��), ���������� ������: 1366x768 ���� (HD Ready), �������� ��������������: ��, ����: NICAM ������, ����� ��� �����. �����������: ���.�����');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(27,11,'LED-��������� 19"-20" Acer AT1926D', 6990,
         '������ ��������� ������: 19"(48.2 ��), ���������� ������: 1366x768 ���� (HD Ready), �������� ��������������: ��, �������� �� ����� DVB-T: ��');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(28,11,'LED-��������� 19"-20" Toshiba 19EL833R', 8990,
         '������ ��������� ������: 19"(48.2 ��), ���������� ������: 1366x768 ���� (HD Ready), �������� ��������������: ��, �������� �� ������ DVB-T/DVB-C: ��, ����: ������');	

INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(29,12,'3D LED ��������� 32"-37" LG 32 LW4500', 22490,
         '������ ��������� ������: 32"(81.2 ��), ���������� ������: 1920x1080 ���� (FullHD), ���������� ��������������� 3D: ��, ���������� 100 ��: ��, �������� �� ������ DVB-T/DVB-C: ��');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(30,12,'3D LED ��������� 32"-37" Samsung UE-32 D6100SW', 23990,
         '������ ��������� ������: 32"(81.2 ��), ���������� ������: 1920x1080 ���� (FullHD), ���������� ��������������� 3D: ��, ���������� 200 ��: ��, �������� �� ������ DVB-T/DVB-C: ��');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(31,12,'3D LED ��������� 32"-37" Sony KDL-32EX720', 27990,
         '������ ��������� ������: 32"(81.2 ��), ���������� ������: 1920x1080 ���� (FullHD), ���������� ��������������� 3D: ��, ���������� 100 ��: ��, �������� �� ������ DVB-T/DVB-C: ��');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(32,12,'3D LED ��������� 32"-37" Samsung UE-32 D6530WS', 28990,
         '������ ��������� ������: 32"(81.2 ��), ���������� ������: 1920x1080 ���� (FullHD), ���������� ��������������� 3D: ��, ���������� 200 ��: ��, �������� �� ������ DVB-T/DVB-C: ��');		 
		 
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(33,13,'����������� ���������� iRiver T8 4Gb Red', 590,
         '���������� ������: 4 ��, ��������� �������: 1 ", ���������� ��������: ��, �������� �����: FM, FM �����������: ���.�����, ������ �� ������������: �� 17 �����');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(34,13,'����������� ���������� Explay X1 4Gb White/blue', 590,
         '��������������� MP3: ��, ��������������� WMA: ��, ��������������� WAV: ��, ���������� ������: 4 ��, ������ �� ������������: �� 13 � 30 ���, ������� �� USB �����: ��');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(35,13,'����������� ���������� Ritmix RF-3310 2Gb', 690,
         '���������� ������: 2 ��, ��� ����� ������: microSD, microSDHC, ���������� ��������: ��, �������� �����: FM, ������ �� ������������: �� 15 �����, �������� �������� ������: ���.�����');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(36,13,'����������� ���������� Explay L70 2Gb', 990,
         '��������������� �����������: ��, ���������� ������: 4 ��, ��������� �������: 2.4 ", ���������� ��������: ��, �������� �����: FM, ������ �� ������������: �� 18 �����'); 
		 
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(37,14,'����������� ����� teXet TB-431HD 4Gb Grey', 1990,
         '������ ��������� ������: FB2,PDF,EPUB,TXT,HTML,RTF,MOBIPOCKET,PDB, ��������� �������: 4.3 ", ���. ������������ �������: LCD-TFT, ���������� ������: 4 ��');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(38,14,'����������� ����� MyOne RDR-01', 2790,
         '������ ��������� ������: EPUB,FB2,HTML,PDF,TXT,RTF,DOC, ��������� �������: 7 ", ���. ������������ �������: LCD-TFT, ��������� �������: ��, ���������� ������: 2 ��');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(39,14,'����������� ����� teXet TB-730HD', 2990,
         '������ ��������� ������: FB2,PDF,EPUB,TXT,HTML,RTF,MOBIPOCKET,PDB, ��������� �������: 7 ", ���. ������������ �������: LCD-TFT, ���������� ������: 4 ��');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(40,14,'����������� ����� Wexler T7002B Black', 3790,
         '������ ��������� ������: EPUB,FB2,TXT,PDF,HTML, ��������� �������: 7 ", ���. ������������ �������: LCD-TFT, ���������� ������: 4 ��, ��� ����� ������: microSD, microSDHC'); 

INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(41,15,'�������� ��������� PANASONIC SC-PT85EE-K', 7299,
         '�����: ��� ������������ ������� - 5.1; �������������� �������: ����� - VCD, SVCD, MPEG4; ����: ��������� �������� - 1000 ��; ����������: HDMI - 4; ���� - ������');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(42,15,'�������� ��������� PHILIPS HTS5120', 11499,
         '�����: ��� ������������ ������� - 2.1; �������������� �������: ����� - AVI, DivX, VCD, SVCD, WMV; ����: ��������� �������� - 400 ��; ����������: HDMI - 1; ���� - ������');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(43,15,'�������� ��������� SAMSUNG HT-BD8200', 18999,
         '�����: ��� ������������ ������� - 2.1; �������������� �������: ����� - DivX, VCD, SVCD, MPEG4; ����: ��������� �������� - 300 ��; ����������: HDMI - 1; ���� - ������');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(44,15,'�������� PANASONIC SC-BFT800EE-K', 29999,
         '�����: ��� ������������ ������� - 5.1; �������������� �������: ����� - AVI, DivX, MPEG4; ����: ��������� �������� - 300 ��; ����������: HDMI - 1; ���� - ������');

INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(45,16,'����������� ����� Micro � DVD Hyundai H-MS1113 Black', 3290,
         '��������������� DVD �����: ��, �������� �����: FM/A�, ������� ''�������'': ��, �������� ����������� ��: 2 x 15 ��, DVD (+/-R/RW), CD(R/RW), MP3, WMA');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(46,16,'����������� ����� Micro � DVD LG XB16', 3790,
         '��������������� DVD �����: ��, �����. ����������� � ����.���������: ��, �������� �����: FM, ������� Dolby Digital: ��, ������ � CD �� USB ����������: ��');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(47,16,'����������� ����� Micro � DVD Philips MC-D107/51', 3990,
         '��������������� DVD �����: ��, �����. ����������� � ����.���������: ��, �������� �����: FM, �������� ����������� ��: 2 x 5 ��, ������� ��������: DVD,2 x 5 ��, ��������: 1 ���');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(48,16,'����������� ����� Micro � DVD LG XB66', 3790,
         '������ ��������� ������: EPUB,FB2,TXT,PDF,HTML, ��������� �������: 7 ", ���. ������������ �������: LCD-TFT, ���������� ������: 4 ��, ��� ����� ������: microSD, microSDHC'); 	
		 
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(49,17,'DVD-����� Soundmax SM-DVD5113 Black', 790,
         '��������������� DVD �����: ��, �������� �����: FM/A�, ������� ''�������'': ��, �������� ����������� ��: 2 x 15 ��, DVD (+/-R/RW), CD(R/RW), MP3, WMA');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(50,17,'DVD-����� Supra DVS-102X', 890,
         '������� ��������: DVD,MPEG4,������, ��������: 1 ���, ������: ���, ��� ��������: ����������� �����, �������� ������: 1, ��� �������� ������: ��������� �����');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(51,17,'DVD-����� Philips DVP 1033/51', 990,
         '��������������� DVD �����: ��, ������� Dolby Digital: ��, ������������� ����������: ������, ����� RCA ����� �����������: 1, ����� RCA ������������ YPbPr: 1');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(52,17,'DVD-����� BBK DV117SI', 1090,
         '��������������� DVD �����: ��, �����. ����������� � ����.���������: ��, ������� Dolby Digital: ��, ������� DTS: ��, ������� ''�������'': ��, ����� �� ����. ������������: ���. �����');
		 
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(53,19,'������� ASUS X53U/K53U E-240', 13490,
         '��� ����������: E-240 1.5���, ����������� ������ (RAM): 2 ��, ������� ���� (HDD): 320 ��, ��������. ������������: Radeon HD6310, ������ ��������� ������: 15.6"(39.6 ��)');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(54,19,'������� Acer Aspire 5250-E303G50Mikk', 13990,
         '��� ����������: E-300 1.3���, ����������� ������ (RAM): 3 ��, ������� ���� (HDD): 500 ��, ��������. ������������: Radeon HD6310, ������ ��������� ������: 15.6"(39.6 ��)');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(55,19,'������� Samsung NP-RV515-S05RU', 16990,
         '��� ����������: E-450 1.65���, ����������� ������ (RAM): 4 ��, ������� ���� (HDD): 500 ��, ������ ����������: Radeon HD 6470M 512��, ������ ��������� ������: 15.6"(39.6 ��)');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(56,19,'������� HP Pavilion dv6-3110er XD571EA', 17990,
         '��� ����������: Phenom II X3 N830 2.1���, ����������� ������ (RAM): 4 ��, ������� ���� (HDD): 500 ��, ������ ����������: Radeon HD 5470 512��');		 

INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(57,21,'��������� ���� e-Machines ET1850 PT.NCBE8.026', 10190,
         '������������ �������: Windows 7 Starter, ��� ����������: Celeron E3400, �������� ������� ����������: 2.6 ���, ����������� ������ (RAM): 2 ��, ������� ���� (HDD): 320 ��');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(58,21,'��������� ���� Irbis E35e E3520/500', 10490,
         '������������ �������: Windows 7 Starter, ��� ����������: Celeron E3500, �������� ������� ����������: 2.7 ���, ����������� ������ (RAM): 2 ��, ������� ���� (HDD): 500 ��');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(59,21,'��������� ���� Packard Bell PB B2622RU', 11990,
         '������������ �������: Windows 7 Starter, ��� ����������: Celeron E3400, �������� ������� ����������: 2.6 ���, ����������� ������ (RAM): 2 ��, ������� ���� (HDD): 500 ��');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(60,21,'��������� ���� Packard Bell IMEDIA A5531', 12990,
         '������������ �������: Windows 7 HB 64 bit, ��� ����������: Athlon II X2 220, �������� ������� ����������: 2.8 ���, ����������� ������ (RAM): 3 ��, ������� ���� (HDD): 500 ��');

INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE) VALUES(61,23,'����������� ����� ASRock Z68 Pro3-M, Z68, S1155, mATX, RTL', 1990);
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE) VALUES(62,23,'����������� ����� Gigabyte GA-H61M-D2-B3, S1155, H61, mATX', 1910);
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE) VALUES(63,23,'����������� ����� Gigabyte GA-HA65M-D2H-B3, S1155, H61, mATX, RTL', 2860);
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE) VALUES(64,23,'����������� ����� MSI H61M-P23, S1155, H61, mATX, OEM', 1950);		 

INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE) VALUES(65,24,'��������� Intel Celeron G440 1,60GHz, 1Mb, 5000MHz, intGPU 650/1000MHz, S1155, OEM', 1450);
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE) VALUES(66,24,'��������� Intel Core i3 2100 3,1GHz, 3Mb, 5000MHz, intGPU upto1100MHz, S1155, OEM', 3880);
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE) VALUES(67,24,'��������� Intel Core i5 2310 2,9GHz, 6Mb, 5000Mhz, intGPU upto1100MHz, S1155, OEM', 5790);
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE) VALUES(68,24,'��������� Intel Core i7 2600� 3,4GHz, 8Mb, 5000MHz, intGPU upto1350MHz, S1155, OEM', 9990);	

INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE) VALUES(69,25,'����������� ������ DIMM 2048Mb PC3-10666(1333Mhz) Kingston (KVR1333D3N9/2G)', 450);
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE) VALUES(70,25,'����������� ������ DIMM 2048Mb PC3-10666(1333Mhz) Samsung original', 450);
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE) VALUES(71,25,'����������� ������ DIMM 4096Mb PC3-10666(1333Mhz) Kingston (KVR1333D3N9/4G)', 800);
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE) VALUES(72,25,'����������� ������ DIMM 4096Mb PC3-10666(1333Mhz) Samsung original', 800);

INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE) VALUES(73,26,'���������� 1Gb PCI-E GeForce GT 440, DDR3, 128bit, VGA, DVI, HDMI, Palit, OEM', 2200);
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE) VALUES(74,26,'���������� 1Gb PCI-E GeForce GTX 550Ti, DDR5, 192bit, VGA, DVI, HDMI, Palit, OEM', 4070);
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE) VALUES(75,26,'���������� 1Gb PCI-E ATI HD6750, DDR5, 128bit, VGA, DVI, HDMI, DP, Sapphire (11186-01-10G), OEM', 3450);
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE) VALUES(76,26,'���������� 1Gb PCI-E ATI HD6770, DDR5, 128bit, VGA, DVI, HDMI, Sapphire (11189-10-10G), OEM', 3780);

INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE) VALUES(77,27,'Ƹ����� ���� 1000,0 Gb HDD Western Digital (WD10EALX) Caviar Blue 7200rpm 32Mb SATAIII-600', 5390);
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE) VALUES(78,27,'������� ���� 2000,0 Gb HDD Seagate (ST2000DL003) Barracuda Green 5900rpm 64Mb SATAIII-600', 5400);
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE) VALUES(79,27,'������� ���� 2000,0 Gb HDD Western Digital (WD20EARX) GreenPower Caviar SE 64Mb SATAIII-600', 5860);
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE) VALUES(80,27,'������� ���� 500,0 Gb HDD Seagate (ST500DM002) Barracuda 7200.12 7200rpm 16Mb SATAIII-600', 3990);

INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE) VALUES(81,28,'���� ������� ATX Foxline 450W (FOC-ATX-450PRS)', 850);
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE) VALUES(82,28,'���� ������� ATX FSP 450W (450PNR)', 1250);
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE) VALUES(83,28,'���� ������� ATX FSP 500W (500PNR) OEM', 1500);
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE) VALUES(84,28,'���� ������� ATX Gigabyte LC 500W (GIC-24EPG-N50AC1-20R)', 1420);

INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE) VALUES(85,29,'������ Miditower ATX ASUS TA-892 450W Gray, Gray-white', 2150);
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE) VALUES(86,29,'������ Miditower ATX ASUS TA-8J2 450W', 1250);
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE) VALUES(87,29,'������ Miditower ATX CaseCom KS-7288 500W', 1890);
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE) VALUES(88,29,' ������ Miditower ATX CaseCom MA-1199 500W)', 1950);

INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(89,30,'������� 17" Acer V173DOB', 4290,
         '������ ��������� ������: 17"(43.2 ��), ����������: 1280x1024 ����, �������: 250 ��/��.�, ������������ �������������: 20000:1, ����. ���� ������ �� �����.: 160*');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(90,30,'������� 19" ViewSonic VA1938wa-LED', 3390,
         '������ ��������� ������: 18.5"(47 ��), ����������: 1366x768 ���� (HD Ready), �������: 250 ��/��.�, ������������ �������������: 10000000:1, ����. ���� ������ �� �����.: 170*');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(91,30,'������� 19" Samsung S19A300N', 3690,
         '������ ��������� ������: 18.5"(47 ��), ����������: 1366x768 ���� (HD Ready), �������: 250 ��/��.�, ������������ �������������: MEGA DCR, ����. ���� ������ �� �����.: 170*');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(92,30,'������� 19" LG E1941S-BN', 3890,
         '������ ��������� ������: 18.5"(47 ��), ����������: 1366x768 ���� (HD Ready), �������: 250 ��/��.�, ������������ �������������: 5000000:1, ����. ���� ������ �� �����.: 170*');

INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(93,31,'���� ������������ A4 Tech G7-630N-1 Grey', 550,
         '������������ ����: ��, ��� ����: ����������, ���������� ����������: 800, 1000, 1200, 1600, 2000 �/�, ������� ����������: �� 15 ������, ��������: ��, ������ �� �������: ��');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(94,31,'���� ������������ Microsoft Wireless 1000', 690,
         '������������ ����: ��, ��� ����: ����������, ���������� ����������: 1000 �/�, ��������: ��, ������ �� �������: �� 10 �������, ����� ��� ��������� �����: ��');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(95,31,'���� ������������ Logitech M235 Red', 750,
         '������������ ����: ��, ��� ����: ����������, ���������� ����������: 1000 �/�, ������� ����������: �� 10 ������, ��������: ��, ������ �� �������: �� 12 �������');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(96,31,'���� ������������ Microsoft WMM4000 U Graph', 1690,
         '������������ ����: ��, ��� ����: ���������� (BlueTrack), ���������� ����������: 1000 �/�, ��������: ��, ������ �� �������: �� 10 �������, ����� ��� ��������� �����: ��');

INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(97,32,'���������� ������������ Logitech K800', 3620,
         '������������ ����������: ��, ��� ������������ ����������: �����, ��������� ��������: 2.4 ���, ������ �� �������: ��, ��������� ��������� �����: USB 2.0');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(98,32,'���������� ��������� A4 Tech KR-85 Black', 279,
         '��������� ����������: ��, ��������� ����� � ��: USB 1.1, ������� ��������: ���������,qwerty,USB 1.1, ��������: 1 ���, ������: ���, ��� ��������: ����������� �����, ������: KR-85');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(99,32,'���������� ��������� Speed-Link BEDROCK', 359,
         '��������� ����������: ��, ���������� ������: 105, ������ ������ (QWERTY): 12 ��, ��������� ����� � ��: PS/2, ������� ��������: ���������,qwerty,105 ������,PS/2, ��������: 1 ���');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(100,32,'���������� ��������� Logitech Classic K100', 369,
         '��������� ����������: ��, �����. ������ Windows: ��, ��������� ����� � ��: PS/2, ������� ��������: ���������,qwerty,PS/2, ��������: 3 ����, ������: ���, ��� ��������: �� ����');		 
		
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(101,33,'���������� ��������� Acer A100 XE.H6REN.015', 11990,
         '������������ �������: Android 3.2, ��� ����������: Tegra 2 250 T20 1���, ���������� ������: 8 ��, ������ ��������� ������: 7"(17.8 ��), ���������� ������� ����������: 5 �����');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(102,33,'���������� ��������� Sony SGPT111RU/S 16Gb Black', 17990,
         '������������ �������: Android 3.1, ��� ����������: Tegra 2 250 1���, ���������� ������: 16 ��, ��� ����� ������: SD, SDHC, MMC, ������ ��������� ������: 9.4"(23.8 ��)');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(103,33,'���������� ��������� Samsung GT-P7500 16Gb White', 21490,
         '�������� GSM 2G / 3G: ��, ��� ����������: Tegra 2 250 T20 1���, ���� ������ (ROM): 16 ��, ������ ��������� ������: 10.1"(25.7 ��), ���������� �������: 1280 x 800 ����');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(104,33,'���������� ��������� Acer A501 XE.H7KEN.022', 23990,
         '������������ �������: Android 3.0, �������� GSM 2G / 3G: ��, �������� ������� ����������: 1 ���, ���� ������ (ROM): 64 ��, ������ ��������� ������: 10.1"(25.7 ��)');

INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(105,34,'�������� Lenovo C205', 17990,
         '������ ��������� ������: 18.5"(47 ��), ����������: 1366x768 ���� (HD Ready), ��� ����������: E-350 1.6���, ����������� ������ (RAM): 2 ��, ������� ���� (HDD): 320 ��');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(106,34,'�������� Acer Aspire Z3100 PWSETE1.018', 21990,
         '������ ��������� ������: 20"(50.8 ��), ����������: 1600�900 ���� (HD Ready), ��� ����������: Pentium E5800, �������� ������� ����������: 3.2 ���, ����������� ������ (RAM): 2 ��');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(107,34,'�������� HP 520-1001ru LN648EA', 31990,
         '������ ��������� ������: 23"(58.4 ��), ����������: 1920x1080 ���� (Full HD), ��������� �������: ��, ��� ����������: Core i3-2120, �������� ������� ����������: 3.3 ���');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(108,34,'�������� Sony VPC-J21S1R/B', 39990,
         '������ ��������� ������: 21.5"(54.6 ��), ����������: 1920x1080 ���� (Full HD), ��������� �������: ��, ��� ����������: Core i3-2310M 2.1���, ����������� ������ (RAM): 4 ��');
		 
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(109,36,'������� DECT teXet ��-D4700� Black', 690,
         '�������. ��������. ������: �� 5, �������� ������: �� 20 �������, ������������ ������ (���): ��, ����� � ������ ���������: �� 10 �����');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(110,36,'������� DECT Philips SE1501B/51', 790,
         '��������� ������� ������: ��, �������. ��������. ������: �� 4, �������� ������: �� 50 �������, ����� � ������ ���������: �� 12 �����, ������� ��������: 1880-1900 ��� DECT,������');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(111,36,'������� DECT Motorola C601E RU', 890,
         '�������. ��������. ������: �� 5, �������� ������: �� 50 �������, ������������ ������ (���): ��, ����� � ������ ���������: �� 12 �����');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(112,36,'������� DECT Panasonic KX-TG1711RUB', 1100,
         '��������� ������� ������: ��, �������� ������: �� 50 �������, ������� Caller ID: ��, ������������ ������ (���): ��, ����� � ������ ���������: �� 15 �����');
		 
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(113,37,'��������� ������� LG T500 Black', 2990,
         '�������� GSM 2G: ��, ���������� �������: 240 x 320 ����, FM ��������: ����������, ���������� ������� ����������: 2 �����, ���������� ������: 60 ��, ��� ����� ������: microSD');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(114,37,'��������� ������� Philips X518 White', 3690,
         '�������� GSM 2G: ��, ���������� �������: 240 x 320 ����, SIM �����: 2, ���������� ������� ����������: 2 �����, ��� ����� ������: microSD, microSDHC');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(115,37,'��������� ������� Nokia X2-00 2Gb Red', 4290,
         '�������� GSM 2G: ��, ���������� ������ �������: 262000, ���������� �������: 320 x 240 ����, ���������� ������� ����������: 5 �����, ���������� ������: 48 ��');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(116,37,'��������� ������� Philips X513', 4350,
         '�������� GSM 2G: ��, ���������� ������ �������: 262144, ���������� �������: 240 x 320 ����, SIM �����: 2, ���������� ������� ����������: 2 �����');		 
		 		 
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(117,39,'����������� �������� ���������� Canon PowerShot A1200 Black', 2690,
         '������. ���������� �������: 12.1 �����, ���������� ����������: 4x, ��������� �������: 2.7 ", ��� ����� ������: SD, SDHC, SDXC, MMC, MMC Plus, HCMMC Plus');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(118,39,'����������� �������� ���������� Samsung ES80 Black', 2690,
         '������. ���������� �������: 12.2 �����, ���������� ����������: 5x, ��������� �������: 2.4 ", ��� ����� ������: SD, SDHC, ����. ������. ��������.: 640x480 ����');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(119,39,'����������� �������� ���������� Samsung ES80 Red', 2690,
         '������. ���������� �������: 12.2 �����, ���������� ����������: 5x, ��������� �������: 2.4 ", ��� ����� ������: SD, SDHC, ����. ������. ��������.: 640x480 ����');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(120,39,'����������� �������� ���������� Olympus VG-110 Black', 2990,
         '������. ���������� �������: 12 �����, ���������� ����������: 4x, ��������� �������: 2.7 ", ��� ����� ������: SD, SDHC, ����. ������. ��������.: 640x480 ����, ���� �����������: ����');
		 
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(121,40,'�������� ����������� SAMSUNG HMX-H304BP', 15599,
         '�������: ���������� - 1, ������ - 1/4.9 �����, ��� - CMOS, ������������ ���������� (�����) - 1920x1080 ����, ����� ���������� - 5 �����; ������� ������: ���������� Flash-������ - 16 ��, ����� ������ - SD, SDHC; Zoom: ����. ���������� - 30x, ����. �������� - 300x; ���� - ������');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(122,40,'�������� ����������� SONY HDR-CX130E Black', 18399,
         '�������: ���������� - 1, ������ - 1/4 �����, ��� - CMOS, ������������ ���������� (�����) - 1920x1080 ����, ����� ���������� - 4.2 �����; ������� ������: ����� ������ - MS, SD, SDHC; Zoom: ����. ���������� - 25x, ����. �������� - 300x; ���� - ������');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(123,40,'�������� ����������� SAMSUNG SMX-F50BP', 5999,
         '�������: ���������� - 1, ������ - 1/6 �����, ��� - CCD, ������������ ���������� (�����) - 720x480 ����; ������� ������: ����� ������ - SD, SDHC; Zoom: ����. ���������� - 52x/65x, ����. �������� - 2200x; ���� - ������');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(124,40,'�������� ����������� CANON LEGRIA M307', 17999,
         '�������: ���������� - 1, ������ - 1/4 �����, ��� - CMOS, ������������ ���������� (�����) - 3.89 ����, ����� ���������� - 3.89 �����; ������� ������: ����� ������ - SD, SDHC; Zoom: ����. ���������� - 15x, ����. �������� - 300x; ���� - ������');		 
		 		 
		COMMIT;
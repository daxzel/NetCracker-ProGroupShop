INSERT INTO "CATALOG" (ID_CATALOG,"NAME")   VALUES(1,'Техника');

       INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME")   VALUES(2,1,'Бытовая техника');
       
              INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME")   VALUES(3,2,'Холодильники');
              INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME")   VALUES(4,2,'Стиральные машины');
              INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME")   VALUES(5,2,'Моечные машины');
              INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME")   VALUES(6,2,'Микроволновые печи');
              
       INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME")   VALUES(7,1,'Электроника');
       
              INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME") VALUES(8,7,'Телевизоры');
              INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME") VALUES(9,7,'Компьютеры');
              INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME") VALUES(10,7,'Телефоны');
              INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME") VALUES(11,7,'Магнитафоны');
       
 INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(0,3,'LG GW-B207 FVQA',33090,
         'Цвет:	 бежевый
        Высота, см:	 175
        Ширина, см:	 89
        Глубина, см:	 72,5
        Общий объем, л:	 511
        Объем холодильной камеры, л:	 346
        Объем морозильной камеры, л:	 165
        Тип управления:	 электронное
        Размораживание холодильной камеры:	 No Frost
        Размораживание морозильной камеры:	 No Frost
        Количество компрессоров:	 1
        Класс энергопотребления:	 A
        Гарантия:	 3 года');
  INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(1,3,'Siemens KA 58NA45RU',62820,
        'Цвет:	 нержавеющая сталь
        Высота, см:	 180
        Ширина, см:	 90
        Глубина, см:	 67.5
        Общий объем, л:	 531
        Объем холодильной камеры, л:	 356
        Объем морозильной камеры, л:	 175
        Тип управления:	 электронное
        Размораживание холодильной камеры:	 No Frost
        Размораживание морозильной камеры:	 No Frost
        Количество компрессоров:	 1
        Класс энергопотребления:	 А+');
    
       
COMMIT;
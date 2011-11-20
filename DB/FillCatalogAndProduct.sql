INSERT INTO "CATALOG" (ID_CATALOG,"NAME")   VALUES(1,'Техника');

       INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME")   VALUES(2,1,'Бытовая техника');
       
              INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME")   VALUES(3,2,'Холодильники');
              INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME")   VALUES(4,2,'Стиральные машины');
              INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME")   VALUES(5,2,'Пылесосы');
              INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME")   VALUES(6,2,'Микроволновые печи');
              
       INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME")   VALUES(7,1,'Телевизоры, аудио, видео');
       
              INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME") VALUES(8,7,'Телевизоры');
			  
			         INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME") VALUES(9,8,'ЖК телевизоры');
					 INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME") VALUES(10,8,'Плазменные телевизоры');
					 INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME") VALUES(11,8,'LED телевизоры');
					 INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME") VALUES(12,8,'3D-телевизоры');
					 
			  INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME") VALUES(13,7,'MP3-плееры');
			  INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME") VALUES(14,7,'Электронные книги');
			  INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME") VALUES(15,7,'Домашние кинотеатры');
			  INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME") VALUES(16,7,'Музыкальные центры');
			  INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME") VALUES(17,7,'DVD-плееры');
					
       INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME") VALUES(18,1,'Компьютеры и ноутбуки');
	   
		      INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME") VALUES(19,18,'Ноутбуки');
			  INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME") VALUES(20,18,'Настольные компьютеры');
					        
				     INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME") VALUES(21,20,'Системный блок');  
                     INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME") VALUES(22,20,'Комплектующие к ПК');
							
							INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME") VALUES(23,22,'Материнские платы');
							INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME") VALUES(24,22,'Процессоры');
							INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME") VALUES(25,22,'Модули памяти');
							INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME") VALUES(26,22,'Видеокарты');
							INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME") VALUES(27,22,'Жесткие диски');
							INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME") VALUES(28,22,'Блок питания');
							INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME") VALUES(29,22,'Корпуса');
							INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME") VALUES(30,22,'Мониторы');
							INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME") VALUES(31,22,'Компьютерные мыши');
							INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME") VALUES(32,22,'Клавиатура');
      	  
				INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME") VALUES(33,18,'Планшетные компьютеры');
				INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME") VALUES(34,18,'Моноблоки');
					 
	   INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME") VALUES(35,1,'Телефоны');
	   
	          INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME") VALUES(36,35,'Домашние телефоны');
			  INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME") VALUES(37,35,'Сотовые телефоны');
	   
	   INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME") VALUES(38,1,'Фото и видео');
	   
	          INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME") VALUES(39,38,'Цифровые фотоаппараты');
			  INSERT INTO "CATALOG" (ID_CATALOG,ID_PARENT,"NAME") VALUES(40,38,'Цифровые видеокамеры');
			         
                    
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(1,3,'Холодильник однодверный Liebherr KX 1021-21', 6990,
         'Габаритные размеры (В*Ш*Г): 63*55*62 см, Объем холодильной камеры: 86 л, Объем морозильной камеры: 6 л, Класс энергопотребления: A, Материал полок на двери: пластик');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(2,3,'Холодильник однодверный Nord ДХ-403-010', 7990,
         'Габаритные размеры (В*Ш*Г): 85*50*52 см, Объем холодильной камеры: 100 л, Объем морозильной камеры: 10 л, Класс энергопотребления: A');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(3,3,'Холодильник однодверный Indesit TT-85.001-WT', 8490,
         'Габаритные размеры (В*Ш*Г): 85*60*62 см, Объем холодильной камеры: 105 л, Объем морозильной камеры: 14 л, Класс энергопотребления: B');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(4,3,'Холодильник однодверный Indesit TT-85.005-T', 9290,
         'Габаритные размеры (В*Ш*Г): 85*60*62 см, Объем холодильной камеры: 105 л, Объем морозильной камеры: 14 л, Класс энергопотребления: B');

INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(5,4,'Стиральная машина INDESIT WIUN 103', 10499,
         'Общие: Класс стирки - A, Тип управления - Электронно-механический; Загрузка: Максимальная загрузка - 3.5 кг; Отжим: Макс. скорость отжима - 1000 Об/мин; Габариты: Глубина - 33 см, Ширина - 59.5 см; Цвет - белый');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(6,4,'Стиральная машина SAMSUNG WF8500NHW', 9999,
         'Общие: Класс стирки - A, Тип управления - Электронный; Загрузка: Максимальная загрузка - 5 кг; Отжим: Макс. скорость отжима - 1000 Об/мин; Индикация: Дисплей - Есть; Габариты: Глубина - 45 см, Ширина - 60 см; Цвет - белый');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(7,4,'Стиральная машина BOSCH WLX 24463 OE', 20999,
         'Общие: Класс стирки - A, Тип управления - Электронно-механический; Загрузка: Максимальная загрузка - 5 кг; Отжим: Макс. скорость отжима - 1200 Об/мин; Индикация: Дисплей - Есть; Габариты: Глубина - 40 см, Ширина - 60 см; Цвет - белый');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(8,4,'Стиральная машина SAMSUNG WFE509NZW', 11999,
         'Общие: Класс стирки - A, Тип управления - Электронный; Загрузка: Максимальная загрузка - 5 кг; Отжим: Макс. скорость отжима - 900 Об/мин; Индикация: Дисплей - Есть; Габариты: Глубина - 45 см, Ширина - 60 см; Цвет - белый');
		 
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(9,5,'Пылесос SAMSUNG VCC47S5H35', 4499,
         'Сбор пыли: Система сбора пыли - Безмешковая; Фильтры: Фильтр тонкой очистки - HEPA11; Управление: Тип - Электронный; Мощность: Макс. потребляемая - 1800 Вт; Габариты: Вес - 4.6 кг');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(10,5,'Пылесос SAMSUNG SC6570', 4199,
         'Сбор пыли: Система сбора пыли - Безмешковая; Фильтры: Фильтр тонкой очистки - HEPA11; Управление: Тип - Электронный; Мощность: Макс. потребляемая - 1800 Вт; Габариты: Вес - 5.2 кг');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(11,5,'Пылесос SAMSUNG SC8853', 20999,
         'Сбор пыли: Система сбора пыли - Безмешковая; Фильтры: Фильтр тонкой очистки - HEPA12; Управление: Тип - Электронный; Мощность: Макс. потребляемая - 2200 Вт; Габариты: Вес - 5.7 кг');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(12,5,'Пылесос DYSON DC32 EXCLUSIVE', 23999,
         'Сбор пыли: Система сбора пыли - Безмешковая; Фильтры: Фильтр тонкой очистки - HEPA; Управление: Тип - Механический; Мощность: Макс. потребляемая - 1400 Вт; Габариты: Вес - 8.7 кг');
		 
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(13,6,'Микроволновая Печь LG MS-1947W', 2799,
         'Общие: Тип управления - Сенсорный; Камера: Объем - 19 л; Мощность: Мощность микроволн - 700 Вт; Цвет - белый');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(14,6,'Микроволновая печь SUPRA MWS–1720', 1199,
         'Общие: Тип управления - Механический; Камера: Объем - 17 л; Мощность: Мощность микроволн - 700 Вт; Цвет - белый');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(15,6,'Микроволновая печь DAEWOO KOC-8H6T', 5999,
         'Общие: Конвекция - Есть, Гриль - Есть, Тип управления - Электронный; Камера: Объем - 24 л; Мощность: Мощность микроволн - 900 Вт; Цвет - черный, серебристый');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(16,6,'Микроволновая печь GORENJE MO20DW II', 3199,
         'Общие: Тип управления - Электронный; Камера: Объем - 20 л; Мощность: Мощность микроволн - 800 Вт; Цвет - белый');
		 
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(17,9,'ЖК-телевизор 19" Philips 19PFL3606H/60', 7990,
         'Высота: 29 см, Ширина: 46 см, Глубина: 5.7 см, Диагональ: 19"(48.2 см), Разрешение экрана: 1366x768 Пикс (HD Ready), Цифровое шумоподавление: Да, Цифровые ТВ тюнеры DVB-T/DVB-C: Да');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(18,9,'ЖК-телевизор 19" Samsung LE-19 D451G3W', 8990,
         'Высота: 32 см, Ширина: 48 см, Глубина: 6.3 см, Диагональ: 19"(48.2 см), Разрешение экрана: 1366x768 Пикс (HD Ready), Цифровое шумоподавление: Да, Цифровые ТВ тюнеры DVB-T/DVB-C: Да');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(19,9,'ЖК-телевизор 19" LG 19 LD320', 8990,
         'Высота: 35 см, Ширина: 46 см, Глубина: 17 см, Диагональ: 19"(48.2 см), Разрешение экрана: 1366x768 Пикс (HD Ready), Цифровой ТВ тюнер DVB-T: Да');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(20,9,'ЖК-телевизор 19"/DVD combo Sharp LC-19 DV200 RU', 11990,
         'Высота: 36 см, Ширина: 47 см, Глубина: 20 см, Диагональ: 19"(48.2 см), Разрешение экрана: 1366x768 Пикс (HD Ready), Встроенный DVD плеер: Да, Цифровой ТВ тюнер DVB-T: Да');

INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(21,10,'Плазменный телевизор 42"-49" LG 42 PT351', 17990,
         'Разрешение экрана: 1024x768 Пикс, Страна: Россия, Размер диагонали экрана: 42"(106.6 см), Технология 600 Гц: Да, Цифровое шумоподавление: Да, Цифровые ТВ тюнеры DVB-T/DVB-C: Да');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(22,10,'Плазменный телевизор 42"-49" Panasonic TX-PR42 C3', 18990,
         'Разрешение экрана: 1024x768 Пикс, Страна: Чехия, Размер диагонали экрана: 42"(106.6 см), Технология 600 Гц: Да, Цифровое шумоподавление: Да, Цифровые ТВ тюнеры DVB-T/DVB-C: Да');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(23,10,'Плазменный телевизор 50"-51" Panasonic TX-PR50 C3', 32990,
         'Разрешение экрана: 1024x768 Пикс, Страна: Чехия, Размер диагонали экрана: 50"(126.9 см), Технология 600 Гц: Да, Цифровое шумоподавление: Да, Цифровые ТВ тюнеры DVB-T/DVB-C: Да');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(24,10,'Плазменный телевизор Samsung PS-43 D452 A5W', 18990,
         'Разрешение экрана: 1024x768 Пикс, Страна: Россия, Размер диагонали экрана: 43"(109.2 см), Технология 600 Гц: Да, Звук: SRS TheaterSound, Плеер для воспр. медиафайлов: доп.опция');		 
		 
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(25,11,'LED-телевизор 19"-20" Vestel V19-LE990 HD', 6590,
         'Размер диагонали экрана: 19"(48.2 см), Разрешение экрана: 1366x768 Пикс (HD Ready), Цифровое шумоподавление: Да, Цифровой ТВ тюнер DVB-T: Да, Звук: NICAM стерео');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(26,11,'LED-телевизор 19"-20" Akai LEA19H03P', 6990,
         'Размер диагонали экрана: 19"(48.2 см), Разрешение экрана: 1366x768 Пикс (HD Ready), Цифровое шумоподавление: Да, Звук: NICAM стерео, Плеер для воспр. медиафайлов: доп.опция');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(27,11,'LED-телевизор 19"-20" Acer AT1926D', 6990,
         'Размер диагонали экрана: 19"(48.2 см), Разрешение экрана: 1366x768 Пикс (HD Ready), Цифровое шумоподавление: Да, Цифровой ТВ тюнер DVB-T: Да');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(28,11,'LED-телевизор 19"-20" Toshiba 19EL833R', 8990,
         'Размер диагонали экрана: 19"(48.2 см), Разрешение экрана: 1366x768 Пикс (HD Ready), Цифровое шумоподавление: Да, Цифровые ТВ тюнеры DVB-T/DVB-C: Да, Звук: стерео');	

INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(29,12,'3D LED Телевизор 32"-37" LG 32 LW4500', 22490,
         'Размер диагонали экрана: 32"(81.2 см), Разрешение экрана: 1920x1080 Пикс (FullHD), Технология воспроизведения 3D: Да, Технология 100 Гц: Да, Цифровые ТВ тюнеры DVB-T/DVB-C: Да');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(30,12,'3D LED Телевизор 32"-37" Samsung UE-32 D6100SW', 23990,
         'Размер диагонали экрана: 32"(81.2 см), Разрешение экрана: 1920x1080 Пикс (FullHD), Технология воспроизведения 3D: Да, Технология 200 Гц: Да, Цифровые ТВ тюнеры DVB-T/DVB-C: Да');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(31,12,'3D LED Телевизор 32"-37" Sony KDL-32EX720', 27990,
         'Размер диагонали экрана: 32"(81.2 см), Разрешение экрана: 1920x1080 Пикс (FullHD), Технология воспроизведения 3D: Да, Технология 100 Гц: Да, Цифровые ТВ тюнеры DVB-T/DVB-C: Да');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(32,12,'3D LED Телевизор 32"-37" Samsung UE-32 D6530WS', 28990,
         'Размер диагонали экрана: 32"(81.2 см), Разрешение экрана: 1920x1080 Пикс (FullHD), Технология воспроизведения 3D: Да, Технология 200 Гц: Да, Цифровые ТВ тюнеры DVB-T/DVB-C: Да');		 
		 
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(33,13,'Портативный медиаплеер iRiver T8 4Gb Red', 590,
         'Встроенная память: 4 ГБ, Диагональ дисплея: 1 ", Встроенный диктофон: Да, Цифровой тюнер: FM, FM трансмиттер: доп.опция, Работа от аккумулятора: до 17 часов');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(34,13,'Портативный медиаплеер Explay X1 4Gb White/blue', 590,
         'Воспроизведение MP3: Да, Воспроизведение WMA: Да, Воспроизведение WAV: Да, Встроенная память: 4 ГБ, Работа от аккумулятора: до 13 ч 30 мин, Зарядка от USB порта: Да');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(35,13,'Портативный медиаплеер Ritmix RF-3310 2Gb', 690,
         'Встроенная память: 2 ГБ, Тип карты памяти: microSD, microSDHC, Встроенный диктофон: Да, Цифровой тюнер: FM, Работа от аккумулятора: до 15 часов, Наушники высокого класса: доп.опция');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(36,13,'Портативный медиаплеер Explay L70 2Gb', 990,
         'Воспроизведение видеофайлов: Да, Встроенная память: 4 ГБ, Диагональ дисплея: 2.4 ", Встроенный диктофон: Да, Цифровой тюнер: FM, Работа от аккумулятора: до 18 часов'); 
		 
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(37,14,'Электронная книга teXet TB-431HD 4Gb Grey', 1990,
         'Формат текстовых файлов: FB2,PDF,EPUB,TXT,HTML,RTF,MOBIPOCKET,PDB, Диагональ дисплея: 4.3 ", Тех. изготовления дисплея: LCD-TFT, Встроенная память: 4 ГБ');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(38,14,'Электронная книга MyOne RDR-01', 2790,
         'Формат текстовых файлов: EPUB,FB2,HTML,PDF,TXT,RTF,DOC, Диагональ дисплея: 7 ", Тех. изготовления дисплея: LCD-TFT, Сенсорный дисплей: Да, Встроенная память: 2 ГБ');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(39,14,'Электронная книга teXet TB-730HD', 2990,
         'Формат текстовых файлов: FB2,PDF,EPUB,TXT,HTML,RTF,MOBIPOCKET,PDB, Диагональ дисплея: 7 ", Тех. изготовления дисплея: LCD-TFT, Встроенная память: 4 ГБ');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(40,14,'Электронная книга Wexler T7002B Black', 3790,
         'Формат текстовых файлов: EPUB,FB2,TXT,PDF,HTML, Диагональ дисплея: 7 ", Тех. изготовления дисплея: LCD-TFT, Встроенная память: 4 ГБ, Тип карты памяти: microSD, microSDHC'); 

INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(41,15,'Домашний кинотеатр PANASONIC SC-PT85EE-K', 7299,
         'Общие: Тип акустической системы - 5.1; Поддерживаемые форматы: Видео - VCD, SVCD, MPEG4; Звук: Суммарная мощность - 1000 Вт; Интерфейсы: HDMI - 4; Цвет - черный');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(42,15,'Домашний кинотеатр PHILIPS HTS5120', 11499,
         'Общие: Тип акустической системы - 2.1; Поддерживаемые форматы: Видео - AVI, DivX, VCD, SVCD, WMV; Звук: Суммарная мощность - 400 Вт; Интерфейсы: HDMI - 1; Цвет - черный');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(43,15,'Домашний кинотеатр SAMSUNG HT-BD8200', 18999,
         'Общие: Тип акустической системы - 2.1; Поддерживаемые форматы: Видео - DivX, VCD, SVCD, MPEG4; Звук: Суммарная мощность - 300 Вт; Интерфейсы: HDMI - 1; Цвет - черный');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(44,15,'Саундбар PANASONIC SC-BFT800EE-K', 29999,
         'Общие: Тип акустической системы - 5.1; Поддерживаемые форматы: Видео - AVI, DivX, MPEG4; Звук: Суммарная мощность - 300 Вт; Интерфейсы: HDMI - 1; Цвет - черный');

INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(45,16,'Музыкальный центр Micro с DVD Hyundai H-MS1113 Black', 3290,
         'Воспроизведение DVD видео: Да, Цифровой тюнер: FM/AМ, Функция ''Караоке'': Да, Мощность фронтальных АС: 2 x 15 Вт, DVD (+/-R/RW), CD(R/RW), MP3, WMA');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(46,16,'Музыкальный центр Micro с DVD LG XB16', 3790,
         'Воспроизведение DVD видео: Да, Воспр. медиафайлов с цифр.носителей: Да, Цифровой тюнер: FM, Декодер Dolby Digital: Да, Запись с CD на USB устройство: Да');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(47,16,'Музыкальный центр Micro с DVD Philips MC-D107/51', 3990,
         'Воспроизведение DVD видео: Да, Воспр. медиафайлов с цифр.носителей: Да, Цифровой тюнер: FM, Мощность фронтальных АС: 2 x 5 Вт, Краткое описание: DVD,2 x 5 Вт, Гарантия: 1 год');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(48,16,'Музыкальный центр Micro с DVD LG XB66', 3790,
         'Формат текстовых файлов: EPUB,FB2,TXT,PDF,HTML, Диагональ дисплея: 7 ", Тех. изготовления дисплея: LCD-TFT, Встроенная память: 4 ГБ, Тип карты памяти: microSD, microSDHC'); 	
		 
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(49,17,'DVD-плеер Soundmax SM-DVD5113 Black', 790,
         'Воспроизведение DVD видео: Да, Цифровой тюнер: FM/AМ, Функция ''Караоке'': Да, Мощность фронтальных АС: 2 x 15 Вт, DVD (+/-R/RW), CD(R/RW), MP3, WMA');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(50,17,'DVD-плеер Supra DVS-102X', 890,
         'Краткое описание: DVD,MPEG4,черный, Гарантия: 1 год, Страна: КНР, Вид гарантии: гарантийный талон, Загрузка дисков: 1, Тип загрузки дисков: выдвижной лоток');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(51,17,'DVD-плеер Philips DVP 1033/51', 990,
         'Воспроизведение DVD видео: Да, Декодер Dolby Digital: Да, Дистанционное управление: полное, Выход RCA видео композитный: 1, Выход RCA компонентный YPbPr: 1');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(52,17,'DVD-плеер BBK DV117SI', 1090,
         'Воспроизведение DVD видео: Да, Воспр. медиафайлов с цифр.носителей: Да, Декодер Dolby Digital: Да, Декодер DTS: Да, Функция ''Караоке'': Да, Пульт ДУ друг. устройствами: доп. опция');
		 
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(53,19,'Ноутбук ASUS X53U/K53U E-240', 13490,
         'Тип процессора: E-240 1.5ГГц, Оперативная память (RAM): 2 ГБ, Жесткий диск (HDD): 320 ГБ, Интегрир. видеосистема: Radeon HD6310, Размер диагонали экрана: 15.6"(39.6 см)');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(54,19,'Ноутбук Acer Aspire 5250-E303G50Mikk', 13990,
         'Тип процессора: E-300 1.3ГГц, Оперативная память (RAM): 3 ГБ, Жесткий диск (HDD): 500 ГБ, Интегрир. видеосистема: Radeon HD6310, Размер диагонали экрана: 15.6"(39.6 см)');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(55,19,'Ноутбук Samsung NP-RV515-S05RU', 16990,
         'Тип процессора: E-450 1.65ГГц, Оперативная память (RAM): 4 ГБ, Жесткий диск (HDD): 500 ГБ, Модель видеокарты: Radeon HD 6470M 512МБ, Размер диагонали экрана: 15.6"(39.6 см)');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(56,19,'Ноутбук HP Pavilion dv6-3110er XD571EA', 17990,
         'Тип процессора: Phenom II X3 N830 2.1ГГц, Оперативная память (RAM): 4 ГБ, Жесткий диск (HDD): 500 ГБ, Модель видеокарты: Radeon HD 5470 512МБ');		 

INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(57,21,'Системный блок e-Machines ET1850 PT.NCBE8.026', 10190,
         'Операционная система: Windows 7 Starter, Тип процессора: Celeron E3400, Тактовая частота процессора: 2.6 ГГц, Оперативная память (RAM): 2 ГБ, Жесткий диск (HDD): 320 ГБ');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(58,21,'Системный блок Irbis E35e E3520/500', 10490,
         'Операционная система: Windows 7 Starter, Тип процессора: Celeron E3500, Тактовая частота процессора: 2.7 ГГц, Оперативная память (RAM): 2 ГБ, Жесткий диск (HDD): 500 ГБ');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(59,21,'Системный блок Packard Bell PB B2622RU', 11990,
         'Операционная система: Windows 7 Starter, Тип процессора: Celeron E3400, Тактовая частота процессора: 2.6 ГГц, Оперативная память (RAM): 2 ГБ, Жесткий диск (HDD): 500 ГБ');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(60,21,'Системный блок Packard Bell IMEDIA A5531', 12990,
         'Операционная система: Windows 7 HB 64 bit, Тип процессора: Athlon II X2 220, Тактовая частота процессора: 2.8 ГГц, Оперативная память (RAM): 3 ГБ, Жесткий диск (HDD): 500 ГБ');

INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE) VALUES(61,23,'Материнская плата ASRock Z68 Pro3-M, Z68, S1155, mATX, RTL', 1990);
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE) VALUES(62,23,'Материнская плата Gigabyte GA-H61M-D2-B3, S1155, H61, mATX', 1910);
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE) VALUES(63,23,'Материнская плата Gigabyte GA-HA65M-D2H-B3, S1155, H61, mATX, RTL', 2860);
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE) VALUES(64,23,'Материнская плата MSI H61M-P23, S1155, H61, mATX, OEM', 1950);		 

INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE) VALUES(65,24,'Процессор Intel Celeron G440 1,60GHz, 1Mb, 5000MHz, intGPU 650/1000MHz, S1155, OEM', 1450);
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE) VALUES(66,24,'Процессор Intel Core i3 2100 3,1GHz, 3Mb, 5000MHz, intGPU upto1100MHz, S1155, OEM', 3880);
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE) VALUES(67,24,'Процессор Intel Core i5 2310 2,9GHz, 6Mb, 5000Mhz, intGPU upto1100MHz, S1155, OEM', 5790);
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE) VALUES(68,24,'Процессор Intel Core i7 2600К 3,4GHz, 8Mb, 5000MHz, intGPU upto1350MHz, S1155, OEM', 9990);	

INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE) VALUES(69,25,'Оперативная память DIMM 2048Mb PC3-10666(1333Mhz) Kingston (KVR1333D3N9/2G)', 450);
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE) VALUES(70,25,'Оперативная память DIMM 2048Mb PC3-10666(1333Mhz) Samsung original', 450);
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE) VALUES(71,25,'Оперативная память DIMM 4096Mb PC3-10666(1333Mhz) Kingston (KVR1333D3N9/4G)', 800);
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE) VALUES(72,25,'Оперативная память DIMM 4096Mb PC3-10666(1333Mhz) Samsung original', 800);

INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE) VALUES(73,26,'Видеокарта 1Gb PCI-E GeForce GT 440, DDR3, 128bit, VGA, DVI, HDMI, Palit, OEM', 2200);
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE) VALUES(74,26,'Видеокарта 1Gb PCI-E GeForce GTX 550Ti, DDR5, 192bit, VGA, DVI, HDMI, Palit, OEM', 4070);
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE) VALUES(75,26,'Видеокарта 1Gb PCI-E ATI HD6750, DDR5, 128bit, VGA, DVI, HDMI, DP, Sapphire (11186-01-10G), OEM', 3450);
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE) VALUES(76,26,'Видеокарта 1Gb PCI-E ATI HD6770, DDR5, 128bit, VGA, DVI, HDMI, Sapphire (11189-10-10G), OEM', 3780);

INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE) VALUES(77,27,'Жёсткий диск 1000,0 Gb HDD Western Digital (WD10EALX) Caviar Blue 7200rpm 32Mb SATAIII-600', 5390);
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE) VALUES(78,27,'Жесткий диск 2000,0 Gb HDD Seagate (ST2000DL003) Barracuda Green 5900rpm 64Mb SATAIII-600', 5400);
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE) VALUES(79,27,'Жесткий диск 2000,0 Gb HDD Western Digital (WD20EARX) GreenPower Caviar SE 64Mb SATAIII-600', 5860);
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE) VALUES(80,27,'Жесткий диск 500,0 Gb HDD Seagate (ST500DM002) Barracuda 7200.12 7200rpm 16Mb SATAIII-600', 3990);

INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE) VALUES(81,28,'Блок питания ATX Foxline 450W (FOC-ATX-450PRS)', 850);
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE) VALUES(82,28,'Блок питания ATX FSP 450W (450PNR)', 1250);
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE) VALUES(83,28,'Блок питания ATX FSP 500W (500PNR) OEM', 1500);
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE) VALUES(84,28,'Блок питания ATX Gigabyte LC 500W (GIC-24EPG-N50AC1-20R)', 1420);

INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE) VALUES(85,29,'Корпус Miditower ATX ASUS TA-892 450W Gray, Gray-white', 2150);
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE) VALUES(86,29,'Корпус Miditower ATX ASUS TA-8J2 450W', 1250);
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE) VALUES(87,29,'Корпус Miditower ATX CaseCom KS-7288 500W', 1890);
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE) VALUES(88,29,' Корпус Miditower ATX CaseCom MA-1199 500W)', 1950);

INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(89,30,'Монитор 17" Acer V173DOB', 4290,
         'Размер диагонали экрана: 17"(43.2 см), Разрешение: 1280x1024 Пикс, Яркость: 250 кд/кв.м, Динамическая контрастность: 20000:1, Макс. угол обзора по гориз.: 160*');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(90,30,'Монитор 19" ViewSonic VA1938wa-LED', 3390,
         'Размер диагонали экрана: 18.5"(47 см), Разрешение: 1366x768 Пикс (HD Ready), Яркость: 250 кд/кв.м, Динамическая контрастность: 10000000:1, Макс. угол обзора по гориз.: 170*');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(91,30,'Монитор 19" Samsung S19A300N', 3690,
         'Размер диагонали экрана: 18.5"(47 см), Разрешение: 1366x768 Пикс (HD Ready), Яркость: 250 кд/кв.м, Динамическая контрастность: MEGA DCR, Макс. угол обзора по гориз.: 170*');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(92,30,'Монитор 19" LG E1941S-BN', 3890,
         'Размер диагонали экрана: 18.5"(47 см), Разрешение: 1366x768 Пикс (HD Ready), Яркость: 250 кд/кв.м, Динамическая контрастность: 5000000:1, Макс. угол обзора по гориз.: 170*');

INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(93,31,'Мышь беспроводная A4 Tech G7-630N-1 Grey', 550,
         'Беспроводная мышь: Да, Тип мыши: оптическая, Оптическое разрешение: 800, 1000, 1200, 1600, 2000 т/д, Рабочее расстояние: до 15 метров, Скроллер: Да, Работа от батареи: Да');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(94,31,'Мышь беспроводная Microsoft Wireless 1000', 690,
         'Беспроводная мышь: Да, Тип мыши: оптическая, Оптическое разрешение: 1000 т/д, Скроллер: Да, Работа от батареи: до 10 месяцев, Отсек для приемного блока: Да');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(95,31,'Мышь беспроводная Logitech M235 Red', 750,
         'Беспроводная мышь: Да, Тип мыши: оптическая, Оптическое разрешение: 1000 т/д, Рабочее расстояние: до 10 метров, Скроллер: Да, Работа от батареи: до 12 месяцев');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(96,31,'Мышь беспроводная Microsoft WMM4000 U Graph', 1690,
         'Беспроводная мышь: Да, Тип мыши: оптическая (BlueTrack), Оптическое разрешение: 1000 т/д, Скроллер: Да, Работа от батареи: до 10 месяцев, Отсек для приемного блока: Да');

INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(97,32,'Клавиатура беспроводная Logitech K800', 3620,
         'Беспроводная клавиатура: Да, Тип беспроводной клавиатуры: радио, Частотный диапазон: 2.4 ГГц, Работа от батареи: Да, Интерфейс приемного блока: USB 2.0');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(98,32,'Клавиатура проводная A4 Tech KR-85 Black', 279,
         'Проводная клавиатура: Да, Интерфейс связи с ПК: USB 1.1, Краткое описание: проводная,qwerty,USB 1.1, Гарантия: 1 год, Страна: КНР, Вид гарантии: гарантийный талон, Модель: KR-85');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(99,32,'Клавиатура проводная Speed-Link BEDROCK', 359,
         'Проводная клавиатура: Да, Количество клавиш: 105, Размер клавиш (QWERTY): 12 мм, Интерфейс связи с ПК: PS/2, Краткое описание: Проводная,qwerty,105 клавиш,PS/2, Гарантия: 1 год');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(100,32,'Клавиатура проводная Logitech Classic K100', 369,
         'Проводная клавиатура: Да, Налич. клавиш Windows: Да, Интерфейс связи с ПК: PS/2, Краткое описание: проводная,qwerty,PS/2, Гарантия: 3 года, Страна: КНР, Вид гарантии: по чеку');		 
		
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(101,33,'Планшетный компьютер Acer A100 XE.H6REN.015', 11990,
         'Операционная система: Android 3.2, Тип процессора: Tegra 2 250 T20 1ГГц, Встроенная память: 8 ГБ, Размер диагонали экрана: 7"(17.8 см), Разрешение матрицы фотокамеры: 5 МПикс');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(102,33,'Планшетный компьютер Sony SGPT111RU/S 16Gb Black', 17990,
         'Операционная система: Android 3.1, Тип процессора: Tegra 2 250 1ГГц, Встроенная память: 16 ГБ, Тип карты памяти: SD, SDHC, MMC, Размер диагонали экрана: 9.4"(23.8 см)');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(103,33,'Планшетный компьютер Samsung GT-P7500 16Gb White', 21490,
         'Стандарт GSM 2G / 3G: Да, Тип процессора: Tegra 2 250 T20 1ГГц, Флэш память (ROM): 16 ГБ, Размер диагонали экрана: 10.1"(25.7 см), Разрешение дисплея: 1280 x 800 Пикс');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(104,33,'Планшетный компьютер Acer A501 XE.H7KEN.022', 23990,
         'Операционная система: Android 3.0, Стандарт GSM 2G / 3G: Да, Тактовая частота процессора: 1 ГГц, Флэш память (ROM): 64 ГБ, Размер диагонали экрана: 10.1"(25.7 см)');

INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(105,34,'Моноблок Lenovo C205', 17990,
         'Размер диагонали экрана: 18.5"(47 см), Разрешение: 1366x768 Пикс (HD Ready), Тип процессора: E-350 1.6ГГц, Оперативная память (RAM): 2 ГБ, Жесткий диск (HDD): 320 ГБ');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(106,34,'Моноблок Acer Aspire Z3100 PWSETE1.018', 21990,
         'Размер диагонали экрана: 20"(50.8 см), Разрешение: 1600х900 Пикс (HD Ready), Тип процессора: Pentium E5800, Тактовая частота процессора: 3.2 ГГц, Оперативная память (RAM): 2 ГБ');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(107,34,'Моноблок HP 520-1001ru LN648EA', 31990,
         'Размер диагонали экрана: 23"(58.4 см), Разрешение: 1920x1080 Пикс (Full HD), Сенсорный дисплей: Да, Тип процессора: Core i3-2120, Тактовая частота процессора: 3.3 ГГц');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(108,34,'Моноблок Sony VPC-J21S1R/B', 39990,
         'Размер диагонали экрана: 21.5"(54.6 см), Разрешение: 1920x1080 Пикс (Full HD), Сенсорный дисплей: Да, Тип процессора: Core i3-2310M 2.1ГГц, Оперативная память (RAM): 4 ГБ');
		 
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(109,36,'Телефон DECT teXet ТХ-D4700А Black', 690,
         'Подключ. дополнит. трубок: до 5, Записная книжка: до 20 номеров, Определитель номера (АОН): Да, Время в режиме разговора: до 10 часов');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(110,36,'Телефон DECT Philips SE1501B/51', 790,
         'Подсветка дисплея трубки: Да, Подключ. дополнит. трубок: до 4, Записная книжка: до 50 номеров, Время в режиме разговора: до 12 часов, Краткое описание: 1880-1900 МГц DECT,черный');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(111,36,'Телефон DECT Motorola C601E RU', 890,
         'Подключ. дополнит. трубок: до 5, Записная книжка: до 50 номеров, Определитель номера (АОН): Да, Время в режиме разговора: до 12 часов');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(112,36,'Телефон DECT Panasonic KX-TG1711RUB', 1100,
         'Подсветка дисплея трубки: Да, Записная книжка: до 50 номеров, Функция Caller ID: Да, Определитель номера (АОН): Да, Время в режиме разговора: до 15 часов');
		 
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(113,37,'Мобильный телефон LG T500 Black', 2990,
         'Стандарт GSM 2G: Да, Разрешение дисплея: 240 x 320 Пикс, FM приемник: встроенный, Разрешение матрицы фотокамеры: 2 МПикс, Встроенная память: 60 МБ, Тип карты памяти: microSD');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(114,37,'Мобильный телефон Philips X518 White', 3690,
         'Стандарт GSM 2G: Да, Разрешение дисплея: 240 x 320 Пикс, SIM карта: 2, Разрешение матрицы фотокамеры: 2 МПикс, Тип карты памяти: microSD, microSDHC');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(115,37,'Мобильный телефон Nokia X2-00 2Gb Red', 4290,
         'Стандарт GSM 2G: Да, Количество цветов дисплея: 262000, Разрешение дисплея: 320 x 240 Пикс, Разрешение матрицы фотокамеры: 5 МПикс, Встроенная память: 48 МБ');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(116,37,'Мобильный телефон Philips X513', 4350,
         'Стандарт GSM 2G: Да, Количество цветов дисплея: 262144, Разрешение дисплея: 240 x 320 Пикс, SIM карта: 2, Разрешение матрицы фотокамеры: 2 МПикс');		 
		 		 
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(117,39,'Фотоаппарат цифровой компактный Canon PowerShot A1200 Black', 2690,
         'Эффект. разрешение матрицы: 12.1 МПикс, Оптическое увеличение: 4x, Диагональ дисплея: 2.7 ", Тип карты памяти: SD, SDHC, SDXC, MMC, MMC Plus, HCMMC Plus');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(118,39,'Фотоаппарат цифровой компактный Samsung ES80 Black', 2690,
         'Эффект. разрешение матрицы: 12.2 МПикс, Оптическое увеличение: 5x, Диагональ дисплея: 2.4 ", Тип карты памяти: SD, SDHC, Макс. разреш. видеозап.: 640x480 Пикс');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(119,39,'Фотоаппарат цифровой компактный Samsung ES80 Red', 2690,
         'Эффект. разрешение матрицы: 12.2 МПикс, Оптическое увеличение: 5x, Диагональ дисплея: 2.4 ", Тип карты памяти: SD, SDHC, Макс. разреш. видеозап.: 640x480 Пикс');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(120,39,'Фотоаппарат цифровой компактный Olympus VG-110 Black', 2990,
         'Эффект. разрешение матрицы: 12 МПикс, Оптическое увеличение: 4x, Диагональ дисплея: 2.7 ", Тип карты памяти: SD, SDHC, Макс. разреш. видеозап.: 640x480 Пикс, Звук видеозаписи: моно');
		 
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(121,40,'Цифровая видеокамера SAMSUNG HMX-H304BP', 15599,
         'Матрица: Количество - 1, Размер - 1/4.9 дюйма, Тип - CMOS, Максимальное разрешение (видео) - 1920x1080 Пикс, Общее разрешение - 5 Мпикс; Система записи: Встроенная Flash-память - 16 Гб, Карты памяти - SD, SDHC; Zoom: Макс. Оптический - 30x, Макс. Цифровой - 300x; Цвет - черный');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(122,40,'Цифровая видеокамера SONY HDR-CX130E Black', 18399,
         'Матрица: Количество - 1, Размер - 1/4 дюйма, Тип - CMOS, Максимальное разрешение (видео) - 1920x1080 Пикс, Общее разрешение - 4.2 Мпикс; Система записи: Карты памяти - MS, SD, SDHC; Zoom: Макс. Оптический - 25x, Макс. Цифровой - 300x; Цвет - черный');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(123,40,'Цифровая видеокамера SAMSUNG SMX-F50BP', 5999,
         'Матрица: Количество - 1, Размер - 1/6 дюйма, Тип - CCD, Максимальное разрешение (видео) - 720x480 Пикс; Система записи: Карты памяти - SD, SDHC; Zoom: Макс. Оптический - 52x/65x, Макс. Цифровой - 2200x; Цвет - черный');
INSERT INTO PRODUCT (ID_PRODUCT,ID_CATALOG,NAME,PRICE,DESCRIPTION) VALUES(124,40,'Цифровая видеокамера CANON LEGRIA M307', 17999,
         'Матрица: Количество - 1, Размер - 1/4 дюйма, Тип - CMOS, Максимальное разрешение (видео) - 3.89 Пикс, Общее разрешение - 3.89 Мпикс; Система записи: Карты памяти - SD, SDHC; Zoom: Макс. Оптический - 15x, Макс. Цифровой - 300x; Цвет - чёрный');		 
		 		 
		COMMIT;
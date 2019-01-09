INSERT INTO Organization (id, version, name, full_name, address, phone, inn, kpp, is_active) VALUES (1, 0, 'JetBrains', 'JetBrains', 'Краснопресненская наб., 14, стр. 1', '849595951', '1234567890', '123456789', TRUE);

INSERT INTO Organization (id, version, name, full_name, address, phone, inn, kpp, is_active) VALUES (2, 0, 'Сбербанк', 'ПАО Сбербанк', 'ул. Кутузовская, д.4', '8800553531', '1234567891', '123456788', TRUE);

INSERT INTO Organization (id, version, name, full_name, address, phone, inn, kpp, is_active) VALUES (3, 0, 'ФБК', 'Фонд борьбы с коррупцией', 'ул. Борьбы, д.13', '', '1234567892', '123456787', TRUE);

INSERT INTO Organization (id, version, name, full_name, address, phone, inn, kpp, is_active) VALUES (4, 0, 'Аналитика', 'ОАО Аналитика', 'ул. Математической статистики, д.20', '894562323', '1234567777', '123456777', FALSE);


INSERT INTO Office (id, version, org_id, name, phone, address, is_active) VALUES (1, 0, 1, 'Деловой центр', '849595951', 'Краснопресненская наб., 14, стр. 1', TRUE);

INSERT INTO Office (id, version, org_id, name, phone, address, is_active) VALUES (2, 0, 2, 'Президент Плаза', '8800553531', 'ул. Кутузовская, д.4', TRUE);

INSERT INTO Office (id, version, org_id, name, phone, address, is_active) VALUES (3, 0, 2, 'Сбербанк плаза', '849563225', 'ул. Грефа, д.8', TRUE);

INSERT INTO Office (id, version, org_id, name, phone, address, is_active) VALUES (4, 0, 3, 'Статистика', '', 'ул. Борьбы, д.13', TRUE);

INSERT INTO Office (id, version, org_id, name, phone, address, is_active) VALUES (5, 0, 4, 'Матстат', '894562323', 'ул. Математической статистики, д.20', FALSE);


INSERT INTO Document_Type (id, version, name, code) VALUES (1, 0, 'Паспорт гражданина РФ', '21');

INSERT INTO Document_Type (id, version, name, code) VALUES (2, 0, 'Вид на жительство в РФ', '12');

INSERT INTO Document_Type (id, version, name, code) VALUES (3, 0, 'Военный билет', '07');


INSERT INTO Document_Data (id, version, doc_type_id, doc_number, doc_date) VALUES (1, 0, 1, '1234567891', '1981-03-23');

INSERT INTO Document_Data (id, version, doc_type_id, doc_number, doc_date) VALUES (2, 0, 1, '4115632147', '1996-07-21');

INSERT INTO Document_Data (id, version, doc_type_id, doc_number, doc_date) VALUES (3, 0, 1, '4115632147', '1996-06-12');

INSERT INTO Document_Data (id, version, doc_type_id, doc_number, doc_date) VALUES (4, 0, 1, '4115825874', '1990-12-31');

INSERT INTO Document_Data (id, version, doc_type_id, doc_number, doc_date) VALUES (5, 0, 3, 'АЕ 4158967', '1987-09-06');

INSERT INTO Document_Data (id, version, doc_type_id, doc_number, doc_date) VALUES (6, 0, 2, '826499121', '1996-01-28');


INSERT INTO Country (id, version, name, code) VALUES (1, 0, 'Российская Федерация', '643');

INSERT INTO Country (id, version, name, code) VALUES (2, 0, 'Республика Беларусь', '112');


INSERT INTO Employee (id, version, office_id, doc_data_id, country_id, first_name, middle_name, second_name, position, phone, is_identified) VALUES (1, 0, 1, 1, 1, 'Владимир', 'Иванович', 'Иванов', 'Программист', '89685061821', TRUE);

INSERT INTO Employee (id, version, office_id, doc_data_id, country_id, first_name, middle_name, second_name, position, phone, is_identified) VALUES (2, 0, 2, 2, 1, 'Владислав', '', 'Перевощиков', 'Аналитик', '', TRUE);

INSERT INTO Employee (id, version, office_id, doc_data_id, country_id, first_name, middle_name, second_name, position, phone, is_identified) VALUES (3, 0, 2, 3, 1, 'Александра', '', 'Иванова', 'Программист', '89675061420', TRUE);

INSERT INTO Employee (id, version, office_id, doc_data_id, country_id, first_name, middle_name, second_name, position, phone, is_identified) VALUES (4, 0, 3, 4, 1, 'Алина', '', 'Александрова', 'Менеджер', '89051012021', TRUE);

INSERT INTO Employee (id, version, office_id, doc_data_id, country_id, first_name, middle_name, second_name, position, phone, is_identified) VALUES (5, 0, 4, 5, 1, 'Владислав', 'Владимирович', 'Осютин', 'Секретарь', '', TRUE);

INSERT INTO Employee (id, version, office_id, doc_data_id, country_id, first_name, middle_name, second_name, position, phone, is_identified) VALUES (6, 0, 1, 6, 2, 'Захар', '', 'Мазуров', 'Программист', '89456221', TRUE);

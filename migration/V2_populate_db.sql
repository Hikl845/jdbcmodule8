-- Вставка worker
INSERT INTO worker (name, birthday, level, salary) VALUES
                                                       ('Ivan Petrov', '1985-04-22', 'Junior', 35000),
                                                       ('Petro Siriv', '2005-01-04', 'Middle', 45000),
                                                       ('Anna Sirova', '1990-05-12', 'Trainee', 2000),
                                                       ('Pavel Olaf', '1988-02-27', 'Senior', 99000),
                                                       ('Victoria Andreeva', '2002-06-30', 'Junior', 37500),
                                                       ('Oleksiy Golda', '1975-12-31', 'Middle', 47500),
                                                       ('Ivan Igorov', '1997-09-15', 'Senior', 80000),
                                                       ('Anton Orlan', '1967-05-19', 'Trainee', 900),
                                                       ('Jack Sho', '1988-02-10', 'Junior', 32500),
                                                       ('Eshli Johanson', '1999-06-20', 'Middle', 45000);

-- Вставка client
INSERT INTO client (name) VALUES
                              ('John Sno'),
                              ('Artem Maksimov'),
                              ('Elva Orih'),
                              ('Riley Gorn'),
                              ('Sara Konor');

-- Вставка project
INSERT INTO project (client_id, start_date, finish_date) VALUES
                                                             (1, '2020-01-01', '2020-08-01'),
                                                             (1, '2019-02-15', '2020-04-15'),
                                                             (2, '2018-03-10', '2019-02-20'),
                                                             (2, '2021-07-05', '2022-10-05'),
                                                             (3, '2017-05-22', '2018-09-22'),
                                                             (3, '2019-11-01', '2020-10-31'),
                                                             (4, '2020-06-13', '2021-01-13'),
                                                             (4, '2022-02-18', '2023-01-18'),
                                                             (5, '2018-09-30', '2019-12-30'),
                                                             (5, '2021-03-01', '2022-12-01');

-- Вставка project_worker
INSERT INTO project_worker (project_id, worker_id) VALUES
                                                       (1, 1), (1, 2), (1, 3),
                                                       (2, 2), (2, 5),
                                                       (3, 4), (3, 6),
                                                       (4, 1), (4, 7), (4, 8),
                                                       (5, 3),
                                                       (6, 2), (6, 9),
                                                       (7, 5), (7, 8),
                                                       (8, 6), (8, 9), (8, 10),
                                                       (9, 4), (9, 7),
                                                       (10, 1), (10, 3), (10, 5);

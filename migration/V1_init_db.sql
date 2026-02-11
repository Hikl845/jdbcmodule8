CREATE TABLE worker (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(1000) NOT NULL
                            CHECK (CHAR_LENGTH(name) >= 2 AND CHAR_LENGTH(name) <= 1000),
                        birthday DATE NOT NULL
                            CHECK (YEAR(birthday) > 1900),
    level VARCHAR(20) NOT NULL
        CHECK (level IN ('Trainee', 'Junior', 'Middle', 'Senior')),
    salary INT NOT NULL
        CHECK (salary >= 100 AND salary <= 100000)
);

CREATE TABLE client (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(1000) NOT NULL
                            CHECK (CHAR_LENGTH(name) >= 2 AND CHAR_LENGTH(name) <= 1000)
);

CREATE TABLE project (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         client_id INT NOT NULL,
                         start_date DATE NOT NULL,
                         finish_date DATE NOT NULL,

                         CHECK (finish_date > start_date),

                         FOREIGN KEY (client_id)
                             REFERENCES client(id)
                             ON DELETE RESTRICT
                             ON UPDATE CASCADE
);


CREATE TABLE project_worker (
                                project_id INT NOT NULL,
                                worker_id INT NOT NULL,

                                PRIMARY KEY (project_id, worker_id),

                                FOREIGN KEY (project_id)
                                    REFERENCES project(id)
                                    ON DELETE CASCADE
                                    ON UPDATE CASCADE,

                                FOREIGN KEY (worker_id)
                                    REFERENCES worker(id)
                                    ON DELETE CASCADE
                                    ON UPDATE CASCADE
);

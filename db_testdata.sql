INSERT INTO user_role (id, name) VALUES (1, 'user');
INSERT INTO user_role (id, name) VALUES (2, 'tester');
INSERT INTO user_role (id, name) VALUES (3, 'admin');

INSERT INTO users (id, city, email, full_name, login, passwd_hash, userrole_id) VALUES (274, 'Olomouc', 'muj@mejl.cz', 'Ondrej Zahradnik', 'tomas', 'fe894434e70d3bb0253ee44df7699b6eed9257c65c7e07d0d7f440b744930111', 1);
INSERT INTO users (id, city, email, full_name, login, passwd_hash, userrole_id) VALUES (1235, 'Olomouc', 'muj@mejl.cz', 'Ondrej Zahradnik', 'jarda', 'fe894434e70d3bb0253ee44df7699b6eed9257c65c7e07d0d7f440b744930111', 1);
INSERT INTO users (id, city, email, full_name, login, passwd_hash, userrole_id) VALUES (1236, 'Olomouc', 'muj@mejl.cz', 'Ondrej Zahradnik', 'pepa', 'fe894434e70d3bb0253ee44df7699b6eed9257c65c7e07d0d7f440b744930111', 2);
INSERT INTO users (id, city, email, full_name, login, passwd_hash, userrole_id) VALUES (1237, 'Olomouc', 'muj@mejl.cz', 'Ondrej Zahradnik', 'admin', 'fe894434e70d3bb0253ee44df7699b6eed9257c65c7e07d0d7f440b744930111', 3);

INSERT INTO category (id, name) VALUES (666, 'pekelná kuchyně');

INSERT INTO user_role (id, name) VALUES (1, 'user');
INSERT INTO user_role (id, name) VALUES (2, 'tester');
INSERT INTO user_role (id, name) VALUES (3, 'admin');

INSERT INTO users (id, city, email, nickname, login, passwd_hash, userrole_id) VALUES (274, 'Olomouc', 'muj@mejl.cz', 'zahradon', 'tomas', 'fe894434e70d3bb0253ee44df7699b6eed9257c65c7e07d0d7f440b744930111', 1);
INSERT INTO users (id, city, email, nickname, login, passwd_hash, userrole_id) VALUES (1235, 'Olomouc', 'muj@mejl.cz', 'zahradon', 'jarda', 'fe894434e70d3bb0253ee44df7699b6eed9257c65c7e07d0d7f440b744930111', 1);
INSERT INTO users (id, city, email, nickname, login, passwd_hash, userrole_id) VALUES (1236, 'Olomouc', 'muj@mejl.cz', 'zahradon', 'pepa', 'fe894434e70d3bb0253ee44df7699b6eed9257c65c7e07d0d7f440b744930111', 2);
INSERT INTO users (id, city, email, nickname, login, passwd_hash, userrole_id) VALUES (1237, 'Olomouc', 'muj@mejl.cz', 'zahradon', 'admin', 'fe894434e70d3bb0253ee44df7699b6eed9257c65c7e07d0d7f440b744930111', 3);

INSERT INTO category (id, name) VALUES (1, 'hlavní jídlo'), (2, 'rychlé občerstvení'), (3, 'potraviny');

INSERT INTO tag(id, name, category_id) VALUES 
    (1, 'menu', 1), (2, 'česká kuchyně', 1), (3, 'regionální kuchyně', 1), (4, 'mezinárodní kuchyně', 1),
    (5, 'bio', 3), (6, 'farmářské', 3), (7, 'regionální', 3),
    (8, 'pizza', 2), (9, 'burger', 2), (10, 'bageta', 2);


CREATE TABLE accident_type (
  id serial PRIMARY KEY,
  name varchar(2000)
);

CREATE TABLE rule (
  id serial PRIMARY KEY,
  name varchar(2000)
);

CREATE TABLE accident (
  id serial PRIMARY KEY,
  name varchar(2000),
  text text,
  address varchar(2000),
  type_id int
);

DROP TABLE IF EXISTS accident_rules;

CREATE TABLE accident_rules (
    accident_id int,
    rule_id int,
    UNIQUE (accident_id, rule_id),
    FOREIGN KEY (accident_id) REFERENCES accident (id),
    FOREIGN KEY (rule_id) REFERENCES rule (id)
);

--

INSERT INTO accident_type (name) VALUES ('Две машины');
INSERT INTO accident_type (name) VALUES ('Машина и человек');
INSERT INTO accident_type (name) VALUES ('Машина и велосипед');

--

INSERT INTO rule (name) VALUES ('Статья. 1');
INSERT INTO rule (name) VALUES ('Статья. 2');
INSERT INTO rule (name) VALUES ('Статья. 3');

--
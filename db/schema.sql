
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

CREATE TABLE accident_rules (
    accident_id int,
    rule_id int,
    UNIQUE (accident_id, rule_id)
);

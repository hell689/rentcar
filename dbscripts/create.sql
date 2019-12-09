CREATE SEQUENCE cars_id_seq;
CREATE SEQUENCE colors_id_seq;
CREATE SEQUENCE gearboxes_id_seq;
CREATE SEQUENCE marks_id_seq;
CREATE SEQUENCE rents_id_seq;
CREATE SEQUENCE requests_id_seq;
CREATE SEQUENCE users_id_seq;

CREATE TABLE cars
(
  id bigint default cars_id_seq.nextval NOT NULL,
  mark bigint NOT NULL,
  gearbox bigint NOT NULL,
  volume float NOT NULL,
  color bigint NOT NULL,
  CONSTRAINT id PRIMARY KEY (id)
);

CREATE TABLE colors
(
  id bigint default colors_id_seq.nextval NOT NULL,
  color char(50) NOT NULL,
  CONSTRAINT pk_colors PRIMARY KEY (id)
);

CREATE TABLE gearboxes
(
  id bigint default gearboxes_id_seq.nextval NOT NULL,
  gearbox char(50) NOT NULL,
  CONSTRAINT pk_gearboxes PRIMARY KEY (id)
);

CREATE TABLE marks
(
  id bigint default marks_id_seq.nextval NOT NULL,
  mark char(255) NOT NULL,
  CONSTRAINT pk_marks PRIMARY KEY (id)
);

CREATE TABLE rents
(
  id bigint default rents_id_seq.nextval NOT NULL,
  request bigint,
  car bigint,
  start_date datetime NOT NULL,
  end_date datetime NOT NULL,
  CONSTRAINT pk_rents PRIMARY KEY (id)
);

CREATE TABLE requests
(
  id bigint default requests_id_seq.nextval NOT NULL,
  mark bigint,
  gearbox bigint,
  volume float,
  color bigint,
  start_date datetime NOT NULL,
  end_date datetime NOT NULL,
  comment char(255),
  user bigint NOT NULL,
  processed bool default false,
  CONSTRAINT pk_requests PRIMARY KEY (id)
);

CREATE TABLE users
(
  id bigint default users_id_seq.nextval NOT NULL,
  name char(255) NOT NULL,
  surname char(255) NOT NULL,
  login char(255) NOT NULL,
  password char(255) NOT NULL,
  address char(255) NOT NULL,
  role integer NOT NULL,
  CONSTRAINT pk_users PRIMARY KEY (id)
);

ALTER TABLE cars ADD CONSTRAINT fk_cars_color
  FOREIGN KEY (color) REFERENCES colors (id) ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE cars ADD CONSTRAINT fk_cars_gearbox
  FOREIGN KEY (gearbox) REFERENCES gearboxes (id) ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE cars ADD CONSTRAINT fk_cars_mark
  FOREIGN KEY (mark) REFERENCES marks (id) ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE rents ADD CONSTRAINT fk_rents_car
  FOREIGN KEY (car) REFERENCES cars (id) ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE rents ADD CONSTRAINT fk_rents_request
  FOREIGN KEY (request) REFERENCES requests (id) ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE requests ADD CONSTRAINT fk_requests_color
  FOREIGN KEY (color) REFERENCES colors (id) ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE requests ADD CONSTRAINT fk_requests_gearbox
  FOREIGN KEY (gearbox) REFERENCES gearboxes (id) ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE requests ADD CONSTRAINT fk_requests_mark
  FOREIGN KEY (mark) REFERENCES marks (id) ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE requests ADD CONSTRAINT fk_requests_user
  FOREIGN KEY (user) REFERENCES users (id) ON DELETE RESTRICT ON UPDATE CASCADE;

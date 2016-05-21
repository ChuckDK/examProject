
DROP DATABASE IF EXISTS AppAcademy;
CREATE DATABASE AppAcademy;
USE AppAcademy;


CREATE TABLE course_responsibles(
	course_responsible_email VARCHAR(50) NOT NULL UNIQUE PRIMARY KEY,
  course_responsible_firstname varchar(50) NOT NULL,
  course_responsible_lastname varchar(50) NOT NULL,
  course_responsible_admin_rights boolean DEFAULT FALSE,
  course_responsible_password VARCHAR(128) NOT NULL
);

CREATE TABLE certificate_templates(
	certificate_template_id INT NOT NULL UNIQUE PRIMARY KEY AUTO_INCREMENT,
	certificate_template_name varchar(50) NOT NULL
);


CREATE TABLE course_participants(
	course_participants_email varchar(50) NOT NULL UNIQUE PRIMARY KEY,
  course_participants_firstname varchar(50) NOT NULL,
  course_participants_lastname varchar(50) NOT NULL
);


CREATE TABLE phones_course_participants(
	course_participants_email varchar(50) NOT NULL, FOREIGN KEY(course_participants_email) REFERENCES course_participants(course_participants_email),
	phone_type varchar(10) NOT NULL DEFAULT 'Mobile',
	phone_number INT NOT NULL DEFAULT 0,
  PRIMARY KEY (course_participants_email, phone_type)
);

CREATE TABLE phones_course_responsibles(
	course_responsible_email varchar(50) NOT NULL, FOREIGN KEY(course_responsible_email) REFERENCES course_responsibles(course_responsible_email),
	phone_type varchar(10) NOT NULL DEFAULT 'Mobile',
	phone_number INT NOT NULL DEFAULT 0,
  PRIMARY KEY (course_responsible_email, phone_type)
);

CREATE TABLE courses(
	course_id INT NOT NULL UNIQUE PRIMARY KEY AUTO_INCREMENT,
	course_name varchar(50) NOT NULL,
  course_start_date DATETIME NOT NULL,
  course_end_date DATETIME NOT NULL,
  course_responsible_email varchar(50) NOT NULL, FOREIGN KEY (course_responsible_email) REFERENCES course_responsibles(course_responsible_email),
  certificate_template_id INT NOT NULL  UNIQUE , FOREIGN KEY (certificate_template_id) REFERENCES certificate_templates(certificate_template_ID)
);


CREATE TABLE certificates(
	course_id INT NOT NULL, FOREIGN KEY (course_id) REFERENCES courses(course_id),
  course_participants_email varchar(50) NOT NULL,FOREIGN KEY (course_participants_email) REFERENCES course_participants(course_participants_email),
  course_certificate_sent boolean DEFAULT FALSE,
  PRIMARY KEY (course_id, course_participants_email)
);



CREATE INDEX AppAcademy ON course_responsibles
(course_responsible_email, course_responsible_firstname, course_responsible_lastname, course_responsible_admin_rights);

CREATE INDEX AppAcademy ON certificate_templates
(certificate_template_id,certificate_template_name);

CREATE INDEX AppAcademy ON course_participants
(course_participants_email, course_participants_firstname, course_participants_lastname);

CREATE INDEX AppAcademy ON phones_course_participants
(course_participants_email, phone_type, phone_number);

CREATE INDEX AppAcademy ON phones_course_responsibles
(course_responsible_email, phone_type, phone_number);

CREATE INDEX AppAcademy ON courses
(course_name, course_start_date, course_end_date, course_responsible_email, certificate_template_id);

INSERT INTO course_responsibles(course_responsible_email, course_responsible_firstname, course_responsible_lastname, course_responsible_admin_rights, course_responsible_password)VALUES
  ('sb@kea.dk','Signe','Borch', TRUE, '12213'),
  ('atb@kea.dk','Asger', 'ThaBomb', FALSE, '123214');

INSERT INTO certificate_templates (certificate_template_id, certificate_template_name) VALUES
  (1, 'SWK TEMPLATE 1'),
  (2, 'SWD TEMPLATE 1'),
  (4, 'SWK TEMPLATE 1');

INSERT INTO course_participants(course_participants_email, course_participants_firstname, course_participants_lastname) VALUES
  ('dtj@gmail.com', 'Dennis', 'TheJew'),
  ('etgc@apple.com', 'Emil', 'TheGasChamber'),
  ('fdm@yahoo.com','Fredrik','DankMeme'),
  ('jbdb@suicidewatch.com','Jonas','BDayBoy');

INSERT INTO phones_course_participants(course_participants_email, phone_type, phone_number) VALUES
  ('dtj@gmail.com','Primary',222),
  ('dtj@gmail.com','Secondary',444),
  ('etgc@apple.com','Primary',333),
  ('etgc@apple.com','Secondary',555),
  ('fdm@yahoo.com','Primary',765),
  ('jbdb@suicidewatch.com','Primary',888),
  ('jbdb@suicidewatch.com','Secondary',999);

INSERT INTO phones_course_responsibles(course_responsible_email, phone_type, phone_number) VALUES
  ('sb@kea.dk','Primary',112),
  ('sb@kea.dk','Secondary',113),
  ('atb@kea.dk','Primary',332),
  ('atb@kea.dk','Secondary',445);

INSERT INTO courses(course_name, course_start_date, course_end_date, course_responsible_email, certificate_template_id) VALUES
  ('SWK','2012-1-1','2013-1-1','sb@kea.dk',1),
  ('SWD','2012/1/1','2013/1/1','atb@kea.dk',2),
  ('OSCA','2012/1/1','2013/1/1','sb@kea.dk',4);

INSERT INTO certificates(course_id, course_participants_email, course_certificate_sent) VALUES
  (1,'dtj@gmail.com', TRUE),
  (1,'etgc@apple.com', TRUE),
  (2,'etgc@apple.com', FALSE),
  (2,'fdm@yahoo.com', FALSE),
  (2,'jbdb@suicidewatch.com', TRUE),
  (3,'fdm@yahoo.com', TRUE);
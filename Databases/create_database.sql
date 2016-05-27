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
	phone_type varchar(20) NOT NULL DEFAULT 'Mobile',
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
  certificate_template_id INT NOT NULL, FOREIGN KEY (certificate_template_id) REFERENCES certificate_templates(certificate_template_ID)
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
  ('klh@appacademy.dk','Kristian','Langborg-Hasen', TRUE, '1'),
  ('jens@jensen.dk','Jens', 'Jensen', FALSE, '1'),
  ('hans@hansen.dk','Hans', 'Hansen', FALSE, '1'),
  ('peter@petersen.dk','Peter', 'Petersen', FALSE, '1');


INSERT INTO certificate_templates (certificate_template_id, certificate_template_name) VALUES
  (1, 'Software Construction'),
  (2, 'Software Design'),
  (3, 'InfoSec Cert'),
  (4, 'InfoSec Certificate');

INSERT INTO course_participants(course_participants_email, course_participants_firstname, course_participants_lastname) VALUES
  ('dtj@gmail.com', 'Frank', 'FrankyBoy'),
  ('etgc@apple.com', 'Tony', 'TwoTimes'),
  ('fdm@yahoo.com','Enrico','TheButcher'),
  ('jbdb@nyc.com','Tiana','Eversmile'),
  ('a@b.d','Butch','Cassidy'),
  ('b@b.d','Sherlock','Holmes'),
  ('ernstscertifikat@forneus.net','Ernest','Hemmingway'),
  ('d@b.d','John Butler','Yeats'),
  ('e@b.d','William','Shakespeare'),
  ('f@b.d','Jan','Sonnergaard'),
  ('g@b.d','Johnny','BeGood'),
  ('h@b.d','Homer','OgHansIlliade'),
  ('i@b.d','Christopher','Hitchens'),
  ('j@b.d','Sam','Harris'),
  ('k@b.d','John','Dennet'),
  ('l@b.d','Richard','Dawkins'),
  ('m@b.d','John F.','Kennedy'),
  ('n@b.d','Scott','Fitzgerald'),
  ('o@b.d','Magna','Carta'),
  ('p@b.d','Opus','Dei'),
  ('q@b.d','Sur','SødSovs'),
  ('r@b.d','Brun','Sovs'),
  ('s@b.d','Franklin Delano','Roosevelt'),
  ('t@b.d','Dwight D.','Eisenhower');

INSERT INTO phones_course_participants(course_participants_email, phone_type, phone_number) VALUES
  ('dtj@gmail.com','Primary',222),
  ('dtj@gmail.com','Secondary',444),

  ('etgc@apple.com','Primary',333),
  ('etgc@apple.com','Secondary',555),

  ('fdm@yahoo.com','Primary',765),

  ('jbdb@nyc.com','Primary',888),
  ('jbdb@nyc.com','Secondary',132999),

  ('a@b.d','Primary',1111111),
  ('a@b.d','Secondary',222222),

  ('b@b.d','Primary',1111411),
  ('b@b.d','Secondary',22225322),

  ('ernstscertifikat@forneus.net','Primary',111571111),
  ('ernstscertifikat@forneus.net','Secondary',1111111),

  ('d@b.d','Primary',2222453),
  ('d@b.d','Secondary',1111111),

  ('e@b.d','Primary',2226432),
  ('e@b.d','Secondary',11134611),

  ('f@b.d','Primary',11111213),
  ('f@b.d','Secondary',2221252),

  ('g@b.d','Primary',111123312),
  ('g@b.d','Secondary',2222152),

  ('h@b.d','Primary',1111543),
  ('h@b.d','Secondary',111325231),

  ('i@b.d','Primary',22282),
  ('i@b.d','Secondary',111111),

  ('j@b.d','Primary',2222232),
  ('j@b.d','Secondary',11113211),

  ('k@b.d','Primary',1111245),
  ('k@b.d','Secondary',22252342),

  ('l@b.d','Primary',1110981),
  ('l@b.d','Secondary',00222222),

  ('m@b.d','Primary',11001111),
  ('m@b.d','Secondary',11110111),

  ('n@b.d','Primary',2222220),
  ('n@b.d','Secondary',11100111),

  ('o@b.d','Primary',22223422),
  ('o@b.d','Secondary',11191111),

  ('p@b.d','Primary',1111111),
  ('p@b.d','Secondary',22221222),

  ('q@b.d','Primary',111141241),
  ('q@b.d','Secondary',22241242),

  ('r@b.d','Primary',111111241),
  ('r@b.d','Secondary',11115761),

  ('s@b.d','Primary',2222967),
  ('s@b.d','Secondary',111167871),

  ('t@b.d','Primary',2222452),
  ('t@b.d','Secondary',11117681);

INSERT INTO phones_course_responsibles(course_responsible_email, phone_type, phone_number) VALUES
  ('klh@appacademy.dk','Primary',112),
  ('klh@appacademy.dk','Secondary',113),
  ('jens@jensen.dk','Primary',332),
  ('jens@jensen.dk','Secondary',445),
  ('hans@hansen.dk','Primary',332),
  ('hans@hansen.dk','Secondary',445),
  ('peter@petersen.dk','Primary',332),
  ('peter@petersen.dk','Secondary',445);

INSERT INTO courses(course_name, course_start_date, course_end_date, course_responsible_email, certificate_template_id) VALUES
  ('SWC','2012-1-1','2013-1-1','klh@appacademy.dk',1),
  ('SWD','2012/1/1','2017/1/1','hans@hansen.dk',2),
  ('InfoSec','2012/1/1','2017/1/1','peter@petersen.dk',4),
  ('SWD','2012/1/1','2017/1/1','peter@petersen.dk',2),
  ('SWD','2012/1/1','2013/1/1','peter@petersen.dk',2),
  ('SWD','2012/1/1','2017/1/1','peter@petersen.dk',2),
  ('InfoSec','2012/1/1','2013/1/1','peter@petersen.dk',3),
  ('SWC','2012/1/1','2013/1/1','peter@petersen.dk',1);


INSERT INTO certificates(course_id, course_participants_email, course_certificate_sent) VALUES
  (1,'dtj@gmail.com', TRUE),
  (1,'etgc@apple.com', TRUE),
  (2,'etgc@apple.com', FALSE),
  (2,'fdm@yahoo.com', TRUE ),
  (2,'jbdb@nyc.com', TRUE),
  (3,'fdm@yahoo.com', TRUE),
  (2,'a@b.d', TRUE ),
  (3,'b@b.d', TRUE ),
  (3,'ernstscertifikat@forneus.net', FALSE ),
  (4,'d@b.d', TRUE ),
  (4,'e@b.d', FALSE ),
  (5,'f@b.d', TRUE ),
  (6,'g@b.d', FALSE ),
  (7,'h@b.d', TRUE ),
  (5,'i@b.d', FALSE ),
  (3,'j@b.d', TRUE ),
  (3,'k@b.d', FALSE ),
  (3,'l@b.d', TRUE ),
  (2,'m@b.d', TRUE ),
  (5,'n@b.d', FALSE ),
  (7,'o@b.d', TRUE ),
  (7,'p@b.d', FALSE ),
  (6,'q@b.d', TRUE ),
  (4,'r@b.d', FALSE ),
  (3,'s@b.d', FALSE ),
  (2,'t@b.d', TRUE );
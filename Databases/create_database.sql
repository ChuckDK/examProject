
DROP DATABASE IF EXISTS course_manager;
CREATE DATABASE course_manager;
USE course_manager;

CREATE TABLE courses(
	course_id INT PRIMARY KEY AUTO_INCREMENT,
	course_name varchar(50),
    course_start_date DATETIME,
    course_end_date DATETIME,
    course_responsible_email varchar(50) REFERENCES course_responsibles (course_responsible_email),
    certificate_template_id INT REFERENCES certificate_templates (certificate_template_ID)
);

CREATE TABLE course_responsibles(
	course_responsible_email varchar(50),
    course_responsible_firstname varchar(50),
    course_responsible_lastname varchar(50),
    course_responsible_admin_rights boolean
);

CREATE TABLE certificate_templates(
	certificate_template_id INT PRIMARY KEY AUTO_INCREMENT,
	certificate_template_name varchar(50)
);

CREATE TABLE certificates(
	course_id INT REFERENCES courses(course_id),
    course_participant_email varchar(50) REFERENCES course_participants(course_participants_email),
    course_certificate_sent boolean
);

CREATE TABLE course_participants(
	course_participants_email varchar(50),
    course_participants_firstname varchar(50),
    course_participants_lastname varchar(50)
);

CREATE TABLE phones_course_participants(
	course_participant_email varchar(50) REFERENCES course_participants(course_participant_email),
	phone_type varchar(10),
	phone_number INT
);

CREATE TABLE phones_course_responsibles(
	course_responsible_email varchar(50) REFERENCES course_responsibles(course_responsible_email),
	phone_type varchar(10),
	phone_number INT
);
    
    
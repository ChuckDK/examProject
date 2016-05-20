USE AppAcademy;
SET sql_mode = '';

SELECT
  course_participants_firstname AS 'First Name',
  course_participants_lastname AS 'Last Name',
  cp.course_participants_email AS Email,
  ph1 AS Phone1,
  ph2 AS Phone2,
  certificates.course_certificate_sent AS 'Course Certificate Sent',
  courses.course_name AS 'Course Name'

FROM course_participants AS cp

JOIN
  (SELECT
    phone1.course_participants_email,
    phone1.phone_number AS ph1,
    phone2.phone_number AS ph2

  FROM
    (SELECT * FROM phones_course_participants
    WHERE phone_type = 'Primary') AS phone1

  JOIN
    (SELECT * FROM phones_course_participants
    WHERE phone_type = 'Secondary') AS phone2

  GROUP BY phone1.course_participants_email) AS cpe ON cp.course_participants_email = cpe.course_participants_email

  JOIN certificates ON cp.course_participants_email = certificates.course_participants_email

  JOIN courses ON certificates.course_id = courses.course_id;


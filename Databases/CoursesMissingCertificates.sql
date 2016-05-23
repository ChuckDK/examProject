USE AppAcademy;


SELECT
  cs.course_name AS CourseName,
  cs.course_start_date AS "Course Start Date",
  cs.course_end_date AS "Course End Date",
  cs.course_responsible_email AS "Course Responsible"
FROM certificates ctf
JOIN courses cs
ON ctf.course_id = cs.course_id
WHERE course_certificate_sent = FALSE
GROUP BY ctf.course_id;


SELECT DISTINCT
  course_participants_firstname AS FirstName,
  course_participants_lastname AS LastName,
  cp.course_participants_email AS EMail,
  course_certificate_sent AS Sent
FROM course_participants cp
JOIN certificates ctf
ON cp.course_participants_email = ctf.course_participants_email
WHERE ctf.course_certificate_sent = FALSE;

UPDATE certificates
  SET course_certificate_sent = TRUE
WHERE certificates.course_participants_email = 'fdm@yahoo.com'
AND certificates.course_id = 2;
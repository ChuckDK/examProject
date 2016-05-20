package control.operations;

import javafx.scene.control.Button;
import model.coursedata.CourseParticipant;

import java.sql.*;
import java.util.ArrayList;


public class MySQLParticipants extends SQLOperations{
    public static ArrayList<CourseParticipant>  getMissing()
    {
        String sqlFetchMissingCertificatesParticipants =
                "SELECT\n" +
                "  course_participants_firstname AS 'FirstName',\n" +
                "  course_participants_lastname AS 'LastName',\n" +
                "  cp.course_participants_email AS Email,\n" +
                "  ph1 AS Phone1,\n" +
                "  ph2 AS Phone2,\n" +
                "  certificates.course_certificate_sent AS 'CourseCertificateSent',\n" +
                "  courses.course_name AS 'CourseName'\n" +
                "\n" +
                "FROM course_participants AS cp\n" +
                "\n" +
                "JOIN\n" +
                "  (SELECT\n" +
                "    phone1.course_participants_email,\n" +
                "    phone1.phone_number AS ph1,\n" +
                "    phone2.phone_number AS ph2\n" +
                "\n" +
                "  FROM\n" +
                "    (SELECT * FROM phones_course_participants\n" +
                "    WHERE phone_type = 'Primary') AS phone1\n" +
                "\n" +
                "  JOIN\n" +
                "    (SELECT * FROM phones_course_participants\n" +
                "    WHERE phone_type = 'Secondary') AS phone2\n" +
                "\n" +
                "  GROUP BY phone1.course_participants_email) AS cpe ON cp.course_participants_email = cpe.course_participants_email\n" +
                "\n" +
                "  JOIN certificates ON cp.course_participants_email = certificates.course_participants_email\n" +
                "\n" +
                "  JOIN courses ON certificates.course_id = courses.course_id WHERE certificates.course_certificate_sent = 0;\n";

        return connectToDatabase(sqlFetchMissingCertificatesParticipants);
    }

    public static ArrayList<CourseParticipant>  getFiltered(String courseName)
    {
        String sqlFetchFilteredParticipants =
                "SELECT\n" +
                "  course_participants_firstname AS 'FirstName',\n" +
                "  course_participants_lastname AS 'LastName',\n" +
                "  cp.course_participants_email AS Email,\n" +
                "  ph1 AS Phone1,\n" +
                "  ph2 AS Phone2,\n" +
                "  certificates.course_certificate_sent AS 'CourseCertificateSent',\n" +
                "  courses.course_name AS 'CourseName'\n" +
                "\n" +
                "FROM course_participants AS cp\n" +
                "\n" +
                "JOIN\n" +
                "  (SELECT\n" +
                "    phone1.course_participants_email,\n" +
                "    phone1.phone_number AS ph1,\n" +
                "    phone2.phone_number AS ph2\n" +
                "\n" +
                "  FROM\n" +
                "    (SELECT * FROM phones_course_participants\n" +
                "    WHERE phone_type = 'Primary') AS phone1\n" +
                "\n" +
                "  JOIN\n" +
                "    (SELECT * FROM phones_course_participants\n" +
                "    WHERE phone_type = 'Secondary') AS phone2\n" +
                "\n" +
                "  GROUP BY phone1.course_participants_email) AS cpe ON cp.course_participants_email = cpe.course_participants_email\n" +
                "\n" +
                "  JOIN certificates ON cp.course_participants_email = certificates.course_participants_email\n" +
                "\n" +
                "  JOIN courses ON certificates.course_id = courses.course_id WHERE courses.course_name = '"+courseName+"';\n";

        return connectToDatabase(sqlFetchFilteredParticipants);
    }

    public static ArrayList<CourseParticipant>  getAll()
    {
        String sqlFetchAllParticipants =
                "SELECT\n" +
                       "  course_participants_firstname AS FirstName,\n" +
                       "  course_participants_lastname AS LastName,\n" +
                       "  cp.course_participants_email AS Email,\n" +
                       "  ph1 AS Phone1,\n" +
                       "  ph2 AS Phone2,\n" +
                       "  certificates.course_certificate_sent AS CourseCertificateSent,\n" +
                       "  courses.course_name AS CourseName\n" +
                       "  \n" +
                       "FROM course_participants AS cp\n" +
                       "\n" +
                       "JOIN\n" +
                       "  (SELECT\n" +
                       "    phone1.course_participants_email,\n" +
                       "    phone1.phone_number AS ph1,\n" +
                       "    phone2.phone_number AS ph2\n" +
                       "\n" +
                       "  FROM\n" +
                       "    (SELECT * FROM phones_course_participants\n" +
                       "    WHERE phone_type = 'Primary') AS phone1\n" +
                       "\n" +
                       "  JOIN\n" +
                       "    (SELECT * FROM phones_course_participants\n" +
                       "    WHERE phone_type = 'Secondary') AS phone2\n" +
                       "\n" +
                       "  GROUP BY phone1.course_participants_email) AS cpe ON cp.course_participants_email = cpe.course_participants_email\n" +
                       "\n" +
                       "  JOIN certificates ON cp.course_participants_email = certificates.course_participants_email\n" +
                       "\n" +
                       "  JOIN courses ON certificates.course_id = courses.course_id;\n" +
                       "\n";


            return connectToDatabase(sqlFetchAllParticipants);
    }

    public static ArrayList<CourseParticipant> connectToDatabase(String sqlStatement)
    {
        ArrayList<CourseParticipant> returnParticipants = new ArrayList<>();
        try {
            Statement statement;
            ResultSet resultSet;

            Class.forName("com.mysql.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/AppAcademy?autoReconnect=true&useSSL=true";

            Connection connection = DriverManager.getConnection(url, "root", "12345678");

            statement = connection.createStatement();

            resultSet = statement.executeQuery(sqlStatement);

            while(resultSet.next())
            {
                returnParticipants.add(new CourseParticipant(
                        resultSet.getString("CourseName"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("LastName"),
                        resultSet.getString("Phone1"),
                        resultSet.getString("Phone2"),
                        resultSet.getString("Email"),
                        resultSet.getBoolean("CourseCertificateSent"),
                        new Button("Send")

                ));
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnParticipants;
    }
}


package control.operations;

import control.settings.MySQLSettings;
import javafx.scene.control.Button;
import model.coursedata.CourseParticipant;

import java.sql.*;
import java.util.ArrayList;


public class MySQLParticipants extends SQLOperations{

    //Method which shows the participants who haven't recieved certificates yet.
    public static ArrayList<CourseParticipant>  getMissing()
    {
        String sqlFetchMissingCertificatesParticipants =
                "SELECT\n" +
                "certificate_template_name AS 'TemplateID', \n"+
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
                "  JOIN courses ON certificates.course_id = courses.course_id " +
                "JOIN certificate_templates ON courses.certificate_template_id = certificate_templates.certificate_template_id\n" +
                "WHERE certificates.course_certificate_sent = 0;\n";

        return connectToDatabase(sqlFetchMissingCertificatesParticipants);
    }

    public static ArrayList<CourseParticipant> getFiltered(int courseID)
    {
        String sqlFetchFilteredParticipants =
                "SELECT\n" +
                "certificate_template_name AS 'TemplateID', \n"+
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
                "  JOIN courses ON certificates.course_id = courses.course_id " +
                "JOIN certificate_templates ON courses.certificate_template_id = certificate_templates.certificate_template_id\n" +
                " WHERE courses.course_id = "+courseID+";\n";

        return connectToDatabase(sqlFetchFilteredParticipants);
    }

    public static ArrayList<CourseParticipant>  getAll()
    {
        String sqlFetchAllParticipants =
                "SELECT\n" +
                        "certificate_template_name AS 'TemplateID', \n"+
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
                       "  JOIN courses ON certificates.course_id = courses.course_id\n" +
                        "JOIN certificate_templates ON courses.certificate_template_id = certificate_templates.certificate_template_id\n" +

                        "\n";


            return connectToDatabase(sqlFetchAllParticipants);
    }

    public static ArrayList<CourseParticipant> getMissingFiltered(int courseID)
    {
        String sqlStatement =
                        "SELECT\n" +
                        "certificate_template_name AS 'TemplateID', \n"+
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
                        "  JOIN courses ON certificates.course_id = courses.course_id " +
                        "JOIN certificate_templates ON courses.certificate_template_id = certificate_templates.certificate_template_id\n" +
                        "WHERE courses.course_id = "+courseID+" AND certificates.course_certificate_sent is false;\n";
        return connectToDatabase(sqlStatement);
    }

    //method which set up connection for database and creates a participant object using a query.
    public static ArrayList<CourseParticipant> connectToDatabase(String sqlStatement)
    {
        ArrayList<CourseParticipant> returnParticipants = new ArrayList<>();
        try {
            Statement statement;
            ResultSet resultSet;

            //Class.forName simply loads a class, it also runs its static initializers.
            Class.forName("com.mysql.jdbc.Driver");

            String url = "jdbc:mysql://"+ MySQLSettings.getHost()+":"+MySQLSettings.getPort()+"/"+MySQLSettings.getDatabaseName();

            //A connection needs a url, a root, and a password.
            Connection connection = DriverManager.getConnection(url, MySQLSettings.getUsername(), MySQLSettings.getPassword());

            //Initialize the connection as an sql statement.
            statement = connection.createStatement();

            //Executes the query string.
            resultSet = statement.executeQuery(sqlStatement);

            //Add course participants to the array (as long ad they are in the database).
            while(resultSet.next())
            {
                String templateID = resultSet.getString("TemplateID");

                Button button = new Button("Send");

                returnParticipants.add(new CourseParticipant(
                        resultSet.getString("CourseName"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("LastName"),
                        resultSet.getString("Phone1"),
                        resultSet.getString("Phone2"),
                        resultSet.getString("Email"),
                        resultSet.getBoolean("CourseCertificateSent"),
                        button

                ));
                button.setOnAction(e->
                {
                    SMTPOperations.sendMissingCertificate(templateID);
                });
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnParticipants;
    }
}


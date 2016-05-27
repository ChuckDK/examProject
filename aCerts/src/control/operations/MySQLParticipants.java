package control.operations;

import control.settings.MySQLSettings;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.coursedata.CourseParticipant;
import view.styling.ACertsColorScheme;

import java.sql.*;
import java.util.ArrayList;


public class MySQLParticipants extends SQLOperations{

    //Method which shows the participants who haven't received certificates yet.
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

    //Method which shows the participants who are linked to a specific course.
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

    //Method which shows all the participants.
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

    //Method which shows the participants from a specific course who haven't received certificates yet.
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

    //Method which set up connection for database and creates a participant object using a query.
    private static ArrayList<CourseParticipant> connectToDatabase(String sqlStatement)
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

            //Add course participants to the array (as long as they are in the database).
            while(resultSet.next())
            {
                String templateID = resultSet.getString("TemplateID");

                Button button = new Button("Send");

                CourseParticipant participant = new CourseParticipant(
                        resultSet.getString("CourseName"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("LastName"),
                        resultSet.getString("Email"),
                        resultSet.getString("Phone1"),
                        resultSet.getString("Phone2"),
                        new SimpleBooleanProperty(resultSet.getBoolean("CourseCertificateSent")),
                        button);

                returnParticipants.add(participant);

                //Add functionality to the button which uses the sendMissingCertificate
                //method from SMTPOperations class to send certificates.
                //See the SMTPOperations class for the method functionality.
                button.setOnAction(e->
                {
                    Stage alertBox = new Stage();
                    Pane pane = new Pane();
                    pane.setStyle(ACertsColorScheme.viewColor());

                    Button ok = new Button("OK");

                    ok.setStyle(ACertsColorScheme.buttonColor());

                    Label message;
                    try
                    {
                        SMTPOperations.sendMissingCertificate(templateID, participant);
                        participant.setCertificateSent(true);
                        message = new Label("Mail sent successfully!");
                    }
                    catch (Exception e1)
                    {
                        e1.printStackTrace();
                        message = new Label("Failed to send certificate!");
                        pane.setStyle("-fx-background-color: FF0000"); //red
                    }
                    pane.getChildren().addAll(ok, message);
                    ok.setLayoutX(75);
                    ok.setLayoutY(60);
                    ok.setPrefWidth(50);

                    ok.setOnAction(event->alertBox.close());

                    double width = com.sun.javafx.tk.Toolkit.getToolkit().getFontLoader().computeStringWidth(message.getText(), message.getFont());

                    message.setLayoutX(100 - width / 2);
                    message.setLayoutY(20);

                    alertBox.setScene(new Scene(pane, 200, 100));
                    alertBox.initModality(Modality.APPLICATION_MODAL);
                    alertBox.showAndWait();
                });
            }

            //Close the connection
            connection.close();
        }

        //Handle any exceptions that might occur.
        catch (Exception e) {
            e.printStackTrace();
        }

        //Return the participant array.
        return returnParticipants;
    }
}

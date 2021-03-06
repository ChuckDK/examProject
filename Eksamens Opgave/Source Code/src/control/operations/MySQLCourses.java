package control.operations;

import control.settings.MySQLSettings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import model.certificates.TemplateID;
import model.coursedata.Course;
import model.coursedata.CourseParticipant;
import model.coursedata.CourseResponsible;
import view.courses.ViewPaneCourseParticipants;
import view.helpers.Utility;
import view.managers.ViewPanesManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class MySQLCourses extends SQLOperations{

    //Method which uses a MySQL statement with the now method from Java to get the active courses.
    public static ArrayList<Course>  getActive()
    {
        //The MySQL statement which will return the email, first name, last name, and the two different phone numbers
        String sqlStatement =
                "SELECT \n" +
                "    course_id,\n"+
                "    course_name,\n" +
                "    course_responsible_email,\n" +
                "    course_start_date,\n" +
                "    course_end_date\n" +
                "from\n" +
                "    courses\n" +
                "where\n" +
                "    course_end_date > now();";

        return connectToDatabase(sqlStatement, false);
    }

    //Method which uses a MySQL statement with the now method from Java to get the inactive courses.
    public static ArrayList<Course>  getInActive()
    {
        //The MySQL statement which will return the email, first name, last name, and the two different phone numbers
        String sqlStatement =
                "SELECT \n" +
                "    course_id,\n"+
                "    course_name,\n" +
                "    course_responsible_email,\n" +
                "    course_start_date,\n" +
                "    course_end_date\n" +
                "from\n" +
                "    courses\n"+
                "where\n" +
                "    course_end_date <= now();";

        return connectToDatabase(sqlStatement, false);
    }

    //Method which uses a MySQL statement with the now method from Java to get the all courses.
    public static ArrayList<Course> getAll()
    {
        //The MySQL statement which will return the email, first name, last name, and the two different phone numbers
        String sqlStatement =
                "SELECT \n" +
                "    course_id,\n"+
                "    course_name,\n" +
                "    course_responsible_email,\n" +
                "    course_start_date,\n" +
                "    course_end_date\n" +
                "from\n" +
                "    courses\n;";
        return connectToDatabase(sqlStatement, false);
    }

    public static ArrayList<Course> getMissing()
    {
        String sqlStatement = "\n" +
                "SELECT\n" +
                "  cs.course_id,\n" +
                "  cs.course_name,\n" +
                "  cs.course_start_date,\n" +
                "  cs.course_end_date,\n" +
                "  cs.course_responsible_email\n" +
                "FROM certificates ctf\n" +
                "JOIN courses cs\n" +
                "ON ctf.course_id = cs.course_id\n" +
                "WHERE course_certificate_sent = FALSE\n" +
                "GROUP BY ctf.course_id;";
        return connectToDatabase(sqlStatement, true);
    }

    //Method which creates a connection to the database and may take a SQL statement in form of a string and execute it,
    private static ArrayList<Course> connectToDatabase(String sqlStatement, boolean missing)
    {
        //Create an array list
        // which will hold all the course responsible objects.
        ArrayList<Course> returnList = new ArrayList<>();

        try {
            Statement statement;
            ResultSet resultSet;

            //Class.forName simply loads a class, including running its static initializers.
            Class.forName("com.mysql.jdbc.Driver");

            String url = "jdbc:mysql://"+ MySQLSettings.getHost()+":"+MySQLSettings.getPort()+"/"+MySQLSettings.getDatabaseName();

            //A connection needs a url, a root, and a password.
            Connection connection = DriverManager.getConnection(url, MySQLSettings.getUsername(), MySQLSettings.getPassword());

            //Initialize the connection as an sql statement.
            statement = connection.createStatement();

            //Executes the query string.
            resultSet = statement.executeQuery(sqlStatement);

            //As long as there is a resultSet, run the loop.
            while(resultSet.next())
            {
                Button button = new Button("View");

                //Call getString with column name on resultSet and thereby get the columns content.
                Course course = new Course(
                        resultSet.getInt("course_id"),
                        resultSet.getString("course_name"),
                        resultSet.getString("course_responsible_email"),
                        resultSet.getString("course_start_date"),
                        resultSet.getString("course_end_date"),
                        new Button("Download"),
                        button
                );
                returnList.add(course);
                System.out.println(course.getCourseName());
                button.setOnAction(e->
                {
                    //Set up the pane for a manager.
                    TabPane tabPane = (TabPane) ViewPanesManager.getInstance(true).getPane(0).getChildren().get(0);
                    //Selects number two in the table view
                    tabPane.getSelectionModel().select(2);

                    //Set up the pane. Typecasting happens because there can be multiple objects connected to the tab pane.
                    TableView<CourseParticipant> participantsTableView = ((ViewPaneCourseParticipants) tabPane.getTabs().get(2).getContent()).getCourseParticipantTableView();
                    ((ToggleButton) ((ViewPaneCourseParticipants) tabPane.getTabs().get(2).getContent()).getChildren().get(1)).setSelected(true);

                    try
                    {
                        ObservableList<CourseParticipant> participants;
                        if(!missing)
                        {
                            participants = FXCollections.observableArrayList(MySQLParticipants.getFiltered(course.getCourseID()));
                        }
                        else
                        {
                            participants = FXCollections.observableArrayList(MySQLParticipants.getMissingFiltered(course.getCourseID()));
                        }
                        participantsTableView.itemsProperty().setValue(participants);
                    }
                    catch (Exception ex)
                    {
                        ex.printStackTrace();
                    }
                });
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnList;
    }

    //Method which returns a list of the templates and sets up a connection for database
    //and creates a participant object using a query.
    public static ArrayList<TemplateID> getTemplateList()
    {
        ArrayList<TemplateID> returnList = new ArrayList<>();
        String sqlStatement =
                "select \n" +
                "    certificate_template_id, certificate_template_name\n" +
                "from\n" +
                "    certificate_templates";

        try {
            Statement statement;
            ResultSet resultSet;

            Class.forName("com.mysql.jdbc.Driver");

            String url = "jdbc:mysql://127.0.0.1:3306/AppAcademy";

            Connection connection = DriverManager.getConnection(url, "root", "12345678");

            statement = connection.createStatement();

            resultSet = statement.executeQuery(sqlStatement);

            //Adding TemplateID objects (as long ad they are there) to the resultList array list.
            while(resultSet.next())
            {
                TemplateID templateID = new TemplateID(
                        resultSet.getInt("certificate_template_id"),
                        resultSet.getString("certificate_template_name"));
                returnList.add(templateID);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnList;
    }
}

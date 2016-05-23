package control.operations;

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
    public static ArrayList<Course>  getActive()
    {
        //The MySQL statement which will return the email, first name, last name, and the two different phone numbers
        String sqlStatement =
                "SELECT \n" +
                "    course_name,\n" +
                "    course_responsible_email,\n" +
                "    course_start_date,\n" +
                "    course_end_date\n" +
                "from\n" +
                "    courses\n" +
                "where\n" +
                "    course_end_date > now();";

        return connectToDatabase(sqlStatement);
    }

    public static ArrayList<Course>  getInActive()
    {
        //The MySQL statement which will return the email, first name, last name, and the two different phone numbers
        String sqlStatement =
                "SELECT \n" +
                        "    course_name,\n" +
                        "    course_responsible_email,\n" +
                        "    course_start_date,\n" +
                        "    course_end_date\n" +
                        "from\n" +
                        "    courses\n"+
                        "where\n" +
                        "    course_end_date <= now();";

        return connectToDatabase(sqlStatement);
    }

    public static ArrayList<Course> getAll()
    {
        //The MySQL statement which will return the email, first name, last name, and the two different phone numbers
        String sqlStatement =
                "SELECT \n" +
                        "    course_name,\n" +
                        "    course_responsible_email,\n" +
                        "    course_start_date,\n" +
                        "    course_end_date\n" +
                        "from\n" +
                        "    courses\n;";
        return connectToDatabase(sqlStatement);
    }

    public static ArrayList<Course> getMissing()
    {
        return null;
    }

    public static ArrayList<Course> connectToDatabase(String sqlStatement)
    {
        //Create an array list which will hold all the course responsible objects.
        ArrayList<Course> returnList = new ArrayList<>();

        try {
            Statement statement;
            ResultSet resultSet;

            Class.forName("com.mysql.jdbc.Driver");

            String url = "jdbc:mysql://127.0.0.1:3306/AppAcademy";

            Connection connection = DriverManager.getConnection(url, "root", "12345678");

            statement = connection.createStatement();

            resultSet = statement.executeQuery(sqlStatement);

            while(resultSet.next())
            {
                Button button = new Button("View");

                Course course = new Course(
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
                    TabPane tabPane = (TabPane) ViewPanesManager.getInstance(true).getPane(0).getChildren().get(0);
                    tabPane.getSelectionModel().select(2);
                    TableView<CourseParticipant> participantsTableView = ((ViewPaneCourseParticipants) tabPane.getTabs().get(2).getContent()).getCourseParticipantTableView();
                    ((ToggleButton) ((ViewPaneCourseParticipants) tabPane.getTabs().get(2).getContent()).getChildren().get(1)).setSelected(true);

                    try
                    {
                        ObservableList<CourseParticipant> participants = FXCollections.observableArrayList(MySQLParticipants.getFiltered(course.getCourseName()));
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

            while(resultSet.next())
            {
                Button button = new Button("View");

                TemplateID templateID = new TemplateID(
                        resultSet.getInt("certificate_template_id"),
                        resultSet.getString("certifiate_template_name"));
                returnList.add(templateID);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnList;
    }
}

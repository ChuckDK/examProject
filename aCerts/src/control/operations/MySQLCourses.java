package control.operations;

import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import model.coursedata.Course;
import model.coursedata.CourseResponsible;
import view.managers.ViewPanesManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class MySQLCourses implements SQLOperations{
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

            String url = "jdbc:mysql://localhost:3306/AppAcademy?autoReconnect=true&useSSL=true";

            Connection connection = DriverManager.getConnection(url, "root", "12345678");

            statement = connection.createStatement();

            resultSet = statement.executeQuery(sqlStatement);

            while(resultSet.next())
            {
                Button button = new Button("View");

                returnList.add(new Course(
                        resultSet.getString("course_name"),
                        resultSet.getString("course_responsible_email"),
                        resultSet.getString("course_start_date"),
                        resultSet.getString("course_end_date"),
                        new Button("Download"),
                        button
                ));

                button.setOnAction(e->
                {
                    ((TabPane) ViewPanesManager.getInstance(true).getPane(0).getChildren().get(0)).getSelectionModel().select(2);
                });
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnList;
    }
}

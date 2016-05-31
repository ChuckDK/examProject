package control.operations;

import control.settings.MySQLSettings;
import model.coursedata.CourseResponsible;

import java.sql.*;
import java.util.ArrayList;

public class MySQLCourseResponsible extends SQLOperations{

    //Method which gets the active course responsibles.
    public static ArrayList<CourseResponsible>  getActive()
    {
        //This MySQL statement will return the email, first name, last name, and the two different phone numbers.
        String sqlStatement =

                "SELECT \n" +
                "    cr.course_responsible_email,\n" +
                "    course_responsible_firstname,\n" +
                "    course_responsible_lastname,\n" +
                "    course_responsible_admin_rights,\n" +
                "    ph1,\n" +
                "    ph2\n" +
                "FROM\n" +
                "    course_responsibles AS cr\n" +
                "        JOIN\n" +
                "    (SELECT \n" +
                "        phones1.course_responsible_email,\n" +
                "            phones1.phone_number AS ph1,\n" +
                "            phones2.phone_number AS ph2\n" +
                "    FROM\n" +
                "        (SELECT \n" +
                "        *\n" +
                "    FROM\n" +
                "        phones_course_responsibles\n" +
                "    where\n" +
                "        phone_type = 'Primary') AS phones1\n" +
                "    JOIN (SELECT \n" +
                "        *\n" +
                "    FROM\n" +
                "        phones_course_responsibles\n" +
                "    WHERE\n" +
                "        phone_type = 'Secondary') AS phones2\n" +
                "    GROUP BY phones1.course_responsible_email) AS pcr ON cr.course_responsible_email = pcr.course_responsible_email\n" +
                "        JOIN\n" +
                "    courses ON cr.course_responsible_email = courses.course_responsible_email\n" +
                "WHERE\n" +
                "    courses.course_end_date >= now();"
                ;
        return connectToDatabase(sqlStatement);
    }

    //This method fetches the inactive course responsibles.
    public static ArrayList<CourseResponsible>  getInActive()
    {
        //This MySQL statement will return the email, first name, last name, and the two different phone numbers
        String sqlStatement =
                "SELECT \n" +
                        "    cr.course_responsible_email,\n" +
                        "    course_responsible_firstname,\n" +
                        "    course_responsible_lastname,\n" +
                        "    course_responsible_admin_rights,\n" +
                        "    ph1,\n" +
                        "    ph2\n" +
                        "FROM\n" +
                        "    course_responsibles AS cr\n" +
                        "        JOIN\n" +
                        "    (SELECT \n" +
                        "        phones1.course_responsible_email,\n" +
                        "            phones1.phone_number AS ph1,\n" +
                        "            phones2.phone_number AS ph2\n" +
                        "    FROM\n" +
                        "        (SELECT \n" +
                        "        *\n" +
                        "    FROM\n" +
                        "        phones_course_responsibles\n" +
                        "    where\n" +
                        "        phone_type = 'Primary') AS phones1\n" +
                        "    JOIN (SELECT \n" +
                        "        *\n" +
                        "    FROM\n" +
                        "        phones_course_responsibles\n" +
                        "    WHERE\n" +
                        "        phone_type = 'Secondary') AS phones2\n" +
                        "    GROUP BY phones1.course_responsible_email) AS pcr ON cr.course_responsible_email = pcr.course_responsible_email\n" +
                        "        JOIN\n" +
                        "    courses ON cr.course_responsible_email = courses.course_responsible_email\n" +
                        "WHERE\n" +
                        "    courses.course_end_date < now()" +
                        "GROUP BY course_responsible_email;";

        return connectToDatabase(sqlStatement);
    }

    //A method which returns all data from the MySQL database regarding the course responsibles.
    public static ArrayList<CourseResponsible>  getAll()
    {
        //The MySQL statement which will return the email, first name, last name, and the two different phone numbers
        String sqlStatement =
                "SELECT\n " +
                "    cr.course_responsible_email,\n" +
                "    course_responsible_firstname,\n" +
                "    course_responsible_lastname,\n" +
                "    course_responsible_admin_rights,\n" +
                "    ph1, ph2\n" +
                "FROM\n" +
                "    course_responsibles AS cr\n" +
                "        JOIN\n" +
                "    (SELECT \n" +
                "        phones1.course_responsible_email,\n" +
                "            phones1.phone_number AS ph1,\n" +
                "            phones2.phone_number AS ph2\n" +
                "    FROM\n" +
                "        (SELECT \n" +
                "        *\n" +
                "    FROM\n" +
                "        phones_course_responsibles\n" +
                "    WHERE\n" +
                "        phone_type = 'Primary') AS phones1\n" +
                "    JOIN (SELECT \n" +
                "        *\n" +
                "    FROM\n" +
                "        phones_course_responsibles\n" +
                "    WHERE\n" +
                "        phone_type = 'Secondary') AS phones2\n" +
                "    GROUP BY phones1.course_responsible_email) AS pcr ON cr.course_responsible_email = pcr.course_responsible_email;";



        return connectToDatabase(sqlStatement);
    }

    public static ArrayList<CourseResponsible> connectToDatabase(String sqlStatement)
    {
        ArrayList<CourseResponsible> returnList = new ArrayList<>();
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

            //Add a course responsibles (as long ad they are there) to the array list and then return it.
            while(resultSet.next())
            {
                returnList.add(new CourseResponsible(
                        resultSet.getString("course_responsible_firstname"),
                        resultSet.getString("course_responsible_lastname"),
                        resultSet.getString("ph1"),
                        resultSet.getString("ph2"),
                        resultSet.getString("course_responsible_email"),
                        resultSet.getBoolean("course_responsible_admin_rights")
                ));
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnList;
    }

    //Method which fetches login information.
    public static CourseResponsible login(String username, String password)
    {
        System.out.println(password);
        System.out.println(username);
        String sqlStatement =
                "SELECT\n " +
                "    cr.course_responsible_email,\n" +
                "    course_responsible_firstname,\n" +
                "    course_responsible_lastname,\n" +
                "    course_responsible_admin_rights,\n" +
                "    ph1, ph2\n" +
                "FROM\n" +
                "    course_responsibles AS cr\n" +
                "        JOIN\n" +
                "    (SELECT \n" +
                "        phones1.course_responsible_email,\n" +
                "            phones1.phone_number AS ph1,\n" +
                "            phones2.phone_number AS ph2\n" +
                "    FROM\n" +
                "        (SELECT \n" +
                "        *\n" +
                "    FROM\n" +
                "        phones_course_responsibles\n" +
                "    WHERE\n" +
                "        phone_type = 'Primary') AS phones1\n" +
                "    JOIN (SELECT \n" +
                "        *\n" +
                "    FROM\n" +
                "        phones_course_responsibles\n" +
                "    WHERE\n" +
                "        phone_type = 'Secondary') AS phones2\n" +
                "    GROUP BY phones1.course_responsible_email) AS pcr ON cr.course_responsible_email = pcr.course_responsible_email\n" +
                "where\n" +
                "    cr.course_responsible_email = '"+username+"'\n" +
                "        and cr.course_responsible_password = '"+password+"';";

        System.out.println(sqlStatement);

        ArrayList<CourseResponsible> user = connectToDatabase(sqlStatement);

        //If the size of the list is equal to 1, it means a matching username and password has been found.
        //If this is the case, the user is returned.
        if(user.size() == 1)
        {
            return user.get(0);
        }
        return null;
    }
}

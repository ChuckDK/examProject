package control.operations;

import model.coursedata.CourseResponsible;

import java.sql.*;
import java.util.ArrayList;

public class MySQLCourseResponsible extends SQLOperations{
    public static ArrayList<CourseResponsible>  getActive()
    {
        //The MySQL statement which will return the email, first name, last name, and the two different phone numbers
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

    public static ArrayList<CourseResponsible>  getInActive()
    {
        //The MySQL statement which will return the email, first name, last name, and the two different phone numbers
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

            Class.forName("com.mysql.jdbc.Driver");

            String url = "jdbc:mysql://127.0.0.1:3306/AppAcademy";

            Connection connection = DriverManager.getConnection(url, "root", "12345678");

            statement = connection.createStatement();

            resultSet = statement.executeQuery(sqlStatement);

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
        if(user.size() == 1)
        {
            return user.get(0);
        }
        return null;
    }
}

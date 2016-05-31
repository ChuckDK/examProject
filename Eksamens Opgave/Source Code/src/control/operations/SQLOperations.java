package control.operations;

import control.settings.MySQLSettings;
import javafx.scene.control.Button;
import model.coursedata.CourseParticipant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SQLOperations {

    //Method which generates an SQL statement that adds a new row.
    public static boolean addNewRow(String table, String[] values)
    {
        //Initiating writing of the query.
        String sqlStatement = "INSERT INTO " +table+" VALUES(";

        //Loop which writes values to the statement.
        for(int i = 0; i < values.length; i++)
        {
            sqlStatement = sqlStatement + values[i];

            //An if statement which ensures the MySQL language comma is set correctly.
            if(i+1 < values.length)
            {
                sqlStatement = sqlStatement + ", ";
            }
        }
        sqlStatement = sqlStatement + ");";
        System.out.println(sqlStatement);
        return(connectToDatabase(sqlStatement));
    }
    public static boolean removeRow(String table, String variable, String value)
    {
        String sqlStatement = "DELETE FROM "+table+" WHERE "+variable+ " = "+value;
        return connectToDatabase(sqlStatement);
    }

    public static boolean removeCourseResponsible(String email)
    {
        String sqlStatement1 = "SET FOREIGN_KEY_CHECKS=0;";
        String sqlStatement2 = "DELETE FROM course_responsibles\n" +
                "WHERE course_responsible_email = '"+email+"';";
        String sqlStatement3 = "SET FOREIGN_KEY_CHECKS=1;";

        return connectToDatabaseMultiple(sqlStatement1, sqlStatement2, sqlStatement3);
    }

    //Method which creates the connection to the database.
    private static boolean connectToDatabaseMultiple(String sqlStatement1, String sqlStatement2, String sqlStatement3)
    {
        try {
            Statement statement;

            //Class.forName simply loads a class, including running its static initializers.
            Class.forName("com.mysql.jdbc.Driver");

            String url = "jdbc:mysql://"+ MySQLSettings.getHost()+":"+MySQLSettings.getPort()+"/"+MySQLSettings.getDatabaseName();

            //A connection needs a url, a root, and a password.
            Connection connection = DriverManager.getConnection(url, MySQLSettings.getUsername(), MySQLSettings.getPassword());

            //Initialize the connection as an sql statement.
            statement = connection.createStatement();
            statement.addBatch(sqlStatement1);
            statement.addBatch(sqlStatement2);
            statement.addBatch(sqlStatement3);

            //Executes the query string.
            statement.executeBatch();

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //Method which creates the connection to the database.
    private static boolean connectToDatabase(String sqlStatement)
    {
        try {
            Statement statement;

            //Class.forName simply loads a class, including running its static initializers.
            Class.forName("com.mysql.jdbc.Driver");

            String url = "jdbc:mysql://"+ MySQLSettings.getHost()+":"+MySQLSettings.getPort()+"/"+MySQLSettings.getDatabaseName();

            //A connection needs a url, a root, and a password.
            Connection connection = DriverManager.getConnection(url, MySQLSettings.getUsername(), MySQLSettings.getPassword());

            //Initialize the connection as an sql statement.
            statement = connection.createStatement();

            //Executes the query string.
            statement.executeUpdate(sqlStatement);

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static int getCertificatesCount()
    {
        int count = 0;
        String sqlStatement =
                "SELECT count(*) as 'count' from certificate_templates";
        try {
            Statement statement;

            //Class.forName simply loads a class, including running its static initializers.
            Class.forName("com.mysql.jdbc.Driver");

            String url = "jdbc:mysql://"+ MySQLSettings.getHost()+":"+MySQLSettings.getPort()+"/"+MySQLSettings.getDatabaseName();

            //A connection needs a url, a root, and a password.
            Connection connection = DriverManager.getConnection(url, MySQLSettings.getUsername(), MySQLSettings.getPassword());

            //Initialize the connection as an sql statement.
            statement = connection.createStatement();

            //Executes the query string.
            ResultSet resultSet = statement.executeQuery(sqlStatement);

            if(resultSet.next())
            {
                count = resultSet.getInt("count");
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }
}

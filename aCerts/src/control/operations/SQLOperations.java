package control.operations;

import javafx.scene.control.Button;
import model.coursedata.CourseParticipant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class SQLOperations {
    public static boolean addNewRow(String table, String[] values)
    {
        String sqlStatement = "INSERT INTO " +table+" VALUES(";
        for(int i = 0; i < values.length; i++)
        {
            sqlStatement = sqlStatement + values[i];
            if(i+1 < values.length)
            {
                sqlStatement = sqlStatement + ", ";
            }
        }
        sqlStatement = sqlStatement + ");";
        System.out.println(sqlStatement);
        if(connectToDatabase(sqlStatement))
        {
            return true;
        }
        else
        {
            return false;
        }

    }
    public static boolean removeRow(String sqlStatement)
    {
        return false;
    }

    private static boolean connectToDatabase(String sqlStatement)
    {
        try {
            Statement statement;

            Class.forName("com.mysql.jdbc.Driver");

            String url = "jdbc:mysql://127.0.0.1:3306/AppAcademy";

            Connection connection = DriverManager.getConnection(url, "root", "12345678");

            statement = connection.createStatement();

            statement.executeUpdate(sqlStatement);

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}

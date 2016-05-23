package control.settings;

public class MySQLSettings extends FileIO
{
    private static String username;
    private static String password;
    private static String databaseName;
    private static String host;
    private static int port;

    public static String getUsername()
    {
        return username;
    }

    public static void setUsername(String username)
    {
        MySQLSettings.username = username;
    }

    public static String getPassword()
    {
        return password;
    }

    public static void setPassword(String password)
    {
        MySQLSettings.password = password;
    }

    public static String getDatabaseName()
    {
        return databaseName;
    }

    public static void setDatabaseName(String databaseName)
    {
        MySQLSettings.databaseName = databaseName;
    }

    public static String getHost()
    {
        return host;
    }

    public static void setHost(String host)
    {
        MySQLSettings.host = host;
    }

    public static int getPort()
    {
        return port;
    }

    public static void setPort(int port)
    {
        MySQLSettings.port = port;
    }
}

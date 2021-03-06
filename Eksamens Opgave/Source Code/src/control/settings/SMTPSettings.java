package control.settings;

//Class from which enables initializing of MySQLSettings objects.
//The class holds getters and setters for the variables.
public class SMTPSettings extends FileIO
{
    private static String username;
    private static String password;
    private static String host;
    private static int port;
    private static String email;

    public static String getUsername()
    {
        return username;
    }

    public static void setUsername(String username)
    {
        SMTPSettings.username = username;
    }

    public static String getPassword()
    {
        return password;
    }

    public static void setPassword(String password)
    {
        SMTPSettings.password = password;
    }

    public static String getHost()
    {
        return host;
    }

    public static void setHost(String host)
    {
        SMTPSettings.host = host;
    }

    public static int getPort()
    {
        return port;
    }

    public static void setPort(int port)
    {
        SMTPSettings.port = port;
    }

    public static String getEmail()
    {
        return email;
    }

    public static void setEmail(String email)
    {
        SMTPSettings.email = email;
    }
}

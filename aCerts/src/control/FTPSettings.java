package control;

/**
 * Created by dennis on 5/13/16.
 */
public class FTPSettings implements SettingsInterface{
    private static String username;
    private static String password;
    private static String host;
    private static int port;


    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        FTPSettings.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        FTPSettings.password = password;
    }

    public static String getHost() {
        return host;
    }

    public static void setHost(String host) {
        FTPSettings.host = host;
    }

    public static int getPort() {
        return port;
    }

    public static void setPort(int port) {
        FTPSettings.port = port;
    }
}

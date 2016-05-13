package model;

/**
 * Created by dennis on 5/13/16.
 */
public class MySQLSettings {
    private String userName;
    private String password;
    private String databaseName;
    private String host;
    private int port;

    public MySQLSettings(String userName, String password, String databaseName, String host, int port) {
        this.userName = userName;
        this.password = password;
        this.databaseName = databaseName;
        this.host = host;
        this.port = port;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}

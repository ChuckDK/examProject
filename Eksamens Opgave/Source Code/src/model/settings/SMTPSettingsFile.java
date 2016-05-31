package model.settings;

import java.io.Serializable;

//Class from which enables initializing of SMTPSettingsFile objects. The class holds some getters and setters which
//may or may not be in use. The ones not in use are for later convenience.
public class SMTPSettingsFile implements Serializable
{
    private String userName;
    private String password;
    private String host;
    private int port;
    private String email;

    public SMTPSettingsFile(String userName, String password, String host, int port, String email) {
        this.userName = userName;
        this.password = password;
        this.host = host;
        this.port = port;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

package model.settings;

import java.io.Serializable;

//Class from which enables initializing of FTPSettingsFile objects. The class holds some getters and setters which
//may or may not be in use. The ones not in use are for later convenience.
public class FTPSettingsFile implements Serializable
{
    private String usernamer;
    private String password;
    private String host;
    private int port;

    public FTPSettingsFile(String usernamer, String password, String host, int port) {
        this.usernamer = usernamer;
        this.password = password;
        this.host = host;
        this.port = port;
    }

    public String getUsernamer() {
        return usernamer;
    }

    public void setUsernamer(String usernamer) {
        this.usernamer = usernamer;
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
}

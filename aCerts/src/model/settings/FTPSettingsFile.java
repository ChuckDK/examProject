package model.settings;

import java.io.Serializable;

/**
 * Created by dennis on 5/13/16.
 */
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

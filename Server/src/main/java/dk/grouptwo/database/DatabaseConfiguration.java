package dk.grouptwo.database;

import java.io.Serializable;

public class DatabaseConfiguration implements Serializable {
    private String driver = "org.postgresql.Driver";
    private String url = "jdbc:postgresql://localhost:5432/sep2db?currentSchema=sep2db";
    private String user = "postgres";
    private String password = "gt-s5570";

    public DatabaseConfiguration() {
    }

    public DatabaseConfiguration(String driver, String url, String user, String password) {
        this.driver = driver;
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public String getDriver() {
        return driver;
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "DatabaseConfiguration{" +
                "driver='" + driver + '\'' +
                ", url='" + url + '\'' +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

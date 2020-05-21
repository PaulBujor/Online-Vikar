package dk.grouptwo.database;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DatabaseConnection {

    private Connection connection;
    private DatabaseConfiguration cfg;
    private String driver;
    private String url;
    private String user;
    private String password;
    private static DatabaseConnection instance;
    private static Lock locker = new ReentrantLock();

    private DatabaseConnection() {
        this.connection = null;
        Gson gson = new Gson();
        File configFile = new File("database.cfg");
        Scanner in = null;
        try {
            in = new Scanner(configFile);
            cfg = gson.fromJson(in.nextLine(), DatabaseConfiguration.class);
        } catch (FileNotFoundException e) {
            System.out.println("Configuration file could not be found, loading default configuration and creating configuration file.");
            cfg = new DatabaseConfiguration();
            try {
                PrintWriter out = new PrintWriter(configFile);
                out.println(gson.toJson(cfg));
            } catch (FileNotFoundException fileNotFoundException) {
                System.out.println("Could not create configuration file. You're on your own. Over.");
            }
        }
        driver = cfg.getDriver();
        url = cfg.getUrl();
        user = cfg.getUser();
        password = cfg.getPassword();
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            synchronized (locker) {
                if (instance == null)
                    instance = new DatabaseConnection();
            }
        }
        return instance;
    }

    public synchronized Connection connect() {
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            connection = null;
        }
        return connection;
    }

}

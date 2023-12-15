package org.example;

import org.example.DB.DBConnection;



public class App {
    public App () {
        DBConnection.DB_NAME = "projectToDo";
        DBConnection.DB_PORT = 3306;
        DBConnection.DB_USER = "root";
        DBConnection.DB_PASSWORD = "";

        Container.getDbConnection().connect();
    }

}

package org.example;

import org.example.DB.DBConnection;

public class Container {
    private static DBConnection dbConnection;

    public static DBConnection getDbConnection(){
        if(dbConnection == null){
            dbConnection = new DBConnection();
        }
        return dbConnection;
    }
}

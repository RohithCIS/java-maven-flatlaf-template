package com.vit.billing.utils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

  public Connection connection;

  public void createDefaultUser() {
    try {
      Statement statement = connection.createStatement();
      statement.execute(
          "CREATE TABLE IF NOT EXISTS USERS (id integer primary key, name text not null, password text not null);");
      statement.execute("INSERT INTO USERS (id, name, password) VALUES (1, 'admin','admin');");
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public boolean authenticate(String name, String password) {
    try {
      Statement statement = connection.createStatement();
      ResultSet rs = statement
          .executeQuery("SELECT * FROM USERS WHERE name = '" + name + "' AND password = '" + password + "'");
      if (rs.next()) {
        return true;
      } else {
        return false;
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }

  public void createNewDatabase(String fileName) {

    String url = "jdbc:sqlite:" + fileName;

    try {
      connection = DriverManager.getConnection(url);
      if (connection != null) {
        DatabaseMetaData meta = connection.getMetaData();
        System.out.println("The driver name is " + meta.getDriverName());
        System.out.println("A new database has been created.");
        createDefaultUser();
      }

    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  public Database() {
    createNewDatabase("vit.db");
  }
}

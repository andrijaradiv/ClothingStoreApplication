package clothing4you;

import com.formdev.flatlaf.FlatDarculaLaf;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static clothing4you.JDBC.createTable;
import static clothing4you.JDBC.*;


public class App {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        try {
            UIManager.setLookAndFeel(new FlatDarculaLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        createTable("users", createUserTable);
        createTable("catalog", createCatalogTable);

        Login myLogin = new Login(null);
    }
}
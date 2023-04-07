package clothing4you;

import clothing4you.backend.JDBC;
import clothing4you.ui.Login;
import com.formdev.flatlaf.FlatDarculaLaf;

import javax.swing.*;
import java.sql.SQLException;

import static clothing4you.backend.JDBC.createTable;
import static clothing4you.backend.JDBC.*;


public class App {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        try {
            UIManager.setLookAndFeel(new FlatDarculaLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }

        JDBC.establishConnection();

        createTable("users", createUserTable);
        createTable("catalog", createCatalogTable);

        Login myLogin = new Login(null);

        // Database connection is closed at the end of the program by java itself
        // JDBC.closeConnection();
    }
}
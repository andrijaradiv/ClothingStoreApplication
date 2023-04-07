package clothing4you.backend;

import clothing4you.backend.JDBC;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
// This class provides methods for registering and logging in users
public class UserManager {

    private UserManager(){}
    //using the JDBC class to communicate with a database, we create users 
    public static void register(String name, String email, String username, String password, String table){
        try {
            JDBC.insertUser(name, email, username, password.toString(), table);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public static void register(String name, String email, String username, String password){
        register(name, email, username, password, "users");
    }
    //checks if the username and password exist in the JDBC class
    public static boolean login(String username, String password, String table){
        try {
            if(JDBC.exists(username, table, "username")){
                if(JDBC.exists(password, table, "password")){
                    ArrayList name = (ArrayList) JDBC.customQuery("select first_name from " + table + " where username==\"" + username + "\";");
                    JOptionPane.showMessageDialog(null, "Welcome " + name.get(0));
                    return true;
                } else{
                    return false;
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
        return false;
    }
    public static boolean login(String username, String password){
        return login(username, password, "users");
    }
}

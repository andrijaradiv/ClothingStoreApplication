package clothing4you;

import java.sql.*;
import java.util.ArrayList;

public class JDBC {
    private static String url = "jdbc:sqlite:/sqlite/clothing4you";
    public static String createUserTable = "CREATE TABLE IF NOT EXISTS users (" +
            "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
            "first_name TEXT," +
            "email TEXT," +
            "username TEXT," +
            "password TEXT" +
            ");";

    public static String createCatalogTable = "CREATE TABLE IF NOT EXISTS catalog (" +
            "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
            "name TEXT," +
            "category TEXT," +
            "size TEXT," +
            "quantity INTEGER," +
            "price FLOAT(2)" +
            ");";

    public JDBC() throws SQLException, ClassNotFoundException {
    }

    static Connection establishConnection() throws SQLException, ClassNotFoundException {
        // Establishes connection with sqlite database

        Connection conn = null;

        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:database/clothing4you.db");

        } catch (Exception e) {
            throw e;
        }

        return conn;
    }

    public static void createTable(String table, String createTable) throws SQLException, ClassNotFoundException {
        // Creates table in sqlite database with table name input and sql create table statement

        Connection conn = establishConnection();
        Statement stmt = conn.createStatement();

        stmt.executeUpdate(createTable);

        ResultSet res = stmt.executeQuery("Select count(*) from " + table + ";");

        if (res.getInt(1) == 0){
        //if(res.get(0).equals("0")){
            if(table.equals("users")){
                insertUser(conn, "admin", "admin@gmail.com", "admin", "admin");
            } else if(table.equals("catalog")){
                insertItem(conn, "T-shirt", "Tops", "M", 1, 20.00);
                insertItem(conn, "Hoodie", "Tops", "M", 1, 25.00);
                insertItem(conn, "Jeans", "Bottoms", "M", 1, 20.00);
                insertItem(conn, "Shorts", "Bottoms", "M", 1, 25.00);
                insertItem(conn, "Beanie", "Hats", "M", 1, 7.50);
                insertItem(conn, "Hat", "Hats", "M", 1, 7.50);
            }
        }
        stmt.close();
        conn.close();
        System.out.println("Table created successfully");
    }

   public static void insertUser(Connection conn, String first_name, String email,String username, String password, String table) throws SQLException, ClassNotFoundException {
        // Inserts user into user table to allow for data query

        conn.setAutoCommit(false);
        Statement stmt = conn.createStatement();
        String sqlStatement = "INSERT INTO " + table + " (first_name,email,username,password) " +
               "VALUES ('" + first_name + "','" + email + "','" +username +"','"+ password + "');";
        stmt.executeUpdate(sqlStatement);

        conn.commit();
        conn.setAutoCommit(true);
   }
    public static void insertUser(Connection conn, String first_name, String email,String username, String password) throws SQLException, ClassNotFoundException {
        insertUser(conn, first_name, email, username, password, "users");
    }
    public static void insertItem(Connection conn, String name, String category, String size, int quantity, double price) throws SQLException, ClassNotFoundException {
        // Inserts user into user table to allow for data query

        conn.setAutoCommit(false);
        Statement stmt = conn.createStatement();
        String sqlStatement = "INSERT INTO catalog (name,category,size,quantity,price) " +
                "VALUES ('" + name + "','" + category + "','" + size + "','" +quantity +"','"+ price + "');";
        stmt.executeUpdate(sqlStatement);

        stmt.close();
        conn.commit();
        conn.setAutoCommit(true);
    }

    public static ArrayList query(String table, String column_name) throws SQLException, ClassNotFoundException {
        // Queries given table and returns array with data in column_name
        ArrayList result = new ArrayList<>();

        Connection conn = establishConnection();
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * from " + table);

        while(resultSet.next()) {
            if(table == "catalog") {
                String name = (resultSet.getString("name"));
                String category = (resultSet.getString("category"));
                String size = (resultSet.getString("size"));
                String quantity = (resultSet.getString("quantity"));
                String price = (resultSet.getString("price"));
                result.add(name + " " + category + " " + size + " " + quantity + " " + price);
            }
            else {
                String first_name = (resultSet.getString("name"));
                String email = (resultSet.getString("category"));
                String username = (resultSet.getString("size"));
                String password = (resultSet.getString("quantity"));
                result.add(first_name + " " + email + " " + username + " " + password);
            }
            //System.out.println(resultSet.getString("email"));
        }
        statement.close();
        conn.close();
        return result;
    }

    public static boolean exists(String data, String table, String column) throws SQLException, ClassNotFoundException {
        // Checks if data in column in table exists, returns true if it exists and false if it does not

        boolean exists = false;
        String sqlSearch = "SELECT " + column + " " +
                "FROM " + table + " " +
                "WHERE EXISTS " +
                "(SELECT " + column + " FROM " + table + " WHERE " + column + " = '" + data + "');";

        Connection conn = establishConnection();
        Statement stmt = conn.createStatement();
        ResultSet res = stmt.executeQuery(sqlSearch);
        exists = res.next();
        stmt.close();
        conn.close();
        return exists;
    }

    // For reference
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        /*
        createTable("users", createUserTable);
        insertUser("andrija", "a@gmail.com","andrija121", "1234");
        insertUser("willy",  "willlego@gmail.com","willo1053", "1234");
        ArrayList a = query("catalog", "username");
        System.out.println(a);
        System.out.println(exists("wow14266", "users", "username"));
         */
    }
}
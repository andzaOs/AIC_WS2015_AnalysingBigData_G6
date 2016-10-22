package tuwien.ac.at;


import java.sql.*;
 
public class DAL {
    private static final String Driver= "com.mysql.jdbc.Driver";
    private static final String DBURL= "jdbc:mysql://localhost:3306/aic";
 
    private static Connection c;
         
    public static Connection connect() throws Exception {
        try {
            Class.forName(Driver);
            c = (DriverManager.getConnection(DBURL, "root", "root"));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return c;
    }
         
    public static void disconnect() throws Exception {
        try {
            c.close();
        } catch (Exception e) {
            throw new Exception("Error");
        }
    }
}
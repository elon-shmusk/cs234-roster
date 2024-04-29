/**
 * This class is responsible for creating a new SQLite database file.
 * It checks if the database file already exists and creates a new one if it does not.
 * @author Kaleb
 */

package src.main;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class CreateSQLiteDatabase {

    /**
     * The main method that creates the SQLite database.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        Connection connection = null;
        try {
            // Load the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");
            
            // SQLite database file path
            String dbFilePath = new File ("Database").getAbsolutePath();
            
            // Check if the database file already exists
            File dbFile = new File(dbFilePath);
            if (dbFile.exists()) {
                System.out.println("Database file already exists.");
                return;
            }
            
            // Connect to the SQLite database (this will create the database file)
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbFilePath);
            
            System.out.println("SQLite database created successfully.");
            
        } catch (ClassNotFoundException e) {
            System.err.println("SQLite JDBC driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error creating SQLite database.");
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println("Error closing SQLite database connection.");
                e.printStackTrace();
            }
        }
    }
}
package src;/*
Purpose: Use GitHub and learn to fork, clone, modify, commit, push, pull request
         a project repository.
         Learn the sequential nature and necessary communication between team members
         using GitHub as a shared software repository
Author of initial code base: Greg Schaper
Authors of modified code base: <Put Team Member Names Here>

TODO: Modify this software so that your team will be added to the course with each team member's
      name being output after the team name is output.
      This will require each team member to clone a forked repository (by one of the team members)
      add their code to add their name to the team and save the changes to the shared repository.
      Team names will be Team1, Team2, Team3, Team4 ... etc. as assigned in class.
 */

 import java.sql.Connection;
 import database.Database;

 import javax.swing.*;

public class Main {

     public static void main(String[] args) {
         // Connect to the SQLite database
         try (Connection conn = Database.connect()) {
             if (conn != null) {
                 System.out.println("Connected to the database.");

                 // Creates Players table if it doesn't exist
                 Database.createTable(conn);

             } else {
                 System.out.println("Failed to connect to the database.");
             }
         } catch (Exception e) {
             System.out.println(e.getMessage());
         }
         new GUI();

         rosterTab roster = new rosterTab();
         SwingUtilities.invokeLater(() -> {
             rosterTab dashboard = new rosterTab();
             dashboard.setVisible(true);
         });
        }
     }
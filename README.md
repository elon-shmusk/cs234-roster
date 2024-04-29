# cs234-roster
#### Deni Velasquez
#### Brian Demyan
#### Fernando Peralta Castro
#### Kaleb Missmer
#### Samuel Cadiz

# Moravian University Women's Basketball Roster Management System

## Purpose

The Moravian University Women's Basketball Roster Management System is a software project aimed at facilitating the management of player rosters for the Moravian University Women's Basketball team. The project allows users to add, remove, and view player information through a graphical user interface (GUI).

## Features

- **Add Player:** Users can add new players to the roster by providing their details such as number, name, position, etc.
- **Remove Player:** Users can remove players from the roster.
- **View Roster:** The system provides a tabbed interface where users can view the roster and team statistics.
- **Edit Player:** Users can modify existing player details such as name, number, position, etc.
- **Archive Player:** Players can be archived, marking them as inactive, while still retaining their information.
- **Restore Player:** Archived players can be restored, making them active again.
- **Filter Roster:** Users can filter the roster to view only active or archived players.
- **Database Connectivity:** The system is connected to an SQLite database backend to store player information persistently.

## Create Executable File

1. **Create Jar File:** 
   - Open Intellij IDEA.
   - Go to File -> Project Structure.
   - Click on Artifacts.
   - Click on the "+" sign and select JAR -> From modules with dependencies.
   - Select the Main class and click OK.
   - Click on the Output Layout tab.
   - Click on the "+" sign and select Directory Content.
   - Select the META-INF folder and click OK.
   - Click Apply and OK.
   - Go to Build -> Build Artifacts -> Build.
   - The JAR file will be created in the out -> artifacts -> cs234_roster_jar folder.

2. **Create Executable File:**
    - Go to https://github.com/dante-biase/jar2app 
    - clone the repository
    - Create a virtual environment
    - Install the requirements
    - Run the command `python3 jar2app.py -i <path to jar file> -o <output directory>`
    - The executable file will be created in the output directory

3. **Give the Executable File a copy of the database file:**
    - Copy the directory app
    - Two finger click on the executable file and click on "Show Package Contents"
    - Go to the directory that contains the jar file
    - Paste the app directory into the same directory as the jar file
    - The executable file is now ready to be used

## How to Use

1. **Installation:** 
   - Clone or download the project repository to your local machine.
   - Ensure that you have SQLite installed and configured:
     - For Windows:
       - Download the SQLite precompiled binaries from [SQLite Download Page](https://www.sqlite.org/download.html) based on your system requirements.
       - Extract the zip file to a location on your computer.
       - Add the extracted folder to your system's PATH environment variable.
     - For macOS:
       - SQLite comes pre-installed on macOS. You can verify the installation by opening Terminal and typing `sqlite3`. If SQLite is installed, you will see the SQLite command prompt.
     - For Linux:
       - Use your package manager to install SQLite. For example, on Ubuntu, you can run `sudo apt-get install sqlite3`.
2. **Compile and Run:** 
   - Compile the Java source files using your preferred IDE or command-line compiler.
   - Run the `Main` class to start the application.
3. **Adding Players:** 
   - Click on the "Add Player" button to add new players to the roster. Enter player information in the dialog box that appears.
4. **Removing Players:** 
   - Click on the "Remove Player" button to remove players from the roster. Select the player(s) to remove from the list.
5. **Editing Players:** 
   - Double-click on a player's entry in the roster to edit their details. Make the desired changes and click "Save" to update the roster.
6. **Viewing Roster:** 
   - The roster tab displays the list of players along with their details such as number, name, position, etc.
7. **Viewing Team Stats:** 
   - Navigate to the team stats tab to view various statistics such as free throws made, free throw percentage, etc.
8. **Archiving and Restoring Players:** 
   - Use the "Archive" button to mark selected players as archived, making them inactive.
   - Archived players can be restored to active status using the "Restore" button.

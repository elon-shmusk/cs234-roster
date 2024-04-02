package src.main.controller;

import src.main.model.*;
import src.main.view.RosterTab;
import java.util.List;


/**
 * Controller class for managing the roster.
 * This class handles the operations related to adding and removing players
 * from the roster, as well as retrieving the list of all players.
 */
public class RosterController {
    private PlayerDatabase playerDatabase;
    private RosterTab rosterTab;

    /**
     * Constructs a new RosterController with the specified player database and roster tab.
     * @param playerDatabase the database of player information
     * @param rosterTab the tab component of the UI that displays the roster
     */
    public RosterController(PlayerDatabase playerDatabase, RosterTab rosterTab) {
        this.playerDatabase = playerDatabase;
        this.rosterTab = rosterTab;
    }

    /**
     * Adds a new player to the roster.
     * @param firstName the first name of the player
     * @param lastName the last name of the player
     * @param position the playing position of the player
     * @param number the jersey number of the player
     * @param year the year of the player
     */
    public void addPlayer(String firstName, String lastName, String position, int number, String year) {
        Player newPlayer = new Player(0, firstName, lastName, position, number, year);
        playerDatabase.addPlayer(newPlayer);
        rosterTab.refreshRoster();
    }

    /**
     * Removes a player from the roster based on player ID.
     * @param playerId the unique ID of the player to be removed
     */
    public void removePlayer(int playerId) {
        playerDatabase.removePlayer(playerId);
        rosterTab.refreshRoster();
    }

    /**
     * Archives a player in the roster based on player ID.
     * @param playerId the unique ID of the player to be archived
     * @author Fernando Peralta Castro
     */
    public void archivePlayer(int playerId) {
        playerDatabase.archivePlayer(playerId);
        rosterTab.refreshRoster();
    }

    /**
     * Updates the first name of a player in the roster.
     * @param playerId the unique ID of the player
     * @param firstName the new first name of the player
     */
    public void updatePlayerFirstName(int playerId, String firstName)
    {
        playerDatabase.setPlayerFirstName(playerId, firstName);
        rosterTab.refreshRoster();
    }

    /**
     * Updates the last name of a player in the roster.
     * @param playerId the unique ID of the player
     * @param lastName the new last name of the player
     */
    public void updatePlayerLastName(int playerId, String lastName)
    {
        playerDatabase.setPlayerLastName(playerId, lastName);
        rosterTab.refreshRoster();
    }

    /**
     * Updates the full name of a player in the roster.
     * @param playerId the unique ID of the player
     * @param firstName the new first name of the player
     * @param lastName the new last name of the player
     */
    public void updatePlayerFullName(int playerId, String firstName, String lastName)
    {
        playerDatabase.setPlayerFirstName(playerId, firstName);
        playerDatabase.setPlayerLastName(playerId, lastName);
        rosterTab.refreshRoster();
    }

    /**
     * Updates the playing position of a player in the roster.
     * @param playerId the unique ID of the player
     * @param position the new playing position of the player
     */
    public void updatePlayerPosition(int playerId, String position)
    {
        playerDatabase.setPlayerPosition(playerId, position);
        rosterTab.refreshRoster();
    }

    /**
     * Updates the year of a player in the roster.
     * @param playerId the unique ID of the player
     * @param year the new year of the player
     */
    public void updatePlayerYear(int playerId, String year)
    {
        playerDatabase.setPlayerYear(playerId, year);
        rosterTab.refreshRoster();
    }

    /**
     * Updates the number of a player in the roster.
     * @param playerId the unique ID of the player
     * @param number the new number of the player
     */
    public void updatePlayerNumber(int playerId, int number)
    {
        playerDatabase.setPlayerNumber(playerId, number);
        rosterTab.refreshRoster();
    }


    /**
     * Retrieves a list of all players in the roster.
     * @return a list of all players
     */

    public List<Player> getAllPlayers() {
        return playerDatabase.getAllPlayers();
    }

    /**
     * Sets the RosterTab for this controller.
     * @param rosterTab the RosterTab to set
     */



    /**
     * Adds player statistics to the database.
     * @param playerId the unique ID of the player
     * @param freeThrowsMade number of free throws made
     * @param freeThrowsPercentage percentage of free throws made
     * @param threePointsMade number of three points made
     * @param threePointsPercentage percentage of three points made
     */
    public void addPlayerStats(int playerId, int freeThrowsMade, double freeThrowsPercentage, int threePointsMade, double threePointsPercentage) {
        playerDatabase.addPlayerStats(playerId, freeThrowsMade, freeThrowsPercentage, threePointsMade, threePointsPercentage);
    }

    /**
     * Updates player statistics in the database.
     * @param playerId the unique ID of the player
     * @param freeThrowsMade number of free throws made
     * @param freeThrowsPercentage percentage of free throws made
     * @param threePointsMade number of three points made
     * @param threePointsPercentage percentage of three points made
     */
    public void updatePlayerStats(int playerId, int freeThrowsMade, double freeThrowsPercentage, int threePointsMade, double threePointsPercentage) {
        playerDatabase.updatePlayerStats(playerId, freeThrowsMade, freeThrowsPercentage, threePointsMade, threePointsPercentage);
    }

    /**
     * Retrieves the player's number of free throws made from the database.
     * @param playerId the unique ID of the player
     * @return the number of free throws made by the player
     */
    public int getFreeThrowsMade(int playerId) {
        return playerDatabase.getFreeThrowsMade(playerId);
    }

    /**
     * Retrieves the player's percentage of free throws made from the database.
     * @param playerId the unique ID of the player
     * @return the percentage of free throws made by the player
     */
    public double getFreeThrowsPercentage(int playerId) {
        return playerDatabase.getFreeThrowsPercentage(playerId);
    }

    /**
     * Retrieves the player's number of three points made from the database.
     * @param playerId the unique ID of the player
     * @return the number of three points made by the player
     */
    public int getThreePointsMade(int playerId) {
        return playerDatabase.getThreePointsMade(playerId);
    }

    /**
     * Retrieves the player's percentage of three points made from the database.
     * @param playerId the unique ID of the player
     * @return the percentage of three points made by the player
     */
    public double getThreePointsPercentage(int playerId) {
        return playerDatabase.getThreePointsPercentage(playerId);
    }

    /**
     * Sets the RosterTab for this controller.
     * @param rosterTab the RosterTab to set
     */
    public void setRosterTab(RosterTab rosterTab) {
        this.rosterTab = rosterTab;
    }
}


package src.main.controller;

import src.main.model.*;
import src.main.view.ArchiveTab;
import src.main.view.GUI;
import src.main.view.RosterTab;
import src.main.view.StatsTab;

import java.util.List;


/**
 * Controller class for managing the roster.
 * This class handles the operations related to adding and removing players
 * from the roster, as well as retrieving the list of all players.
 */
public class RosterController {
    private PlayerDatabase playerDatabase;
    private RosterTab rosterTab;
    private StatsTab statsTab;
    private ArchiveTab archiveTab;

    /**
     * Constructs a new RosterController with the specified player database and roster tab.
     * @param playerDatabase the database of player information
     */
    public RosterController(PlayerDatabase playerDatabase) {
        this.playerDatabase = playerDatabase;
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
     * Retrieves the number of a player in the roster.
     * @param playerId the unique ID of the player
     * @return the number of the player
     */
    public int getPlayerNumber(int playerId) {
        return playerDatabase.getPlayerNumber(playerId);}

    /** Retrieves the full name of a player in the roster.
     * @param playerId the unique ID of the player
     * @return the full name of the player
     */
    public String getPlayerFullName(int playerId) {
        return playerDatabase.getPlayerFullName(playerId);
    }


    /**
     * Retrieves a list of all players in the roster.
     * @return a list of all players
     */

    public List<Player> getAllPlayers() {
        return playerDatabase.getAllPlayers();
    }

    /**
     * Retrieves the number of players in the roster.
     * @return the number of players
     */
    public int getNumberOfPlayers() {
        return playerDatabase.getNumberOfPlayers();
    }


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

    public void addPracticeStats(int playerId, int freeThrowsMade, int freeThrowsAttempted, int threePointsMade, int threePointsAttempted) {
        playerDatabase.addPracticeStats(playerId, freeThrowsMade, freeThrowsAttempted, threePointsMade, threePointsAttempted);
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
        statsTab.refreshStats();
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
     * Retrieves the player's number of free throws attempted from the database.
     * @param playerId the unique ID of the player
     * @return the number of free throws attempted by the player
     */
    public int getFreeThrowsAttempted(int playerId) {
        return playerDatabase.getFreeThrowsAttempted(playerId);
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
     * Retrieves the player's number of three points attempted from the database.
     * @param playerId the unique ID of the player
     * @return the number of three points attempted by the player
     */
    public int getThreePointsAttempted(int playerId) {
        return playerDatabase.getThreePointsAttempted(playerId);
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
     * Sets the free throws made by the player in the database.
     * @param playerId the unique ID of the player
     * @param freeThrowsMade the number of free throws made by the player
     * @author Samuel Cadiz
     */
    public void setFreeThrowsMade(int playerId, int freeThrowsMade) {
        playerDatabase.setFreeThrowsMade(playerId, freeThrowsMade);
        statsTab.refreshStats();
    }

    /**
     * Sets the free throws percentage of the player in the database.
     * @param playerId the unique ID of the player
     * @param freeThrowsPercentage the free throws percentage of the player
     * @author Samuel Cadiz
     */
    public void setFreeThrowsPercentage(int playerId, double freeThrowsPercentage) {
        playerDatabase.setFreeThrowsPercentage(playerId, freeThrowsPercentage);
        statsTab.refreshStats();
    }

    /**
     * Sets the number of three points made by the player in the database.
     * @param playerId the unique ID of the player
     * @param threePointsMade the number of three points made by the player
     * @author Samuel Cadiz
     */
    public void setThreePointsMade(int playerId, int threePointsMade) {
        playerDatabase.setThreePointsMade(playerId, threePointsMade);
        statsTab.refreshStats();
    }

    /**
     * Sets the number of three points made by the player in the database.
     * @param playerId the unique ID of the player
     * @param threePointsPercentage the number of three points made by the player
     * @author Samuel Cadiz
     */
    public void setThreePointsPercentage(int playerId, double threePointsPercentage) {
        playerDatabase.setThreePointsPercentage(playerId, threePointsPercentage);
        statsTab.refreshStats();
    }

    /**
     * Archives a player in the database.
     * @param playerId the unique ID of the player to archive
     */
    public boolean isPlayerArchived(int playerId) {
        return playerDatabase.isArchived(playerId);
    }

    /**
     * Unarchives a player in the database.
     * @param playerId the unique ID of the player to unarchive
     */
    public void unarchivePlayer(int playerId) {
        playerDatabase.unarchivePlayer(playerId);
        archiveTab.refreshArchive();
    }

    /**
     * Gets the player ID of an archived player.
     * @param firstName the first name of the player
     * @param lastName the last name of the player
     * @return the player ID of the archived player
     */
    public int getArchivedPlayerId(String firstName, String lastName) {
        return playerDatabase.getArchivedPlayerId(firstName, lastName);
    }


    /**
     * Sets the RosterTab for this controller.
     * @param rosterTab the RosterTab to set
     */
    public void setRosterTab(RosterTab rosterTab) {
        this.rosterTab = rosterTab;
    }

    /**
     * Sets the StatsTab for this controller.
     * @param statsTab the StatsTab to set
     * @author Samuel Cadiz
     */
    public void setStatsTab(StatsTab statsTab) {
        this.statsTab = statsTab;
    }

    /**
     * Sets the ArchiveTab for this controller.
     * @param archiveTab the ArchiveTab to set
     */
    public void setArchiveTab(ArchiveTab archiveTab) {
        this.archiveTab = archiveTab;
    }
}


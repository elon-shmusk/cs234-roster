package src.main.controller;

import src.main.model.*;
import src.main.view.RosterTab;
import java.util.List;

public class RosterController {
    private PlayerDatabase playerDatabase;
    private RosterTab rosterTab;

    public RosterController(PlayerDatabase playerDatabase, RosterTab rosterTab) {
        this.playerDatabase = playerDatabase;
        this.rosterTab = rosterTab;
    }

    public void addPlayer(String firstName, String lastName, String position, int number) {
        Player newPlayer = new Player(0, firstName, lastName, position, number);
        playerDatabase.addPlayer(newPlayer);
        rosterTab.refreshRoster();
    }

    public void removePlayer(int playerId) {
        playerDatabase.removePlayer(playerId);
        rosterTab.refreshRoster();
    }

    public void updatePlayerFirstName(int playerId, String firstName)
    {
        playerDatabase.setPlayerFirstName(playerId, firstName);
        rosterTab.refreshRoster();
    }

    public void updatePlayerLastName(int playerId, String lastName)
    {
        playerDatabase.setPlayerLastName(playerId, lastName);
        rosterTab.refreshRoster();
    }

    public void updatePlayerFullName(int playerId, String firstName, String lastName)
    {
        playerDatabase.setPlayerFirstName(playerId, firstName);
        playerDatabase.setPlayerLastName(playerId, lastName);
        rosterTab.refreshRoster();
    }

    public void updatePlayerPosition(int playerId, String position)
    {
        playerDatabase.setPlayerPosition(playerId, position);
        rosterTab.refreshRoster();
    }

    public void updatePlayerNumber(int playerId, int number)
    {
        playerDatabase.setPlayerNumber(playerId, number);
        rosterTab.refreshRoster();
    }

    public List<Player> getAllPlayers() {
        return playerDatabase.getAllPlayers();
    }

    public void setRosterTab(RosterTab rosterTab) {
        this.rosterTab = rosterTab;
    }

}

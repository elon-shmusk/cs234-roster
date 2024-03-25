package src.main;

/**
 * Represents a player with an ID and a name.
 */
public class Player {
    private int id;
    private String name;

    /**
     * Constructs a new Player with the given ID and name.
     * @param id The unique identifier for the player.
     * @param name The name of the player.
     */
    public Player(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Retrieves the player's ID.
     * @return The unique identifier of the player.
     */
    public int getId() {
        return id;
    }

    /**
     * Retrieves the player's name.
     * @return The name of the player.
     */
    public String getName() {
        return name;
    }
}

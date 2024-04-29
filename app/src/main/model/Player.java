/* Player.java
 * This class represents a player on a basketball team.
 * It contains the player's first name, last name, position, number, and year.
 * It also contains the player's free throws made and attempted, and three pointers made and attempted.
 * This class is used to create player objects that are used in the PlayerAdapter class.
 * This class is used in the PlayerAdapter class to create a list of players that are displayed in the PlayerFragment

 */

package src.main.model;

public class Player {
    private int id;
    private String firstName;
    private String lastName;
    private String position;
    private int number;
    private String year;

    private int freeThrowsMade;

    private int freeThrowsAttempted;

    private int threePointersMade;

    private int threePointersAttempted;


    /**
     * Constructor for creating a player with basic details.
     *
     * @param id        the unique ID of the player
     * @param firstName the first name of the player
     * @param lastName  the last name of the player
     * @param position  the position of the player
     * @param number    the number of the player
     * @param year      the year of the player
     */
    public Player(int id, String firstName, String lastName, String position, int number, String year) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.number = number;
        this.year = year;
    }

    /**
     * Constructor for creating a player with free throws details.
     *
     * @param id                  the unique ID of the player
     * @param freeThrowsMade      the number of free throws made by the player
     * @param freeThrowsAttempted the number of free throws attempted by the player
     */
    public Player(int id, int freeThrowsMade, int freeThrowsAttempted) {
        this.id = id;
        this.freeThrowsMade = freeThrowsMade;
        this.freeThrowsAttempted = freeThrowsAttempted;

    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPosition() {
        return position;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    public String getYear() {
        return year;
    }


    public int getFreeThrowsMade() {
        return freeThrowsMade;
    }

    public int getFreeThrowsAttempted() {
        return freeThrowsAttempted;
    }

    public int getThreePointersMade() {
        return threePointersMade;
    }

    public int getThreePointersAttempted() {
        return threePointersAttempted;
    }

}

package src.main.model;

public class Player {
    private int id;
    private String firstName;
    private String lastName;
    private String position;
    private int number;
    private String year;

    public Player(int id, String firstName, String lastName, String position, int number, String year) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.number = number;
        this.year = year;
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
}

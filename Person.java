public class Person {

    // Two private variables for storing user's first and last names
    private String firstName;

    private String lastName;

    // Getter for first name
    public String getfirstName() {

        return firstName;
    }

    // Setter for first name
    public void setfirstName(String firstName) {

        this.firstName = firstName;
    }

    // Getter for last name
    public String getlastName() {


        return lastName;
    }

    // Setter for last name
    public void setlastName(String lastName) {

        this.lastName = lastName;
    }

    // Constructor that takes only the first name and sets the last name to a blank string if the user does not enter a last name
    public Person(String firstName) {

        this.firstName = firstName;

        this.lastName = "";
    }

    // Constructor that takes both first name and last name
    public Person(String firstName, String lastName) {

        this.firstName = firstName;

        this.lastName = lastName;
    }
}
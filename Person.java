public class Person {

    // Two private variables for storing user's first and last names
    private String personsFirstname;

    private String personsLastname;

   // Getter for first name
    public String getpersonsFirstname() {

        return personsFirstname;
    }

    // Setter for first name
    public void setpersonsFirstname(String personsFirstname) {

        this.personsFirstname = personsFirstname;
    }

    // Getter for last name
    public String getpersonsLastname() {

        return personsLastname;
    }

    // Setter for last name
    public void setpersonsLastname(String personsLastname) {

        this.personsLastname = personsLastname;
    }

    // Constructor that takes only the first name and sets the last name to a blank string if the user does not enter a last name
    public Person(String personsFirstname) {

        this.personsFirstname = personsFirstname;

        this.personsLastname = "";
    }

    // Constructor that takes both first name and last name
    public Person(String personsFirstname, String personsLastname) {

        this.personsFirstname = personsFirstname;

        this.personsLastname = personsLastname;
    }
}






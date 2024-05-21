public class Person {

    // Two private String variables for storing person's first and last name.
    private String personsFirstname;

    private String personsLastname;

    // Two public getters and two public setters for person's first and last names.
    // Public String getpersonsFirstname() is a getter for the first name, which returns the value of personsFirstname.
    public String getpersonsFirstname() {

        return personsFirstname;
    }

    // Public void setpersonsFirstname(String personsFirstname) is a setter for the personsFirstname.
    public void setpersonsFirstname(String personsFirstname) {

        this.personsFirstname = personsFirstname;
    }

    // Public String getpersonsLastname() is a getter for the last name, which returns the value of personsLastname.
    public String getpersonsLastname() {

        return personsLastname;
    }

    // Public void setpersonsLastname(String personsLastname) is a setter for the personsLastname.
    public void setpersonsLastname(String personsLastname) {

        this.personsLastname = personsLastname;
    }

    // Two overloaded constructors
    // Public Person(String firstName) is a constructor that takes personsFirstname and sets it to a valve of personsFirstname,
    // while also setting the personsLastname field to a blank ("") if the last name is blank.
    public Person(String personsFirstname) {

        this.personsFirstname = personsFirstname;

        this.personsLastname = "";
    }

    // Public Person(String firstName) is a constructor that takes two Strings personsFirstname and personsLastname and sets them to
    // personsFirstname and personsLastname.
    public Person(String personsFirstname, String personsLastname) {

        this.personsFirstname = personsFirstname;

        this.personsLastname = personsLastname;
    }
}






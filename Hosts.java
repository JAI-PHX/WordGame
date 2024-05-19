// Child class that extends Person.
public class Hosts extends Person {

// Two constructors that use first and last name (last name optional) which then calls a
// superclass to set the users first and last name (last name optional).
    public Hosts(String personsfirstname) {

        super(personsfirstname);
    }

    public Hosts(String personsfirstname, String personslastname) {

        super(personsfirstname, personslastname);
    }

    // Generates a random number and stores it.
    public void randomizeNum() {

        Numbers numbers = new Numbers();

        numbers.generateNumber();
    }
}

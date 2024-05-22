public class Hosts extends Person {

// Two constructors that use first and last name (last name optional) which then calls a
// superclass to set the users first and last name (last name optional).
    public Hosts(String personsfirstname) {

        // Calls a superclass with users-first name.
        super(personsfirstname);
    }
// Same operation as code up above but for users who use their first and last name
    public Hosts(String personsfirstname, String personslastname) {

        super(personsfirstname, personslastname);
    }

    // Generates a random number and stores it.
    public void randomizeNum() {

        // Calls the static generateNumber() from the Numbers class.
        Numbers.generateNumber();
    }
}

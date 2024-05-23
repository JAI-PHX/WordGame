public class Hosts extends Person {

// Two constructors that use first and last name (last name optional) which then calls a
// superclass to set the users first and last name (last name optional).
    public Hosts(String firstName) {

        // Calls a superclass with users-first name.
        super(firstName);
    }
// Same operation as the code up above but for users who use their first and last name
    public Hosts(String firstName, String lastName) {

        super(firstName, lastName);
    }

    // Generates a random number and stores it.
    public void randomizeNum() {

        // Calls the static generateNumber() from the Numbers class.
        Numbers.generateNumber();
    }
}

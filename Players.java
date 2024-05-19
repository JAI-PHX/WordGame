// Child class that extends Person.
public class Players extends Person {

    // private instance variable that stores users' money.
    private int money;

    // Public getter and setter for the amount of money.
    // Getter.
    public int getMoney() {

        return money;
    }

    // Setter.
    public void setMoney(int money) {

        this.money = money;
    }

    // Constructors that initialize the money field to 1000
    // Constructor used for user who use only their first name.
    public Players(String personsFirstname) {

        super(personsFirstname);

        this.money = 1000;
    }

    // Constructor used for user who uses their first and last name.
    public Players(String personsFirstname, String personsLastname) {

        super(personsFirstname, personsLastname);

        this.money = 1000;
    }

    // Override the toString method to display player first and last name (last name optional) with their current amount of money.
    @Override

    public String toString() {

        return getpersonsFirstname() + " " + getpersonsLastname() + ": $" + money + ".00";
    }
}

public class Players extends Person {

    private int money;

    // Public getter and setter for the amount of money.
    public int getMoney() {

        return money;
    }
    public void setMoney(int money) {

        this.money = money;
    }

    // Constructors initializing the money field to 1000.
    public Players(String personsFirstname) {

        super(personsFirstname);

        this.money = 1000;
    }

    // Same operation as code up top but for user who imputed their first and last name
    public Players(String personsFirstname, String personsLastname) {

        super(personsFirstname, personsLastname);

        this.money = 1000;
    }

    // Override the toString method to display player first and last name (last name optional) with their current amount of money.
    @Override

    public String toString() {

        return getfirstName() + " " + getlastName() + ": $" + money + ".00";
    }
}

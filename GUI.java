import javax.swing.*;

import java.util.ArrayList;

import java.util.List;

public class GUI extends JFrame {

    // Player array
    private List<Players> players = new ArrayList<>();

    private Hosts host;

    // Display players through a label
    private JLabel playerInput;

    //Display host through a label
    private JLabel hostInput;

    // Display the phase though a label
    private JLabel phaseinput;

    // Indicator if the game started
    private boolean gameStarted = false;

    private int currentPlayerIndex = 0;

    public GUI() {

        // Set size of the GUI interface
        setSize(400, 300);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Label to display player, host, and phrase
        playerInput = new JLabel("Current Players: " + getPlayerNames());

        add(playerInput);

        JButton addPlayerButton = new JButton("Add Player");

        addPlayerButton.addActionListener(e -> addPlayer());

        add(addPlayerButton);

        hostInput = new JLabel("Host: ");

        add(hostInput);

        JButton addHostButton = new JButton("Add Host");

        addHostButton.addActionListener(e -> addHost());

        add(addHostButton);

        phaseinput = new JLabel("Phrase: _____");

        add(phaseinput);

        JButton startTurnButton = new JButton("Start Turns");

        startTurnButton.addActionListener(e -> startTurns());

        add(startTurnButton);

        setVisible(true);
    }

    // Add players
    private void addPlayer() {
        String firstName = JOptionPane.showInputDialog("Enter your first name:");

        if (firstName != null && !firstName.trim().isEmpty()) {

            String lastName = JOptionPane.showInputDialog("Enter your last name (optional):");

            Players player;

            if (lastName == null || lastName.trim().isEmpty()) {

                player = new Players(firstName);

            } else {

                player = new Players(firstName, lastName);

            }
            //Player storage
            players.add(player);

            updateplayerInput();
        }
    }

    // Add host
    private void addHost() {

        String hostName = JOptionPane.showInputDialog("Enter your host name:");

        if (hostName != null && !hostName.trim().isEmpty()) {

            host = new Hosts(hostName);

            String gamePhrase = JOptionPane.showInputDialog("Enter the game phrase:");

            new Phrases(gamePhrase);

            //Update label
            hostInput.setText("Host: " + host.getfirstName());

            updatephaseinput();
        }
    }

    // Update player label
    private void updateplayerInput() {

        playerInput.setText("Players: " + getPlayerNames());
    }

    // Display all player names
    private String getPlayerNames() {

        StringBuilder names = new StringBuilder();

        for (Players player : players) {

            if (names.length() > 0) {

                names.append(", ");
            }
            names.append(player.getfirstName()).append(" ").append(player.getlastName());
        }
        return names.toString();
    }

    // Update phrase
    private void updatephaseinput() {

        phaseinput.setText("Phrase: " + Phrases.getplayingPhrase());
    }

    private void startTurns() {

        if (players.isEmpty() || host == null) {

            JOptionPane.showMessageDialog(this, "Add players and a host.");
            return;
        }
        gameStarted = true;
        takeTurn();
    }

    // Player turn rotation/loop
    private void takeTurn() {

        if (!gameStarted) {
            return;
        }
        Players currentPlayer = players.get(currentPlayerIndex);

        String playerGuess = JOptionPane.showInputDialog(currentPlayer.getfirstName() + ", enter your guess:");

        if (playerGuess != null && !playerGuess.trim().isEmpty()) {

            try {
                Phrases.findLetters(playerGuess);

                updatephaseinput();

                if (Phrases.gamePhrase.contains(playerGuess)) {

                    boolean isMoneyPrize = new java.util.Random().nextBoolean();

                    Award award = isMoneyPrize ? new Money() : new Physical();

                    int winnings = award.displayWinnings(currentPlayer, true);

                    if (isMoneyPrize) {

                        currentPlayer.setMoney(currentPlayer.getMoney() + winnings);

                        JOptionPane.showMessageDialog(this, currentPlayer + " guessed correctly and won: $" + winnings +
                                "! The phrase is: " + Phrases.getplayingPhrase());

                    } else {

                        JOptionPane.showMessageDialog(this, currentPlayer + " guessed correctly and won: " +
                                currentPlayer.getPrize() + "! The phrase is: " + Phrases.getplayingPhrase());
                    }
                } else {

                    JOptionPane.showMessageDialog(this, currentPlayer.getfirstName() + " guessed incorrectly.");
                }
                if (!Phrases.getplayingPhrase().contains("_")) {

                    JOptionPane.showMessageDialog(this, currentPlayer + " solved the puzzle and won the game!");

                    gameStarted = false;

                    int response = JOptionPane.showConfirmDialog(this, "Play another game?", "Game Over", JOptionPane.YES_NO_OPTION);

                    if (response == JOptionPane.YES_OPTION) {

                        resetGameState();

                    } else {
                        System.exit(0);
                    }
                } else {

                    currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
                    takeTurn();
                }
            } catch (MultipleLettersException e) {

                JOptionPane.showMessageDialog(this, e.getMessage());
                takeTurn();
            }
        }
    }

    // Reset game
    private void resetGameState() {

        phaseinput.setText("Playing Phrase: _____");

        currentPlayerIndex = 0;

        String gamePhrase = JOptionPane.showInputDialog("Enter game phrase:");

        if (gamePhrase != null && !gamePhrase.trim().isEmpty()) {

            new Phrases(gamePhrase);

            updatephaseinput();
        }
        gameStarted = true;

        takeTurn();
    }

    //Main method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(GUI::new);
    }
}




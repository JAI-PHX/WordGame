import javax.swing.*;

import java.awt.*;

import java.util.ArrayList;

import java.util.List;

public class GUI extends JFrame {

    // Player array
    private List<Players> players = new ArrayList<>();

    private Hosts host;

    //Display host through a label
    private JLabel playerInput;

    //Display host through a label
    private JLabel hostInput;

    // Display the phase though a label
    private JLabel phaseInput;

    private JTextArea messageArea;

    private JCheckBox saveMessagesCheckbox;

    // Indicator if the game started
    private boolean gameStarted = false;

    private int currentPlayerIndex = 0;

    // Set size of the GUI interface
    public GUI() {

        setSize(400, 300);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        // Setup menu bar
        JMenuBar menuBar = createMenuBar();

        setJMenuBar(menuBar);

        // Setup main panel
        JPanel mainPanel = new JPanel();

        mainPanel.setLayout(new BorderLayout());

        // Player and host panel
        JPanel infoPanel = new JPanel();

        infoPanel.setLayout(new GridLayout(3, 1));

        playerInput = new JLabel("Current Players: " + getPlayerNames());

        hostInput = new JLabel("Host: ");

        phaseInput = new JLabel("Phrase: _____");

        infoPanel.add(playerInput);

        infoPanel.add(hostInput);

        infoPanel.add(phaseInput);

        mainPanel.add(infoPanel, BorderLayout.NORTH);

        // Control panel on the top of the GUI
        JPanel controlPanel = new JPanel();

        controlPanel.setLayout(new FlowLayout());

        // Start button
        JButton startTurnButton = new JButton("Start");

        startTurnButton.addActionListener(e -> startTurns());

        controlPanel.add(startTurnButton);

        // Check box to save the messages
        saveMessagesCheckbox = new JCheckBox("Save Messages");

        saveMessagesCheckbox.setToolTipText("Click the Save box to save message, leave blank to not save messages.");

        controlPanel.add(saveMessagesCheckbox);

        mainPanel.add(controlPanel, BorderLayout.SOUTH);

        // Output
        messageArea = new JTextArea(15, 70);

        messageArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(messageArea);

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        add(mainPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    // Menu bar
    private JMenuBar createMenuBar() {

        JMenuBar menuBar = new JMenuBar();

        // Game menu
        JMenu gameMenu = new JMenu("Game");

        gameMenu.setMnemonic('G');

        JMenuItem addPlayerMenuItem = new JMenuItem("Add Player");

        addPlayerMenuItem.addActionListener(e -> addPlayer());

        gameMenu.add(addPlayerMenuItem);

        JMenuItem addHostMenuItem = new JMenuItem("Add Host");

        addHostMenuItem.addActionListener(e -> addHost());

        gameMenu.add(addHostMenuItem);

        menuBar.add(gameMenu);

        // About section of the GUi
        JMenu aboutMenu = new JMenu("About");

        aboutMenu.setMnemonic('A');

        JMenuItem layoutMenuItem = new JMenuItem("Layout");

        layoutMenuItem.addActionListener(e -> showLayoutInfo());

        aboutMenu.add(layoutMenuItem);

        menuBar.add(aboutMenu);

        return menuBar;
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
            players.add(player);

            updatePlayerInput();
        }
    }

    // Add host
    private void addHost() {

        String firstName = JOptionPane.showInputDialog("Enter your host's first name:");

        if (firstName != null && !firstName.trim().isEmpty()) {

            String lastName = JOptionPane.showInputDialog("Enter your host's last name (optional):");

            if (lastName == null || lastName.trim().isEmpty()) {

                host = new Hosts(firstName);

            } else {

                host = new Hosts(firstName, lastName);
            }

            String gamePhrase = JOptionPane.showInputDialog("Enter the game phrase:");

            new Phrases(gamePhrase);

            hostInput.setText("Host: " + host.getfirstName() + " " + host.getlastName());

            updatePhaseInput();
        }
    }

    // Update user input
    private void updatePlayerInput() {

        playerInput.setText("Players: " + getPlayerNames());
    }

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

    // Update the phase
    private void updatePhaseInput() {
        phaseInput.setText("Phrase: " + Phrases.getplayingPhrase());
    }

    // Start turn
    private void startTurns() {

        if (players.isEmpty() || host == null) {

            showMessage("Add players and a host.");

            return;
        }
        gameStarted = true;

        takeTurn();
    }

    // Rotate players who enter their guess
    private void takeTurn() {

        if (!gameStarted) {
            return;
        }
        Players currentPlayer = players.get(currentPlayerIndex);

        String playerGuess = JOptionPane.showInputDialog(currentPlayer.getfirstName() + ", enter your guess:");

        if (playerGuess != null && !playerGuess.trim().isEmpty()) {

            try {
                Phrases.findLetters(playerGuess);

                updatePhaseInput();

                if (Phrases.getplayingPhrase().contains(playerGuess)) {

                    boolean isMoneyPrize = new java.util.Random().nextBoolean();

                    Award award = isMoneyPrize ? new Money() : new Physical();

                    int winnings = award.displayWinnings(currentPlayer, true);

                    if (isMoneyPrize) {

                        currentPlayer.setMoney(currentPlayer.getMoney() + winnings);

                        showMessage(currentPlayer + " guessed correctly and won: $" + winnings +
                                "! The phrase is: " + Phrases.getplayingPhrase());
                    } else {

                        showMessage(currentPlayer + " guessed correctly and won: " +
                                currentPlayer.getPrize() + "! The phrase is: " + Phrases.getplayingPhrase());
                    }
                } else {

                    showMessage(currentPlayer.getfirstName() + " guessed incorrectly.");
                }

                if (!Phrases.getplayingPhrase().contains("_")) {

                    showMessage(currentPlayer + " solved the puzzle and won the game!");

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

                showMessage(e.getMessage());

                takeTurn();
            }
        }
    }

    private void showMessage(String message) {

        if (saveMessagesCheckbox.isSelected()) {

            messageArea.append(message + "\n");

        } else {
            messageArea.setText(message);
        }
    }

    // Reset game
    private void resetGameState() {

        phaseInput.setText("Playing Phrase: _____");

        currentPlayerIndex = 0;

        String gamePhrase = JOptionPane.showInputDialog("Enter game phrase:");

        if (gamePhrase != null && !gamePhrase.trim().isEmpty()) {

            new Phrases(gamePhrase);

            updatePhaseInput();
        }
        gameStarted = true;
        takeTurn();
    }

    private void showLayoutInfo() {

        JOptionPane.showMessageDialog(this, "I chosen layout for better organization and user experience since the layout is simple and straightforward.");
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(GUI::new);
    }
}

import javax.sound.sampled.*;

import javax.swing.*;

import java.awt.*;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.io.File;

import java.io.IOException;

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

        // Setup player and host panel
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

        saveMessagesCheckbox.setToolTipText("Check to save messages.");

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

        JMenuItem attributionMenuItem = new JMenuItem("Attribution");

        attributionMenuItem.addActionListener(e -> showAttributionInfo());

        aboutMenu.add(attributionMenuItem);

        menuBar.add(aboutMenu);

        return menuBar;
    }

    // Add players
    private void addPlayer() {

        String firstName = JOptionPane.showInputDialog("Enter player's first name:");

        if (firstName != null && !firstName.trim().isEmpty()) {

            String lastName = JOptionPane.showInputDialog("Enter player's last name (optional):");

            Players player = (lastName == null || lastName.trim().isEmpty()) ?

                    new Players(firstName) : new Players(firstName, lastName);

            players.add(player);

            updatePlayerInput();
        }
    }

    // Add host
    private void addHost() {

        String firstName = JOptionPane.showInputDialog("Enter host's first name:");

        if (firstName != null && !firstName.trim().isEmpty()) {

            String lastName = JOptionPane.showInputDialog("Enter host's last name (optional):");

            host = (lastName == null || lastName.trim().isEmpty()) ?

                    new Hosts(firstName) : new Hosts(firstName, lastName);

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
            names.append(player.getfirstName());

            if (player.getlastName() != null && !player.getlastName().isEmpty()) {

                names.append(" ").append(player.getlastName());
            }
        }
        return names.toString();
    }

    // Update the phase
    private void updatePhaseInput() {

        phaseInput.setText("Phrase: " + Phrases.getplayingPhrase());
    }

    private void startTurns() {

        // Start turn
        if (players.isEmpty() || host == null) {

            showMessage("Add players and a host.");

            return;
        }
        takeTurn(0);
    }
    // Rotate players who enter their guess
    private void takeTurn(int currentPlayerIndex) {

        // Take a player's turn
        Players currentPlayer = players.get(currentPlayerIndex);

        String playerGuess = JOptionPane.showInputDialog(currentPlayer.getfirstName() + " " + currentPlayer.getlastName() + ", enter your guess:");

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

                        showMessage(currentPlayer.getfirstName() + " " + currentPlayer.getlastName() + " guessed correctly and won: $" + winnings +

                                "! The phrase is: " + Phrases.getplayingPhrase());

                        displayImage("money");

                    } else {

                        String prizeName = currentPlayer.getPrize();

                        showMessage(currentPlayer.getfirstName() + " " + currentPlayer.getlastName() + " guessed correctly and won: " +

                                prizeName + "! The phrase is: " + Phrases.getplayingPhrase());

                        displayImage(prizeName);
                    }
                } else {

                    showMessage(currentPlayer.getfirstName() + " " + currentPlayer.getlastName() + " guessed incorrectly.");

                    playFailureSound();

                    displayBouncingGif();
                }
                if (!Phrases.getplayingPhrase().contains("_")) {

                    showMessage(currentPlayer.getfirstName() + " " + currentPlayer.getlastName() + " solved the puzzle and won the game!");

                    int response = JOptionPane.showConfirmDialog(this, "Play another game?", "Game Over", JOptionPane.YES_NO_OPTION);

                    if (response == JOptionPane.YES_OPTION) {

                        resetGameState();

                    } else {

                        System.exit(0);
                    }
                } else {

                    currentPlayerIndex = (currentPlayerIndex + 1) % players.size();

                    takeTurn(currentPlayerIndex);
                }
            } catch (MultipleLettersException e) {

                showMessage(e.getMessage());

                takeTurn(currentPlayerIndex);
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

        phaseInput.setText("Phrase: _____");

        String gamePhrase = JOptionPane.showInputDialog("Enter game phrase:");

        if (gamePhrase != null && !gamePhrase.trim().isEmpty()) {

            new Phrases(gamePhrase);

            updatePhaseInput();
        }
        takeTurn(0);
    }

    private void showLayoutInfo() {

        JOptionPane.showMessageDialog(this, "I chosen layout for better organization and user experience since the layout is simple and straightforward.");
    }

    private void showAttributionInfo() {

        JOptionPane.showMessageDialog(this, "Images I chose for my game.");
    }


    //PNG files of the physical rewards and losses
    private void displayImage(String prizeName) {

        String imagePath = null;

        try {

            switch (prizeName) {

                case "Ring Alarm System":

                    imagePath = "Screenshot 2024-07-07 193034.png";

                    break;

                case "painting":

                    imagePath = "Screenshot 2024-07-07 193055.png";

                    break;

                case "TV":

                    imagePath = "Screenshot 2024-07-07 193110.png";

                    break;

                case "vacation to Hawaii":

                    imagePath = "Screenshot 2024-07-07 193128.png";

                    break;

                case "new car":

                    imagePath = "Screenshot 2024-07-07 193134.png";

                    break;

                case "money":

                    imagePath = "Screenshot 2024-07-07 195637.png";

                    break;

            }
            if (imagePath != null) {

                ImageIcon icon = new ImageIcon(imagePath);

                JOptionPane.showMessageDialog(this, "", "Prize Won!", JOptionPane.INFORMATION_MESSAGE, icon);

            }
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    //Play sound when a player guesses wrong
    private void playFailureSound() {

        try {

            File soundFile = new File("412427__florianreichelt__fail-sound-effect-accoustic-guitar.wav");

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);

            AudioFormat baseFormat = audioStream.getFormat();

            AudioFormat decodedFormat = new AudioFormat(

                    AudioFormat.Encoding.PCM_SIGNED,

                    baseFormat.getSampleRate(),

                    16,

                    baseFormat.getChannels(),

                    baseFormat.getChannels() * 2,

                    baseFormat.getSampleRate(),

                    false);

            AudioInputStream decodedAudioStream = AudioSystem.getAudioInputStream(decodedFormat, audioStream);

            Clip clip = AudioSystem.getClip();

            clip.open(decodedAudioStream);

            clip.start();

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {

            e.printStackTrace();
        }
    }

    //Display a gif that is animated to bounce from corner to corner of the screen
    private void displayBouncingGif() {

        ImageIcon gifIcon = new ImageIcon("giphy-ezgif.com-resize.gif");

        JLabel gifLabel = new JLabel(gifIcon);

        // Create a box to display the GIF
        JDialog dialog = new JDialog(this, "Failure Animation", true);

        dialog.setLayout(null);

        dialog.setSize(800, 600);

        dialog.add(gifLabel);

        gifLabel.setBounds(0, 0, gifIcon.getIconWidth(), gifIcon.getIconHeight());

        //Animation timer
        Timer animationTimer = new Timer(10, new ActionListener() {

            int x = 0, y = 0;

            int xSpeed = 2, ySpeed = 2;

            @Override

            public void actionPerformed(ActionEvent e) {

                x += xSpeed;

                y += ySpeed;

                if (x + gifIcon.getIconWidth() > dialog.getWidth() || x < 0) {

                    xSpeed = -xSpeed;
                }
                if (y + gifIcon.getIconHeight() > dialog.getHeight() || y < 0) {

                    ySpeed = -ySpeed;
                }
                gifLabel.setBounds(x, y, gifIcon.getIconWidth(), gifIcon.getIconHeight());
            }
        });
        animationTimer.start();

        // Timer to close animation in 5 seconds
        Timer closeTimer = new Timer(5000, e -> {

            dialog.dispose();

            animationTimer.stop();

        });
        closeTimer.setRepeats(false);

        closeTimer.start();


        dialog.setVisible(true);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(GUI::new);
    }
}

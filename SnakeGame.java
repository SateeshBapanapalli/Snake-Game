import javax.swing.*;
import java.awt.*;

public class SnakeGame extends JFrame {
    private GamePanel gamePanel;
    private JPanel instructionPanel;
    private JPanel gameContainer;
    private JButton startButton;
    private JButton resetButton;
    private JPanel scorePanel;
    private JLabel scoreLabel;
    private JLabel levelLabel;
    private CardLayout cardLayout;

    public SnakeGame() {
        cardLayout = new CardLayout();
        setLayout(cardLayout);
        
        // Instructions Panel
        instructionPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel instructionsLabel = new JLabel("<html><h2>Game Instructions</h2><br/>" +
                "- Use arrow keys to move the snake<br/>" +
                "- Eat apples to grow and score points<br/>" +
                "- Avoid hitting walls and yourself<br/>" +
                "- Complete level goals to advance</html>");
        startButton = new JButton("Start Game");
        
        instructionPanel.add(instructionsLabel, gbc);
        instructionPanel.add(startButton, gbc);

        // Game Container
        gameContainer = new JPanel(new BorderLayout());
        
        // Score Panel
        scorePanel = new JPanel(new FlowLayout());
        scoreLabel = new JLabel("Score: 0");
        levelLabel = new JLabel("Level: 1");
        scorePanel.add(scoreLabel);
        scorePanel.add(levelLabel);
        gameContainer.add(scorePanel, BorderLayout.NORTH);

        // Reset Button
        resetButton = new JButton("Play Again");
        resetButton.setVisible(false);

        // Game Panel
        gamePanel = new GamePanel(scoreLabel, levelLabel, resetButton);
        gameContainer.add(gamePanel, BorderLayout.CENTER);

        // Add both panels to frame
        add(instructionPanel, "instructions");
        add(gameContainer, "game");
        
        // Show instructions first
        cardLayout.show(getContentPane(), "instructions");

        startButton.addActionListener(e -> {
            cardLayout.show(getContentPane(), "game");
            gamePanel.startGame();
            gamePanel.requestFocusInWindow();
        });

        resetButton.addActionListener(e -> {
            gamePanel.resetGame();
            resetButton.setVisible(false);
            gamePanel.requestFocusInWindow();
        });

        this.setTitle("Snake Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(600, 700);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new SnakeGame();
    }
}
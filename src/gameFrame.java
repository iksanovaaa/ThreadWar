import javax.swing.*;

public class gameFrame extends JFrame {
    gameBoard gamePanel;

    gameFrame(){
        gamePanel = new gameBoard();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(gamePanel);
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setVisible(true);
    }
}
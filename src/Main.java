import javax.swing.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static AtomicInteger hit = new AtomicInteger(0);
    public static AtomicInteger miss = new AtomicInteger(0);
    private static gameFrame frame;

    public static void main(String[] args) {
        frame = new gameFrame();
        Score();
    }

    public static synchronized void Score(){
        String s;
        s = "Война потоков - Попаданий: " + hit + ", Промахов: " + miss;
        frame.setTitle(s);
        if (Integer.parseInt(String.valueOf(miss)) >= 5)
            gameOver();
    }

    public static synchronized void gameOver(){
        gameBoard.ThreadKiller();
        gameBoard.buddy.alive = false;
        JOptionPane.showMessageDialog(null, "Игра окончена! Ваш счет: " + hit,
                "Thread War", JOptionPane.INFORMATION_MESSAGE);
        frame.dispose();
        System.exit(0);
    }

}

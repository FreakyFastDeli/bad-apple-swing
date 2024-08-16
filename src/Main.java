import javax.swing.*;
import java.awt.image.ImageObserver;

public class Main {
    public static void main(String[] args) {
        String frameFile = "/users/carter/Code/java/untitled/in/frames.txt";

        JFrame frame = new JFrame("LME Bad Apple!!");
        ImageRenderer renderer = new ImageRenderer(frameFile);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(ImageObserver.WIDTH, ImageObserver.HEIGHT);
        frame.add(renderer);
        frame.setVisible(true);
    }
}
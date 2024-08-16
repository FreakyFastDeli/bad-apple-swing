import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        String frameFile = "in/night.txt";

        JFrame frame = new JFrame("LME Bad Apple!!");
        ImageRenderer renderer = new ImageRenderer(frameFile);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(ImageRenderer.WIDTH, ImageRenderer.HEIGHT);
        frame.add(renderer);
        frame.setVisible(true);
    }
}
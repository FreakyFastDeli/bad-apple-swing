import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ImageRenderer extends JPanel {
    private static final int WIDTH = 480;  // Set your image width here
    private static final int HEIGHT = 360; // Set your image height here
    private ArrayList<String> frames;      // Store each frame as a String
    private int currentFrame = 0;
    private Timer timer;

    public ImageRenderer(String frameFile) {
        frames = loadFrames(frameFile);

        // Set up a timer to repaint at 30fps (33ms interval)
        timer = new Timer(33, e -> {
            currentFrame = (currentFrame + 1) % frames.size();
            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);

        String currentFrameData = frames.get(currentFrame);
        String[] rows = currentFrameData.split(",");

        for (int y = 0; y < rows.length; y++) {
            if (!rows[y].isEmpty()) {
                String[] tokens = rows[y].split(" ");
                for (int i = 0; i < tokens.length; i += 2) {
                    int start = Integer.parseInt(tokens[i]);
                    int end = Integer.parseInt(tokens[i + 1]);
                    g.drawLine(start, y, end, y);
                }
            }
        }
    }

    private ArrayList<String> loadFrames(String frameFile) {
        ArrayList<String> frameData = new ArrayList<>();
        StringBuilder frameBuilder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(frameFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                frameData.add(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return frameData;
    }

    public static void main(String[] args) {
        // Path to your combined txt file
        String frameFile = "/users/carter/Code/java/untitled/in/frames.txt";

        JFrame frame = new JFrame("Image Renderer");
        ImageRenderer renderer = new ImageRenderer(frameFile);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.add(renderer);
        frame.setVisible(true);
    }
}

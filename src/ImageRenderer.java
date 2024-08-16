import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ImageRenderer extends JPanel {
    protected static final int WIDTH = 480;
    protected static final int HEIGHT = 360;
    private ArrayList<String> frames;
    private int currentFrame = 0;
    private Timer timer;

    public ImageRenderer(String frameFile) {
        frames = loadFrames(frameFile);

        timer = new Timer(33, _ -> {
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
        System.out.println("wrong class");
    }
}

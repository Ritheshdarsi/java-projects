import java.awt.*;
import java.util.Random;
import javax.swing.*;

public class SelectionSortVisualizer extends JPanel {
    private static final int WIDTH = 800, HEIGHT = 600;
    private int[] array;
    private int delay = 50;
    private boolean isSorting = false;

    public SelectionSortVisualizer() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        generateArray();

        JFrame frame = new JFrame("Selection Sort Visualizer");
        JButton startBtn = new JButton("Start Selection Sort");
        JButton resetBtn = new JButton("Reset");

        startBtn.addActionListener(e -> {
            if (!isSorting) {
                new Thread(this::selectionSort).start();
            }
        });

        resetBtn.addActionListener(e -> {
            generateArray();
            repaint();
        });

        JPanel controls = new JPanel();
        controls.add(startBtn);
        controls.add(resetBtn);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this, BorderLayout.CENTER);
        frame.add(controls, BorderLayout.SOUTH);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void generateArray() {
        array = new int[100];
        Random rand = new Random();
        for (int i = 0; i < array.length; i++)
            array[i] = rand.nextInt(HEIGHT - 50) + 20;
    }

    private void selectionSort() {
        isSorting = true;
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
                repaint();
                sleep();
            }
            if (minIndex != i) {
                swap(i, minIndex);
                repaint();
                sleep();
            }
        }
        isSorting = false;
    }

    private void swap(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private void sleep() {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException ignored) {}
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int barWidth = WIDTH / array.length;
        for (int i = 0; i < array.length; i++) {
            int x = i * barWidth;
            int height = array[i];
            g.setColor(Color.BLUE);
            g.fillRect(x, HEIGHT - height, barWidth - 2, height);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SelectionSortVisualizer::new);
    }
}

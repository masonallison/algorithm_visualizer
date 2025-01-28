import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * A class to Define an the algorithm visualizer
 * @author Mason Allison
 */
public class AlgorithmVisualizer extends JPanel {
    private int numRectangles;
    private int[] heights;
    private int maxHeight;
    private SortingAlgorithm sortingAlgorithm;
    public static Timer timer;    
    private final Random random = new Random();

    /**
     * Creates the graphical interface to display the graph and calls a sort method based on which key is pressed
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Algorithm Visualizer Tool");
        frame.setSize(800,600);
        AlgorithmVisualizer algorithmVisualizer = new AlgorithmVisualizer(100,400);
        frame.add(algorithmVisualizer);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()== KeyEvent.VK_B) {
                    algorithmVisualizer.startBubbleSortForward();
                }
                else if(e.getKeyCode() == KeyEvent.VK_Q) {
                    algorithmVisualizer.startQuickSortForward();
                }
                else if(e.getKeyCode() == KeyEvent.VK_R) {
                    algorithmVisualizer.newGraph();
                }
                else if(e.getKeyCode() == KeyEvent.VK_S) {
                    algorithmVisualizer.startBubbleSortReverse();
                }
                else if(e.getKeyCode() == KeyEvent.VK_W) {
                    algorithmVisualizer.startQuickSortReverse();
                }
                else if(e.getKeyCode() == KeyEvent.VK_I) { 
                    algorithmVisualizer.insertionSortForward();
                }
                else if(e.getKeyCode()==KeyEvent.VK_A) { 
                    algorithmVisualizer.insertionSortReverse();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        });
    }
    /**
     * Renders a series of rectangles on the graph and instruction messages
     * @param graphics The object used for drawing the components of the display
     */
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        int panelWidth = getWidth();
        int panelHeight = getHeight();
        double barWidth = (double)panelWidth/(double)numRectangles;
        
        Color color1 = new Color(50,50,50);
        Color color2 = new Color(100,100,100);
        Color color3 = new Color(150,150,150);

        for(int i=0; i<numRectangles;i++) {
            int barHeight = heights[i];
            int y = panelHeight-barHeight;
            int x = (int) (i*barWidth);
            if(i % 3==0) {
                graphics.setColor(color3);
            }
            else if(i % 2 ==0) {
                graphics.setColor(color2);
            }
            else {
                graphics.setColor(color1);
            }
            graphics.fillRect(x, y, (int) barWidth, barHeight);
           
        }
        
            graphics2D.setFont( new Font("Times New Roman", Font.BOLD, 20));
            String[] messages = {
                "'B' - Bubble Sort Ascending | 'S' - Bubble Sort Descending",
                "'Q' - Quick Sort Ascending | 'W' - Quick Sort Descending",
                "'I' - Insertion Sort Ascending | 'A' - Insertion Sort Descending",
                "'R' - Reset"
            };
            graphics2D.setColor(Color.DARK_GRAY);
            FontMetrics fontMetrics = graphics2D.getFontMetrics();
            int y = (20);

            int messageHeight = fontMetrics.getHeight();
            for (String message : messages) {
                int messageWidth = fontMetrics.stringWidth(message);
                int x = (panelWidth-messageWidth)/2;
                graphics2D.drawString(message, x, y);
                y+= messageHeight +5;
            }
        }
    /**
     * Defines an AlgorithmVisualizer
     * @param numRectangles The number of rectangles on the graph
     * @param maxHeight The max height that the rectangles can be
     */
    public AlgorithmVisualizer(int numRectangles, int maxHeight) {
        this.numRectangles = numRectangles;
        this.maxHeight = maxHeight;
        heights = new int[numRectangles];
        for(int i=0; i<numRectangles; i++) {
            heights[i] = random.nextInt(maxHeight)+1;
        }
    }
    /**
     * Starts sorting the graph with BubbleSortForward
     */
    public void startBubbleSortForward() {
        stopSorting();
        sortingAlgorithm = new BubbleSortForward();
        sortingAlgorithm.sort(heights, this::repaint);
    }
    /**
     * Starts sorting the graph with BubbleSortReverse
     */
    public void startBubbleSortReverse() {
        stopSorting();
        sortingAlgorithm = new BubbleSortReverse();
        sortingAlgorithm.sort(heights, this::repaint);
    }
    /**
     * Starts sorting the graph with QuickSortForward
     */
    public void startQuickSortForward() {
        stopSorting();
        sortingAlgorithm = new QuickSortForward();
        sortingAlgorithm.sort(heights, this::repaint);
    }
    /**
     * Starts sorting the graph with QuickSortReverse
     */
    public void startQuickSortReverse() {
        stopSorting();
        sortingAlgorithm = new QuickSortReverse();
        sortingAlgorithm.sort(heights, this::repaint);
    }
    /**
     * Starts sorting the graph with InsertionSortForward
     */
    public void insertionSortForward() {
        stopSorting();
        sortingAlgorithm = new InsertionSortForward();
        sortingAlgorithm.sort(heights, this::repaint);
    }
    /**
     * Starts sorting the graph with InsertionSortReverse
     */
    public void insertionSortReverse() {
        stopSorting();
        sortingAlgorithm = new InsertionSortBackward();
        sortingAlgorithm.sort(heights, this::repaint);
    }
    /**
     * Creates a new graph and displays it
     */
    public void newGraph() {
        stopSorting();
        for (int i = 0; i < numRectangles; i++) {
            heights[i] = random.nextInt(maxHeight) + 1;
        }
        repaint();
    }
    /**
     * Stops any active sorting occuring
     */
    private void stopSorting() {
        if(timer != null && timer.isRunning()) {
            timer.stop();
            timer = null;
        }
        timer = null;
    }
   
}
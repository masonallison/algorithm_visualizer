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
    public static void main(String[] args) {
        JFrame frame = new JFrame("Algorithm Visualizer Tool");
        frame.setSize(800,600);
        AlgorithmVisualizer rectangle = new AlgorithmVisualizer(100,400);
        frame.add(rectangle);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()== KeyEvent.VK_B) {
                    rectangle.startBubbleSortForward();
                }
                else if(e.getKeyCode() == KeyEvent.VK_Q) {
                    rectangle.startQuickSortForward();
                }
                else if(e.getKeyCode() == KeyEvent.VK_R) {
                    rectangle.newGraph();
                }
                else if(e.getKeyCode() == KeyEvent.VK_S) {
                    rectangle.startBubbleSortReverse();
                }
                else if(e.getKeyCode() == KeyEvent.VK_W) {
                    rectangle.startQuickSortReverse();
                }
                else if(e.getKeyCode() == KeyEvent.VK_I) { 
                    rectangle.insertionSortForward();
                }
                else if(e.getKeyCode()==KeyEvent.VK_A) { 
                    rectangle.insertionSortBackward();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        });
    }
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
    
    public AlgorithmVisualizer(int numRectangles, int maxHeight) {
        this.numRectangles = numRectangles;
        this.maxHeight = maxHeight;
        heights = new int[numRectangles];
        for(int i=0; i<numRectangles; i++) {
            heights[i] = random.nextInt(maxHeight)+1;
        }
    }
    public void startBubbleSortForward() {
        stopSorting();
        sortingAlgorithm = new BubbleSortForward();
        sortingAlgorithm.sort(heights, this::repaint);
    }
    public void startBubbleSortReverse() {
        stopSorting();
        sortingAlgorithm = new BubbleSortReverse();
        sortingAlgorithm.sort(heights, this::repaint);
    }
    public void startQuickSortForward() {
        stopSorting();
        sortingAlgorithm = new QuickSortForward();
        sortingAlgorithm.sort(heights, this::repaint);
    }

    public void startQuickSortReverse() {
        stopSorting();
        sortingAlgorithm = new QuickSortReverse();
        sortingAlgorithm.sort(heights, this::repaint);
    }

    public void insertionSortForward() {
        stopSorting();
        sortingAlgorithm = new InsertionSortForward();
        sortingAlgorithm.sort(heights, this::repaint);
    }

    public void insertionSortBackward() {
        stopSorting();
        sortingAlgorithm = new InsertionSortBackward();
        sortingAlgorithm.sort(heights, this::repaint);
    }
    public void newGraph() {
        stopSorting();
        for (int i = 0; i < numRectangles; i++) {
            heights[i] = random.nextInt(maxHeight) + 1;
        }
        repaint();
    }
    private void stopSorting() {
        if(timer != null && timer.isRunning()) {
            timer.stop();
            timer = null;
        }
        timer = null;
    }
   
}
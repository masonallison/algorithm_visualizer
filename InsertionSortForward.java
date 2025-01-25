import javax.swing.Timer;
/**
 * A class that implements the interface SortingAlgorithm to use insertion sort in ascending order
 * @author Mason Allison
 */
public class InsertionSortForward implements SortingAlgorithm {
    /**
     * Sorts the graph with ascending insertion sort
     * @param array An array of integers
     * @param repaint A task that can be executed concurrently
     */
    @Override
    public void sort(int[] array, Runnable repaint) {
        AlgorithmVisualizer.timer = new Timer(100, e -> {
        boolean swapped = false;
        for(int i=1; i<array.length;i++) {
            int temp = array[i];

            int j = i-1;
            while(j >= 0 && array[j] > temp) {
                array[j+1] = array[j];
                j--;
                swapped = true;
            }
            array[j+1] = temp;
            repaint.run();
            if(swapped) {
                break;
            }
        }
        if(!swapped) {
            AlgorithmVisualizer.timer.stop();
        }
    });
    AlgorithmVisualizer.timer.start();
    }
}
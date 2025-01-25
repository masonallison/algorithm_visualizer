import javax.swing.Timer;
/**
 * A class that implements the interface SortingAlgorithm to use bubble sort in ascending order
 * @author Mason Allison
 */
public class BubbleSortForward implements SortingAlgorithm {
    @Override
    /**
     * Sorts the graph in ascending order with Bubble Sort
     * @param array An array of integers
     * @param repaint A task that can be executed concurrently
     */
    public void sort(int[] array, Runnable repaint) {
        AlgorithmVisualizer.timer = new Timer(100, e ->{
            boolean swapped = false;
            for(int i=0; i<array.length-1;i++) {
                if(array[i] > array[i+1]) {
                    int temp = array[i];
                    array[i] = array[i+1];
                    array[i+1]=temp;

                    repaint.run();
                    return;
                }
            }
            if(!swapped) {
                AlgorithmVisualizer.timer.stop();
            }

        });
        AlgorithmVisualizer.timer.start();
    }
    
}
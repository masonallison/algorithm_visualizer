import javax.swing.Timer;
/**
 * A class that implements the interface SortingAlgorithm to use bubble sort in descending order
 * @author Mason Allison
 */
public class BubbleSortReverse implements SortingAlgorithm {
    /**
     * Sorts the graph in descending order with Bubble Sort
     * @param array An array of integers
     * @param repaint A task that can be executed concurrently
     */
    @Override
    public void sort(int[] array, Runnable repaint) {
        AlgorithmVisualizer.timer = new Timer(100, e ->{
            boolean swapped = false;
            for(int i=0; i<array.length-1;i++) {
                if(array[i] < array[i+1]) {
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
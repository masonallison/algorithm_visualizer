import javax.swing.Timer;

public class BubbleSortReverse implements SortingAlgorithm {
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
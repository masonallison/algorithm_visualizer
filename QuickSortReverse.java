import javax.swing.Timer;
import java.util.ArrayDeque;
import java.util.Queue;

public class QuickSortReverse implements SortingAlgorithm {
    private Queue<int[]> tasks = new ArrayDeque<>();

    @Override
    public void sort(int[] array, Runnable repaint) {
        tasks.offer(new int[]{0, array.length-1});
        AlgorithmVisualizer.timer = new Timer(100, e -> {
            if(tasks.isEmpty()) {
                AlgorithmVisualizer.timer.stop();
                return;
            }
            int[] range = tasks.poll();
            int start = range[0];
            int end = range[1];
            if(start<end) {
                int pivot = partitionReverse(array, start, end);
                tasks.offer(new int[]{start, pivot-1});
                tasks.offer(new int[]{pivot+1,end});
            }
            repaint.run();
            
        });
        AlgorithmVisualizer.timer.start();
        
    }
    
    private int partitionReverse(int[] array, int start, int end) {
        int pivot = array[end];
        int i=start;
        for(int j=start; j<end;j++) {
            if(array[j]>=pivot) {
                
                int temp = array[i];
                array[i]=array[j];
                array[j]=temp;
                i++;
                
            }
        }
        int temp = array[i];
        array[i]=array[end];
        array[end]=temp;
        return i;
    }
}
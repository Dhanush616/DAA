import java.util.Random;
import java.util.concurrent.TimeUnit;

public class QuickSortPerformance {

    public static void main(String[] args) {
        int avgIncrement = 50000;
        int worstIncrement = 1000;
        int maxAvgSize = 1000000; 
        int maxWorstSize = 15000; 
        Random random = new Random();

        // Limiting to one core
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "1");

        System.out.println("Average Case Performance");
        System.out.println("Array Size | Time (ms)");
        System.out.println("-----------------------");

        // Average case with 50000 increment
        for (int size = avgIncrement; size <= maxAvgSize; size += avgIncrement) {
            int[] averageCase = random.ints(size, 0, size).toArray();
            long startTime = System.nanoTime();
            quickSort(averageCase, 0, averageCase.length - 1);
            long endTime = System.nanoTime();
            System.out.printf("%10d | %8d\n", size, TimeUnit.NANOSECONDS.toMillis(endTime - startTime));
        }

        System.out.println("\nWorst Case Performance");
        System.out.println("Array Size | Time (ms)");
        System.out.println("-----------------------");

        // Worst case with 1000 increment
        for (int size = worstIncrement; size <= maxWorstSize; size += worstIncrement) {
            int[] worstCase = generateWorstCase(size);
            long startTime = System.nanoTime();
            quickSort(worstCase, 0, worstCase.length - 1);
            long endTime = System.nanoTime();
            System.out.printf("%10d | %8d\n", size, TimeUnit.NANOSECONDS.toMillis(endTime - startTime));
        }
    }

    private static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static int[] generateWorstCase(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = size - i;
        }
        return arr;
    }
}

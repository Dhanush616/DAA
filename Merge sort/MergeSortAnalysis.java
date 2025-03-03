import java.util.Arrays;
import java.util.Random;

public class MergeSortAnalysis {
    public static void main(String[] args) {
        // Limit execution to a single core
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "1");

        int increment = 100000;
        int maxSize = 1500000;
        Random random = new Random();

        System.out.printf("%-10s %-15s %-15s %-15s\n", "Size", "Best Case (ms)", "Worst Case (ms)", "Average Case (ms)");
        for (int size = increment; size <= maxSize; size += increment) {
            int[] bestCase = generateBestCase(size);
            int[] worstCase = generateWorstCase(size);
            int[] averageCase = generateAverageCase(size, random);

            long bestTime = timeMergeSort(Arrays.copyOf(bestCase, bestCase.length));
            long worstTime = timeMergeSort(Arrays.copyOf(worstCase, worstCase.length));
            long averageTime = timeMergeSort(Arrays.copyOf(averageCase, averageCase.length));

            System.out.printf("%-10d %-15d %-15d %-15d\n", size, bestTime, worstTime, averageTime);
        }
    }

    private static int[] generateBestCase(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = i;
        }
        return array;
    }

    private static int[] generateWorstCase(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = size - i;
        }
        return array;
    }

    private static int[] generateAverageCase(int size, Random random) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(size);
        }
        return array;
    }

    private static long timeMergeSort(int[] array) {
        long startTime = System.currentTimeMillis();
        mergeSort(array, 0, array.length - 1);
        return System.currentTimeMillis() - startTime;
    }

    private static void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);
            merge(array, left, mid, right);
        }
    }

    private static void merge(int[] array, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        for (int i = 0; i < n1; ++i)
            leftArray[i] = array[left + i];
        for (int j = 0; j < n2; ++j)
            rightArray[j] = array[mid + 1 + j];

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                array[k++] = leftArray[i++];
            } else {
                array[k++] = rightArray[j++];
            }
        }

        while (i < n1) {
            array[k++] = leftArray[i++];
        }

        while (j < n2) {
            array[k++] = rightArray[j++];
        }
    }
}

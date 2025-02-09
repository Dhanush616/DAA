import java.util.Random;

public class Performance  {
    // Generate an array where no two elements are same
    public static int[] generateSequentialArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = i; // Ensuring index and element are the same
        }
        return arr;
    }

    // Generate an array with random numbers
    public static int[] generateRandomArray(int size) {
        Random rand = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(10000); // Random numbers between 0 and 9999
        }
        return arr;
    }

    // Sequential Search
    public static int sequentialSearch(int[] arr, int key) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == key) {
                return i;
            }
        }
        return -1;
    }

    // Selection Sort
    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            int temp = arr[minIdx];
            arr[minIdx] = arr[i];
            arr[i] = temp;
        }
    }

    //run performance tests for linear search
    public static void runLinearSearchTest(int[] sizes) {
        for (int size : sizes) {
            int[] arr = generateSequentialArray(size);
            int key = -1; // Key is always negative, ensuring it's never found

            // Measure search time
            long startSearch = System.nanoTime();
            sequentialSearch(arr, key);
            long searchTimeNs = (System.nanoTime() - startSearch)/ 1_000_000;// Time in ms

            System.out.printf("Search - Size: %d, Time: %d ms\n", size, searchTimeNs);
        }
    }

    // Method to run performance tests for sorting
    public static void runSortTest(int[] sizes) {
        for (int size : sizes) {
            int[] arr = generateRandomArray(size);

            // Measure sort time
            long startSort = System.nanoTime();
            selectionSort(arr);
            long sortTimeMs = (System.nanoTime() - startSort) / 1_000_000; // Time in ms

            System.out.printf("Sort - Size: %d, Time: %d ms\n", size, sortTimeMs);
        }
    }

    public static void main(String[] args) {
        int[] searchSizes = new int[50];  
        int[] sortSizes = new int[75];
        
        for(int i = 0; i < 50; i++) {
            searchSizes[i] = 100000000 + (i * 10000000);// array for searching
        }
        for(int i = 0; i < 75; i++) {
            sortSizes[i] = 5000 + (i * 2000); // array for sorting
        }
       
        runLinearSearchTest(searchSizes);
        runSortTest(sortSizes);
    }
}

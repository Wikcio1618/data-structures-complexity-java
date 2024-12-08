
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class PerformanceTest {

    public static void main(String[] args) throws IOException {
        // MinHeap<Double> ds = new MinHeap<>();
        Random rand = new Random();
        int reps = 100_000;
        // Number of elements to test
        // 5_000_000
        // , 10_000_000
        int[] sizes = {1000, 2000, 4000, 8000};

        try (FileWriter writer = new FileWriter("times/sort_merge_quick.csv")) {
            writer.write("N,quickbest(ms),quickrandom(ms),quickworst(ms),mergebest(ms),mergerandom(ms),mergeworst(ms),reps\n");

            for (int size : sizes) {
                writer.write(size + ",");
                ArrayList<Integer> best = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    best.add(i);
                }

                ArrayList<Integer> random = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    random.add(rand.nextInt(100));
                }

                ArrayList<Integer> worst = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    worst.add(size - i);
                }

                long startTime = System.currentTimeMillis();
                Sort.quickSort(best);
                long endTime = System.currentTimeMillis();
                long elapsedTime = endTime - startTime;
                writer.write(elapsedTime + ",");
// //////////////////////
                startTime = System.currentTimeMillis();
                Sort.quickSort(random);
                endTime = System.currentTimeMillis();
                elapsedTime = endTime - startTime;
                writer.write(elapsedTime + ",");
                // //////////////////////
                startTime = System.currentTimeMillis();
                Sort.quickSort(worst);
                endTime = System.currentTimeMillis();
                elapsedTime = endTime - startTime;
                writer.write(elapsedTime + ",");
                // //////////////////////
                best = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    best.add(i);
                }

                random = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    random.add(rand.nextInt(100));
                }

                worst = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    worst.add(size - i);
                }

                startTime = System.currentTimeMillis();
                Sort.mergeSort(best);
                endTime = System.currentTimeMillis();
                elapsedTime = endTime - startTime;
                writer.write(elapsedTime + ",");
                // //////////////////////
                startTime = System.currentTimeMillis();
                Sort.mergeSort(random);
                endTime = System.currentTimeMillis();
                elapsedTime = endTime - startTime;
                writer.write(elapsedTime + ",");
                // //////////////////////
                startTime = System.currentTimeMillis();
                Sort.mergeSort(worst);
                endTime = System.currentTimeMillis();
                elapsedTime = endTime - startTime;
                writer.write(elapsedTime + ",");

// //////////////////
                writer.write(reps + "\n");

            }
            writer.close();
        }

    }

    public static void measureSortingPerformance() throws IOException {
        int size = 100000; // Example size
        ArrayList<Integer> best = new ArrayList<>();
        ArrayList<Integer> random = new ArrayList<>();
        ArrayList<Integer> worst = new ArrayList<>();
        Random rand = new Random();

        // Initialize arrays
        for (int i = 0; i < size; i++) {
            best.add(i); // Already sorted
            random.add(rand.nextInt(100)); // Random input
            worst.add(size - i); // Reverse sorted
        }

        // Measure performance
        measureSort("Best", best);
        measureSort("Random", random);
        measureSort("Worst", worst);
    }

    private static void measureSort(String label, ArrayList<Integer> list) {
        int iterations = 5; // Number of repetitions for averaging
        long totalElapsedTime = 0;

        for (int i = 0; i < iterations; i++) {
            ArrayList<Integer> copy = new ArrayList<>(list); // Avoid modifying the original list
            long startTime = System.currentTimeMillis();
            Sort.mergeSort(copy); // Call your sorting method
            long endTime = System.currentTimeMillis();
            totalElapsedTime += (endTime - startTime);
        }

        long averageTime = totalElapsedTime / iterations;
        System.out.println(label + " case: " + averageTime + " ms");
    }


}

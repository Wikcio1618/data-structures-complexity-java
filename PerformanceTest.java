
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class PerformanceTest {

    public static void main(String[] args) {
        // measureExecutionTime(
        //         "map_insertion_results.csv",
        //         () -> new CustomMap<Integer, Integer>(100000000), // Supplier to create a new CustomMap instance
        //         (map, size) -> {
                    
        //             for (int i = 0; i < size; i++) {
        //                 map.put(1, 1);
        //             }
        //         },
        //         new int[]{100, 1000, 5000, 10000, 10000, 100000, 1000000, 10000000} // Array of sizes to test
        // );
        CustomMap<Integer, Integer> customMap = new CustomMap<>(1_000_000);
        Random random = new Random();

        // Number of elements to test
        int[] sizes = {10_000, 50_000, 100_000, 500_000, 1_000_000};

        System.out.println("Size (N), Time (ns)");
        for (int size : sizes) {
            long startTime = System.nanoTime();

            for (int i = 0; i < size; i++) {
                customMap.put(random.nextInt(), random.nextInt());
            }

            long endTime = System.nanoTime();
            long elapsedTime = endTime - startTime;

            System.out.println(size + ", " + elapsedTime);
        }

    }

    /**
     * Generic performance measurement function.
     *
     * @param fileName The name of the file to write results.
     * @param dataStructureSupplier A supplier to initialize a new data
     * structure for each size.
     * @param operation A consumer that performs an operation on the data
     * structure.
     * @param sizes An array of sizes to test.
     * @param <DS> The type of the data structure being tested.
     */
    public static <DS> void measureExecutionTime(
            String fileName,
            Supplier<DS> dataStructureSupplier,
            BiConsumer<DS, Integer> operation,
            int[] sizes
    ) {
        try (FileWriter writer = new FileWriter("times/" + fileName)) {
            // Write header to the CSV file
            writer.write("N,T(ms)\n");

            for (int size : sizes) {
                // Initialize the data structure
                DS dataStructure = dataStructureSupplier.get();

                // Measure the time for the passed operation
                long startTime = System.currentTimeMillis();
                operation.accept(dataStructure, size); // Execute the operation
                long endTime = System.currentTimeMillis();

                // Calculate elapsed time in milliseconds
                long elapsedTime = endTime - startTime;

                // Write the result to the file
                writer.write(size + "," + elapsedTime + "\n");
                System.out.println("Size: " + size + " -> Time: " + elapsedTime + " ms");
            }

            System.out.println("Results written to " + fileName);

        } catch (IOException e) {
        }

    }
}

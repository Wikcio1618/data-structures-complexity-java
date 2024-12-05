
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class PerformanceTest {

    public static void main(String[] args) throws IOException {
        MinHeap<Double> ds = new MinHeap<>();
        Random random = new Random();
        int reps = 100_000;
        // Number of elements to test
        int[] sizes = {100_000, 50_0000, 100_000_00, 500_000_00, 1_000_000_00};

        
            try (FileWriter writer = new FileWriter("times/min_heap.csv")) {
                writer.write("N,insert(ms),search(ms),removeRoot(ms),reps\n");

                for (int size : sizes) {
                    writer.write(size + ",");

                    ds.clear();
                    for (int i = 0; i < size; i++) {
                        ds.insert(random.nextDouble());
                    }

                    long startTime = System.currentTimeMillis();
                    for (int i = 0; i < reps; i++) {
                        ds.insert(random.nextDouble());
                    }
                    long endTime = System.currentTimeMillis();
                    long elapsedTime = endTime - startTime;
                    writer.write(elapsedTime + ",");
// //////////////////////
                    ds.clear();
                    for (int i = 0; i < size; i++) {
                        ds.insert(random.nextDouble());
                    }
                    startTime = System.currentTimeMillis();
                    for (int i = 0; i < reps; i++) {
                        ds.search(random.nextDouble());
                    }
                    endTime = System.currentTimeMillis();
                    elapsedTime = endTime - startTime;
                    writer.write(elapsedTime + ",");
// //////////////////////
                    ds.clear();
                    for (int i = 0; i < size; i++) {
                        ds.insert(random.nextDouble());
                    }
                    startTime = System.currentTimeMillis();
                    for (int i = 0; i < reps; i++) {
                        ds.removeRoot();
                    }
                    endTime = System.currentTimeMillis();
                    elapsedTime = endTime - startTime;
                    writer.write(elapsedTime + ",");
// //////////////////
                    writer.write(reps + "\n");

                }
                writer.close();
            }

    }

}


import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class PerformanceTest {

    public static void main(String[] args) throws IOException {
        SinglyLinkedList<Integer> ds = new SinglyLinkedList<>();
        Random random = new Random();
        int reps = 1000;
        // Number of elements to test
        int[] sizes = {10_000, 50_000, 100_000, 500_000, 1_000_000};

        
            try (FileWriter writer = new FileWriter("times/singly_list.csv")) {
                writer.write("N,insert(ms),search(ms),removeFirst(ms),removeStart(ms),removeEnd(ms),reps\n");

                for (int size : sizes) {
                    writer.write(size + ",");

                    ds.clear();
                    for (int i = 0; i < size; i++) {
                        ds.insert(random.nextInt());
                    }

                    long startTime = System.currentTimeMillis();
                    for (int i = 0; i < reps; i++) {
                        ds.insert(i);
                    }
                    long endTime = System.currentTimeMillis();
                    long elapsedTime = endTime - startTime;
                    writer.write(elapsedTime + ",");
// //////////////////////
                    ds.clear();
                    for (int i = 0; i < size; i++) {
                        ds.insert(random.nextInt());
                    }
                    startTime = System.currentTimeMillis();
                    for (int i = 0; i < reps; i++) {
                        ds.search(i);
                    }
                    endTime = System.currentTimeMillis();
                    elapsedTime = endTime - startTime;
                    writer.write(elapsedTime + ",");
// //////////////////////

                    startTime = System.currentTimeMillis();
                    for (int i = 0; i < reps; i++) {
                        ds.removeFirst(i);
                    }
                    endTime = System.currentTimeMillis();
                    elapsedTime = endTime - startTime;
                    writer.write(elapsedTime + ",");
// //////////////////////
                    ds.clear();
                    for (int i = 0; i < size; i++) {
                        ds.insert(random.nextInt());
                    }
                    startTime = System.currentTimeMillis();
                    for (int i = 0; i < reps; i++) {
                        ds.removeStart();
                    }
                    endTime = System.currentTimeMillis();
                    elapsedTime = endTime - startTime;
                    writer.write(elapsedTime + ",");
// //////////////////////
                    ds.clear();
                    for (int i = 0; i < size; i++) {
                        ds.insert(random.nextInt());
                    }
                    startTime = System.currentTimeMillis();
                    for (int i = 0; i < reps; i++) {
                        ds.removeEnd();
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

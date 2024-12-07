
import java.util.ArrayList;
import java.util.Arrays;

public class Sort {

    public static <T extends Comparable<T>> void quickSort(ArrayList<T> arr) {
        partition(arr, 0, arr.size() - 1);
    }

    private static <T extends Comparable<T>> void partition(ArrayList<T> arr, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        T pivot = arr.get(hi);
        int idx = lo;
        for (int i = lo; i < hi; i++) {
            if (arr.get(i).compareTo(pivot) <= 0) {
                swap(arr, i, idx);
                idx++;
            }
        }
        swap(arr, idx, hi);
        partition(arr, lo, idx - 1);
        partition(arr, idx + 1, hi);
    }

    private static <T> void swap(ArrayList<T> arr, int i, int j) {
        T temp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, temp);
    }

    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(4, 5, 1, 2, 6, 1, 1, 2, 4, 1, 2, 4));
        Sort.quickSort(arr);
        System.out.println(arr.toString());
    }
}

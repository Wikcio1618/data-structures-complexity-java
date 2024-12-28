
import java.util.ArrayList;

public class Sort {

    public static <T extends Comparable<T>> void quickSort(ArrayList<T> arr) {
        quickRec(arr, 0, arr.size() - 1);
    }

    private static <T extends Comparable<T>> void quickRec(ArrayList<T> arr, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        T pivot = arr.get(hi);
        int idx = lo - 1;
        for (int i = lo; i < hi; i++) {
            if (arr.get(i).compareTo(pivot) <= 0) {
                idx++;
                swap(arr, i, idx);

            }
        }
        swap(arr, idx + 1, hi);

        quickRec(arr, lo, idx);
        quickRec(arr, idx + 2, hi);
    }

    public static <T extends Comparable<T>> void mergeSort(ArrayList<T> arr) {
        mergeRec(arr, 0, arr.size() - 1);
    }

    // Main function that sorts arr[l..r] using
    // merge()
    private static <T extends Comparable<T>> void mergeRec(ArrayList<T> arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int m = l + (r - l) / 2;
        mergeRec(arr, l, m);
        mergeRec(arr, m + 1, r);

        merge(arr, l, m, r);
    }

    @SuppressWarnings("unchecked")
    private static <T extends Comparable<T>> void merge(ArrayList<T> arr, int l, int m, int r) {
        int nl = m - l + 1;
        int nr = r - m;
        T[] L = (T[]) new Comparable[nl];
        T[] R = (T[]) new Comparable[nr];
        for (int i = 0; i < nl; i++) {
            L[i] = arr.get(l + i);
        }
        for (int i = 0; i < nr; i++) {
            R[i] = arr.get(m + 1 + i);
        }
        int i = 0;
        int j = 0;
        int k = l;
        while (i < nl && j < nr) {
            if (L[i].compareTo(R[j]) <= 0) {
                arr.set(k++, L[i++]);
            } else {
                arr.set(k++, R[j++]);
            }
        }
        while (i < nl) {
            arr.set(k++, L[i++]);
        }
        while (j < nr) {
            arr.set(k++, R[j++]);
        }
    }

    private static <T> void swap(ArrayList<T> arr, int i, int j) {
        T temp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, temp);
    }

    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            arr.add(i);
        }
        Sort.quickSort(arr);
        System.out.println(arr.toString());
    }
}


public interface DataStructureInterface<T> {

    public void insert(T value);

    public boolean search(T value);

    public T deleteFirst(T value);

    public boolean isEmpty();

    public void display();

    public void clear();
}

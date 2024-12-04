
// uses linear probing for handling collision; 
// doubles capacity upon reaching load threshhold
public class CustomMap<K, V> {

    private final double loadThresh = 0.7;

    private int capacity;
    private boolean[] used;
    private K[] keys;
    private V[] values;
    public int length;

    @SuppressWarnings("unchecked")
    public CustomMap(int capacity) {
        this.capacity = capacity;
        this.used = new boolean[capacity];
        this.keys = (K[]) new Object[capacity];
        this.values = (V[]) new Object[capacity];
        this.length = 0;
    }

    public V get(K key) {
        int curr = getHash(key) % capacity;
        while (used[curr]) {
            if (keys[curr].equals(key)) {
                return values[curr];
            }
            curr = (curr + 1) % capacity;
        }

        return null;
    }

    public V put(K key, V value) {
        int curr = getHash(key) % capacity;
        while (used[curr]) {
            if (keys[curr].equals(key)) {
                V prev = values[curr];
                values[curr] = value;
                return prev;
            }
            curr = (curr + 1) % capacity;
        }

        used[curr] = true;
        keys[curr] = key;
        values[curr] = value;
        length++;

        if (1.0 / capacity * length > loadThresh) {
            resize();
        }

        return null;
    }

    private int getHash(K key) {
        return key.hashCode() & 0x7FFFFFFF;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        int newCapacity = capacity * 2;
        boolean[] newUsed = new boolean[newCapacity];
        K[] newKeys = (K[]) new Object[newCapacity];
        V[] newValues = (V[]) new Object[newCapacity];

        for (int i = 0; i < capacity; i++) {
            if (!used[i]) {
                continue;
            }
            int curr = getHash(keys[i]) % newCapacity;
            while (newUsed[curr]) {
                curr = (curr + 1) % newCapacity;
            }
            newUsed[curr] = true;
            newKeys[curr] = keys[i];
            newValues[curr] = values[i];
        }

        capacity = newCapacity;
        keys = newKeys;
        values = newValues;
        used = newUsed;
    }

    public static void main(String[] args) {
        // Step 1: Initialize the map with an initial capacity of 4
        CustomMap<String, Integer> map = new CustomMap<>(4);

        // Step 2: Insert some key-value pairs and verify the operations
        System.out.println("Adding key-value pairs...");
        System.out.println("Put (Alice, 10): " + map.put("Alice", 10)); // Should print: null
        System.out.println("Put (Bob, 20): " + map.put("Bob", 20));    // Should print: null
        System.out.println("Put (Eve, 30): " + map.put("Eve", 30));    // Should print: null

        // Step 3: Retrieve values and ensure correctness
        System.out.println("\nRetrieving values...");
        System.out.println("Get Alice: " + map.get("Alice")); // Should print: 10
        System.out.println("Get Bob: " + map.get("Bob"));     // Should print: 20
        System.out.println("Get Eve: " + map.get("Eve"));     // Should print: 30
        System.out.println("Get Charlie (not present): " + map.get("Charlie")); // Should print: null

        // Step 4: Test overwriting a value
        System.out.println("\nOverwriting value for Alice...");
        System.out.println("Put (Alice, 15): " + map.put("Alice", 15)); // Should print: 10 (old value)
        System.out.println("Get Alice: " + map.get("Alice"));          // Should print: 15 (new value)

        // Step 5: Test collision handling (assuming a small hash table leads to collisions)
        System.out.println("\nTesting collision handling...");
        System.out.println("Put (Dean, 40): " + map.put("Dean", 40));  // Should trigger collision resolution
        System.out.println("Get Dean: " + map.get("Dean"));           // Should print: 40

        // Step 6: Trigger resizing by adding more elements
        System.out.println("\nAdding more elements to trigger resizing...");
        System.out.println("Put (Charlie, 50): " + map.put("Charlie", 50)); // Should trigger resize
        System.out.println("Get Charlie: " + map.get("Charlie"));          // Should print: 50
        System.out.println("Get Alice after resize: " + map.get("Alice")); // Should print: 15
        System.out.println("Get Bob after resize: " + map.get("Bob"));     // Should print: 20

        // Step 7: Verify map's internal length
        System.out.println("\nMap length: " + map.length); // Should print the total number of elements in the map
    }

}

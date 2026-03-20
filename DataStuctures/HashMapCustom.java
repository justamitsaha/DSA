
import java.util.LinkedList;

/**
 * Custom HashMap Implementation using Chaining (Array + Linked List).
 * 
 * @param <K> the type of keys
 * @param <V> the type of values
 */
public class HashMapCustom<K, V> {

    private static class Entry<K, V> {
        K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private LinkedList<Entry<K, V>>[] table;
    private int size;
    private static final int DEFAULT_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;

    @SuppressWarnings("unchecked")
    public HashMapCustom() {
        table = new LinkedList[DEFAULT_CAPACITY];
        size = 0;
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % table.length;
    }

    public void put(K key, V value) {
        if ((double) size / table.length >= LOAD_FACTOR) {
            rehash();
        }

        int index = hash(key);
        if (table[index] == null) {
            table[index] = new LinkedList<>();
        }

        for (Entry<K, V> entry : table[index]) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        }

        table[index].add(new Entry<>(key, value));
        size++;
    }

    public V get(K key) {
        int index = hash(key);
        LinkedList<Entry<K, V>> bucket = table[index];
        if (bucket != null) {
            for (Entry<K, V> entry : bucket) {
                if (entry.key.equals(key)) {
                    return entry.value;
                }
            }
        }
        return null;
    }

    public void remove(K key) {
        int index = hash(key);
        LinkedList<Entry<K, V>> bucket = table[index];
        if (bucket != null) {
            Entry<K, V> toRemove = null;
            for (Entry<K, V> entry : bucket) {
                if (entry.key.equals(key)) {
                    toRemove = entry;
                    break;
                }
            }
            if (toRemove != null) {
                bucket.remove(toRemove);
                size--;
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void rehash() {
        LinkedList<Entry<K, V>>[] oldTable = table;
        table = new LinkedList[oldTable.length * 2];
        size = 0;

        for (LinkedList<Entry<K, V>> bucket : oldTable) {
            if (bucket != null) {
                for (Entry<K, V> entry : bucket) {
                    put(entry.key, entry.value);
                }
            }
        }
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        HashMapCustom<String, Integer> map = new HashMapCustom<>();
        map.put("One", 1);
        map.put("Two", 2);
        map.put("Three", 3);
        
        System.out.println("Get One: " + map.get("One"));
        System.out.println("Get Two: " + map.get("Two"));
        
        map.put("One", 11);
        System.out.println("Get One (updated): " + map.get("One"));
        
        map.remove("Two");
        System.out.println("Get Two (after remove): " + map.get("Two"));
        System.out.println("Size: " + map.size());
    }
}

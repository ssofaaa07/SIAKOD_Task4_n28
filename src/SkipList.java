import java.util.concurrent.ConcurrentSkipListMap;

public interface MySkipList<K, V> {

    public V get(K key);

    public void put(K key, V value);

    public void remove(K key);

}

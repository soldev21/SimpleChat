package main.registry;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sherif on 6/15/2018.
 */
public interface ClientHandlerRegistry<K,V> {
    public V get(K k);
    public void add(K k,V v);
    public void remove(K k);
    public void reset();
    public Map<K, V> getRegistry();
}

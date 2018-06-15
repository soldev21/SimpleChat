package main.registry;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sherif on 6/15/2018.
 */
public class HashMapClientHandlerRegistry<K,V> implements ClientHandlerRegistry<K,V>{

    private Map<K, V> registry;

    public HashMapClientHandlerRegistry() {
        registry = new HashMap<>();
    }

    @Override
    public V get(K k){
        return registry.get(k);
    }

    @Override
    public void add(K k,V v){
        registry.put(k,v);
    }

    @Override
    public void remove(K k){
        registry.remove(k);
    }

    @Override
    public void reset(){
        registry = new HashMap<>();
    }

    @Override
    public Map<K, V> getRegistry() {
        return registry;
    }
}

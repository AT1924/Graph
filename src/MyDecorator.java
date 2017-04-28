package src;
import java.util.*;

import support.graph.*;

/**
 * Your implementation of decorations.
 * All methods must run in O(1) time.
 */
public class MyDecorator<K,V> implements CS16Decorator<K,V>{
    private HashMap<K,V> _map;
    public MyDecorator() {
    	_map = new HashMap<K,V>();
    
    }

    /**
     * Gets the decoration associated with the given key.
     * @param key the key used to retrieve a specific value.
     * @return the value associated with the key parameter
     */
    @Override
    public V getDecoration(K key) {
        return _map.get(key);
    }

    /**
     * Sets the decoration for the specified key to value.
     * @param key the key that will be used to retrieve your value
     * @param value the value associated with your key
     */
    @Override
    public void setDecoration(K key, V value) {
    	_map.put(key, value);
        
    }

    /**
     * Returns true if there is a decoration for the given key,
     * false otherwise.
     * @param key a key that may or may not be in your decorator
     * @return a boolean value that returns true if the key is valid
     */
    @Override
    public boolean hasDecoration(K key) {
        return _map.containsKey(key);
    }

    /**
     * Removes the decoration for the given key and returns the
     * value associated with it.
     * @param key key associated with a value
     * @return value removed
     */
    @Override
    public V removeDecoration(K key) {
        return _map.remove(key);
    }

    /**
     * Returns a Set of all keys for this decoration.
     * @return a Set of all keys in your decorator
     */
    @Override
    public Set<K> getKeys() {
        return _map.keySet();
    }
}

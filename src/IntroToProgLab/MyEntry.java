package IntroToProgLab;

import java.util.Map;

final class MyEntry <K, V> implements Map.Entry <K, V> {
    private K key;
    private V value;

    MyEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }
    public MyEntry(){

    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public V setValue(V value) {
        return this.value=value;
    }

}
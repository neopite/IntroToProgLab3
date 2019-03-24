package IntroToProgLab;

public class HashMap<K, V> {
    private final int DEFAULT_CAPACITY = 16;
    private Bucket<MyEntry<K, V>>[] arrayOfIndexes;

    public HashMap(int size) {
        // UNCHECKED CAST EXCEPTION POSSIBLE
        arrayOfIndexes = (Bucket<MyEntry<K, V>>[]) new Bucket[size];
    }

    public HashMap() {
        //UNCHECKED CAST EXCEPTION POSSIBLE
        arrayOfIndexes = (Bucket<MyEntry<K, V>>[]) new Bucket[DEFAULT_CAPACITY];
    }

    public void inputArrayOfBuckets() {
        for (int itter = 0; itter < arrayOfIndexes.length; itter++) {
            Bucket bucket = new Bucket();
            arrayOfIndexes[itter] = bucket;
        }
    }

    public void put(K key, V value) {
        MyEntry<K, V> myEntry = new MyEntry<>(key, value);
        int ind = index(key);
        if (arrayOfIndexes[ind].size() == 0) {
            arrayOfIndexes[ind].add(myEntry);
            return;
        } else {
            for (int itter = 0; itter < arrayOfIndexes[ind].size(); itter++) {
                if (!myEntry.getKey().equals(arrayOfIndexes[ind].get(itter).getKey())) {
                    if (hashCode(myEntry.getKey()) == hashCode(arrayOfIndexes[ind].get(itter).getKey())) {
                        System.out.println("collision add linked list");
                        arrayOfIndexes[ind].add(myEntry);
                    }
                } else {
                    System.out.println("Error:similar keys");
                    System.exit(-1);
                }
            }
        }
    }

    V get(K key) {
        for (int itter = 0; itter < arrayOfIndexes.length; itter++) {
            for (int x = 0; x < arrayOfIndexes[itter].size(); x++) {
                MyEntry<K, V> myEntry = arrayOfIndexes[itter].get(x);
                if (key.equals(myEntry.getKey())) {
                    return arrayOfIndexes[index(key)].get(x).getValue();
                }
            }
        }
        return null;
    }

    public int hashCode(K word) {
        String str = word.toString();
        int hash = 7;
        for (int i = 0; i < str.length(); i++) {
            hash = hash * 31 + str.charAt(i);
        }
        return hash;
    }

    private int index(K word) {
        int n = 16;
        return hashCode(word) & (n - 1);

    }

    @Override
    public boolean equals(Object obj) {
        return this == obj;
    }

}

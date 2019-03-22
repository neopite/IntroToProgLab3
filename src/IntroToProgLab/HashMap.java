package IntroToProgLab;

public class HashMap <K,V> {
    private final int DEFAULT_CAPACITY = 16;
    private Bucket <MyEntry<K, V>>[] arrayOfIndexes;

    public HashMap(int size) {
        // UNCHECKED CAST EXCEPTION POSSIBLE
        arrayOfIndexes = (Bucket <MyEntry<K, V>>[]) new Bucket[size];
    }

    HashMap() {
        //UNCHECKED CAST EXCEPTION POSSIBLE
        arrayOfIndexes = (Bucket <MyEntry<K, V>>[]) new Bucket[DEFAULT_CAPACITY];
    }
    public void inputArrayOfBuckets(){
        for (int itter = 0; itter < arrayOfIndexes.length; itter++) {
            Bucket bucket = new Bucket();
            arrayOfIndexes[itter] = bucket;
        }
    }

    public void put(K key, V value) {
        if (value.equals(this.get(key))) {
            System.out.println("ERROR: Collision.");
            return;
        } else {
            MyEntry <K, V> myEntry = new MyEntry<>(key, value);
            int ind = index((String) key);
            arrayOfIndexes[ind].add(myEntry);
        }
    }
    V get(K key){
        int hash = hashCode((String) key);
        for (int itter = 0; itter < arrayOfIndexes.length; itter++) {
            for (int x = 0; x < arrayOfIndexes[itter].size(); x++) {
                MyEntry <K,V> myEntry = arrayOfIndexes[itter].get(x);
                if (key.equals(myEntry.getKey())) {
                    return arrayOfIndexes[index((String) key)].get(x).getValue();
                }
            }
        }
        return null;
    }

    private int hashCode(String word) {
        int hash = 7;
        for (int i = 0; i < word.length(); i++) {
            hash = hash * 31 + word.charAt(i);
        }
        return hash;
    }
    private int index(String word){
        int n = 16;
        return hashCode(word) & (n-1);

    }
    @Override
    public boolean equals(Object obj) {
        return this == obj;
    }

    public static void main(String[] args) {
    }

}

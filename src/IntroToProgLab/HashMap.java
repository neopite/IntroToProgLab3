package IntroToProgLab;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class HashMap <K, V> {
    private Bucket <MyEntry<K, V>>[] arrayOfIndexes;
    private int occupiedSlots;

    public HashMap(int size) {
        // UNCHECKED CAST EXCEPTION POSSIBLE
        arrayOfIndexes = (Bucket<MyEntry<K, V>>[]) new Bucket[size];
    }

    public HashMap() {
        //UNCHECKED CAST EXCEPTION POSSIBLE
        arrayOfIndexes = (Bucket<MyEntry<K, V>>[]) new Bucket[16];
    }

    public void inputArrayOfBuckets() {
        for (int itter = 0; itter < arrayOfIndexes.length; itter++) {
            if (arrayOfIndexes[itter] != null) {
                continue;
            } else {
                Bucket<MyEntry<K,V>> bucket = new Bucket <MyEntry<K,V>>();
                arrayOfIndexes[itter] = bucket;
            }
        }
    }

    private void inputArrayOfBuckets(Bucket<MyEntry<K, V>>[] array) {
        for (int itter = 0; itter < array.length - 1; itter++) {
            if (array[itter] != null) {
                continue;
            } else {
                Bucket<MyEntry<K,V>> bucket = new Bucket<>();
                arrayOfIndexes[itter] = bucket;
            }
        }
    }

    private void put(K key, V value) {
        if (resizeFactor(arrayOfIndexes)) expand(arrayOfIndexes, this);
        MyEntry<K, V> myEntry = new MyEntry<>(key, value);
        int ind = index(key);
        arrayOfIndexes[ind].add(myEntry);
    }

    V get(K key) {
        int ind = index(key);
        for (int x = 0; x < arrayOfIndexes[ind].size(); x++) {
            MyEntry<K, V> myEntry = arrayOfIndexes[ind].get(x);
            if (key.equals(myEntry.getKey())) {
                return arrayOfIndexes[index(key)].get(x).getValue();
            }
        }
        return null;
    }

    public boolean resizeFactor(Bucket<MyEntry<K, V>>[] array) {
        int counter = 0;
        for (int itter = 0; itter < array.length; itter++) {
            if (array[itter].size() >= 1) counter++;
        }
        occupiedSlots = counter;
        if (counter / array.length >= 0.8) {
            return true;
        }

        return false;
    }

    public void expand(Bucket<MyEntry<K, V>>[] array, HashMap<K, V> hashMap) {
        Bucket<MyEntry<K, V>>[] arrayOfBuckets = (Bucket<MyEntry<K, V>>[]) new Bucket[array.length * 2];
        for (int itter = 0; itter < array.length; itter++) {
            arrayOfBuckets[itter] = array[itter];
            arrayOfIndexes[itter] = arrayOfBuckets[itter];
        }

       // hashMap.inputArrayOfBuckets(arrayOfBuckets);
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
        return hashCode(word) & (arrayOfIndexes.length - 1);

    }

    public void inputVocabular(String file, HashMap<String, String> hashMap) throws FileNotFoundException {
        try (final Scanner reader = new Scanner(new FileReader(file))) {
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] buffer = line.split(";");
                hashMap.put(buffer[0], buffer[1]);
            }
        }
    }
}


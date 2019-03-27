package IntroToProgLab;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class HashMap <K, V> {
    Bucket <MyEntry<K, V>>[] arrayOfIndexes;

    public HashMap(int size) {
        // UNCHECKED CAST EXCEPTION POSSIBLE
        arrayOfIndexes = (Bucket<MyEntry<K, V>>[]) new Bucket[size];
    }

    public HashMap() {
        //UNCHECKED CAST EXCEPTION POSSIBLE
        arrayOfIndexes = (Bucket<MyEntry<K, V>>[]) new Bucket[32];
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
        for (int itter = 0; itter < array.length; itter++) {
            if (array[itter] != null) {
                continue;
            } else {
                Bucket<MyEntry<K,V>> bucket = new Bucket<>();
                arrayOfIndexes[itter] = bucket;
            }
        }
    }

    private void put(K key, V value) {
        if (loadFactor()) {
            this.expand();
        }
        MyEntry<K, V> myEntry = new MyEntry<>(key, value);
        int ind = index(key);
        System.out.println(ind + " " + arrayOfIndexes.length);
        arrayOfIndexes[ind].add(myEntry);
    }

    V get(K key) {
        int ind = index(key);
        for (int x = 0; x < arrayOfIndexes[ind].size(); x++) {
            MyEntry<K, V> myEntry = arrayOfIndexes[ind].get(x);
            if (key.equals(myEntry.getKey())) {
                return arrayOfIndexes[ind].get(x).getValue();
            }
        }
        return null;
    }

    private boolean loadFactor() {
        int counter = 0;
        for (Bucket<MyEntry<K, V>> bucket : arrayOfIndexes) {
            if (bucket != null && bucket.size() > 0) {
                counter++;
            }
        }
        if (counter  >= 0.8 * arrayOfIndexes.length) {
            return true;
        }
        return false;
    }

    private void expand() {
        Bucket <MyEntry<K, V>>[] oldBucketArray = arrayOfIndexes;
       // HashMap<K, V> oldMap = this;
        this.arrayOfIndexes = (Bucket<MyEntry<K, V>>[]) new Bucket[oldBucketArray.length * 2];
        HashMap<K, V> newMap = (HashMap<K, V>) new HashMap(oldBucketArray.length * 2);
        for (Bucket<MyEntry<K, V>> oldBucket : oldBucketArray) {
            ArrayList<MyEntry<K, V>> entryList = oldBucket.getEntries();
            for (MyEntry<K, V> entry : entryList) {
                newMap.put(entry.getKey(), entry.getValue());
            }
        }
        this.arrayOfIndexes = newMap.arrayOfIndexes;
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


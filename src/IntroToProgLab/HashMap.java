package IntroToProgLab;

public class HashMap<K,V > {
    final int defaultCapacity = 16;
    private Bucket[] arrayOfIndexs;

    public HashMap(int size) {
        arrayOfIndexs = new Bucket[size];
    }

    public HashMap() {
        arrayOfIndexs = new Bucket[defaultCapacity];
    }
    public void inputArrayOfBuckets(){
        for (int itter = 0; itter < arrayOfIndexs.length; itter++) {
            Bucket bucket=new Bucket();
            arrayOfIndexs[itter]=bucket;
        }
    }

    public void put(K key, V value) {
        MyEntry<K,V> myEntry=new MyEntry<>(key,value);
        int ind=index((String) key);
        arrayOfIndexs[ind].add(myEntry);
    }
    public MyEntry<K,V> get(K key){
        int hash=hashCode((String) key);
        for (int itter = 0; itter < arrayOfIndexs.length; itter++) {
            for (int x = 0; x < arrayOfIndexs[itter].size(); x++) {
                MyEntry<K,V> myEntry= (MyEntry<K, V>) arrayOfIndexs[itter].get(x);
                if (key.equals(myEntry.getKey())) {
                    return (MyEntry<K, V>) arrayOfIndexs[index((String) key)].get(x);
                }
            }
        }
        return null;
    }
    public V value(MyEntry<K,V> myEntry){
        return myEntry.getValue();
    }
    public int hashCode(String word) {
        int hash=7;
        for (int i = 0; i < word.length(); i++) {
            hash=hash*31+word.charAt(i);
        }
        return hash;
    }
    public int index(String word){
        int n=16;
        int index=hashCode(word) & (n-1);
        return index;
    }
    @Override
    public boolean equals(Object obj) {
        return this==obj;
    }

    public static void main(String[] args) {
    }

}

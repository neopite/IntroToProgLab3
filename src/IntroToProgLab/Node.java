package IntroToProgLab;

public class Node<V,K> {
    int hash;
    K key;
    V value;
    Node nextElem;
    public Node(K key,V value) {
        this.key = key;
        this.value = value;
        nextElem=null;
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
}

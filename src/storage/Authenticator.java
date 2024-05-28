package storage;

public class Authenticator {
    private final IndexedHeap<String> minHeap;
    private final IndexedHeap<String> maxHeap;

    public Authenticator(String[] codes) {
        minHeap = new IndexedHeapTree<>(String.CASE_INSENSITIVE_ORDER);  // ASCENDING
        maxHeap = new IndexedHeapTree<>(String.CASE_INSENSITIVE_ORDER.reversed());  // DESCENDING

        for(String code : codes) {
            minHeap.insert(code);
            maxHeap.insert(code);
        }
    }

    public String getCode(int i) {
        return minHeap.getAtIndex(i) + maxHeap.getAtIndex(i);
    }
}
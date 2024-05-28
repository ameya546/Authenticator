package testheap;
import storage.IndexedHeap;
import storage.IndexedHeapTree;
public class TestScanner {
    public static void main(String[] args) {
        IndexedHeap<String> minHeap = new IndexedHeapTree<>(String.CASE_INSENSITIVE_ORDER);

        minHeap.insert("asd");
        minHeap.insert("kml");
        minHeap.insert("zjn");
        minHeap.insert("edf");
        minHeap.insert("inh");
        minHeap.insert("xdf");
        System.out.println(minHeap.getAtIndex(1));
        System.out.println(minHeap.getAtIndex(2));
        System.out.println(minHeap.getAtIndex(3));
        System.out.println(minHeap.getAtIndex(4));
        System.out.println(minHeap.getAtIndex(5));
        System.out.println(minHeap.getAtIndex(6));


        IndexedHeap<String> maxHeap = new IndexedHeapTree<>(String.CASE_INSENSITIVE_ORDER.reversed());

        maxHeap.insert("asd");
        maxHeap.insert("kml");
        maxHeap.insert("zjn");
        maxHeap.insert("edf");
        maxHeap.insert("inh");
        maxHeap.insert("xdf");

        System.out.println(maxHeap.getAtIndex(1));
        System.out.println(maxHeap.getAtIndex(2));
        System.out.println(maxHeap.getAtIndex(3));
        System.out.println(maxHeap.getAtIndex(4));
        System.out.println(maxHeap.getAtIndex(5));

    }
}
package testheap;
import storage.*;

public class TestHeap {
    public TestHeap() {
        IndexedHeap<String> heap=new IndexedHeapArray<>(String.CASE_INSENSITIVE_ORDER);
        quickTest(heap);
        System.out.println();
        heap=new IndexedHeapArray<>(String.CASE_INSENSITIVE_ORDER.reversed()); //For a max-heap!
        quickTest(heap);
        System.out.println();

        /*IndexedHeap<String> theap=new IndexedHeapTree<>(String.CASE_INSENSITIVE_ORDER);
        heap=new IndexedHeapArray<>(String.CASE_INSENSITIVE_ORDER);
        doubleTest(heap,theap); //Yields the precise same results for both!
        doubleTest(theap,new IndexedHeapTree<>(String.CASE_INSENSITIVE_ORDER.reversed()));*/

        IndexedHeap<Integer> numheap=new IndexedHeapArray<>((a,b)->a-b);
        testNumbers(numheap);
    }

    private void doubleTest(IndexedHeap<String> heap1, IndexedHeap<String> heap2) {
        for (int i=0;i<10;i++) {
            String t=Character.toString((char)(Math.random()*26+65));
            System.out.print("\t"+t);
            heap1.insert(t);
            heap2.insert(t);
        }
        System.out.println();
        for (String s:heap1) System.out.print(s);
        System.out.println();
        for (String s:heap2) System.out.print(s);
        System.out.println();
        System.out.println("At multiple indices: 2:"+heap1.getAtIndex(2)+"|"+heap2.getAtIndex(2)
            +"\t10:"+heap1.getAtIndex(10)+"|"+heap2.getAtIndex(10)+"\t6:"
            +heap1.getAtIndex(6)+"|"+heap2.getAtIndex(6));
        System.out.println("Emptying both:");
        while (!heap1.isEmpty() | !heap2.isEmpty())
            System.out.print(" ["+heap1.remove()+"|"+heap2.remove()+"]");
        System.out.println();
    }

    private void quickTest(IndexedHeap<String> heap) {
        for (int i=0;i<10;i++) {
            String t=Character.toString((char)(Math.random()*26+65));
            System.out.print("\t"+t);
            heap.insert(t);
        }
        System.out.println();
        for (String s:heap) System.out.print(s);
        System.out.println();
        while (!heap.isEmpty()) System.out.print("\t"+heap.remove());
        System.out.println();
    }

    private void testNumbers(IndexedHeap<Integer> heap) {
        System.out.println("Number time");
        for (int i=0;i<20;i++) {
            heap.insert((int)(Math.random()*1000));
        }
        for (int v:heap) System.out.print(" "+v);
        System.out.println();
        while (!heap.isEmpty()) System.out.print(" "+heap.remove());
        System.out.println();
    }
    public static void main(String[] args) {new TestHeap();}
}

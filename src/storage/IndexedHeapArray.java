package storage;

import java.util.Comparator;
import java.util.Iterator;

/**
 * Sample contiguous implementation.
 * Loosely based on example by Weiss.
 *
 * Note that you won't actually be using this for anything; consider it a sort
 * of 'sanity check'.
 */
public class IndexedHeapArray<E> implements IndexedHeap<E> {
    /* Note that the default capacity would normally either be MUCH larger, or
     * simply look nothing like this. e.g. you might expect it to be an INITIAL
     * capacity, with the expectation that it'll grow. Defining it as both
     * rigid and so small is absurd.  */
    public static final int DEFAULT_HEAP_CAPACITY=100;
    private int size; //How many members we currently have in this thing
    private E[] heap; //Yarp. Generic array. Deal with it. (Or don't)
    Comparator<E> comparator; //How we'll be defining 'priority'

    //Note: no 'default constructor' because we NEED a comparator!
    public IndexedHeapArray(Comparator<E> comparator) {
        this(DEFAULT_HEAP_CAPACITY, comparator);
    }
    @SuppressWarnings("unchecked")
    public IndexedHeapArray(int capacity, Comparator<E> comparator) {
        this.heap=(E[])(new Object[capacity+1]); //+1, since 0 is kept empty
        this.size=0;
        this.comparator=comparator;
    }

    /**
     * Inserts the element into the heap. Fairly standard heap implementation.
     *
     * @param item entry to be added
     * @throws HeapOTroubleException if there's no space (you shouldn't encounter this!)
     */
    public void insert(E item) {
        int hole = ++size; //Where the new element *could* go
        //Is this code weird? Yes. Does that matter? No.
        for (heap[0]=item; comparator.compare(item,heap[hole/2])<0; hole/=2)
            heap[hole]=heap[hole/2];
        heap[hole]=item;
        heap[0]=null; //solely for GC reasons
    }

    /**
     * Removes and returns the element with the 'highest priority', however
     * that's defined by the heap's comparator.
     *
     * @return removed element with highest priority
     * @throws HeapOTroubleException on underflow
     */
    public E remove() {
        E forReturn=peek();
        heap[1]=heap[size]; //Stick the stranded element at the top
        heap[size--]=null; //Clear the entry, then decrement the count
        percolateDown(1); //The item we moved to the root might move down!
        return forReturn;
    }

    /**
     * Note: PRIVATE! This is not for external use!
     * @param hole index of element that might need to 'swap down'
     */
    private void percolateDown(int hole) {
        int child;
        E temp=heap[hole];
        for (; hole*2 <= size; hole=child) {
            child = hole*2; //Index of left-child
            if (child!=size && comparator.compare(heap[child+1],heap[child])<0)
                child++; //'child' references smaller of two children (if both exist)
            if (comparator.compare(heap[child],temp)<0)
                heap[hole]=heap[child];
            else
                break;
        }
        heap[hole]=temp;
    }

    /**
     * Like remove, but less removing.
     *
     * @return element with highest priority
     * @throws HeapOTroubleException on underflow
     */
    public E peek() {
        return heap[1]; //Like, by definition, dood
        //Actually, on that note, we could've had a default implementation!
    }

    /**
     * ...do you seriously need this one explained?
     * Waffles. It makes waffles for you.
     *
     * @return waffles. Tasty tasty waffles.
     */
    public boolean isEmpty() {
        return size==0;
    }

    /**
     * Accessor for this heap's comparator (which defines the prioritization).
     *
     * @return the comparator
     */
    public Comparator<E> getComparator() {
        return comparator;
    }

    /**
     * Requests the member stored at a particular tree node index.
     * As a heap, this has zero value. For other uses...
     *
     * @param index index of requested element. Counting starts at 1 for root
     * @return requested element
     * @throws HeapOTroubleException if requested element/position doesn't exist
     */
    public E getAtIndex(int index) {
        if (index<1 || index>size) throw new HeapOTroubleException();
        return heap[index];
    }

    public Iterator<E> iterator() {
        return new HeapArrator();
    }
    private class HeapArrator implements Iterator<E> { //I'm not going to comment "how to count"
        private int pos=1;
        public boolean hasNext() {
            return pos<=size;
        }
        public E next() {
            return heap[pos++];
        }
    }
}

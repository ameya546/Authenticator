package storage;
import java.util.Comparator;

/**
 * Interface for a generalized Binary Heap, <em>WITH A TWIST</em>.
 * Note that this is neither a MinHeap nor a MaxHeap. Instead, it relies on a
 * <b>Comparator</b> provided in its constructor.
 * As a consequence, there is <em>no</em> expectation that the stored type (T)
 * be e.g. <em>Comparable</em>; only that a Comparator could exist to allow for
 * comparisons.
 * FUN FACT: String.CASE_INSENSITIVE_ORDER gives a lexicographic comparator.
 * FUNNER FACT: if you have a Comparator c, c.reversed() creates its complement
 *
 * Okay, so what's the twist?
 * This is an 'indexed' heap! ...the heck is that?
 * An indexed heap concerns itself not solely with acting as a heap, but also
 * cares about the <em>perceived index</em> of entries. How does that make
 * sense when the point of an ADT is to hide the underlying organization?
 * ...don't worry about it.
 *
 * If one envisions the complete tree (whether as an array, or the equivalent
 * actual tree, but still numbered according to the same convention), we should
 * be able to request an arbitrary element based on its expected position/index
 * and receive it!
 * If the requested index doesn't exist, then we're in a whole heap o' trouble!
 *
 * Additionally, the heap property normally requires only that children be
 * compared to their parents (never each other), but this model clearly relies
 * on the expectation of adhering <em>strictly</em> to the prescribed algorithm
 * (or else the indices and numbers wouldn't align).
 * @param <E> what it holds (must align with its Comparator)
 */
public interface IndexedHeap<E> extends Iterable<E> {
    /**
     * Inserts the element into the heap. Ostensibly behaves
     * like a traditional heap. In practice, appears to, whether
     * it does or not!
     * @param item entry to be added
     * @throws HeapOTroubleException if there's no space (you shouldn't encounter this!)
     */
    public void insert(E item);

    /**
     * Removes and returns the element with the 'highest priority', however
     * that's defined by the heap's comparator.
     * @return removed element with highest priority
     * @throws HeapOTroubleException on underflow
     */
    public E remove();

    /**
     * Like remove, but less removing.
     * @return element with highest priority
     * @throws HeapOTroubleException on underflow
     */
    public E peek();

    /**
     * ...do you seriously need this one explained?
     * Waffles. It makes waffles for you.
     * @return waffles. Tasty tasty waffles.
     */
    public boolean isEmpty();

    /**
     * Accessor for this heap's comparator (which defines the prioritization).
     * @return the comparator
     */
    public Comparator<E> getComparator();

    /**
     * Requests the member stored at a particular tree node index.
     * As a heap, this has zero value. For other uses...
     * @param index index of requested element. Counting starts at 1 for root
     * @return requested element
     * @throws HeapOTroubleException if requested element/position doesn't exist
     */
    public E getAtIndex(int index);

    //Note that there's obviously an Iterator<E> iterator() needed.
}
package storage;

import java.util.Comparator;
import java.util.Iterator;

public class IndexedHeapTree<E> implements IndexedHeap<E>{

    Node root = null;
    Comparator<E> comparator; //How we'll be defining 'priority'
    private int index = 0;
    /**
     * Inner class representing a node in the heap tree.
     * */
    private class Node {
        E item;
        Node left;
        Node right;

        Node (E item) {
            this.item = item;
        }
    }
    /**
     * Creates a new insance of IndexedHeapTree with a comparator
     * @param comparator the comparator used to determine priority.
     * **/
    public IndexedHeapTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }
    /**
     * Inserts a new item into the heap
     * @param item the item to be inserted.
     * **/
    @Override
    public void insert(E item) {
        ++index;
        Node node = new Node(item);

        if (index == 1) {
            root = node;
            return;
        }

        Node parentNode = root;

        int cnt  = 32, ans  = 0;
        do {
            --cnt;
            ans = index & 1 << cnt;
        } while (ans == 0);

        while (cnt > 1) {
            --cnt;
            parentNode = (index & 1 << cnt) == 0 ? parentNode.left : parentNode.right;
        }

        --cnt;
        if ((index & 1 << cnt) == 0) {
            parentNode.left = node;
        } else {
            parentNode.right = node;
        }

        swim(node, index);
    }
    /**
     * Performs the swim operation, to restore the heap after insertion.
     * @param node the starting node in the operation.
     * @param index the index of the node in the heap.
     * **/
    private void swim(Node node, int index) {
        if (index <= 1) return;

        int parentIndex = index / 2;

        Node parentNode = getNodeAtIndex(parentIndex);

        if (getComparator().compare(node.item, parentNode.item) < 0) {
            swap(node, parentNode);
            swim(parentNode, index / 2);
        }

        return;
    }
    /**
     * Swaps the items of 2 nodes.
     * @param node1 the first node.
     * @param node2 the second node.
     * **/
    private void swap(Node node1, Node node2) {
        E tmp = node1.item;
        node1.item = node2.item;
        node2.item = tmp;
    }
    /**
     * Removes and returns the item with the highest priority from the heap.
     * @return  the item with the highest priority or null if empty..
     * **/
    @Override
    public E remove() {
        if(isEmpty()) return null;

        if(index == 1) {
            index--;
            return root.item;
        }

        E result = root.item;

        Node lastNode = getNodeAtIndex(index);
        Node parentOfLastNode = getNodeAtIndex(index / 2);
        if(index % 2 == 0) {
            parentOfLastNode.left = null;
        } else {
            parentOfLastNode.right = null;
        }

        swap(root, lastNode);
        index--;

        sink(root, 0, index);

        return result;
    }
    /**
     * Perfroms the sink operation to restore the heap after removing the root item.
     * @param node the node at the root of the operation.
     * @param curIndex the current index of the node.
     * @param index the last index in the heap.
     * **/
    private void sink (Node node, int curIndex, int index) {
        // case 1 = no child node
        if (node == null || (node.left == null && node.right == null)) {
            return;
        }

        // case 2 = only left child is present
        if(node.right == null) {
            if(getComparator().compare(node.item, node.left.item) < 0) {
                return;
            } else {
                swap(node, node.left);
                return;
            }
        }

        // case 3 = both children present
        // case 3.1 = node item is greater than both child
        if((getComparator().compare(node.item, node.left.item) < 0 && getComparator().compare(node.item, node.right.item) < 0) ) {
            return;
        } else if (getComparator().compare(node.left.item, node.right.item) < 0 ) {
            swap(node, node.left);
            sink(node.left, curIndex * 2, index);
        } else if (getComparator().compare(node.left.item, node.right.item) >= 0) {
            swap(node, node.right);
            sink(node.right, curIndex * 2 + 1, index);
        }
    }
    /**
     * Retrieves the item with the highest priority from the heap tree without removing it.
     * @return the item with the highest priority or null if empty.
     * **/
    @Override
    public E peek() {
        return isEmpty() ? null : root.item;
    }
    /**
     * Checks if heap is empty.
     * @return true if the heap is empty false otherwise.
     * **/
    @Override
    public boolean isEmpty() {
        return index == 0 ? true : false;
    }
    /**
     * Returns the comparator used in the heap tree.
     * @return the comparator.
     * **/
    @Override
    public Comparator getComparator() {
        return this.comparator;
    }
    /**
     * Retrieves the node at the specified index in the heap.
     * @param index the index of the node.
     * @return the node at the specified index.
     * **/
    private Node getNodeAtIndex(int index) {
        Node node = root;
        int cnt  = 32, ans  = 0;
        do {
            --cnt;
            ans = index & 1 << cnt;
        } while (ans == 0);

        while (cnt > 0) {
            --cnt;

            node = (index & 1 << cnt) == 0 ? node.left : node.right;
        }

        return node;
    }
    /**
     * Retrieves the item at the specified index in the heap.
     * @return the item at the specified index.
     * **/
    @Override
    public E getAtIndex(int index) {
        Node node = root;
        int cnt  = 32, ans  = 0;
        do {
            --cnt;
            ans = index & 1 << cnt;
        } while (ans == 0);

        while (cnt > 0) {
            --cnt;

            node = (index & 1 << cnt) == 0 ? node.left : node.right;
        }

        return node.item;
    }
    /**
     * Returns an iterator over the elements of the heap.
     * @return an iterator.
     * **/
    @Override
    public Iterator iterator() {
        return null;
    }
}
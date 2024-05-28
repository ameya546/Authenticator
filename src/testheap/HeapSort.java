package testheap;

import storage.HeapOTroubleException;
import storage.IndexedHeap;
import storage.IndexedHeapTree;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class HeapSort {

    private static final String DATA_TYPE_INTEGERS = "INTEGERS";
    private static final String DATA_TYPE_STRINGS = "STRINGS";
    private static final String SORT_ORDER_ASC = "ASC";
    private static final String SORT_ORDER_DESC = "DESC";

    private String userDataType = DATA_TYPE_INTEGERS;
    private int inputNumberOfIntegers = 0;
    private String[] inputStrings = null;
    private String userSortOrder = SORT_ORDER_ASC;

    public HeapSort (){
    }

    public static void main(String[] args) {
        HeapSort heapSort = new HeapSort();
        boolean continueFlag = true;
        Scanner scanner = new Scanner(System.in);

        while(continueFlag) {
            heapSort.getUserInput(scanner);

            if (DATA_TYPE_INTEGERS.equals(heapSort.userDataType)){
                int[] unsortedIntegers = new int[heapSort.inputNumberOfIntegers];
                IndexedHeap<Integer> maxHeap = heapSort.getIntegerHeap();

                Random rand = new Random();
                for (int i = 0; i < heapSort.inputNumberOfIntegers; i++) {
                    unsortedIntegers[i] = rand.nextInt(1000000000);
                }

                System.out.println("Unsorted Integers : " + Arrays.toString(unsortedIntegers));
                int[] sortedIntegers = heapSort.sortIntegers(unsortedIntegers, maxHeap);
                System.out.println("Sorted Integers : " + Arrays.toString(sortedIntegers));

            } else if (DATA_TYPE_STRINGS.equals(heapSort.userDataType)){
                IndexedHeap<String> maxHeap = heapSort.getStringHeap();

                System.out.println("unsorted Strings : " + Arrays.toString(heapSort.inputStrings));
                String[] sortedArray = heapSort.sortStrings(heapSort.inputStrings, maxHeap);
                System.out.println("Sorted Strings : " + Arrays.toString(sortedArray));

            } else {
                throw new HeapOTroubleException();
            }

            continueFlag = "Y".equalsIgnoreCase(heapSort.checkUserWantstoContinue(scanner)) ? true : false;
        }

        scanner.close();
    }

    /**
     * Gets user input for the data type and sort order.
     * @param scanner Scanner object to read user input.
     */
    private void getUserInput(Scanner scanner) {
        userDataType = getDataTypeFromUser(scanner);
        getDataFromUser(scanner);
        userSortOrder = getSortOrderFromUser(scanner);
    }
    /**
     * Prompts the user to imput the data type string or int.
     * @param scanner Scanner object to read user input.
     * @return The selected data type as a string.
     */
    public String getDataTypeFromUser (Scanner scanner){
        System.out.print("Select Data Type - 1.Integers  2.Strings : ");
        int choice = scanner.nextInt();
        System.out.println();
        if (choice == 1){
            return this.DATA_TYPE_INTEGERS;
        } else if (choice == 2) {
            return this.DATA_TYPE_STRINGS;
        }

        return "INVALID";
    }
    /**
     * Gets data from user based of the selected data type.
     * @param scanner Scanner object to read user input.
     */
    public void getDataFromUser (Scanner scanner){
        if (DATA_TYPE_INTEGERS.equals(userDataType)) {
            System.out.print("Enter number of Integers to sort : ");
            this.inputNumberOfIntegers = scanner.nextInt();
        } else if(DATA_TYPE_STRINGS.equals(userDataType)) {
            System.out.print("Enter Strings to sort : ");

            String line = null;
            do {
                line = scanner.nextLine();
            } while (line == null || "".equals(line));

            inputStrings= line.split(" ");
        }
        System.out.println();
        return;
    }
    /**
     * Prompts the user to select the sort order (ascending or descending).
     * @param scanner Scanner object to read user input.
     * @return The selected sort order as a string.
     */
    public String getSortOrderFromUser (Scanner scanner){
        System.out.print("Select Sort Order - 1.Ascending  2.Descending : ");
        int choice = scanner.nextInt();
        System.out.println();
        if (choice == 1){
            return this.SORT_ORDER_ASC;
        } else if (choice == 2) {
            return this.SORT_ORDER_DESC;
        }

        return "INVALID";
    }
    /**
     * Creates an instance of the IndexedHeap for integers based on the selected sort order.
     * @return  An IndexedHeap object for integers.
     */
    private IndexedHeap<Integer> getIntegerHeap() {
        return SORT_ORDER_ASC.equals(userSortOrder) ?
                new IndexedHeapTree<Integer>((a, b) -> a - b) :
                new IndexedHeapTree<Integer>((a, b) -> b - a);
    }
    /**
     * Creates an instance of the IndexedHeap for String based on the selected sort order.
     * @return  An IndexedHeap object for strings.
     */
    private IndexedHeap<String> getStringHeap() {
        return SORT_ORDER_ASC.equals(userSortOrder) ?
                new IndexedHeapTree<String>(String.CASE_INSENSITIVE_ORDER) :
                new IndexedHeapTree<String>(String.CASE_INSENSITIVE_ORDER.reversed());
    }
    /**
     * Sorts the array of integers using the specified IndexHeap
     * @param integers Array of integers to be sorted
     * @param maxHeap IndexedHeap object to perform the sorting operation.
     * @return  The sorted array of integers.
     */
    private int[] sortIntegers(int[] integers, IndexedHeap<Integer> maxHeap){
        for(int x : integers)
            maxHeap.insert(x);

        for (int i = 0; i < integers.length; i++)
            integers[i] = maxHeap.remove();

        return integers;
    }
    /**
     * Sorts the array of strings using the specified IndexHeap
     * @param strings Array of strings to be sorted
     * @param maxHeap IndexedHeap object to perform the sorting operation.
     * @return  The sorted array of strings.
     */
    private String[] sortStrings(String[] strings, IndexedHeap<String> maxHeap){
        for (String str : strings) {
            maxHeap.insert(str);
        }

        for (int i = 0; i < strings.length; i++) {
            strings[i] = maxHeap.remove();
        }

        return strings;
    }
    /**
     * Asks if the user wants to try again
     * @param scanner Scanner object to read user input.
     * @return  "Y" if the user wants to continue "N" otherwise.
     */
    private String checkUserWantstoContinue(Scanner scanner) {
        System.out.print("Do you want to try again (y/n) ? : ");
        String userChoice = scanner.next();
        return "y".equalsIgnoreCase(userChoice) ? "Y" : "N";
    }
}
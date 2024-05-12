import java.util.*;

public class Main {
    private static final Random random = new Random(666); /* We specify the root to fix the randomness,
    thus we always obtain the same array with getRandomNumeralArray function, allowing us to work with the same data
    everytime */

    public static void main(String[] args) {
        System.out.println("Starting...");
        // initialize your array or other data here
        long start = System.nanoTime();
        // call the function you want to test the time it takes to run it here
        long end = System.nanoTime();
        System.out.printf("It took %f milliseconds%n", (double) (end - start) / 1_000_000);
    }

    public static int sumUpTo(int num) { // O(1) time complexity
        return num >= 0 ? num * (num + 1) / 2 : -1;
    }

    // Here is my implementation of a Binary Search Tree to test the Binary Search functionality
    public static class BinarySearchTree <T extends Comparable<T>> {
        private class Node {
            private final T value;

            private Node leftNode = null;
            private Node rightNode = null;

            public Node(T value) {
                this.value = value;
            }

            public T getValue() {
                return value;
            }

            public void addValue(T value) {
                if (getValue().compareTo(value) < 0) {
                    if (rightNode == null) {
                        rightNode = new Node(value);
                    }
                    else {
                        rightNode.addValue(value);
                    }
                } else {
                    if (leftNode == null) {
                        leftNode = new Node(value);
                    }
                    else {
                        leftNode.addValue(value);
                    }
                }
            }

            public boolean findValue(T value) {
                int compareResult = getValue().compareTo(value);
                if (compareResult == 0) return true;
                else if (compareResult < 0) {
                    return rightNode != null && rightNode.findValue(value);
                } else {
                    return leftNode != null && leftNode.findValue(value);
                }
            }
        }

        private final Node root;

        public BinarySearchTree(T[] array) { // the array parameter shouldn't contain duplicates for the BinarySearch to obtain O(log(n)) time complexity
            root = new Node(array[0]);
            for (int i = 1; i < array.length; i++) {
                root.addValue(array[i]);
            }
        }

        public boolean binarySearch(T value) { // O(log(n)) time complexity
            return root.findValue(value);
        }
    }
    public static int sumUpToLoop(int num) { // O(n) time complexity
        if (num >= 0) {
            int sum = 0;
            for (int i = 1; i <= num; i++) {
                sum += i;
            }
            return sum;
        }
        return -1;
    }

    public static void mergeSort(int[] array, int start, int end) { // O(n*log2(n)) time complexity
        if (end - start < 2) return;
        int mid = (end + start) / 2;
        mergeSort(array, start, mid);
        mergeSort(array, mid, end);
        Merge(array, start, mid, end);
    }
    private static void Merge(int[] array, int start, int mid, int end) {
        if (array[mid - 1] <= array[mid]) return;
        int i = start;
        int j = mid;
        int tempIndex = 0;
        int[] tempArray = new int[end - start];
        while (i < mid && j < end) {
            tempArray[tempIndex++] = array[i] < array[j] ? array[i++] : array[j++];
        }
        System.arraycopy(array, i, array, start + tempIndex, mid - i);
        System.arraycopy(tempArray, 0, array, start, tempIndex);
    }

    public static void bubbleSortIncreasing(int[] array) { // O(n^2) time complexity
        for (int i = 0; i < array.length -1; i++) {
            for (int j = i +1; j < array.length; j++) {
                if(array[i] > array[j]) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
    }

    // Utility functions

    /**
     * A function to obtain a random array of numerals of the given size
     * @param size the size of the returned array
     * @return a random array of numerals
     */
    public static int[] getRandomNumeralArray(int size) {
        return getRandomIntegerArray(size, 10);
    }

    /**
     * A function to obtain a random array of integers of the given size
     * @param size the size of the returned array
     * @param bound the upper bound for the values of the array (excluded)
     * @return a random array of integers
     */
    public static int[] getRandomIntegerArray(int size, int bound) {
        if (size >= 0) {
            int[] result = new int[size];
            for (int i = 0; i < size; i++) {
                result[i] = random.nextInt(bound);
            }
            return result;
        }
        return null;
    }
}
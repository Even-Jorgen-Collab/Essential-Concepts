package Algorithms.Search_algorithms;

public class BinarySearch {
    private BinarySearch() {}

    /**
     * A search algorithm inspired from notes from class.
     * <p> O(log_2(n)) <p>
     * @param array to search through
     * @param value to search for
     * @return index of value if found
     * @version 14.09.22
     * @apiNote NB: The {@param array} must be sorted
     */
    public static int binarySearch(int[] array, int value) {

        // Lower point of our searching range
        int lower = 0;
        // Upper point of our searching range
        int upper = array.length;
        // The point where we split our searching range
        int split;

        // While our range is not smaller than two elements
        while (upper-lower > 1) {
            // Finds the middle point in our range, where we will split
            split = lower + (int) Math.floor((double) (upper-lower)/2);
            // Is the value at the split larger than the value?
            if (array[split] > value) {
                //  The split becomes our upper range
                upper = split - 1;
            // Is the value at the split smaller than the value?
            } else if (array[split] < value) {
                // The split becomes our lower range
                lower = split + 1;
            // Is the value at the split the value we are looking for?
            } else {
                // Return the split, which then is the index of the value
                return split;
            }
        }

        // The value is not in the list
        return -1;
    }
}

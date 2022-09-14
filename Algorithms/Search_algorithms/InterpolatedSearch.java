package Algorithms.Search_algorithms;

public class InterpolatedSearch {
    private InterpolatedSearch() {}

    /**
     * A search algorithm inspired from notes from class.
     * <p> {@value O((log_2((log_2(n)))} <p>
     * @param array to search through
     * @param value to search for
     * @return index of value if found
     * @version 14.09.22
     * @apiNote NB: The {@param array} must be sorted & uniformly distributed
     */
    public static int interpolatedSearch(int[] array, int value) { 

        // Lower point of our searching range
        int lower = 0;
        // Upper point of our searching range
        int upper = array.length;
        // The point where we split our searching range
        int split;

        // While our range is not smaller than two elements
        while (upper-lower > 1) {
            // Finds the the point to split based on interplation
            split = interpolate(array, lower, upper-1, value);
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


    /**
     * Determine where to split based on interplation
     * @param array to search through
     * @param value to search for
     * @param upper lower point of searching range
     * @param value upper point of searching range
     * @return (int) interplated split index
     */
    private static int interpolate(int[] array, int lower, int upper, int value) {
        int width = array[upper] - array[lower];
        double ratio = (double) (value - array[lower])/width;

        return lower + (int) Math.floor(ratio * (upper-lower));
    }
}

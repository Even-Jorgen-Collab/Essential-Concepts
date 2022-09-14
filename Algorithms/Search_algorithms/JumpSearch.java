package Algorithms.Search_algorithms;

public class JumpSearch {
    private JumpSearch() {}

    /**
     * A search algorithm inspired from notes from class.
     * <p> {@value O(sqrt(n))} <p>
     * @param array to search through
     * @param value to search for
     * @return index of value if found
     * @version 14.09.22
     * @apiNote NB: The {@param array} must be sorted
     */
    public static int jumpSearch(int[] array, int value) { 

        // Calculating jump size based on the square root of the length of the array
        int jump = (int) Math.floor(Math.sqrt(array.length));

        // Containing the element in the array that is currently inspected
        int current = 0;


        // Jumping through elements 
        while(current < array.length && array[current] < value) {
            current += jump;
        }

        if (current >= array.length) {return -1;}
        if (array[current] == value) {return current;}

        /**
         * If the algorithm cant find the element, for example, if 
         * it jumped over the value, it would be vise to make a new 
         * return statement like this:
         */
        if (array[current] > value) {
            // return linearSearch(array, value, currentJump);
        }
        return -1;
    }
}

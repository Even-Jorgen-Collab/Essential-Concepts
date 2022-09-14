package Algorithms.Sorting_algorithms;

public class InsertionSort {
    private InsertionSort() {}


    /**
     * A sorting algorithm inspired from notes from class.
     * <p> Time & Space complexity: <p>
     *  <table> <tr>
                <th>Scenario</th> <th>Memory</th> <th>Time</th> 
            </tr>
            <tr>
                <td>Best</td> <td>O(1)</td> <td>O(n)</td> 
            </tr>
            <tr>
                <td>Average</td> <td>O(1)</td> <td>O(n^2)</td> 
            </tr>
            <tr>
                <td>Worst</td> <td>O(1)</td> <td>O(n^2)</td> 
            </tr> 
        </table>
     * @param array to sort
     * @version 14.09.22
     * @Type: Stable sorting
     * @apiNote NB: The {@param array} must contain integers
     */
    public static void insertionSort(int[] array) { 
        int position;
        for (int index = 1; index < array.length-1; index++) {
            position = findPosition(array, index);
            insert(array, position, index);
        }
    }


    /**
     * Moves all elements between position and last 1 index forward.
     * The <code>last</code> value gets placed at <code>array[position]</code> 
     * @param array array to move
     * @param position index of first element in the section
     * @param last index of last elemeent in the section
     */
    private static void insert(int[] array, int position, int last) {
        int value = array[last];
        for (int index = last; index > position; index--) {
            array[index] = array[index-1];
        }
        array[position] = value;
    }


    /**
     * Finds the index of the first value which is greater than
     * the value at the <code>last</code> index 
     * @param array array to search in
     * @param last index of an element
     * @return index of value greater than or equal to <code>last</code>
     */
    private static int findPosition(int[] array, int last) {
        for (int index = 0; index < last; index++) {
            if (array[index] >= array[last]) {
                return index;
            }
        }
        return last;
    }
}

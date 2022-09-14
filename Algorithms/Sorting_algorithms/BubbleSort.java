package Algorithms.Sorting_algorithms;

public class BubbleSort {
    private BubbleSort() {}


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
    public static void bubbleSort(int[] array) { 

        // Is the two values in question swapped:
        boolean swapped = true;

        while (swapped) {
            swapped = false;
            for (int i = 1; i < array.length-1; i++) {
                if (array[i] < array[i-1]) {
                    swap(array, i-1,i);
                    swapped = true;
                }
            }
        }
    }


    /**
     * Swaps the index of two values in an array
     * @param array array to use
     * @param left index of the left value
     * @param right index of the right value
     */
    private static void swap(int[] array, int left, int right) {
        int temporary = array[right];
        array[right] = array[left];
        array[left] = temporary;
    }
}

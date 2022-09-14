package Algorithms.Sorting_algorithms;

public class SelectionSort {
    private SelectionSort() {}


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
     * @Type: Unstable sorting
     * @apiNote NB: The {@param array} must contain integers
     */
    public static void selectionSort(int[] array) { 

        // Minimum value
        int minimum; 

        // Finds minimum value between current index and end index
        // Swaps the minimum value and the index
        for (int index = 0; index < array.length-2; index++) {
            minimum = findMinimum(array, index);
            swap(array, index, minimum);
        }
    }


    /**
     * Finds the minimum value in the specified section
     * in an array
     * @param array to search through
     * @param start start index of the section to search through
     * @return int index of the minimum value
     */
    private static int findMinimum(int[] array, int start) {
        int minimum = start;

        for (int index = start+1; index < array.length-1; index++) {
            if (array[index] < minimum) {
                minimum = index;
            }
        }
        return minimum;
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

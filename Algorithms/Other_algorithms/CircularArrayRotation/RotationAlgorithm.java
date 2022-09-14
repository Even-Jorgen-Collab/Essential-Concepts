package Algorithms.Other_algorithms.CircularArrayRotation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RotationAlgorithm {
    private RotationAlgorithm() {}
    

    /**
     * Circular Array Rotation Algorithm
     * @param a List to be rotated
     * @param k Number of rotations
     * @author Jorgfi
     * @return rotated list as array
     */
    public static int[] circularArrayRotation(List<Integer> a, int k) {
        /**
        *   Implemented algorithm
        * -----------------------------------------------------------------------
        */
        
        //  Input array, a1, length: n
        final  ArrayList<Integer> a1 = (ArrayList<Integer>) a;
        // Mofied array, a2, length: n+k
        final int[] a2 = new int[a1.size()+k];
        // Output array, a3, length: n
        final int[] a3 = new int[a1.size()];
        
        
        /**
        *   Operation 1
        * -----------------------------------------------------------------------
        */
        
        // Adds elements a1[0] -> a1[n] into a2[k] -> a2[n+k] 
        int counter = 0;
        for (int i = k; i < a2.length; i++) {
            a2[i] = a1.get(counter);
            counter++;
        }
        
         
        /**
        *   Operation 2
        * -----------------------------------------------------------------------
        */
    
        // Adds elements a2[n+1] -> a2[n+k] into a3[0] -> a3[k-1] 
        for (int i = a1.size(); i < a2.length; i++) {
            a3[i-a1.size()] = a2[i];
        } 
        
        // Adds a2[k] -> a2[n] into a3[k] -> a3[n]
        for (int i = k; i < a1.size(); i++) {
            a3[i] = a2[i];
        }
        
        
        // Return operated array
        return a3;     
    }



    
    /**
     * Circular Array Rotation Method
     * @param a List to be rotated
     * @param k Number of rotations
     * @author Java
     * @return rotated list
     */
    public static List<Integer> usingCollectionInterface(List<Integer> a, int k) {
        Collections.rotate(a, k); return a;
    }
}
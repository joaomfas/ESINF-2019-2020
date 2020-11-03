/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genericsortingarrays;

import java.util.Arrays;

/**
 *
 * @author DEI-ISEP
 */
public class GenericSortingArrays {

    /**
     * Swaps two vector positions O(1)
     */
    public static <E> void swap(E[] v, int i, int j) {

        E temp = v[i];
        v[i] = v[j];
        v[j] = temp;
    }

    //  printArray                                
    public static <E> void printArray(E[] v) {
        for (E element : v) {
            System.out.println(", " + element);
        }
    }

    /**
     * Selection Sort Algorithm
     */
    public static <E extends Comparable<E>> void selectionSort(E[] v) {
        for (int i = 0; i < v.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < v.length; j++) {
                if (v[j].compareTo(v[min]) < 0) {
                    min = j;
                }
            }
            swap(v, i, min);
        }
    }

    /**
     * Bubble Sort Algorithm
     *
     * @param v
     */
    public static <E extends Comparable<E>> void bubbleSort(E[] v) {
        for (int i = 0; i < v.length - 1; i++) {
            for (int j = v.length - 1; j > i; j--) {
                if (v[j - 1].compareTo(v[j]) > 0) {
                    swap(v, j, j - 1);
                }
            }
        }
    }

    /**
     * insertionSort Algorithm
     */
    public static <E extends Comparable<E>> void insertionSort(E[] v) {
        for (int i = 1; i < v.length; i++) {
            int j = i;
            E x = v[i];
            while (j > 0 && (x.compareTo(v[j-1]) < 0)) {                
                v[j] = v[j-1];
                j--;
            }
            v[j] = x;
        }
    }

    /**
     * Mergesort Algorithm
     */
    private static <E extends Comparable<E>> void merge(E[] S1, E[] S2, E[] S) {
        int left = S.length/2;
        int right = S.length - left;
        
        int i = 0;
        int j = 0;
        int k = 0;
        while(i < left && j < right){
            if(S1[i].compareTo(S2[i]) <= 0){
                S[k++] = S1[i++];
            }else{
                S[k++] = S2[j++];
            }
        }
        while(i < left){
            S[k++]=S1[i++];
        }
        while(j < right){
            S[k++]=S2[j++];
        }
    }

    public static <E extends Comparable<E>> void mergeSort(E[] S) {
        if(S.length >= 2){
            int mid = (S.length)/2;
            E[] S1 = (E[]) new Comparable[mid];
            E[] S2 = (E[]) new Comparable[S.length-mid];
            
            System.arraycopy(S, 0, S1, 0, S1.length);
            System.arraycopy(S, S1.length, S2, 0, S2.length);
            mergeSort(S1);
            mergeSort(S2);
            
            merge(S1,S2,S);
        }
    }

    /**
     * Quicksort Algorithm
     */
    public static <E extends Comparable<E>> void quickSort(E v[]) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private static <E extends Comparable<E>> void quickSort(E v[], int left, int right) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

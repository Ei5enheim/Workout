/*
 *  Author: Rajesh Gopidi
 *
 */

import java.util.*;
import java.io.*;

public class InPlaceMergeSort {

    static BufferedReader reader = null;

    public static int readInput () throws Exception {

        int number = -1;
        if (reader == null )
            reader = new BufferedReader(new InputStreamReader(System.in));

        String line = reader.readLine().trim();
        if (line != null)
            number = Integer.valueOf(line);

        //System.out.println("number of TestCases " + number);

        return number;
    }

    public static String readLine () throws Exception {

        if (reader == null )
            reader = new BufferedReader(new InputStreamReader(System.in));

        String line = reader.readLine().trim();

        return (line);

    }

    public void merge (int[] array, int a, int b, int x, int y, int w) {

        int i = a, j = x;
        int temp = 0;

        while (i<=b && j <= y) {

            if (array[i] < array[j]) {
                temp = array[i];
                array[i] = array[w];
                array[w] = temp;         
                i++;
            } else {
                temp = array[j];
                array[j] = array[w];
                array[w] = temp;         
                j++;
            }
            w++;

            while (i<=b) {
                temp = array[i];
                array[i] = array[w];
                array[w] = temp;         
                i++;
                w++;
            }

            while (j<=y) {
                temp = array[j];
                array[j] = array[w];
                array[w] = temp;         
                j++;
                w++;
            }
        }
    }

    public void insertionSort (int[] array, int a, int b) {

        int i = a, j = a-1;

        while (i <= b) {
            int key = array[i];
            int k = i-1;
            while (k > -1 && array[k] > key) {
                array[k+1] = array[k];
                k--;
            }

            if (k+1 != i) {
                array[k+1] = key;
            }
            i++;
        }
    }

    public void mergeSort (int[] array, int l, int u) {

        if (u-l < 1)
           return;
       
        if (u-l == 1) 
            insertionSort(array, l, u);

        int m = (l + u)/2, w = l + u - m;
        int u1 = w;

        sort(array, l, m, w);
        while (w-l > 1) {
            u1 = m;  
            m = (l+u1)/2;
            w = l + u1 - m;
            sort(array, w, u1, l);
            merge(array, l, m, u1+1, u, w);
        }
        insertionSort(array, l, u);  
        print(array, u);
    }

    public void sort (int[] array, int l, int u, int w) {

        int m = 0;
        if (u - l > 0) {
            m = (u + l)/2;
            mergeSort(array, l, m);
            mergeSort(array, m+1, u);
            merge(array, l, m, m+1, u, w);
        } 
    }

    public void print (int[] array, int u) {

        int i = 0;

        System.out.println();
        while (i < u) {
            System.out.print(array[i] + " ");
            i++;
        }
        System.out.println(array[u]);
    }

    public static void main (String[] args) throws Exception {
       
        InPlaceMergeSort obj = new InPlaceMergeSort(); 

        int numberOfTestCases = readInput();
        
        while(numberOfTestCases-- > 0) {
            
            int size = readInput();
            String[] in = readLine().split(" ");
            int[] nums = new int[size];
            
            int i = 0;
            
            while ( i < size) {
                nums[i] = Integer.valueOf(in[i]);    
                i++;
            }
            obj.mergeSort(nums, 0, size-1);
        }
    }
}

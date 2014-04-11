/**
 * Author: Rajesh Gopidi
 *
 */

public class SelectionSort 
{
    public static void rSort (int[] array, int start, int end)
    {
        if (start == end)
            return;

        for (int i = start; i <= end; i++) {
            if (array[start] > array[i]) {
                array[start] ^= array[i];
                array[i] ^= array[start];
                array[start] ^= array[i];
            }
        }
        rSort (array, start+1, end);
    }

    public static void sort (int[] array)
    {

        for (int i = 0; i < array.length -1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    array[i] ^= array[j];
                    array[j] ^= array[i];
                    array[i] ^= array[j];
                }
            }
        }
    }

    public static void main (String[] args)
    {
        int[] array = {2,9,5,8,1,3,6,12,9,5,11};
        
        SelectionSort.rSort(array, 0, array.length-1);
        SelectionSort.sort(array);
        
        for (int i = 0; i < array.length; i++)
            System.out.print(array[i]+", ");

        System.out.println();
    }

}

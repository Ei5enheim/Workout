/*
 *  Author: Rajesh Gopidi
 *
 */

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

    public void mergeSort (int[] array, int i, int j) {

        if (j-i < 1) 
            return;
        
        if (j-i == 1) 
            insertionSort(array, i, j);



    }

    public static void main (String[] args) {


    }


}

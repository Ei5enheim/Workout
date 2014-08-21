/*
 * Author: Rajesh Gopidi
 *
 */

public class Merge
{

    public static int[] merge (int[] left, int[] right)
    {
        int bound = left.length + right.length;
        int[] list = new int[bound];
        int leftIndx = 0, rightIndx = 0;
        int leftRef = -9999, rightRef = -9999;

        for (int indx = 0; indx < bound; indx++) {

            if (leftIndx < left.length) {
                leftRef = left[leftIndx];
            } else {
                leftRef = 9999;
            }

            if (rightIndx < right.length) {
                rightRef = right[rightIndx];
            } else {
                rightRef = 9999;
            }

            if (leftRef < rightRef) {
                list[indx] = leftRef;
                leftIndx++;
            } else {
                list[indx] = rightRef;
                rightIndx++;
            }            
        }
        return (list);
    }

    public static void main (String[] args)
    {
        int[] list1 = {1, 4, 8, 13, 19};
        int[] list2 = {3, 6, 9, 11, 16, 17, 21};

        int[] list = merge(list1, list2);

        System.out.println();
        for (int elem: list) {
            System.out.print(elem + ", ");
        }
        System.out.println();
    }

}

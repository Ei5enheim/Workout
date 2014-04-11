/**
 * Author: Rajesh Gopidi
 *
 */

public class MPSA
{
    static int start;
    static int end;

    public static int max (int x, int y)
    {
        return (x > y ? x:y);
    }

    public static int min (int x, int y)
    {
        return (x < y ? x : y);
    }

    public static int maxProduct (int[] seq)
    {
        int max_product = 1, min_product = 1, max_sofar = 0xFFFFFFFF;
        int temp_max_begin = -1;
        int temp_min_begin = -1;

        for (int i = 0; i < seq.length; i++) {
            if (seq[i] > 0) {
                max_product *= seq[i]; 
                min_product = min(min_product * seq[i], 1);
                if (temp_max_begin == -1)
                    temp_max_begin = i;
            } else if (seq[i] == 0) {
                max_product = 1;
                min_product = 1;
                temp_max_begin = -1;
                temp_min_begin = -1;
            } else {
                int temp = max_product;
                int swap = -1;
                if (min_product*seq[i] > 1) {
                    swap = temp_max_begin;
                    temp_max_begin = temp_min_begin;
                    max_product = min_product*seq[i];
                } else {
                    temp_max_begin = -1;
                    max_product  = 1;
                }
                //max_product = max (min_product*seq[i], 1);
                temp_min_begin = swap;
                min_product = temp * seq[i];
            }

            if (max_product > max_sofar) {
                max_sofar = max_product;
                start = temp_max_begin;
                end = i;
            }
        }
        return (max_sofar);
    }

    public static void display (int[] seq)
    {
        for (int i = MPSA.start; i <= MPSA.end; i++) 
            System.out.print(seq[i] + ",");
    }

    public static void main(String[] args)
    {
        int[] seq = {-2, 2, -3, -5, 2};
        System.out.println(MPSA.maxProduct(seq));    
        MPSA.display(seq);
    }
} 

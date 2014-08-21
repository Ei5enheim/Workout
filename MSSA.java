/**
 * Author : Rajesh Gopidi
 */

public class MSSA
{
    public static int start;
    public static int end;
    
    public static int kadaneAlgo (int[] seq)
    {
        int curr_max_sum = seq[0];
        int max_sum = 0xFFFFFFFF;
        int temp_start = 0;

        for (int i = 1; i < seq.length; i++) {
            if (curr_max_sum < 0) {
                temp_start = i;
                curr_max_sum = seq[i]; 
            } else {
                curr_max_sum += seq[i];
            }

            if (curr_max_sum > max_sum) {
                start = temp_start;
                max_sum = curr_max_sum;
                end = i;
            }
        }
        return (max_sum);
    }

    public static void main (String[] args)
    {
        int[] seq = {-2, -3, 4, -1, -2, 1, 5, -3};
        System.out.println("Maximum sum is: " + MSSA.kadaneAlgo(seq));

        for (int i = MSSA.start; i < MSSA.end; i++)
            System.out.print(seq[i] + ",");

    }
} 

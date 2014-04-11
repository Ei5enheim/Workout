/*
 * Author: Rajesh Gopidi
 *
 */

public class Combinations 
{
    static int r1, r2, r3;

    public static void generate(int[] pattern)
    {
        for (int i = 0; i < 4; i++) {
            if (pattern[i] < 0 || pattern[i] > 3) {
                pattern[i] = 4;
                for (int j = i+1; j < 5; j++) {
                    if (pattern[j] < 0 || pattern[j] > 3) {
                        pattern[j] = 5;
                        for (int k = j+1; k < 6; k++) {
                            if (pattern[k] < 0 || pattern[k] > 3) {
                                pattern[k] = 6;
                                display(pattern);
                                return;
                            }
                        }
                    }
                }   
            }
        }
    }

    public static void display(int[] pattern)
    {
        for (int k = 0; k < pattern.length; k++)
            System.out.print(pattern[k] + " ");
        //System.out.println();
    }

    public static void initialize(int[] pattern, int start, int end)
    {
        for (int k = start; k <= end; k++)
            pattern[k] = -1;
    }

    public static void execute(int[] pattern)
    {
        int a = 0, b=0, c=0;

        for (int i = 0; i < pattern.length; i++) {
            switch (pattern[i]) {
            case (1):
                r2 = b;
                break;
            case (2):
                r1 = a;
                //c = 1;
                break;
            case (3):
                //r1 = a;
                c = 1;
                //System.out.print("  r1 = "+ r1+"  r2= "+r2);
                break;
            case (4):
                //a =1;
                r3 = c;
                break;
            case (5):
                a= 1;
                //r3 =c;
                break;
            case(6):
                b =1;
                //System.out.print("  r3= "+r3);
                break;
            }
        }
    }

    public static void main (String[] args)
    {
        int pattern[] = new int[6];

        for (int i = 0; i < 4; i++) {
            initialize(pattern, 0, 5);
            pattern[i] = 1;
            for (int j = i+1; j < 5; j++) {
                initialize(pattern, i+1, 5);
                pattern[j] = 2;
                
                for (int k = j+1; k < 6; k++) {
                    initialize(pattern, j+1, 5);
                    pattern[k] = 3;
                    generate(pattern);
                    execute(pattern);
                    System.out.print(" r1 = "+ r1+"  r2 = "+r2+"  r3 = "+r3);
                    System.out.println();
                }
            }
        }

    }
}

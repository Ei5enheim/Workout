/**
 * Author: Rajesh Gopidi
 *
 */

public class ConstructingLMIS
{
    private int[] subSequence;
    private int[] backPointers;
    private int len;
    private int[] seq;
    
    public ConstructingLMIS()
    {

    }

    public ConstructingLMIS (int len, int[] seq)
    {
        this.seq = seq;
        subSequence = new int[len];
        backPointers = new int[len];
    }

    public int findPos (int[] A, int l, int r, int key)
    {
        int m = 0;

        while (r - l > 1) {
            m = l + (r - l)/2;    
            if (seq[A[m]] >= key)
                r = m;
            else
                l = m;        
        }
        return (r);
    }

    public int compute()
    {
        subSequence[0] = 0;
        backPointers[0] = -1;
        len = 1;

        for (int i = 1; i < seq.length; i++) {
           if (seq[i] > seq[subSequence[len-1]]) {
                backPointers[i] = subSequence[len-1];
                subSequence[len++] = i;
            } else if (seq[i] < seq[subSequence[0]]) {
                subSequence[0] = i;
                backPointers[i] = -1;
            } else {
                int index = findPos(subSequence, 0, len-1, seq[i]);
                subSequence[index] = i;
                backPointers[i] = subSequence[index - 1];
            } 
        }
        return (len);
    }

    public void display ()
    {
        int i = subSequence[len-1];

        System.out.println();
        while (i >= 0) {
            System.out.print(seq[i] + " ");
            System.out.print("["+backPointers[i]+"]");
            i = backPointers[i];
        }
    }

    public static void main (String[] args) 
    {
        int A[] = { 2, 5, 3, 7, 11, 8, 10, 13, 6 };

        ConstructingLMIS obj = new ConstructingLMIS(A.length, A);
        obj.compute();
        obj.display();
    }
} 

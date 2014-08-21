/**
 * Author: Rajesh Gopidi
 *
 */

public class LMIS
{
    private int[] subSequence;
    private int len;
    private int[] seq;
    
    public LMIS()
    {

    }

    public LMIS (int len, int[] seq)
    {
        this.seq = seq;
        subSequence = new int[len];
    }

    public int findPos (int[] A, int l, int r, int key)
    {
        int m = 0;

        while (r - l > 1) {
            m = l + (r - l)/2;    
            if (A[m] >= key)
                r = m;
            else
                l = m;        
        }
        return (r);
    }

    public int compute()
    {
        subSequence[0] = seq[0];
        len = 1;

        for (int i = 1; i < seq.length; i++) {
           if (seq[i] > subSequence[len-1]) {
                subSequence[len++] = seq[i];
            } else if (seq[i] < subSequence[0]) {
                subSequence[0] = seq[i];
            } else {
                int index = findPos(subSequence, 0, len-1, seq[i]);
                subSequence[index] = seq[i];
            } 
        }

        return (len);
    }

    public static void main (String[] args) 
    {
        int A[] = { 2, 5, 3, 7, 11, 8, 10, 13, 6 };

        LMIS obj = new LMIS(A.length, A);
       
        System.out.println("Length of the longest increasing sequence " + obj.compute()); 
    }
} 

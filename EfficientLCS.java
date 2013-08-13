/**
 * Author: Rajesh Gopidi
 *
 */

import java.util.LinkedList;

public class EfficientLCS 
{
    private int[] length;
    private int[] seq1, seq2;

    public EfficientLCS ()
    {

    }

    public EfficientLCS (int[] seq1, int[] seq2)
    {
        if (seq1.length > seq2.length) {
            this.seq1 = seq1;
            this.seq2 = seq2;
        } else {
            this.seq2 = seq1;
            this.seq1 = seq2;
        }
        length = new int[this.seq2.length];
    }

    public void computeLCS()
    {
        int diag = 0;
        for (int i = 1; i < seq1.length; i++) {
            diag = 0;
            for (int j = 1; j < seq2.length; j++) {
                diag = length[j];
                if (seq1[i] == seq2[j]) {
                    length[j] = length[j] + 1;
                } else if (length[j] < length[j-1]) {
                    length[j] = length[j-1];
                }
            }
        }
    }

    public void displayLCS()
    {
        System.out.println("Length of Longest Common Sequence is "+
                            length[seq2.length-1]);

    }

    public static void main (String[] args)
    {
        int[] seq1 = {-99,1,0,0,1,0,1,0,1}; 
        int[] seq2 = {-99,0,1,0,1,1,0,1,1,0};

        //int[] seq1 = {-99, 1,2,3,2,4,1,2};
        //int[] seq2 = {-99,2,4,3,1,2,1};

        EfficientLCS obj = new EfficientLCS(seq1, seq2);
        obj.computeLCS();
        obj.displayLCS();
    }
}

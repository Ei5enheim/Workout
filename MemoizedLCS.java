/**
 *
 * Author: Rajesh Gopidi
 *
 */

import java.util.LinkedList;

public class MemoizedLCS
{
    private static int I = 1024;
    private static int J = 1;
    private static int POW = 10;
    private int[][] length;
    private int[][] indices;
    private int[] seq1, seq2;

    public MemoizedLCS ()
    {

    }

    public MemoizedLCS (int[] seq1, int[] seq2)
    {
        this.seq1 = seq1;
        this.seq2 = seq2;
        length = new int[seq1.length][seq2.length];
        indices = new int[seq1.length][seq2.length];
    }

    public int computeLCS(int i, int j)
    {
        if ((i == 0) || (j == 0))
            return 0;
        
        if (length[i][j] > 0)
            return length[i][j];

        if (seq1[i] == seq2[j]) {
            length[i][j] = computeLCS(i-1, j-1) + 1;
            indices[i][j] = I * (i-1) + j-1;
        } else if (computeLCS(i-1, j) >= computeLCS(i, j-1)) {
            length[i][j] = length[i-1][j];
            indices[i][j] = -(I * (i-1) + j);
        } else {
            length[i][j] = length[i][j-1];
            indices[i][j] =  -(I * (i) + j-1);
        }
        return (length[i][j]);
    }

    public void displayLCS()
    {
        System.out.println("Length of Longest Common Sequence is "+
                            length[seq1.length-1][seq2.length-1]);

        LinkedList<Integer> list = new LinkedList<Integer>();
        int row = seq1.length-1, col = seq2.length - 1, temp = -1, index = -1;
        while ((row != 0) && (col != 0)) {
            temp = indices[row] [col];
            if (temp < 0)
                index = -temp;
            else
                index = temp;

            if (temp > 0) 
                list.addFirst(seq1[row]);
            
            col = (((1<< POW) -1) & index) & 0x7FFFFFFF;
            row = index >> POW; 

        }
        Integer i = list.poll();
    
        while (i != null) {
            System.out.print(i);
            i = list.poll();
        }

        System.out.println();
    }

    public void mDisplayLCS()
    {
        System.out.println("Length of Longest Common Sequence is "+
                            length[seq1.length-1][seq2.length-1]);

        LinkedList<Integer> list = new LinkedList<Integer>();
        int row = seq1.length-1, col = seq2.length - 1, temp = -1, index = -1;
        while ((row != 0) && (col != 0)) {

            if ((seq1[row] == seq2[col]) && (length[row][col] == (length[row-1][col-1]+1))) {
                list.addFirst(seq1[row]);
                row--;
                col--;
            } else if (length[row][col] == length[row-1][col]) {
                row--;
            } else {
                col--;
            }
        }
        Integer i = list.poll();

        while (i != null) {
            System.out.print(i);
            i = list.poll();
        }

        System.out.println();
    }

    public static void main (String[] args)
    {
        //int[] seq1 = {-99,1,0,0,1,0,1,0,1}; 
        //int[] seq2 = {-99,0,1,0,1,1,0,1,1,0};

        int[] seq1 = {-99, 1,2,3,2,4,1,2};
        int[] seq2 = {-99,2,4,3,1,2,1};

        MemoizedLCS obj = new MemoizedLCS(seq1, seq2);
        obj.computeLCS(seq1.length-1, seq2.length-1);
        obj.displayLCS();
        obj.mDisplayLCS();
    }
}

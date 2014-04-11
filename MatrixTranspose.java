/*
 * Author: Rajesh Gopidi
 * Grad student at UNC Chapel Hill
 *
 */

public class MatrixTranspose
{

    int [][] source;
    int [][] result;
    int [][] opt_result;
    int dim;

    public MatrixTranspose ()
    {

    }

    public MatrixTranspose (int dim)
    {
        this.dim = dim;
        source = new int[dim][dim];
        result = new int[dim][dim];
        opt_result = new int[dim][dim];
    }

    public void transpose ()
    {
        for (int k = 0; k < dim/32; k++) {
            for (int l = 0; l < dim/32; l++) {
                for (int j = 0; j < 32; j++) {
                    for (int i = 0; i < 32; i++) {
                        result[l*32 + j][k*32 + i] = source[k*32 + i][l*32 + j];
                    }
                }
            }
        }
        //display();
    }

    public void optTranspose ()
    {  
        int num_procs = 4;
    
        for (int index = 0; index < (dim/(num_procs*32)); index++) {
            for (int p = 0; p < num_procs; p++) {
                for (int k = 0; k < dim/32; k++) {
                    for (int j = 0; j < 32; j++) {
                        for (int i = 0; i < 32; i++) {
                            opt_result[k*32 + j][p*(dim/num_procs)+(index*32) + i] = source[p*(dim/num_procs)+(index*32) + i][k*32 + j];
                        }
                    }
                }
            }
        }
    }

    public boolean check()
    {
        for (int i =0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if (result[i][j] != opt_result[i][j])
                    return (false);
            }
        }
        return (true);
    } 

    public void generateSource()
    {
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                source[i][j] = (j) * dim + i+1;
            }
        }

    }

    public void display()
    {
        System.out.println("Source \n");
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                System.out.print(source[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("result \n");
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main (String[] args)
    {
        MatrixTranspose obj = new MatrixTranspose (256);
        obj.generateSource();
        obj.transpose(); 
        obj.optTranspose();
        if (obj.check())
            System.out.println("Works great");
        else
            System.out.println("nah!, it doesn't work");

    }
}

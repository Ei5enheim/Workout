/*
 * Author: Rajesh Gopidi
 *
 */

import java.io.*;

public class ReadInput
{

    public int readInput () throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int number = Integer.valueOf(reader.readLine());

        System.out.println("number of TestCases " + number);

        return number;
    }

    public static void main (String[] args) {
        ReadInput obj = new ReadInput();

        try {
            obj.readInput();
        } catch (Exception e) {
            System.out.println(e);   
        }
    }
}


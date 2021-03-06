/*
 * Author: Rajesh Gopidi
 *
 */

import java.io.*;

public class ReadInput
{
    static BufferedReader reader = null;

    public static int readInput () throws Exception {

        int number = -1;
        if (reader == null )
            reader = new BufferedReader(new InputStreamReader(System.in));

        String line = reader.readLine().trim();
        if (line != null)
            number = Integer.valueOf(line);

        //System.out.println("number of TestCases " + number);

        return number;
    }

    public static String readLine () throws Exception {

        if (reader == null )
            reader = new BufferedReader(new InputStreamReader(System.in));

        String line = reader.readLine().trim();

        return (line);

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


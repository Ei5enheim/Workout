/*
 * Author: Rajesh Gopidi
 * A Grad student at UNC Chapel Hill
 */
import java.util.*;
import java.io.*;

class A 
{
    int numTestCases;
    int amount;
    int numItems;
    int caseNum;
    Hashtable<Integer, Integer> table;
    BufferedReader in;

    public A () {
        caseNum = 0;
    }
    
    public void check () throws Exception {

        String tokens[] = null;
        int currItem = 0;
        int pos = 0;

        tokens = in.readLine().split("\\s");
        
        while (pos < tokens.length) {
            currItem = Integer.valueOf(tokens[pos]);
            if (table.containsKey(amount-currItem)) {
                System.out.println("Case #"+caseNum+": "+ table.get(amount-currItem)+" "+ (pos+1));
                break;
            } else {
                table.put(currItem, pos+1);
            }
            pos++;
        }
    }

    public void run () throws Exception {
        
        //System.out.println ("number of test cases: "+ numTestCases); 
        
        while (numTestCases > 0) {
            caseNum++;
            amount = Integer.valueOf(in.readLine().trim());;
            numItems = Integer.valueOf(in.readLine().trim());
            //System.out.println(numItems);
            table = new Hashtable<Integer, Integer>(numItems);
            check();
            numTestCases--;
        }
    }

    public static void main (String[] args) throws Exception {
        A obj = new A();
        obj.in = new BufferedReader(new FileReader(args[0]));
        obj.numTestCases = Integer.valueOf(obj.in.readLine().trim());
        obj.run();
    }

}

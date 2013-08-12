
/**
 * Write a description of class FindNthNode here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 * 
 * 
 */

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;
import java.util.Date;

public class FindNthNode
{
    LinkedList<Integer> list;
    
    public FindNthNode ()
    {
        list = new LinkedList<Integer> ();
        buildList();
    }
    
    private void buildList()
    {
        int i = 0;
        
        Random random = new Random(new Date().getTime());
        while (i < 15) {
            list.add(random.nextInt(200));
            i++;
        }
        
    }
    
    void printList()
    {
        ListIterator<Integer> iterator = list.listIterator();
        int i = 1;
        while (iterator.hasNext())
        {
            
          System.out.print("pos="+i+" "+iterator.next()+",  ");
          i++;
        }
        
        System.out.println("\ndone with printing");
    }
    
    int find (int pos) 
    {
        ListIterator<Integer> iterator = list.listIterator();
        int i = 1;
        int result = 0;
        
        ListIterator<Integer> iterator1 = list.listIterator();
        while (iterator.hasNext()) {
                
            if (pos-1 < i) {
                result = iterator1.next();           
            }   
            iterator.next();
            i++;
        }
        return (result);
    }

 public static void main (String[] args)
 {
     FindNthNode obj = new FindNthNode();
     obj.printList();
     int result = obj.find(11);
     System.out.println("Nth number is "+ result);
          
 }
}

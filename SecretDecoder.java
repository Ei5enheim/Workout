/*
Please write complete compilable code.
Your class should be named Solution
Read input from standard input (STDIN) and print output to standard output(STDOUT).
For more details, please check https://www.interviewstreet.com/recruit/challenges/faq/view#stdio
*/

import java.io.*;
import java.util.*;
import static java.lang.Character.*;

class QueueElement 
{
	Character sub;
	HashSet<Character> set;

	public QueueElement ()
	{

	}

	public QueueElement (Character sub, HashSet<Character> set)
	{
		this.sub = sub;
		this.set = set;
	} 

	public int getLen ()
	{
		return (set.size());
	}
	
	public boolean equals (Object o)
	{

		if (o instanceof QueueElement)
		{
			QueueElement temp = (QueueElement) o;
			if ((sub == temp.sub) && (set.size() == temp.set.size()))
				return (true);
		}
		return (false);
	}
}

public class SecretDecoder
{
    private static int OLD = 0;
    private static int NEW = 1;
    private PriorityQueue<QueueElement> queue;
    private Hashtable <Character, HashSet<Character>[]> encoder;
    private HashSet<Character> decodedSet = new HashSet<Character>();
    private Hashtable <Integer, ArrayList<String>> dict;
    private Hashtable<Character, Object> decoder = new Hashtable<Character, Object>();
    private HashSet<String> history = new HashSet<String>();
    
    public SecretDecoder()
    {
        encoder = new Hashtable <Character, HashSet<Character>[]>();
        dict = new Hashtable <Integer, ArrayList<String>>();
	    queue = new  PriorityQueue<QueueElement>(11, new Comparator<QueueElement>(){
						
				public int compare(QueueElement one,
					  		QueueElement two)
				{
					if (one.getLen() > two.getLen())
						return (1);
					else if (one.getLen() < two.getLen())
						return (-1);
					if (getNumericValue(one.sub) > getNumericValue(two.sub))
						return (1);
					else if (getNumericValue(one.sub) < getNumericValue(two.sub))
						return (-1);
					return 0;
                }});
    }
    
    public void intiliazeDict (Object[] words)
    {
	String word = null;
	for (int i = 0; i < words.length; i++) {
		word = (String) words[i];
		if (dict.get(word.length()) == null) {
			dict.put(Integer.valueOf(word.length()),
					new ArrayList<String>());
		}
		dict.get(word.length()).add(word);
	}
    }
    
    public void decode (String msg)
    {
	int i = 0, j = 0, k = 0;
	char ch;
	String[] tokens = msg.split("\\s");
	char[] array = null;
	HashSet <Character>[] sets = null;
	boolean newWord = false;

	queue.clear();
	decodedSet.clear();
	decoder.clear();
	encoder.clear();
	history.clear();
	
	for (i = 0; i < tokens.length; i++) {
		newWord = true;
		if (!history.contains(tokens[i])) {
			array = tokens[i].toCharArray();
			List temp = dict.get(array.length);
			for (j = 0; j < temp.size(); j++) {
				char[] refArray = ((String) temp.get(j)).toCharArray();
				for (k = 0; k < array.length; k++) {
					ch = array[k];
					if (decoder.get(ch) == null) {
						sets = encoder.get(ch);
						if (sets != null) {
							if (!sets[OLD].isEmpty()) {
								if (sets[OLD].contains(refArray[k])) {
									sets[NEW].add(refArray[k]);
									//System.out.println(ch +"+--> "+refArray[k]); 
								}
							} else {
								sets[NEW].add(refArray[k]);
								//System.out.println(ch +"+--> "+refArray[k]);
							}
						} else {
							sets = new HashSet[2];
							sets[OLD] = new HashSet<Character>();
							sets[NEW] = new HashSet<Character>();
							encoder.put(ch, sets);
							sets[NEW].add(refArray[k]);
							//System.out.println(ch +"+--> "+refArray[k]);
						}
					} 
				}
			}
			HashSet<Character> swap = null;
			for (k = 0; k < array.length; k++) {
				sets = encoder.get(array[k]);
				if (sets[NEW].size() != 0) {
					queue.remove(new QueueElement(array[k], 
									sets[OLD]));				
					if (sets[NEW].size() == 1) {
						decodedSet.add((Character) sets[NEW].toArray()[0]);
						decoder.put(array[k],
					    		(Character) sets[NEW].toArray()[0]);
						//System.out.println("added"+ array[k] +"--> "+ (Character) sets[NEW].toArray()[0]);	
					} else if (sets[NEW].size() != 0){
						swap = sets[OLD];
						sets[OLD] = sets[NEW];
						sets[NEW] = swap;
						swap.clear();
						//System.out.println("Adding element "+array[k]+" to the queue, size"+sets[OLD].size());
						queue.add(new QueueElement(array[k], sets[OLD]));
					}
				}
			}
		}
	}
	QueueElement el = null;
	while ((el = queue.poll()) != null) {
		for (k = 0; k < el.set.size(); k++) {
			if (decodedSet.contains((Character)el.set.toArray()[k]))
				el.set.remove((Character)el.set.toArray()[k]);
		}
		if (el.set.size() == 1) {
			decodedSet.add((Character)el.set.toArray()[0]);
			decoder.put(el.sub, el.set.toArray()[0]);
		} else {
			//System.out.println("character is "+el.sub + " size "+el.set.size());
			decoder.put(el.sub, el.set.toArray());
		}
	}
    }

    public void displayDecodedMsg (String msg) 
    {
	    String[] tokens = msg.split("\\s");
	    StringBuffer buffer; 
	
	    for (int i = 0; i < tokens.length; i++) {
		    buffer = new StringBuffer();;
		    decodeCharacter (buffer, tokens[i], 0);
		    System.out.print(buffer + " ");
	    }
    } 

    public void decodeCharacter (StringBuffer buffer, String msg, int offset)
    {
	if (offset >= msg.length())
		return;

	if (decoder.get(msg.charAt(offset)) instanceof Object[]) {
		Object[] array = (Object[]) decoder.get(msg.charAt(offset));
		for (int k = 0; k < array.length; k++) {
			if (!decodedSet.contains((Character) array[k])) {
				buffer.delete(offset, buffer.length());
				buffer.insert(offset, (Character) array[k]);
				decodeCharacter(buffer, msg, offset + 1);
				String temp = buffer.toString();
				if (dict.get(temp.length()).contains(temp)) {
					decoder.put(msg.charAt(offset), temp.charAt(offset));	
					decodedSet.add(temp.charAt(offset));
					return;
				}
			}
		}
		//System.out.println("**WARNING no character found"); 		
	} else {
		buffer.append((Character)decoder.get(msg.charAt(offset)));
		decodeCharacter(buffer, msg, offset + 1);
	}	
    }

    public static void main (String[] args) throws Exception
    {
	    List<String> list = new ArrayList<String>();
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    SecretDecoder decoder = new SecretDecoder();	

	    String line = br.readLine().trim();
	    System.out.println("Fist line of input is: "+line);
	    if (line.equals("//dict")) {
		    while (!line.equals("//secret")) {
			    line = br.readLine().trim();
			    list.add(line);
		    }
		    System.out.println("Read the dictionary part");
		    decoder.intiliazeDict(list.toArray());
		    line = br.readLine();
		    while (line != null) {
			    decoder.decode(line);
			    decoder.displayDecodedMsg(line);
			    System.out.println();
			    line = br.readLine();
		    }		
	    }
    }
}

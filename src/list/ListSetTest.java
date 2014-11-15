package list;
import java.io.IOException;

import io.IO;
public class ListSetTest {
//takes in the command line arguments and creates a set from them
	public static void main(String[] args) throws IOException {
		String 	contents=IO.stdin.readLine(); List l=List.NIL;
		String[] split =contents.split(" ");
		for (int i=0; i < split.length; i++){
			int k = Integer.parseInt(split[i]);
			l = l.push(k);
		}
		ListSet s = new ListSet(l);
		
		List other =List.NIL;
		other.push(1);
		ListSet otherz = new ListSet(other);
		IO.stdout.println("this is set "+s.toString());
		IO.stdout.println("this is size "+s.isMember(1));
		//IO.stdout.println("this is next "+s.next());
	}

}

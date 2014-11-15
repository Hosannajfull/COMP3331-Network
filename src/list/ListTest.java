package list;
import io.IO;
import java.io.IOException;

public class ListTest {

	/**
	 *takes in the command line arguments after a formatting check and passes them to
	 *a list object which is then sorted and purged
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		System.out.println("input the number with spaces between the numbers");
		List l = List.NIL;
		String 	contents=IO.stdin.readLine(); 
		String[] split =contents.split(" ");
		for (int i=0; i < split.length; i++){
			int k = Integer.parseInt(split[i]);
			l = l.push(k);
		}
		IO.stdout.println(l.purge(l).toString()); 
		IO.stdout.println("This is mergeSort: "+l.sort());
		IO.stdout.println("Original List!" + l.reverse().toString());

	}
	

}
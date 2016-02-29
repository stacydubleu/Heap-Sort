import java.io.*;
import java.util.*;

public class Sort {
	public static void main(String[] args) throws Exception{
		int total=0; //total number of items in the input file
		FileReader input = new FileReader(args[0]); //input file
		Scanner in = new Scanner(input);
		while (in.hasNextInt()){ //while there is still data
			in.nextInt(); //move onto next data
			total++; //increment total number of items in the file
		}
		in.close();
		
		heapSort heap = new heapSort(total+1); //create heap with size of total+1 for the index counter
		
		FileReader in2 = new FileReader(args[0]); //reopen input file
		in = new Scanner(in2);
		FileWriter output1 = new FileWriter(args[1]); //output 1 file
		PrintWriter out1 = new PrintWriter(output1);
		heap.buildHeap(in, out1); //place data into heap

		FileWriter output2 = new FileWriter(args[2]); //output 2 file
		PrintWriter out2 = new PrintWriter (output2);
		heap.deleteHeap(out1, out2); //delete heap
		in.close();
		out1.close();
		out2.close();
	}
}
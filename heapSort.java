import java.io.*;
import java.util.*;

public class heapSort{
	static int size; //number of items from file
	static int[] HeapAry; //heap
	
	/**constructor creates heap array of size arraySize that includes an extra slot to keep count of current data items in the array*/
	public heapSort(int arraySize){
		HeapAry=new int[arraySize]; 
		size=arraySize-1;
		HeapAry[0]=0;
	}
	
	/**build heap by taking in data from input file and storing heap into output file 1*/
	public void buildHeap(Scanner input, PrintWriter out1){
		int data; //stores the integer data from input
		out1.println("Building Heap: "); 
		while (input.hasNextInt()){ //while there is data in the file
			data=input.nextInt(); //save this data
			if (!isHeapFull()){ //and heap is not full
				insertOneDataItem(data); //insert data
				bubbleUp(HeapAry[0]); //sort via bubble up
			}
			printHeap(out1); //output to file 1
		}
	}
	
	/**delete heap starting with last element in the last level by moving it to the root and then bubble down to sort*/
	public void deleteHeap(PrintWriter out1, PrintWriter out2){
		out1.println("Deleting Heap: ");
		while (!isHeapEmpty()){ //while heap is not empty
			out2.println("Node 1 contains: " + HeapAry[1]); //output to file 2 the node deleted (the root or smallest data)
			HeapAry[1]=HeapAry[HeapAry[0]]; //place the last element in the last level as the root
			HeapAry[0]--; //index of heap decrement by 1
			bubbleDown(1); //sort heap again by bubbling down said element
			printHeap(out1); //print out sorted heap
		}
	}
	
	/**adds data to heap*/
	public void insertOneDataItem(int data){
		HeapAry[0]++; //increment index of current number of elements in heap
		HeapAry[HeapAry[0]]=data; //add data to heap
	}
	
	/**sort data according to the parent node where the data must be larger than the contents of the parent node*/
	public void bubbleUp(int dataIndex){ 
		int parentIndex=dataIndex/2; //find parent index in heap
		int temp=HeapAry[dataIndex]; //store the data in a temporary variable
		if (dataIndex!=1&&HeapAry[dataIndex]<HeapAry[parentIndex]){ //if the data index isn't the root and the data is larger than the data in its parent node
			HeapAry[dataIndex]=HeapAry[parentIndex]; //switch indexes with parent
			HeapAry[parentIndex]=temp; 
			bubbleUp(parentIndex); //continue checking with any parent nodes it may have after switch
		}
		return;
	}
	
	/**sort data according to the children nodes where the data is smaller than the smallest data between its children nodes*/
	public void bubbleDown(int dataIndex){
		int child1Index=dataIndex*2; //find left child index
		int child2Index=dataIndex*2+1; //find right child index
		int temp=HeapAry[dataIndex]; //store data in a temporary variable
		if (child2Index<=HeapAry[0]&&(child1Index<=HeapAry[0])){ //if the children's index is within the current index of the array
			if (HeapAry[child1Index]>HeapAry[child2Index]&&HeapAry[dataIndex]>HeapAry[child2Index]){ //if data from right child is smaller than the left child and data is larger than that of the right child
					HeapAry[dataIndex]=HeapAry[child2Index]; //swap indexes with right child
					HeapAry[child2Index]=temp; 
					bubbleDown(child2Index); //continue checking with any children nodes after switch
			}
			else if (HeapAry[dataIndex]>HeapAry[child1Index]){ //if data from left child is smaller than that of the right child and data is larger than data from the the left child 
				HeapAry[dataIndex]=HeapAry[child1Index]; //swap indexes with left child
				HeapAry[child1Index]=temp;
				bubbleDown(child1Index); //continue checking wiht any children nodes after switch
			}
		}
		return;
	}
	
	/**checks if heap is empty*/
	public boolean isHeapEmpty(){
		if(HeapAry[0]==0){ return true; }
		else return false;
	}
	
	/**checks if heap is full*/
	public boolean isHeapFull(){
		if (HeapAry[0]==size) { return true; }
		else return false;
	}
	
	/**prints out contents within heap*/
	public void printHeap(PrintWriter out){
		if (HeapAry[0]<=10){ //if heap is less than 10 in size, print out just the data which remains
			for (int node=1; node<=HeapAry[0]; node++){
				int data=HeapAry[node];
				out.println("Node " + node + " contains: " + data);
			}
		}
		else for (int node=1; node<=10; node++){ //print up to 10 data elements if the heap size is greater than 10
			int data = HeapAry[node];
			out.println("Node" + node + " contains: " + data);
		}
		out.println();
	}
}
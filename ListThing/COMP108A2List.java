/*

Name:
Student ID:
University email address:

Time Complexity and explanation: 
f denotes initial cabinet size
n denotes the total number of requests 
d denotes number of distinct requests
You can use any of the above notations or define additional notation as you wish.

appendIfMiss():
	
moveToFront():
	
freqCount():

*/

import java.util.*;
import java.io.*;


class COMP108A2List {

	private static Scanner keyboardInput = new Scanner (System.in);
	public static Node head, tail; // head and tail of the linked list
	private static final int MaxInitCount = 10;
	private static final int MaxReqCount = 100;
	public static int initCount;
	public static int reqCount;

	public static int[] reqData = new int[MaxReqCount]; // store the requests, accessible to all methods


	// DO NOT change the main method
	public static void main(String[] args) throws Exception {
		
		int[] initData = new int[MaxInitCount];

		initCount = 0;
		reqCount = 0;
		head = null;
		tail = null;

		try {
			initCount = 10;
			if (initCount > MaxInitCount || initCount <= 0)
				System.exit(0);

				initData[0] = 3;
				initData[1] = 6;
				initData[2] = 9;
				initData[3] = 4;
				initData[4] = 7;
				initData[5] = 10;
				initData[6] = 5;
				initData[7] = 8;
				initData[8] = 2;
				initData[9] = 1;

			Random rnd = new Random();

			reqCount = 30;
			reqData = new int[reqCount];

			if (reqCount > MaxReqCount || reqCount <= 0)
				System.exit(0);

			for(int i=0;i < reqCount; i++)
				reqData[i] = rnd.nextInt(reqCount)+1;
			
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}

		try {
			System.out.println("appendIfMiss...");
			// create a list with the input data
			// call appendIfMiss()
			for (int i=initCount-1; i>=0; i--) {
				insertNodeHead(new Node(initData[i]));
			}
			appendIfMiss();

			printList();
			printRequests();

		}
		catch (Exception e) {
			System.out.println("appendIfMiss exception! " + e);
		}

		try {
			System.out.println("moveToFront...");
			// empty the previous list and restart with the input data
			// then call moveToFront()
			emptyList();
			for (int i=initCount-1; i>=0; i--) {
				insertNodeHead(new Node(initData[i]));
			}
			moveToFront();

			printList();
			printRequests();
		}
		catch (Exception e) {
			System.out.println("moveToFront exception!\t" + e.getMessage());
		}

		try {
			System.out.println("freqCount...");
			// empty the previous list and restart with the input data
			// then call freqCount()
			emptyList();
			for (int i=initCount-1; i>=0; i--) {
				insertNodeHead(new Node(initData[i]));
			}
			freqCount();

			printList();
			printRequests();
			printStats();
		}
		catch (Exception e) {
			System.out.println("freqCount exception!");
		}
						
	}
	
	// append to end of list when miss
	static void appendIfMiss() {
		/*
		Task 1.1 (15%) Implement the appendIfMiss() method that appends a missed ﬁle to the end of the list and does not reorganise the list.
		*/
		for (int i=0; i < reqData.length; i++) {
			Node curr = head;
			boolean found = false;
			while (curr != null && !found) {
				curr.freq++;
				if(curr.data==reqData[i]){
					found = true;
				}
				curr = curr.next;
			}
			if(!found){
				insertNodeTail(new Node(reqData[i]));
			}			
		}
	}

	// move the file requested to the beginning of the list
	static void moveToFront() {
		/*
		Task 1.2 (15%) Implement the moveToFront() method that moves a requested ﬁle or inserts a missed ﬁle to the front of the list. 
		When moving a node in the list, you have to make sure that the next ﬁeld of aﬀected nodes, head, and tail are updated properly. 
		*/
		//append missing - we have to do this to make sure we have the full list of files
		appendIfMiss();

		for (int i = 0; i < reqData.length; i++)
		{
			moveToHead(reqData[i]);
		}
	}
	
	// move the file requested so that order is by non-increasing frequency
	static void freqCount() {
		/*
		Task 1.3 (15%) Implement the freqCount() method that moves a requested ﬁle or inserts a missed ﬁle in a position such 
		that the ﬁles in the list are in non-increasing order. 
		Importantly when the requested ﬁle has the same frequency count as other ﬁles, the request ﬁle should be placed at the end among them. 
		Again make sure you update next, head, tail properly.
		*/

		appendIfMiss();

		//add items to an array for sorting

		Node curr;
		curr = head.next;
		Node mostFrequent = head;
		
		try{

			while (curr != null) {
				if(curr.freq >= mostFrequent.freq){
					mostFrequent = curr;
					moveToHead(curr.data);
				}
				curr = curr.next;				
			}
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}


	}

	static void moveToHead(int id) {
	
		Node prev = head;
		Node foundNode = null;
		Node curr;

		head.freq++;

		if(head.data != id){

			curr = head;

			while(curr != null){
				curr.freq++;
				if (curr.data == id){
					foundNode = curr;
					prev.next = curr.next;
				}
				prev = curr;
				curr = curr.next;
			}
			
			if(foundNode!=null)
			{
				insertNodeHead(foundNode);
			}
		}
	}

	static void insertNodeHead(Node newNode) {

		newNode.next = head;
		if (head == null)
			tail = newNode;
		head = newNode;
	}

	static void insertNodeTail(Node newNode) {

		newNode.next = null;
		tail.next = newNode;
		tail = newNode;		
	}

	// DO NOT change this method
	// delete the node at the head of the linked list
	static Node deleteHead() {
		Node curr;

		curr = head;
		if (curr != null) {
			head = head.next;
			if (head == null)
				tail = null;
		}
		return curr;
	}

	// DO NOT change this method
	// print the content of the list in two orders:
	// from head to tail
	static void printList() {
		Node curr;

		System.out.print("List: ");
		curr = head;
		while (curr != null) {
			System.out.print(curr.data + " ");
			curr = curr.next;
		}
		System.out.println();
	}

	static void printRequests() {
		
		System.out.print("Requests: ");

		for (int i : reqData) {
			System.out.print(i + " ");
		}
		System.out.println();
	}

	static void printStats() {
		
		Node curr;

		System.out.println("Stats: ");
		System.out.println("File\tFreq.");
		curr = head;
		while (curr != null) {
			System.out.println(curr.data + "\t" + curr.freq);
			curr = curr.next;
		}
		System.out.println();
	}
	
	// DO NOT change this method
	static void emptyList() {
		
		while (head != null)
			deleteHead();
	}

}

// You should not edit this class unless you want to implement
// a doubly linked list.
class Node {
	public int data; 
	public Node next;
	public int freq;
    
	// constructor to create a new node with data equals to parameter i
	public Node (int i) {
		next = null;
		data = i;
		freq = 1;
	}
}


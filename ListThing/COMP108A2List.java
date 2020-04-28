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
import java.text.Bidi;


class COMP108A2List {

	private static Scanner keyboardInput = new Scanner (System.in);
	public static Node head, tail; // head and tail of the linked list
	private static final int MaxInitCount = 10;
	private static final int MaxReqCount = 100;
	public static int initCount;
	public static int reqCount;

	public static int[] reqData = new int[MaxReqCount]; // store the requests, accessible to all methods

	//2 different ways to generate test data
	static void GenerateStandardData(int[] initData)
	{
		initCount = 0;
		reqCount = 0;
		head = null;
		tail = null;

		emptyList();

		try {
			initCount = 3;
			if (initCount > MaxInitCount || initCount <= 0)
				System.exit(0);

				initData[0] = 20;
				initData[1] = 30;
				initData[2] = 10;

			reqCount = 6;
			reqData = new int[reqCount];

			reqData[0] = 20;
			reqData[1] = 30;
			reqData[2] = 5;
			reqData[3] = 30;
			reqData[4] = 5;
			reqData[5] = 20;
			
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	static void GenerateTestData(int[] initData)
	{
		initCount = 0;
		reqCount = 0;
		head = null;
		tail = null;

		emptyList();

		try {
			initCount = 3;
			if (initCount > MaxInitCount || initCount <= 0)
				System.exit(0);

				initData[0] = 20;
				initData[1] = 30;
				initData[2] = 10;

			reqCount = 6;
			reqData = new int[reqCount];

			reqData[0] = 20;
			reqData[1] = 30;
			reqData[2] = 5;
			reqData[3] = 30;
			reqData[4] = 5;
			reqData[5] = 20;
			
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	static void GenerateRandomData(int[] initData)
	{
		initCount = 0;
		reqCount = 0;
		head = null;
		tail = null;

		emptyList();

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
	}

	// DO NOT change the main method
	// Main method only changed to genrate test data without keyboard input
	public static void main(String[] args) throws Exception {
		
		int[] initData = new int[MaxInitCount];

		GenerateTestData(initData);
		//GenerateRandomData(initData);

		try {
			System.out.println("appendIfMiss...");
			// create a list with the input data
			// call appendIfMiss()
			for (int i=initCount-1; i>=0; i--) {
				insertNodeHead(new Node(initData[i]));
			}

			appendIfMiss();
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
		}
		catch (Exception e) {
			System.out.println("freqCount exception! " + e.getMessage());
		}
						
	}
	
	// append to end of list when miss
	static void appendIfMiss() {
		/*
		Task 1.1 (15%) Implement the appendIfMiss() method that appends a missed ﬁle to the end of the list and does not reorganise the list.
		*/
		
		//System.out.print("Before ");
		//printList();

		for (int i=0; i < reqData.length; i++) {

			Node curr = head;

			boolean found = false;
			while (curr != null && !found) {
				
				if(curr.data==reqData[i]){
					found = true;
					curr = null;
				}
				else{
					curr = curr.next;
				}				
			}
			if(!found){
				insertNodeTail(new Node(reqData[i]));
			}	
		}

		//System.out.print("After ");
		//printList();

	}

	// move the file requested to the beginning of the list
	static void moveToFront() {
		/*
		Task 1.2 (15%) Implement the moveToFront() method that moves a requested ﬁle or inserts a missed ﬁle to the front of the list. 
		When moving a node in the list, you have to make sure that the next ﬁeld of aﬀected nodes, head, and tail are updated properly. 
		*/
		//append missing - we have to do this to make sure we have the full list of files

		//System.out.print("Before ");
		printList();

		for (int i = 0; i < reqData.length; i++)
		{

			Node curr = head;
			boolean found = false;

			while(curr != null)
			{
				if(curr.data == reqData[i]){
					moveToHead(curr);
					found = true;
					curr = null;
				}
				else{
					curr = curr.next;
				}
			}

			if(!found){
				insertNodeHead(new Node(reqData[i]));
			}
		}

		//System.out.print("After ");
		printList();

	}
	
	// move the file requested so that order is by non-increasing frequency
	static void freqCount() {
		/*
		Task 1.3 (15%) Implement the freqCount() method that moves a requested ﬁle or inserts a missed ﬁle in a position such 
		that the ﬁles in the list are in non-increasing order. 
		Importantly when the requested ﬁle has the same frequency count as other ﬁles, the request ﬁle should be placed at the end among them. 
		Again make sure you update next, head, tail properly.
		*/
		// System.out.print("Before search ");
		//printList();

		for (int i = 0; i < reqData.length; i++)
		{

			Node curr = head;
			boolean found = false;

			while(curr != null)
			{
				curr.freq++;
				if(curr.data == reqData[i]){
					moveToHead(curr);
					found = true;
					curr = null;
				}
				else{
					curr = curr.next;
				}
			}

			if(!found){
				insertNodeHead(new Node(reqData[i]));
			}
		}

		// System.out.print("After search");
		printList();

		sortList();

		printList();
		//printFrequency();
		
	}

	static void sortList(){
		
		int count = 0;
		Node n = head;
		while(n!=null){
			count++;
			n = n.next;
		}

		Node[] nodes = new Node[count];
		Node curr = head;

		for (int i = 0; i < count; i++) {
			nodes[i] = new Node(curr.data);
			nodes[i].freq = curr.freq;
			curr = curr.next;
		}

		Arrays.sort(nodes);

		emptyList();
		for (int i=nodes.length-1; i>=0; i--) {
			Node newNode = new Node(nodes[i].data);
			newNode.freq = nodes[i].freq;
			insertNodeHead(newNode);
		}

		System.out.print("List: ");
		for (int i = 0; i < count; i++) 
		{
			
			System.out.print(nodes[i].data + " ");
		}
		System.out.print("\r\n");
	}

	static void moveToHead(Node node) {
	
		if(node != head){

			Node prev = head;
			Node curr = head.next;

			while(curr != null){

				if (curr.data == node.data){
					prev.next = curr.next;
					curr.next = head;
					head = curr;
					curr = null;
				}
				else
				{
					prev = curr;
					curr = curr.next;
				}
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

	static void printFrequency() {
		
		Node curr;

		System.out.print("Frequency: ");
		curr = head;
		while (curr != null) {
			System.out.print(curr.freq + " ");
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
class Node implements Comparable{
	public int data; 
	public Node next;
	public int freq;
    
	// constructor to create a new node with data equals to parameter i
	public Node (int i) {
		next = null;
		data = i;
		freq = 1;
	}

	@Override
	public int compareTo(Object o) {
		
		Node n = (Node) o;
		return this.freq - n.freq;
	}
}


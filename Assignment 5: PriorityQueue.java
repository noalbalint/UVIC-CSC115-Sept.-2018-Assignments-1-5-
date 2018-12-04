/*
* Name: Noal Balint
* Date: Dec. 3 2018
* Filename: PriorityQueue.java
* Details: CSC115 Assignment 5
*/

import java.util.NoSuchElementException;

/**
 * The shell of the class, to be completed as part of the
 * CSC115 Assignment 5 : Emergency Room
 */

/**
 * Complete this class as per the Heap.html specification document.
 * Fill in any of the parts that have the comment:
 * ********  COMPLETE *******
 * Do not change method headers or code that has been supplied.
 * Delete all messages to you, including this one, before submitting.
 */

public class PriorityQueue<E extends Comparable<E>> {

	private Heap<E> heap;

	// create new PQ
	public PriorityQueue() {
		heap = new Heap<E>();
	}

	// remove from queue. return removed item.
  public E dequeue() {
		return heap.removeRootItem();
  }

	// add to queue
 	public void enqueue(E item) {
    	heap.insert(item);
    }

	// chcek if queue is empty
  public boolean isEmpty() {
		return heap.isEmpty();
  }

	// return the item at the front of the queue
  public E peek() {
		return heap.getRootItem();
  }

	// print PQ method (for debug purposes- not accessible to client)
	private void printPQ()
	{
		heap.print_vector();
	}

  public static void main(String args []) {

	PriorityQueue <ER_Patient> p1 = new PriorityQueue <ER_Patient> ();

	System.out.println();
	System.out.println("TESTING START");
	System.out.println();

	System.out.println("is the PQ empty? :: " + p1.isEmpty());
	p1.enqueue(new ER_Patient("Walk-in"));
	p1.enqueue(new ER_Patient("Chronic"));
	p1.enqueue(new ER_Patient("Chronic"));
	p1.enqueue(new ER_Patient("Major fracture"));
	p1.enqueue(new ER_Patient("Life-threatening"));
	p1.enqueue(new ER_Patient("Walk-in"));

	System.out.println("$TEST$ proper tree shape after enqueue?");
	p1.printPQ();
	System.out.println("is the PQ empty? :: " + p1.isEmpty());
	System.out.println();

	System.out.println("$TEST$ proper tree shape after deque?");
	p1.dequeue();
	p1.printPQ();
	System.out.println("is the PQ empty? :: " + p1.isEmpty());
	System.out.println();

	System.out.println("$TEST$ peek working? Root node: ");
	System.out.println(p1.peek());
	System.out.println();
	System.out.println("TESTING FINISH : SUCCESS");
	
  }
}

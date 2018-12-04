/*
* Name: Noal Balint
* ID: V00906500
* Date: Dec. 3 2018
* Filename: Heap.java
* Details: CSC115 Assignment 5
*/

import java.util.NoSuchElementException;
import java.util.Vector;


public class Heap<E extends Comparable<E>> {

	private Vector<E> heapArray;

	// create new Heap
	public Heap() {
		heapArray = new Vector<E>();
	}

	//return true if empty, false if not.
	public boolean isEmpty(){
		if (heapArray.isEmpty())
		{
			return true;
		}
		return false;

	}

	// return number of elements in the vector (heapArray)
	public int size(){
	 return heapArray.size();
	}

	// @item1 moves to item2's index, and visa versa.
	private void swap(int item1, int item2)
	{
		int x = item1;
		int y = item2;
		E item1E = heapArray.get(item1);
		E item2E = heapArray.get(item2);

		heapArray.set(y, item1E);
		heapArray.set(x, item2E);

	}

	// If the item has higher priority than it's parent, swap with parent.
	// Continue bubbling until the item is lower priority than relative parent.
	private void BubbleUp(E item, int index)
	{
		int parent = (index - 1) / 2;

		if(item.compareTo(heapArray.get(parent)) > 0)
		{
			swap(index, parent);
			BubbleUp(item, parent);
		}
	}

	// add an item to the list in level order, than Bubble as necessary to get
	// correct tree structure. Recal Heap is always BALANCED and FULLL tree.
	public void insert(E item){

		heapArray.add(item);
		int index = size() - 1;
		int parent = (index - 1) / 2;

		if(item.compareTo(heapArray.get(parent)) > 0)
		{
			BubbleUp(item, index);
		}
		// NOTE this is the correct logic for the compareTo in this assignment.
		// it means "if item has lower priority than it's parent ... "
	}

	// helper method for @removeRootItem. If @item is lower priority than one or both
	// child nodes, swap with its bigger child, then bubbleDown until @item is higher
	// priority than either child, or until no children exist.
	private void bubbleDown(int index)
	{
		// @index has no children
		if(biggerChild(index) == -1)
		{
			return;
		}

		E item = heapArray.get(index);
		int swapChild = biggerChild(index);

		// if child has higher priority than item
		if(item.compareTo(heapArray.get(swapChild)) < 0)
		{
			swap(index, swapChild);
			bubbleDown(swapChild);
		}
		// if child is not higher priorirty than item
		return;

	}

	// helper method for bubbleDown()
	// returns the index of the child of @itemIndex which has higher priority.
	// if both are EQUAL priority, it returns the left child. Also checks if
	// children exist by seeing if their would-be index is in the populated vector area.
	private int biggerChild(int itemIndex)
	{
		int leftChild = (itemIndex * 2) + 1;
		int rightChild = (itemIndex * 2) + 2;

		// right child doesn't exist
		if(leftChild > heapArray.size() - 1 && rightChild <= heapArray.size() - 1)
		{
			return rightChild;
		}
		// left child doesn't exist
		else if(rightChild > heapArray.size() - 1 && leftChild <= heapArray.size() - 1)
		{
			return leftChild;
		}
		// both children don't exist
		else if(leftChild > heapArray.size() - 1 && rightChild > heapArray.size() - 1)
		{
			return -1;
		}

		// "if right chlid higher priority than left child"
		else if(heapArray.get(rightChild).compareTo(heapArray.get(leftChild)) > 0)
		{
			return rightChild;
		}
		return leftChild;
	}

	// swap the root item with the most recently-added item, then delete the item
	// in the most recently-added position (what used to be the root). Then bubbleDown
	// if realtive root has lower priority than it's highest-priotiy child
	public E removeRootItem(){

		// root item being removed
		E returnRoot = heapArray.get(0);
		int lastAdded = size() - 1;
		swap(0, lastAdded);
		heapArray.remove(size() - 1);

		// root item after swapping
		E root = getRootItem();

		if(root.compareTo(heapArray.get(biggerChild(0))) < 0)
		{
			bubbleDown(0);
		}
		return returnRoot;
	}

	public E getRootItem(){
		return heapArray.get(0);
	}

	/******** Tool methods ********/
	private int indexOf(E p){
		for (int i = 1; i < heapArray.size(); i++) {
			if (heapArray.elementAt(i).equals(p))   {
				return i;
			}
		}
		return -1;
	}

	/********  DEBUG USE methods ********/
	public void print_vector() {
		System.out.println(" *************** Array is ***************");
		for (int i = 0; i < heapArray.size(); i++){
			System.out.println(heapArray.elementAt(i));
		}
	}

	public static void main(String args []){

		Heap <ER_Patient> hp = new Heap <ER_Patient>();
		Heap <ER_Patient> hp2 = new Heap <ER_Patient>();

		System.out.println();
		System.out.println("TESTING START");

		System.out.println("$TEST$ is array empty? :: " + hp.isEmpty());
		System.out.println("$TEST$ size: " + hp.size());

		hp.insert(new ER_Patient ("Chronic"));
		hp.insert(new ER_Patient ("Life-threatening"));
		hp.insert(new ER_Patient ("Walk-in"));
		hp.insert(new ER_Patient ("Major fracture"));
		hp.insert(new ER_Patient ("Chronic"));
		hp.insert(new ER_Patient ("Major fracture"));
		hp.insert(new ER_Patient ("Walk-in"));
		hp.insert(new ER_Patient ("Chronic"));

		System.out.println("$TEST$ testing insert: ");
		System.out.println();
		hp.print_vector();
		System.out.println("$TEST$ is array empty? :: " + hp.isEmpty());
		System.out.println("$TEST$ size: " + hp.size());

		System.out.println();

		System.out.println("$TEST$ removeRoot: ");
		System.out.println();
		hp.removeRootItem();
		hp.print_vector();
		System.out.println("$TEST$ is array empty? :: " + hp.isEmpty());
		System.out.println("$TEST$ size: " + hp.size());

		System.out.println();

		System.out.println("$TEST$ testing new heap. Insert:");
		hp2.insert(new ER_Patient ("Chronic"));
		hp2.insert(new ER_Patient ("Chronic"));
		hp2.insert(new ER_Patient ("Major fracture"));
		hp2.insert(new ER_Patient ("Major fracture"));
		hp2.insert(new ER_Patient ("Walk-in"));
		hp2.insert(new ER_Patient ("Major fracture"));

		System.out.println();
		hp2.print_vector();

		System.out.println("$TEST$ removeRootItem() on the new heap: ");
		hp2.removeRootItem();
		System.out.println();
		hp2.print_vector();

		System.out.println("TESTING FINISHED : SUCCESS");
	}
}

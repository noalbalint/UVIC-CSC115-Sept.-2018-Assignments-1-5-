/*
* Name: Noal Balint
* Date: Nov. 3 2018
* Filename: StringStack.java
* Details: CSC115 Assignment 3
*/
 /*
 * The shell of the class, to be completed as part of the CSC115 Assignment 3 : Calculator.
 */


public class StringStack {

	public Node head; // also known as @top

	//check if the stack is empty. Returns true or false.
	public boolean isEmpty() {
		try
		{
			if (head == null)
			{

				return true;
			} return false;
		} catch (StackEmptyException e)
		{
			System.out.println("the stack is empty!");
			throw new StackEmptyException(e.toString());
		}
	}

	// remove and return the 'top' value of the stack
	public String pop() {
		if(isEmpty())
		{
			throw new StackEmptyException("Stack is empty!");
		}
		String tempItem = head.item;
		head = head.next;
		return tempItem;
	}

	// return the 'top' value of the stack without removing it.
	public String peek() {
		if(isEmpty())
		{
			throw new StackEmptyException("Stack is empty!");
		}
		return head.item;
	}

	// send @item to the top of the stack
	public void push(String item) {
		head = new Node(item, head); // if empty, this still works: head.next = null;
	}

	// pop all items form the stack
	public void popAll() {
	while(isEmpty() == false)
		{
			pop();
		}
	}

	// convert the stack to a string
	public String toString(){
		StringBuilder s = new StringBuilder("{");

		Node curr = head;
		while (curr != null)
		{
			if(curr.next == null)
			{
				s.append(curr.item);
			} else {
				s.append(curr.item + ", ");
			}
			curr = curr.next;
		}
		s.append("}");

		return s.toString();
	}

public static void main(String[] args)
{
	StringStack s = new StringStack();
	// testing!!
	System.out.println(s.isEmpty());
	s.push(")");
	System.out.println(s.isEmpty());
	System.out.println(s.toString());
	System.out.println(s.peek());
	s.push("3");
	s.push("+");
	s.push("4");
	s.push("(");
	System.out.println(s.toString());
	System.out.println(s.peek());
	s.pop();
	System.out.println(s.toString());
	s.popAll();
	System.out.println(s.toString());

}

}

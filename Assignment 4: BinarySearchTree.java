/*
* Name: Noal Balint
* Date: Nov. 24 2018
* Filename: BinarySearchTree.java
* Details: CSC115 Assignment 4
*/

import java.util.Iterator;


/**
 * BinarySearchTree is an ordered binary tree, where the element in each node
 * comes after all the elements in the left subtree rooted at that node
 * and before all the elements in the right subtree rooted at that node.
 * For this assignment, we can assume that there are no elements that are
 * identical in this tree.
 * A small modification will allow duplicates.
 */
public class BinarySearchTree<E extends Comparable<E>> extends BinaryTree<E> {

	// the root is inherited from BinaryTree.

	/**
	 * Create an empty BinarySearchTree.
	 */
	public BinarySearchTree() {
		super();
	}

	/**
	 * Creates a BinarySearchTree with a single item.
	 * @param item The single item in the tree.
	 */
	public BinarySearchTree(E item) {
		super(item);
	}

	/**
 	 * <b>These methods are not allowed in a BinarySearchTree.</b>
	 * It's description from the subclass:<br>
	 * <br>
	 * {@inheritDoc}
	 * @throws UnsupportedOperationException if this method is invoked.
	 */

	public void attachLeftSubtree(BinaryTree<E> left) {
		throw new UnsupportedOperationException();
	}

	public void attachRightSubtree(BinaryTree<E> right) {
		throw new UnsupportedOperationException();
	}

	// insert interface
	public void insert(E item) {

		if(root == null)
			{
				root = new TreeNode<E>(item);
			}
		insert(item, root);

	}

	// insert implementation. Add an item to the tree. Must not already exist in the tree.
	private void insert(E item, TreeNode<E> node){

		// insert item smaller than node item
		if(item.compareTo(node.item) < 0)
			{
				if (node.left != null)
				{
					insert(item, node.left);
				}
				else
				{
					node.left = new TreeNode<E>(item);
					node.left.parent = node;
				}
			}
		// insert item bigger than node item
		else if(item.compareTo(node.item) >= 1)
			{
				if(node.right != null)
				{
					insert(item, node.right);
				}
				else
				{
					node.right = new TreeNode<E>(item);
					node.right.parent = node;
				}
			}

	}

	// Sorta pointless imo, seach() is way stronger. Anyways, this
	// takes the item you want and returns that item if it exists in the
	// tree, otherwise return null
	public E retrieve(E item)
	{
		TreeNode<E> bro = search(item, root);

		if(bro == null)
		{
			System.out.println("couldn't find item in array");
			return null;
		}
		return bro.item;
	}

	//delete interface
	public E delete(E item) {

		return delete(item, root);
	}

	//returns the node that contains the specified item
	private TreeNode<E> search(E item, TreeNode<E> node)
	{
		if(item.compareTo(node.item) == 0)
		{
			return node;
		}
		else if(item.compareTo(node.item) < 0) //item smaller than root item
			{
				if (node.left != null)
				{
					return search(item, node.left);
				}
				else
				{
					System.out.println("in Search(): item does not exist1");
					return null;
				}

			}
		else //if(item.compareTo(node.item) > 0) // item bigger than root item
			{
				if(node.right != null)
				{
					return search(item, node.right);
				}
				else
				{
					System.out.println("in Search(): item does not exist2");
					return null;
				}
			}
	}

	//find leftmost node in right tree. Used for 2child delete
	private TreeNode<E> findLeftmost(TreeNode<E> node)
	{
		TreeNode<E> temp = node.right;

		while(temp.left != null)
		{
			temp = temp.left;
		}
		return temp;
	}

	//delete method implementation. more complex deletion methods
	private E delete(E item, TreeNode<E> node)
	{
		//catch null node
		if (node == null)
		{
			System.out.println("No tree exists. Exiting matrix... ");
			return null;
		}

		TreeNode<E> theNode = search(item, node);

		//catch case where search() could not find the item and returns null
		if(theNode == null)
		{
			System.out.println("broooo that item don't exist in tha tree; what r u doinnnnn");
			return null;
		}

		//no children case
		if(theNode.left == null && theNode.right == null)
		{
			E tempItem = theNode.item;

			if(theNode.item.compareTo(theNode.parent.item) < 0)
			{
				theNode.parent.left = null;
			}
			else if(theNode.item.compareTo(theNode.parent.item) > 0)
			{
				theNode.parent.right = null;
			}

			//catch case where you are deleting the ONLY node in an array
			else if(theNode == root)
			{
				root = null;
			}
			return tempItem;

		}

		//one child case: right child
		else if(theNode.left == null && theNode.right != null)
		{

			E tempItem = theNode.item;

			if(theNode.item.compareTo(theNode.parent.item) < 0)
			{
				theNode.right.parent = theNode.parent;
				theNode.parent.left = theNode.right;
			}
			else if(theNode.item.compareTo(theNode.parent.item) > 0)
			{
				theNode.left.parent = theNode.parent;
				theNode.parent.right = theNode.right;
			}

			return tempItem;
		}

		//one child case: left child
		else if(theNode.left != null && theNode.right == null)
		{
			E tempItem = theNode.item;

			if(theNode.item.compareTo(theNode.parent.item) < 0)
			{
				theNode.parent.left = theNode.left;
			}
			else if(theNode.item.compareTo(theNode.parent.item) > 0)
			{
				theNode.parent.right = theNode.left;
			}
			return tempItem;
		}

		//two children case
		else if(theNode.left != null && theNode.right != null)
		{
			E theItem = theNode.item;
			TreeNode<E> temp = findLeftmost(theNode);
			theNode.item = temp.item;
			delete(temp.item, temp.parent);
			return theItem;
		}

		return null;
	}


	/**
	 * Internal test harness.
	 * @param args Not used.
	 */
	public static void main(String[] args) {


		//something to get you started.
		BinarySearchTree<String> tree = new BinarySearchTree<String>();

		String s1 = new String("optimal");
		String s2 = new String("needs");
		String s3 = new String("programmers");
		String s4 = new String("CSC115");
		tree.insert(s1);
		tree.insert(s2);
		tree.insert(s3);
		tree.insert(s4);
		//test retrieve()
		String test = tree.retrieve("needs");
		if (test != null && !test.equals("")) {
			System.out.println("retrieving the node that contains "+s2);
			if (test.equals(s2)) {
				System.out.println("Confirmed");
			} else {
				System.out.println("retrieve returns the wrong item");
			}
		} else {
			System.out.println("retrieve returns nothing when it should not");
		}
		Iterator<String> it = tree.inorderIterator();
		System.out.println("printing out the contents of the tree in sorted order:");
		while (it.hasNext()) {
			System.out.print(it.next()+" ");
		}
		System.out.println();
		DrawableBTree<String> dbt = new DrawableBTree<String>(tree);
		dbt.showFrame();

		// more testing

		BinarySearchTree<Integer> tree2 = new BinarySearchTree<Integer>();
			tree2.insert(Integer.valueOf(10));
			tree2.insert(Integer.valueOf(24));
			tree2.insert(Integer.valueOf(-5));
			tree2.insert(Integer.valueOf(-10));
			tree2.insert(Integer.valueOf(15));
			tree2.insert(Integer.valueOf(12));
			tree2.insert(Integer.valueOf(14));
			tree2.insert(Integer.valueOf(86));

		//temp tree to test the attach TL and TR methods
		//test BinaryTree constructor
		BinaryTree<Integer> tree3 = new BinaryTree<Integer>(666);

		//test height()
		System.out.println("Tree height initial: " + tree2.height());

		//original tree2
		DrawableBTree<Integer> dbt2 = new DrawableBTree<Integer>(tree2);
		dbt2.showFrame();

		//test no child (leaf node) delete
		tree2.delete(Integer.valueOf(86));

		//result
		DrawableBTree<Integer> dbt3 = new DrawableBTree<Integer>(tree2);
		dbt3.showFrame();

		//test left child delete
		tree2.delete(Integer.valueOf(-5));

		//result
		DrawableBTree<Integer> dbt4 = new DrawableBTree<Integer>(tree2);
		dbt4.showFrame();

		//test right child delete
		tree2.delete(Integer.valueOf(12));

		//result
		DrawableBTree<Integer> dbt4_2 = new DrawableBTree<Integer>(tree2);
		dbt4_2.showFrame();

		//test 2 child delete
		tree2.delete(Integer.valueOf(10));

		//test deleting a value that doesn't exist
		System.out.println("This should cause some errors");
		tree2.delete(Integer.valueOf(999999));

		//result
		DrawableBTree<Integer> dbt5 = new DrawableBTree<Integer>(tree2);
		dbt5.showFrame();

		//test height() again
		System.out.println("Tree height after removals: " + tree2.height());

		//test preorder
		Iterator<String> iter = tree.preorderIterator();
		System.out.print("Preorder:	");
		while(iter.hasNext())
		{
			System.out.print(iter.next() + " , ");
		}
		System.out.println();

		//test inorder
		Iterator<String> iter2 = tree.inorderIterator();
		System.out.print("Inorder:	");
		while(iter2.hasNext())
		{
			System.out.printf(iter2.next() + " , ");
		}
		System.out.println();

		//test postorder
		Iterator<String> iter3 = tree.postorderIterator();
		System.out.print("Postorder:	");
		while(iter3.hasNext())
		{
			System.out.print(iter3.next() + " , ");
		}
		System.out.println();

		//test attachRightSubtree()
		tree2.attachLeftSubtree(tree3);

		//test attachLeftSubtree()
		tree2.attachRightSubtree(tree3);


	}

}

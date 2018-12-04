/*
* Name: Noal Balint
* Date: Nov. 24 2018
* Filename: BinaryTree.java
* Details: CSC115 Assignment 4
*/

import java.util.Iterator;

/*
 * BinaryTree is an ADT in which each left child is smaller than it's
 * root and each right child is larger than it's root
 */

/**
 * BinaryTree is a basic generic BinaryTree data structure.
 * It is referenced based, using TreeNodes to connect the tree.
 * It contains any element determined by the placeholder E.
 * The basic ADT is adapted from <i>Java, Walls &amp; Mirrors,</i> by Prichard and Carrano.
 */
public class BinaryTree<E> {

	/* The root is inherited by any subclass
	 * so it must be protected instead of private.
	 */
	protected TreeNode<E> root;

	/**
	 * Create an empty BinaryTree.
	 */
	public BinaryTree() {
		root = null;
	}

	/**
	 * Create a BinaryTree with a single item.
	 * @param item The only item in the tree.
	 */
	public BinaryTree(E item) {
		root = new TreeNode<E>(item);
	}

	/**
	 * Used only by subclasses and classes in the same package (directory).
	 * @return The root node of the tree.
	 */
	protected TreeNode<E> getRoot() {
		return root;
	}

	/**
	 * Creates a binary tree from a single item for the root and two subtrees.
	 * Once the two subtrees are attached, their references are nullified to prevent
	 * multiple entries into <i>this</i> tree.
	 * @param item The item to be inserted into the root of this tree.
	 * @param leftTree A binary tree, which may be empty.
	 * @param rightTree A binary tree, which may be empty.
	 */
	public BinaryTree(E item, BinaryTree<E> leftTree, BinaryTree<E> rightTree) {
		root = new TreeNode<E>(item);
		attachLeftSubtree(leftTree);
		attachRightSubtree(rightTree);
	}

	/**
	 * Attaches a subtree to the left of the root node, replacing any subtree that was
	 * previously there.
	 * @param left The new left subtree of the root.
	 *	This subtree may be empty.
	 *	Once attached, this parameter reference is nullified to prevent multiple
	 *	access to <i>this</i> tree.
	 * @throws TreeException if <i>this</i> tree is empty.
	 */
	public void attachLeftSubtree(BinaryTree<E> left) {
		if (root == null) {
			throw new TreeException("Cannot attach to an empty tree.");
		}
		if (left == null) {
			return;
		}
		root.left = left.root;
		left.root.parent = root;
		left = null;
	}

	/**
	 * @return a preorder iterator of the binary tree.
	 */
	public Iterator<E> preorderIterator() {
		return new BinaryTreeIterator<E>("preorder",root);
	}

	/**
	 * @return an inorder iterator of the binary tree.
	 */
	public Iterator<E> inorderIterator() {
		return new BinaryTreeIterator<E>("inorder", root);
	}

	/**
	 * @return a postorder iterator of the binary tree.
	 */
	public Iterator<E> postorderIterator() {
		return new BinaryTreeIterator<E>("postorder",root);

	}

/* NOTE TO STUDENT:
 * Comment and complete the following methods.
 */
	public boolean isEmpty() {
		if(root.left == null && root.right == null)
		{
			return true;
		}
		return false;
	}

	public void attachRightSubtree(BinaryTree<E> right) {
		if (root == null) {
			throw new TreeException("Cannot attach to an empty tree.");
		}
		if (right == null) {
			return;
		}
		right.root.parent = root;
		root.right = right.root;
		right = null;
	}

	public int height() {
		if(root == null)
		{
			return 0;
		}

		int h = height(root);

		return h;
	}

	/*
	 * NOTE TO STUDENT:
	 * The height of a tree is recursively defined as:
	 * 1 + the maximum (height of the left subtree rooted at node
	 *		or height of right subtree rooted at node.
	 */

	// unfortunately need a long method like this, bc Math.max not workin'

	// returns the height of a binary tree rooted at @node
	private int height(TreeNode<E> node)
	{

	// no children
	if(node.left == null && node.right == null)
	{
		return 1;
	}

	//left child only
	else if(node.left != null && node.right == null)
	{
		return height(node.left) + 1;
	}

	//right child only
	else if(node.right != null && node.left == null)
	{
		return height(node.right) + 1;
	}

		// if left and right chile exist, return the height of whichever
		// subtree is larger, +1 to account for the root node. Recursively defined.

		if(height(node.left) > height(node.right))
		{
			return height(node.left) + 1;
		}
		else //if(height(node.left) <= height(node.right))
		{
			return height(node.right) + 1;
		}
	}


}

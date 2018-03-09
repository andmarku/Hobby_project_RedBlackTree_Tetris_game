package redBlackTree;

import java.util.ArrayList;
import java.util.List;

/**
 * A class which implements a RedBlackTree data structure, which is a binary
 * tree that has a maximum difference with a factor of two between two paths.
 * This ensures that the finding an element will take log(n) time.
 * 
 * The invariants that accomplishes this are threefold: a) the root must be
 * colored black, b) no red node can have a red parent node, and c) no path from
 * the root to a null leaf can have more black nodes than any other. The
 * constraints a) and b) ensures that the largest difference between any two
 * paths will occur when one consist only of black nodes and the other
 * interleave red and black node, making the latter twice as long.
 * 
 * @author Markus
 *
 */
class RBTree implements IRBTree {
	private RBTNode root;
	private int size;
	private List<RBTNode> treeAsArray;

	protected RBTree(){
		treeAsArray = new ArrayList<>();
		size = 0;
	}
	
	protected RBTNode getRoot() {
		return root;
	}
	/**
	 * A method which prints a linked tree using a folder structure with the
	 * root most to the left.
	 * 
	 * @param tree
	 *            the tree to print.
	 */
	private static RBTNode RBTNodeFactory(int value) {
		return new RBTNode(value, null);
	}
	
	@Override
	public void addNode(int value) {
		if (root == null) {
			root = RBTNodeFactory(value);
			root.changeCol();

		} else {
			// Search for the right position of the new value
			RBTNode match = findMatch(value);

			// If the value already was in the tree, return
			if (match.getValue() == value)
				return;

			// Create a new node and add the (best) match as its parent
			RBTNode x = RBTNodeFactory(value);
			x.setParent(match);

			// If the closest match was too small, add the new node as its right
			// child
			if (match.getValue() < value)
				match.setRightChild(x);
			// If the closest match was too large, add the new node as its left
			// child
			else
				match.setLeftChild(x);
		} // End of large else statement
		size = size + 1;
	}

	private RBTNode findMatch(int value) {
		if (root == null)
			return null;
		RBTNode match = findMatch(root, value);
		return match;
	}

	/**
	 * 
	 * @param node
	 *            the node to be examined.
	 * @param value
	 *            the value to check for.
	 * @return the closest match to the value.
	 */
	private RBTNode findMatch(RBTNode x, int value) {
		if( x == null)
			return null;
		// Returning a match or calling the method recursively
		int xValue = x.getValue();
		if (xValue == value)
			return x;
		else if (xValue > value)
			return x.hasLeftChild() ? findMatch(x.getLeftChild(), value) : x;
		else
			return x.hasRightChild() ? findMatch(x.getRightChild(), value) : x;
	}

	protected void zig(RBTNode child, RBTNode par){
		if(child == null || par == null)
			return;
		if(child == par.getParent())
			return;
		
		/* Fixing relation with granparent*/
		RBTNode grandpar = par.getParent();
		child.setParent(grandpar);
		if(grandpar==null){
			root = child;
		}else if(isLeftZig(par, grandpar)){
			grandpar.setLeftChild(child);
		}else{
			grandpar.setRightChild(child);
		}
		
		/* The swap*/
		RBTNode grandchild;
		if(isLeftZig(child, par)){
			grandchild = child.getRightChild();
			par.setLeftChild(grandchild);
			child.setRightChild(par);
		}else{
			grandchild = child.getLeftChild();
			par.setRightChild(grandchild);
			child.setLeftChild(par);
		}
		par.setParent(child);
	}
	/*
	 * The zigzag sets the actual node, x, in the position that the grandparent,
	 * gp, had, and assigns the two children of x (can be null) to p and gp. It
	 * also switches the reference from an eventual great grandparent to x.
	 * 
	 * Then the color of p and its eventual children are inverted.
	 *
	 * Assumes that the grandparent, gs, the parents sibling, ps, and the
	 * sibling of x, s, are all black, while the parent, p, and the actual node,
	 * x, are red.
	 * 
	 * Also assumes that gs, p, and x are not null.
	 */
	protected void zigzag(RBTNode x, RBTNode p, RBTNode gp) {
		boolean isLeftZig = isLeftZig(p, gp);
		// Gets great grandparent for later updating
		RBTNode ggp = gp.getParent();
		// Gets the left child of x
		RBTNode leftChild = x.getLeftChild();
		// Gets the right child of x
		RBTNode rightChild = x.getRightChild();

		// Assignments and rotations if it's a left zigzag
		if (isLeftZig) {
			// Switch x with gp
			x.setRightChild(gp);
			x.setLeftChild(p);
			x.setParent(ggp); // It's ok if ggp is null
			gp.setParent(x);
			p.setParent(x);
			// Reattach the previous children of x
			gp.setLeftChild(rightChild);
			p.setRightChild(leftChild);
		}
		// Assignments and rotations if it's a right zigzig
		else {
			// Switch x with gp
			x.setLeftChild(gp);
			x.setRightChild(p);
			x.setParent(ggp); // It's ok if ggp is null
			gp.setParent(x);
			p.setParent(x);
			// Reattach the previous children of x
			gp.setRightChild(rightChild);
			p.setLeftChild(leftChild);
		}
		// Only x and gp need to change color in the zigzag rotation
		x.changeCol();
		gp.changeCol();

		// Update the root, if needed
		if (gp == root)
			root = x;
		// Otherwise ggp is not null and x must be connected to ggp
		else {
			// If the parent is on the left zig from ggp
			if (ggp.getValue() > x.getValue())
				ggp.setLeftChild(x);
			else
				ggp.setRightChild(x);
		}
	}

	private boolean isLeftZig(RBTNode child, RBTNode p) {
		// If the first zig is right
		return p.getLeftChild() == child ? true : false;
	}

	@Override
	public boolean isBalanced() {
		return IsBalanced.isOrdered(this);
	}

	@Override
	public List<Integer> getTree() {
		//TODO fix crash
		treeAsArray = ToArray.convertToList(this);
		return ToArray.convertToIntegerList(treeAsArray);
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public void recolor(int indexOfNode) {
		treeAsArray.get(indexOfNode).changeCol();
	}

	@Override
	public void zig(int indexOfChild, int indexOfPar) {
		RBTNode child = treeAsArray.get(indexOfChild);
		RBTNode par = treeAsArray.get(indexOfPar);
		zig(child,par);
	}

	@Override
	public void zigzag(int indexOfChild, int indexOfGrandParent) {
		RBTNode child = treeAsArray.get(indexOfChild);
		RBTNode grandpar = treeAsArray.get(indexOfGrandParent);
		RBTNode par = child.getParent();
		zigzag(child, par, grandpar);
	}

	/***
	 * ***** FOR THE TEST PROGRAM
	 */
	
	
	/**
	 * A method for finding the node that matches the value
	 * 
	 * @param value
	 *            the value to search the tree for
	 * @return either the node that matches the value or null, if such node can
	 *         not be found
	 */
	protected RBTNode find(int value) {
		if (root == null)
			return null;
		return find(root, value);
	}

	private RBTNode find(RBTNode x, int value) {
	// Returning a match, null or calling the method recursively
	int xValue = x.getValue();
	// Return if the node matches the value
	if (xValue == value)
		return x;
	// If the found nodes value is too small, iterate downwards or return
	// null if
	// there are no nodes further down the branch
	else if (xValue > value)
		return x.hasLeftChild() ? find(x.getLeftChild(), value) : null;
	// If the found nodes value is too large, iterate downwards or return
	// null if
	// there are no nodes further down the branch
	else
		return x.hasRightChild() ? find(x.getRightChild(), value) : null;
}
}

package redBlackTree;

import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import javax.swing.*;

/**
 * A class for testing the tree class.
 * 
 * A RedBlackTree is in order when a) the root is black, b) a red node never has
 * a red parent, c) there are exactly as many black nodes on each branch
 * (counting from null).
 * 
 * The point of those rules are that if the tree fulfills these requirements it
 * will then have log(n) as the maximal depth, with n as the total number of
 * nodes.
 * 
 * A RedBlackTree is also a binary search tree, which means that each node have
 * up to two children and that it holds for any node x that the (eventual) left
 * child is smaller than x and the (eventual) right child is larger. The various
 * operations below all conserve that property though.
 * 
 * @author Markus
 *
 */
class DebuggingClass {
	static int valOne;
	static int valTwo;
	static int valThree;

	public static void main(String[] args) {
		RBTree  tree = new RBTree ();
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(6);
		stack.push(5);
		stack.push(15);
		stack.push(4);
		stack.push(8);
		stack.push(9);
		stack.push(11);
		stack.push(10);
		stack.push(12);
		Scanner sc = new Scanner(System.in);
		String command;
		int score = 0;

		while (!stack.isEmpty()) {
			tree.addNode(stack.pop());
			System.out.println("Next round! The tree currently looks like: \n");
			printLinkedTree(tree);
			while (true) {
				command = sc.next();
				// command = JOptionPane.showInputDialog("Please input a
				// command");

				// Prints an array which follows the 'multiplicative formula'
				if (command.equalsIgnoreCase("array")) {
					List<RBTNode> ls = ToArray.convertToList(tree);

					System.out.println("\nThe array looks like: ");
					for (RBTNode node : ls) {
						if (node == null) {
							System.out.print(" nullNode ");
						} else {
							System.out.print(" " + node.getValue() + " ");
						}
					}
					System.out.println("\n");
				}
				// Prompts the user for a number and then adds that number to
				// the tree.
				if (command.equalsIgnoreCase("add")) {
					add(tree);
				}
				// Prompts for three nodes and then changes the tree according
				// to the zigzig pattern
				if (command.equalsIgnoreCase("zig")) {
					zig(tree);
				}
				// Prompts for three nodes and then changes the tree according
				// to the zigzig pattern
				if (command.equalsIgnoreCase("zigzag")) {
					zigzag(tree);
				}
				// Prompts for three nodes and then changes the color of these
				// three
				if (command.equalsIgnoreCase("recolor")) {
					recolor(tree);
				}
				// Changes the color of the root
				if (command.equalsIgnoreCase("recolor_root")) {
//					recolor_root(tree);
				}
				// Goes to the next round
				if (command.equalsIgnoreCase("pass")) {
					break;
				}
				System.out.println("The tree currently looks like: \n");
				printLinkedTree(tree);
			}
			if (IsBalanced.isOrdered(tree)) {
				score++;
				System.out.println("Congratulations! The tree is in order. Your score is: " + score + "\n");
			} else {
				System.out.println("The tree is not in order. Try again. \n\n");
			}
		}
		sc.close();
	}

	private static void add(RBTree  tree) {
		try {
			valOne = Integer.parseInt(JOptionPane.showInputDialog("Please input a value to add"));
			tree.addNode(valOne);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "That was not an integer", "OBS!", +JOptionPane.ERROR_MESSAGE);
		}
	}

	private static void zig(RBTree  tree) {
		try {
			valOne = Integer.parseInt(JOptionPane.showInputDialog("Please input the value of the child"));
			valTwo = Integer.parseInt(JOptionPane.showInputDialog("Please input a value of the parent"));
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Something was not an integer", "OBS!", +JOptionPane.ERROR_MESSAGE);
			return;
		}
		RBTNode x = tree.find(valOne);
		RBTNode p = tree.find(valTwo);
		if (x == null || p == null) {
			JOptionPane.showMessageDialog(null, "At least one of the three nodes could not be found", "OBS!",
					+JOptionPane.ERROR_MESSAGE);
			return;
		}
		tree.zig(x, p);
	}

	private static void zigzag(RBTree  tree) {
		try {
			valOne = Integer.parseInt(JOptionPane.showInputDialog("Please input the value of the child"));
			valTwo = Integer.parseInt(JOptionPane.showInputDialog("Please input a value of the parent"));
			valThree = Integer.parseInt(JOptionPane.showInputDialog("Please input a value of the grandparent"));
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Something was not an integer", "OBS!", +JOptionPane.ERROR_MESSAGE);
			return;
		}
		RBTNode x = tree.find(valOne);
		RBTNode p = tree.find(valTwo);
		RBTNode gp = tree.find(valThree);
		if (x == null || p == null || gp == null) {
			JOptionPane.showMessageDialog(null, "At least one of the three nodes could not be found", "OBS!",
					+JOptionPane.ERROR_MESSAGE);
			return;
		}
		tree.zigzag(x, p, gp);
	}

	private static void recolor(RBTree  tree) {
//		try {
//			int val = Integer.parseInt(JOptionPane.showInputDialog("Please input a value of the node"));
//		} catch (NumberFormatException e) {
//			JOptionPane.showMessageDialog(null, "Something was not an integer", "OBS!", +JOptionPane.ERROR_MESSAGE);
//			return;
//		}
//
//		RBTNode node = tree.find(val);
//		if (node == null) {
//			JOptionPane.showMessageDialog(null, " The node could not be found", "OBS!",
//					+JOptionPane.ERROR_MESSAGE);
//			return;
//		}
//		tree.recolor(node);
	}
	
	/**
	 * A method which prints a linked tree using a folder structure with the
	 * root most to the left.
	 * 
	 * @param tree
	 *            the tree to print.
	 */
	private static void printLinkedTree(RBTree tree) {
		RBTNode root = tree.getRoot();
		if (root == null)
			System.out.println("Empty tree");
		printLinkedTree(root, "");
	}

	private static void printLinkedTree(RBTNode node, String indent) {
		// Can be changed to print a string containing '-' at each leaf.
		if (node == null)
			return;
		printLinkedTree(node.getRightChild(), indent + "\t");
		System.out.println(indent + node.getValue() + " (" + node.getColor().toString() + ")");
		printLinkedTree(node.getLeftChild(), indent + "\t");
	}
	
	private static void printNode(RBTNode node) {
		System.out.println("Value of node: " + node.getValue());
		if (node.getParent() == null) {
			System.out.println("Parent is null");
		} else {
			System.out.println("Value of parent is: " + node.getParent().getValue());
		}
		if (node.getLeftChild() == null) {
			System.out.println("Left child is null");
		} else {
			System.out.println("Value of left child: " + node.getLeftChild().getValue());
		}
		if (node.getRightChild() == null) {
			System.out.println("Right child is null");
		} else {
			System.out.println("Value of right child: " + node.getRightChild().getValue());
		}
		System.out.print("\n");
	}
}

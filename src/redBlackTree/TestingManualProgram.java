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
public class TestingManualProgram {
	static int valOne;
	static int valTwo;
	static int valThree;

	public static void main(String[] args) {
		RBTreeManual tree = new RBTreeManual();
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
			Auxiliary.printLinkedTree(tree);
			while (sc.hasNext()) {
				command = sc.next();
				// command = JOptionPane.showInputDialog("Please input a
				// command");

				// Prints an array which follows the 'multiplicative formula'
				if (command.equalsIgnoreCase("array")) {
					List<RBTNode> ls = Auxiliary.convertToArray(tree);

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
				if (command.equalsIgnoreCase("zigzig")) {
					zigzig(tree);
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
					recolor_root(tree);
				}
				// Goes to the next round
				if (command.equalsIgnoreCase("pass")) {
					break;
				}
				System.out.println("The tree currently looks like: \n");
				Auxiliary.printLinkedTree(tree);
			}
			if (TestRBTreeOrder.isOrdered(tree)) {
				score++;
				System.out.println("Congratulations! The tree is in order. Your score is: " + score + "\n");
			} else {
				System.out.println("The tree is not in order. Try again. \n\n");
			}
		}
		sc.close();
	}

	private static void add(RBTreeManual tree) {
		try {
			valOne = Integer.parseInt(JOptionPane.showInputDialog("Please input a value to add"));
			tree.addNode(valOne);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "That was not an integer", "OBS!", +JOptionPane.ERROR_MESSAGE);
		}
	}

	private static void zigzig(RBTreeManual tree) {
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
		tree.zigzig(x, p, gp);
	}

	private static void zigzag(RBTreeManual tree) {
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

	private static void recolor(RBTreeManual tree) {
		try {
			valOne = Integer.parseInt(JOptionPane.showInputDialog("Please input the value of the left child"));
			valTwo = Integer.parseInt(JOptionPane.showInputDialog("Please input a value of the right child"));
			valThree = Integer.parseInt(JOptionPane.showInputDialog("Please input a value of the parent"));
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Something was not an integer", "OBS!", +JOptionPane.ERROR_MESSAGE);
			return;
		}
		RBTNode leftChild = tree.find(valOne);
		RBTNode rightChild = tree.find(valTwo);
		RBTNode x = tree.find(valThree);
		if (x == null || leftChild == null || rightChild == null) {
			JOptionPane.showMessageDialog(null, "At least one of the  three nodes could not be found", "OBS!",
					+JOptionPane.ERROR_MESSAGE);
			return;
		}
		tree.recolorTriangle(x, leftChild, rightChild);
	}

	private static void recolor_root(RBTreeManual tree) {
		tree.recolorRoot();
	}
}

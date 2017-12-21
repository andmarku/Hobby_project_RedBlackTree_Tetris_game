package redBlackTree;

import java.util.Scanner;
import java.util.Stack;

import javax.swing.*;

/**
 * A class for testing the tree class.
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
		stack.push(4);
		stack.push(5);
		stack.push(6);
		stack.push(7);
		stack.push(8);
		stack.push(9);
		stack.push(10);
		stack.push(11);
		stack.push(12);
		Scanner sc = new Scanner(System.in);
		String command;

		while(!stack.isEmpty()){
			tree.addNode(stack.pop());
			System.out.println("Next round! The tree currently looks like: \n");
			Auxiliary.printLinkedTree(tree);
			while (sc.hasNext()) {
				command = sc.next();
				// command = JOptionPane.showInputDialog("Please input a command");
				if (command.equalsIgnoreCase("add")) {
					add(tree);
				}
				if (command.equalsIgnoreCase("zigzig")) {
					zigzig(tree);
				}
				if (command.equalsIgnoreCase("zigzag")) {
					zigzag(tree);
				}
				if (command.equalsIgnoreCase("recolor")) {
					recolor(tree);
				}
				if (command.equalsIgnoreCase("recolor_root")) {
					recolor_root(tree);
				}
				System.out.println("The tree is ordered: " + TestRBTreeOrder.isOrdered(tree) + "\n\n");
				break;
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

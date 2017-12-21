package redBlackTree;

import java.util.Scanner;

/**
 * A class for testing the tree class.
 * 
 * @author Markus
 *
 */
public class TestingProgram {
	public static void main(String[] args) {
		RBTree tree = new RBTree();
		
//		tree.addNode(0);
//		tree.addNode(2);
//		tree.addNode(20);
//		tree.addNode(60);
//		tree.addNode(40);
//		tree.addNode(84);
//		tree.addNode(530);
//		System.out.println("The tree is ordered: " + TestRBTreeOrder.isOrdered(tree));
//		Auxiliary.printLinkedTree(tree);
//		
		
		Scanner sc = new Scanner(System.in);
		int col;
		while (sc.hasNextInt()) {
			col = sc.nextInt();
			tree.addNode(col);
			System.out.println("The tree, after adding " + col + " is ordered: " + TestRBTreeOrder.isOrdered(tree));
			Auxiliary.printLinkedTree(tree);
			
		}
		sc.close();

	}
}

package redBlackTree;

import java.util.List;

public class DebuggingGameClass {
	public static void main(String[] args) {
		System.out.println("Testing the Game class \n");
		Game game = new Game();
		
		game.newGame();
		pStats(game);
		
		System.out.println("Adding a first node");
		game.addNode();
		pStats(game);
		
		System.out.println("Adding a second node");
		game.addNode();
		pStats(game);
		
		System.out.println("Adding a third node");
		game.addNode();
		pStats(game);
		
		System.out.println("Adding a fourth node");
		game.addNode();
		pStats(game);
		
		System.out.println("Adding a fifth node");
		game.addNode();
		pStats(game);
		
		System.out.println("Adding a sixth node");
		game.addNode();
		pStats(game);

	}
	private static void pStats(IGame g){
		System.out.println("Printing out stats:");
		System.out.println("Level: " + g.getLevel());
		System.out.println("Number of nodes left: " + g.getNodesLeft());
		System.out.println("Is it balanced?: " + g.isBalanced());
		printTreeAsArray(g.getTree());
		System.out.println("The tree currently looks like:");

		// TODO set the back to private
		// Remember to make to tree public in the game class.
		printLinkedTree((RBTree) ((Game) g).tree);
		
		System.out.println("--------------- \n");
	}
	
	private static void printTreeAsArray(List<Integer> treeLs){
		if (treeLs == null)
			System.out.println("Empty tree");
		
		System.out.println("The array return with information about tree:");
		for( Integer num : treeLs){
			System.out.print("\t" + num);
		}
		System.out.print("\n");
	}
	
	private static void printLinkedTree(RBTree tree) {
		RBTNode root = tree.getRoot();
		if (root == null)
			System.out.println("\t Empty tree");
		printLinkedTree(root, "\t");
	}

	private static void printLinkedTree(RBTNode node, String indent) {
		// Can be changed to print a string containing '-' at each leaf.
		if (node == null)
			return;
		printLinkedTree(node.getRightChild(), indent + "\t");
		System.out.println(indent + node.getValue() + " (" + node.getColor().toString() + ")");
		printLinkedTree(node.getLeftChild(), indent + "\t");
	}
}

package redBlackTree;

import java.util.ArrayList;
import java.util.List;

public class ToArray {

	/**
	 * A method which prints a linked tree using a folder structure with the
	 * root most to the left.
	 * 
	 * @param tree
	 *            the tree to print.
	 */
	public static List<RBTNode> convertToList(RBTreeManual tree) {
		List<RBTNode> ls = new ArrayList<>();
		if (tree.getRoot() == null)
			return ls;

		// Fill the list with lots of null nodes.

		for (int j = 0; j < 100; j++) {
			ls.add(null);
		}

		convertToList(tree.getRoot(), 0, ls);

		// Fit the size
		List<RBTNode> finalList = new ArrayList<>();
		int i = 0;
		boolean hasNextNode = false;
		for (RBTNode node : ls) {
			if (node != null) {
				finalList.add(node);
			} else {
				for (int j = i + 1; j < ls.size(); j++) {
					if (ls.get(j) != null) {
						hasNextNode = true;
						break;
					}
				}
				if (hasNextNode)
					finalList.add(node);
				hasNextNode = false;
			}
			i++;
		}
		return finalList;
	}

	private static void convertToList(RBTNode node, int posOfNode, List<RBTNode> ls) {
		if (node == null)
			return;
		ls.add(posOfNode, node);
		// Add one to index before multiplication, then subtract to get correct
		// index
		convertToList(node.getLeftChild(), (posOfNode + 1) * 2 - 1, ls);
		convertToList(node.getRightChild(), (posOfNode + 1) * 2, ls);
	}

	/**
	 * Turns a list with nodes into a list with integer, with 1 as red, 2 as
	 * black and -1 as null.
	 * 
	 * @param ls
	 *            list with nodes
	 * @return a list with integers denoting the color of the corresponding
	 *         nodes, with -1 for null nodes
	 */
	public static List<Integer> convertToIntegerList(List<RBTNode> ls) {
		List<Integer> intLs = new ArrayList<>();
		for( RBTNode node : ls){
			if( node == null)
				intLs.add(-1);
			if( node.getColor() == RBTNode.Col.RED)
				intLs.add(1);
			if( node.getColor() == RBTNode.Col.BLACK)
				intLs.add(1);
		}
		return intLs;
	}

}

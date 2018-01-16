package redBlackTree;

import java.util.List;

/**
 * An interface specifying how the GUI can interact with the game model (which
 * contains and manipulates the tree).
 * 
 * @author Markus
 * @version 1
 */
public interface IGame {

	/**
	 * Resets the tree and starts a new game.
	 */
	void newGame();

	/**
	 * Add a node to the tree. The node will be added according to a schema
	 * determined by the level.
	 */
	void addNode();

	/**
	 * Query method for the number of nodes left in the round.
	 * 
	 * @return the number of nodes left on the level.
	 */
	int getNodesLeft();

	/**
	 * Query method for the level.
	 * 
	 * @return which level the current game is played on.
	 */
	int getLevel();

	/**
	 * Query method for the whether the tree is in order or not.
	 * 
	 * @return true if the tree is balanced, otherwise false.
	 */
	boolean isBalanced();

	/**
	 * Query method for the nodes in the tree, where each node is represented
	 * with 1 if it's red, 2 if it's black and 0 if it's null.
	 * 
	 * @return a list containing integers representing tree.
	 */
	List<Integer> getTree();

	/**
	 * Modifier method which changes the color of the specified node.
	 * 
	 * @param indexOfNode
	 *            the index of the node to be recolored.
	 */
	void recolor(int indexOfNode);

	/**
	 * Modifier method which performs a zig-operation on the tree.
	 * 
	 * @param indexOfChild
	 *            the index of the child node of the operation,
	 * @param indexOfPar
	 *            the index of the parent node of the operation.
	 */
	void zig(int indexOfChild, int indexOfPar);

	/**
	 * Modifier method which performs a zigzag-operation on the tree.
	 * 
	 * @param indexOfChild
	 *            the index of the child node of the operation,
	 * @param indexOfGrandParent
	 *            the index of the grand parent node of the operation.
	 */
	void zigzag(int indexOfChild, int indexOfGrandParent);
}

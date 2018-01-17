package redBlackTree;

import java.util.List;
import java.util.Stack;

public class Game implements IGame {
	// Game related
	private int level;
	private RBTreeManual tree;
	private Stack<Integer> valuesToAdd;

	@Override
	public void newGame() {
		newGame(1);
	}
	
	@Override
	public void newGame(int level) {
		tree = new RBTreeManual();
		this.level = level;
		valuesToAdd = Levels.getLevelList(level);
	}

	@Override
	public void addNode() {
		if(getNodesLeft() > 0)
			tree.addNode(valuesToAdd.pop());
	}

	@Override
	public int getNodesLeft() {
		return valuesToAdd.size();
	}

	@Override
	public int getLevel() {
		return level;
	}

	/*
	 * *** Tasks forwarded to the tree class. ***
	 */
	
	@Override
	public boolean isBalanced() {
		return tree.isBalanced();
	}

	@Override
	public List<Integer> getTree() {
		return tree.getTree();
	}

	@Override
	public void recolor(int indexOfNode) {
		tree.recolor(indexOfNode);
	}

	@Override
	public void zig(int indexOfChild, int indexOfPar) {
		tree.zig(indexOfChild, indexOfPar);
		
	}

	@Override
	public void zigzag(int indexOfChild, int indexOfGrandParent) {
		tree.zigzag(indexOfChild, indexOfGrandParent);
		
	}

}

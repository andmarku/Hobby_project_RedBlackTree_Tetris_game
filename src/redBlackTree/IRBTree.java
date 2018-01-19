package redBlackTree;

import java.util.List;

public interface IRBTree {
	void addNode(int value);

	boolean isBalanced();

	List<Integer> getTree();

	int getSize();

	void recolor(int indexOfNode);

	void zig(int indexOfChild, int indexOfPar);

	void zigzag(int indexOfChild, int indexOfGrandParent);
}

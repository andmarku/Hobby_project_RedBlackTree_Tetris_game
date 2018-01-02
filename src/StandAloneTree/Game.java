package StandAloneTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.swing.JOptionPane;

public class Game implements IMovable, IModelable {
	private List<IViewListener> listeners;
	Thread timer;
	
	// Game related
	private int score;
	private RBTreeManual tree;
	private Stack<Integer> valuesToAdd;

	public Game() {
		// Initialising the list of listeners
		listeners = new ArrayList<>();
		
		// Initialising timer thread
		timer = new Thread();
		
		// Initialising the game
		score = 0;
		tree = new RBTreeManual();

		// Initialises the values that the player will face.
		valuesToAdd = new Stack<Integer>();
		valuesToAdd.push(6);
		valuesToAdd.push(5);
		valuesToAdd.push(15);
		valuesToAdd.push(4);
		valuesToAdd.push(8);
		valuesToAdd.push(9);
		valuesToAdd.push(11);
		valuesToAdd.push(10);
		valuesToAdd.push(12);
	}

	private void nextRound() {
		int score = updateScore();
		tree.addNode(valuesToAdd.pop());
		boolean isInOrder = TestRBTreeOrder.isOrdered(tree);
		List<RBTNode> nodes = Auxiliary.convertTreeToList(tree);
		notifyViewListeners(score, isInOrder, nodes);
	}

	private int updateScore() {
		if (TestRBTreeOrder.isOrdered(tree))
			score++;
		return score;
	}
	
	private void zigzig(int child, int parent, int grandparent) {
		RBTNode x = tree.find(child);
		RBTNode p = tree.find(parent);
		RBTNode gp = tree.find(grandparent);
		tree.zigzig(x, p, gp);
	}

	private void zigzag(int a, int b, int c) {
		RBTNode x = tree.find(a);
		RBTNode p = tree.find(b);
		RBTNode gp = tree.find(c);
		tree.zigzag(x, p, gp);
	}

	private void recolor(int left, int right, int p) {
		RBTNode leftChild = tree.find(left);
		RBTNode rightChild = tree.find(right);
		RBTNode x = tree.find(p);
		tree.recolorTriangle(x, leftChild, rightChild);
	}

	private void recolor_root() {
		tree.recolorRoot();
	}

	////////////////////// Getters
	public int getScore() {
		return score;
	}

	public RBTNode getTree() {
		return tree.getRoot();
	}

	////////////////////// Interface IMovable
	@Override
	public void subscribe(IViewListener view) {
		listeners.add(view);
	}

	@Override
	public void unSubscribe(IViewListener view) {
		listeners.remove(view);

	}

	private void notifyViewListeners(int score, boolean isInOrder, List<RBTNode> nodes) {
		for (IViewListener view : listeners) {
			view.update(score, isInOrder, nodes);
		}
	}

	////////////////////// Interface IMovable
	@Override
	public void makeMove(String command) {
		// Changes the color of the root
		if (command.equalsIgnoreCase("recolor_root")) {
			recolor_root();
		}
		// Goes to the next round
		if (command.equalsIgnoreCase("nextRound")) {
			nextRound();
		}
	}

	@Override
	public void makeMove(String command, int a, int b, int c) {
		// Prompts for three nodes and then changes the tree according
		// to the zigzig pattern
		if (command.equalsIgnoreCase("zigzig")) {
			zigzig(a, b, c);
		}
		// Prompts for three nodes and then changes the tree according
		// to the zigzig pattern
		if (command.equalsIgnoreCase("zigzag")) {
			zigzag(a, b, c);
		}
		// Prompts for three nodes and then changes the color of these
		// three
		if (command.equalsIgnoreCase("recolor")) {
			recolor(a, b, c);
		}
	}
}

package redBlackTree;

/**
 * Node class for filling a RedBlackTree.
 * 
 * @author Markus
 *
 */
class RBTNode {
	protected enum Col {
		RED, BLACK;
		public static Col opposite(Col t) {
			return t == BLACK ? RED : BLACK;
		}
	}
	private Col color;
	private int value;
	private RBTNode parent;
	private RBTNode leftChild;
	private RBTNode rightChild;
	protected RBTNode(int value) {
		this(value, null);
	}
	
	protected RBTNode(int value, RBTNode parent) {
		// Always initialize as red.
		this.color = Col.RED;
		this.value = value;
	}
	
	protected void setParent(RBTNode node){
		parent = node;
	}

	protected void setLeftChild(RBTNode node){
		leftChild = node;
	}

	protected void setRightChild(RBTNode node){
		rightChild = node;
	}
	protected RBTNode getParent(){
		return parent;
	}

	protected RBTNode getLeftChild(){
		return leftChild;
	}

	protected RBTNode getRightChild(){
		return rightChild;
	}
	
	protected int getValue(){
		return value;
	}
	protected Col getColor(){
		return color;
	}
	protected void changeCol() {
		color = Col.opposite(color);
	}
	
	protected boolean isRoot(){
		return parent == null? true : false;
	}
	
	protected boolean hasLeftChild(){
		return leftChild == null? false:true;
	}
	
	protected boolean hasRightChild(){
		return rightChild == null? false:true;
	}
}

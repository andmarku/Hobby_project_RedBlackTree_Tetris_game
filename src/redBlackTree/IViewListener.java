package redBlackTree;

import java.util.List;

public interface IViewListener {
	public void update(int score, boolean isInOrder, List<RBTNode> nodes);
}

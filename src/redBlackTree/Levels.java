package redBlackTree;

import java.util.Stack;

class Levels {
	protected static Stack<Integer> getLevelList(int level){
		switch (level){
		case 2:
			return getLevelTwo();
		default:
			return getLevelOne();
		}
	}
	
	private static Stack<Integer> getLevelOne(){
		Stack<Integer> valuesInLevel = new Stack<Integer>();
		valuesInLevel.push(15);
		valuesInLevel.push(11);
		valuesInLevel.push(10);
		valuesInLevel.push(6);
		valuesInLevel.push(5);
		valuesInLevel.push(12);
		return valuesInLevel;
	}
	
	private static Stack<Integer> getLevelTwo(){
		Stack<Integer> valuesInLevel = new Stack<Integer>();
		valuesInLevel.push(6);
		valuesInLevel.push(5);
		valuesInLevel.push(15);
		valuesInLevel.push(4);
		valuesInLevel.push(8);
		valuesInLevel.push(9);
		valuesInLevel.push(11);
		valuesInLevel.push(10);
		valuesInLevel.push(12);
		return valuesInLevel;
	}
}

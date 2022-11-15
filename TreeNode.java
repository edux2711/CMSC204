//Eduardo Gonzalez
public class TreeNode<T> {
	
	//Each node in a tree will store its data and will point to its left and right child
	protected T data;
	protected TreeNode<T> left, 
						  right;
	
	//First constructor, takes data and stores it in the node, left and right are set to null
	public TreeNode(T dataNode) {
		this.data = dataNode;
		left = right = null;
	}
	
	//Second constructor, copies the fields of a node 
	public TreeNode(TreeNode<T> node) {
		this.data = node.data;
		this.left = node.left;
		this.right = node.right;
	}
	
	//Getters and setter:
	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public void setLeft(TreeNode<T> left) {
		this.left = left;
	}

	public void setRight(TreeNode<T> right) {
		this.right = right;
	}

	public TreeNode<T> getLeft() {
		return left;
	}

	public TreeNode<T> getRight() {
		return right;
	}
	
}

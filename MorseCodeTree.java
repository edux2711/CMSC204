import java.util.ArrayList;

public class MorseCodeTree implements LinkedConverterTreeInterface<String> {

	//Root of tree and any possible subtree
	private TreeNode<String> root;
	
	//Constructor. Calls the buildTree method and sets the root of the tree to an empty string
	public MorseCodeTree() {
		root = new TreeNode<String>("");
		buildTree();
	}
	
	//Returns a reference to the root
	@Override
	public TreeNode<String> getRoot() {
		return root;
	}

	/*
	 * sets the root of the MorseCodeTree
	 */
	@Override
	public void setRoot(TreeNode<String> newNode) {
		root = newNode;
	}

	/*
	 * Adds element to the correct position in the tree based on the code 
	 * This method will call the recursive method addNode
	 */
	@Override
	public void insert(String code, String result) {
		addNode(this.root, code, result);
	}

	/*
	 * This is a recursive method that adds element to the correct
	 * position in the tree based on the code.
	 */
	@Override
	public void addNode(TreeNode<String> root, String code, String letter) {
		
		//Create a new node to store a letter
		TreeNode<String> newNode = new TreeNode<>(letter);
		
		//If the first character of code is '.' and the left child is null, set newNode equal to the left child of root
		if(code.charAt(0) == '.') {
			if(root.left == null)
				root.left = newNode;
			//Otherwise move to the left child of root and check the next character of code
			else
				addNode(root.left, code.substring(1), letter);
		} 
		
		//If the first character of code is '-' and the right child is null, set newNode equal to the right child of root
		if(code.charAt(0) == '-') {
			if(root.right == null)
				root.right = newNode;
			//Otherwise move to the right child of root and check the next character of code
			else
				addNode(root.right, code.substring(1), letter);
		} 
		
	}

	/*
	 * Fetch the data in the tree based on the code 
	 * This method will call the recursive method fetchNode
	 */
	@Override
	public String fetch(String code) {
		return fetchNode(root, code);
	}

	/*
	 * This is the recursive method that fetches the data of the TreeNode that
	 * corresponds with the code A '.' (dot) means traverse to the left.
	 */
	@Override
	public String fetchNode(TreeNode<String> root, String code) {
		//String variable to hold the result of the fetch
		String result = "";
		
		//If the first character of code is '.' and if there is no other character after code,
		//set result equal to the letter of the left child of root
		if(code.charAt(0) == '.') {
			if(code.substring(1).length() == 0)
				result = root.left.data;
			//Otherwise, set result equal to the result of the call when passing the left child of root and the next character of code as parameters
			else
				result = fetchNode(root.left, code.substring(1));
		} 

		//If the first character of code is '-' and if there is no other character after code,
		//set result equal to the letter of the right child of root
		if(code.charAt(0) == '-') {
			if(code.substring(1).length() == 0)
				result = root.right.data;
			//Otherwise, set result equal to the result of the call when passing the right child of root and the next character of code as parameters
			else
				result = fetchNode(root.right, code.substring(1));

		} 

		//Finally, if the first character of code is a '/', then set result equal to a space " "
		if(code.charAt(0) == '/')
			result = " ";
		
		//Return result
		return result;

	}

	@Override
	public LinkedConverterTreeInterface<String> delete(String data) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	public LinkedConverterTreeInterface<String> update() throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	/*
	 * This method builds the MorseCodeTree by inserting the nodes 
	 * of the tree level by level based on the code.
	 */
	@Override
	public void buildTree() {
		//The root will have a value of "" (empty string) level one: insert(".", 
		//"e"); insert("-", "t"); level two: insert("..", "i"); insert(".-", "a");
		//insert("-.", "n"); insert("--", "m"); etc. Look at the tree and 
		//the table of codes to letters in the assignment description.
		
		//level one
		insert(".", "e"); insert("-", "t");
		
		//level two: 		
		insert("..", "i"); insert(".-", "a"); insert("-.", "n"); insert("--", "m");
		
		//level three
		insert("...", "s"); insert("..-", "u"); insert(".-.", "r"); insert(".--", "w"); insert("-..", "d"); insert("-.-", "k"); insert("--.", "g"); insert("---", "o");
		
		//level four
		insert("....", "h"); insert("...-", "v"); insert("..-.", "f"); insert(".-..", "l"); insert(".--.", "p"); insert(".---", "j"); insert("-...", "b"); insert("-..-", "x"); insert("-.-.", "c"); insert("-.--", "y"); insert("--..", "z"); insert("--.-", "q");
	}

	/*
	 * Returns an ArrayList of the items in the linked Tree in LNR (In-order) 
	 * Traversal order Used for testing to make sure tree is built correctly
	 */
	@Override
	public ArrayList<String> toArrayList() {

		ArrayList<String> result = new ArrayList<>();
		
		if(root != null) {
			LNRoutputTraversal(root.left, result);
			
			result.add(root.getData().trim());
			
			LNRoutputTraversal(root.right, result);
		}
		
		return result;
	}
	

	/*
	 * The recursive method to put the contents of the tree in an ArrayList in LNR (Inorder)
	 */
	@Override
	public void LNRoutputTraversal(TreeNode<String> root, ArrayList<String> list) {

		if(root != null) {
			LNRoutputTraversal(root.left, list);
			
			list.add(root.getData().trim());

			LNRoutputTraversal(root.right, list);
		}
	}
	
}

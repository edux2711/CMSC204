/*
 * Eduardo Gonzalez
 */
import java.util.*;

public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T>{
	
	Comparator<T> compareableObject;
	
	//Creates an empty list that is associated with the specified comparator.
	public SortedDoubleLinkedList(Comparator<T> compareableObject)	{
		super();
		this.compareableObject = compareableObject; 		
	}

	//Inserts the specified element at the correct position in the sorted list. 
	//Notice we can insert the same element several times. 
	//Your implementation must traverse the list only once in order to perform the insertion.
	//Do not implement this method using iterators.
	//Notice that you don't need to call any of the super class methods in order to implement this method.
	public void add(T data) {
		
		//Create newNode that holds data and insert it in the end of the list.
		Node newNode = new Node(data,null,tail), 
			 currentNode = null;
		
		//Current tail's next will now point to newNode.
		if(tail != null) {
			tail.next = newNode;
			currentNode = tail; // CurrentNode will hold data of the second node.
		}
		//newNode will now be the head.
			tail = newNode;
				
		//If newNode is the first element in the list, make the tail equal to newNode too.
		if(size == 0) 
			head = newNode;
		size++;

		//Variable to help swap data and sort it.
		T temp = null;
		
		//Compare newNode's data to the data of all the other nodes.
		while (currentNode != null && compareableObject.compare(currentNode.data, currentNode.next.data) > 0) {
			
			//Swap if currentNode's data is supposed to come after currentNode.next.
			temp = currentNode.data;
			currentNode.data = currentNode.next.data;
			currentNode.next.data = temp;
			
			//Move back currentNode to it previous node.
			currentNode = currentNode.prev;
		}

	}
	
	//This operation is invalid for a sorted list. 
	//An UnsupportedOperationException will be generated using the message "Invalid operation for sorted list."
	public void addToEnd(T data) throws UnsupportedOperationException{
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}
	
	//This operation is invalid for a sorted list. 
	//An UnsupportedOperationException will be generated using the message "Invalid operation for sorted list."
	public void addToFront(T data) throws UnsupportedOperationException{
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}
	
	//Implements the iterator by calling the super class iterator method.
	public ListIterator<T> iterator(){
		return super.iterator();
	}
	
	//Implements the remove operation by calling the super class remove method.
	public Node remove​(T data, Comparator<T> comparator) {
		return super.remove(data, comparator);
	}

}


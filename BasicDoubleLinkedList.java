/*
 * Eduardo Gonzalez
 */
import java.util.*;

public class BasicDoubleLinkedList<T> implements Iterable<T>{
	
	protected Node head, tail;
	protected int size;
	
	//Default constructor: Sets head, tail and size to null, null, and 0 respectively to start an empty list.
	public BasicDoubleLinkedList() {
		head = null;
		tail = null;
		size = 0;
	}
	
	//Adds an element to the end of the list and updates the size of the list.
	public void addToEnd(T data) {
		
		//Create new node that holds data. Sets next equal to null and prev equal to current tail.
		Node newNode = new Node(data,null,tail);

		//Current tail's next will point to newNode if tail is not null.
		if(tail != null)
			tail.next = newNode;
		
		//newNode becomes the tail of the list.
		tail = newNode;
		
		//If newNode is the first node in the list, make the head equal to newNode too.
		if(size == 0) 
			head = newNode;
		
		//Increase size because a node was added to the list.
		size++;
	}
	
	//Adds element to the front of the list and updates the size of the list.
	public void addToFront(T data) {
		
		//Create new node that holds data. Sets next equal to the current head, and prev will be null.
		Node newNode = new Node(data,head,null);
		
		//Current head's prev will now point to newNode if the tail is not null.
		if(head != null)
			head.prev = newNode;
		
		//newNode becomes the head of the list.
		head = newNode;
		
		//If newNode is the first element in the list, make the tail equal to newNode too.
		if(size == 0) 
			tail = newNode;
		
		//increase size because a node was added to the list.
		size++;
	}
	
	//Returns but does not remove the first element from the list.
	public T getFirst() {
		if (size == 0) return null;
		return head.data;
	}
		
	//Returns but does not remove the last element from the list.
	public T getLast() {
		if (size == 0) return null;
		return tail.data;
	}

	//Returns the number of nodes in the list.
	public int getSize() {
		return size;
	}
	
	@Override //This method returns an object of the DoubleLinkedListIterator.
	public ListIterator<T> iterator() {
		return new DoubleLinkedListIterator();
	}
	
	//Removes the first instance of the targetData from the list.
	//Returns the node containing targetData if it is found in the list, returns null if targetData is not found.
	public Node remove(T targetData, Comparator<T> comparator) {
		
		if (size == 0) return null; //If the list is empty, return null.

		//Create variables to traverse the linked list.
		Node currentNode = head, temp; 
		int index = 0;
		
		//Go through the linked list.
		while (index < getSize() && currentNode != null) {
			
			//If targetData is found in the list, link the previous node to the next node.
			if(comparator.compare(targetData, currentNode.data) == 0) {
				
				//If prev is null then currentNode is the head, so next will now be the new head.
				if(currentNode.prev == null)
					head = currentNode.next;
		
				//If next is null then currentNode must be the tail, so prev will be the new tail.
				else if (currentNode.next == null)
					tail = currentNode.prev;
				
				else { 
					//If the node being eliminated is not the head of the tail, then link the previous node to the next node.
					
					//temp variable to hold prev node.
					temp = currentNode.prev;
					//prev node will now point to next node.
					currentNode.prev = currentNode.next;
					//next node will point to prev node.
					currentNode.next = temp;
				}
				
				size--; //Decrease size because a node was removed.
				return currentNode; // return node that contains the first instance found of targetData.
			}
			
			//Increase index and update currentNode to hold the corresponding next element in the linked list.
			index++;
			currentNode = currentNode.next;
		}
		
		return null; // return null if targetData is not found in the list
	}
	
	//Removes and returns the first element from the list.
	public T retrieveFirstElement() {
		if (size == 0 && head == null) return null; //Return null if there is nothing in the list.
		
		//Result variable to hold the element to be removed.
		T result = null;
		
		//Set result equal to head.data, set head equal to the second in the list element and decrease size.
		result = head.data;
		head = head.next;
		size--;
	
		//Return result variable containing the data of removed first element.
		return result;
	}
	
	//Removes and returns the last element from the list.
	public T retrieveLastElement() {
		if (size == 0 && tail == null) return null;//Return null if there is nothing in the list.
		
		//Result variable to hold the element to be removed.
		T result = null;
		
		//Set result equal to tail.data, set tail equal to the second to last element, and decrease size.
		result = tail.data;
		tail = tail.prev;
		size--;
		
		//Return result variable containing the data of the removed last element.
		return result;
	}
	
	//Returns an ArrayList of all the items in the Double Linked list.
	public ArrayList<T> toArrayList(){
		ArrayList<T> resultArray = new ArrayList<>();
		//Node variable to traverse the linked list.
		Node currentNode = head; 
		int index = 0;

		//Go through the linked list and add the data of the currentNode to the ArrayList.
		while (index < getSize() && currentNode != null) {
			resultArray.add(currentNode.data);
			//Increase index and update currentNode to hold the corresponding element in the linked list.
			index++;
			currentNode = currentNode.next;
		}
		
		//Return completed ArrayList.
		return resultArray;
	}
	
	//Inner class node
	public class Node {
		//Stores data of the node.
		protected T data;
		
		//next and prev links a node to the next and previous nodes. null means that there is no node to link to.
		protected Node next, prev;
		
		//Constructor to initiate data, next, and prev attributes.
		public Node(T data, Node next, Node prev) {
			this.data = data;	
			this.next = next;
			this.prev = prev;
		}
		
		//Constructor to initiate data. Sets next and prev to null.
		public Node(T data) {
			this(data, null, null);	
		}
	}
	
	//Inner class DoubleLinkedListIterator-------------------------------------------
	public class DoubleLinkedListIterator implements ListIterator<T>{

		private Node pointer,      //Cursor of the iterator.
					 lastReturned; //Keeps track of the previous element the cursor was pointing to.
		private int pointerIndex;  //keeps track of the position the cursor is on the list
				
		//Constructor to initialize pointer equal to head of the BasicDoubleLinkedList, and index equal to 0.
		public DoubleLinkedListIterator() {
			pointer = head;
			pointerIndex = 0;
		}
		
		@Override
		public boolean hasNext() {
			//If pointerIndex is less than 0, then return true because there is a next element
			//If pointerIndex reaches size, then there is no next element.
			return pointerIndex < size;
		}

		@Override
		public T next() throws NoSuchElementException {
			//If hasNext is false, throw exception.
			if (hasNext() == false) 
				throw new NoSuchElementException();
			
			//Store the data of the current pointer in result.
			T result = pointer.data;
			lastReturned = pointer; //Remember the current pointer before updating it.

			//Advance pointer, and increase pointerIndex.
			pointer = pointer.next;
			pointerIndex++;
			
			return result; 
		}

		@Override
		public boolean hasPrevious() {
			//If pointerIndex is greater than 0, then return true because there is a previous element in the list.
			//If pointerIndex reaches 0, then there is no previous element.
			return pointerIndex > 0;
		}

		@Override
		public T previous() {
			//If hasPrevious is false, throw exception.
			if (hasPrevious() == false) 
				throw new NoSuchElementException();

			//If pointer is at the end of the list, then make it equal to tail to move it back.
			//Also make lastReturned equal to tail.
			if(pointer == null) {
				pointer = tail;
				lastReturned = tail;
			}
			else {
				//If pointer is not at the end of the list, pointer and lastReturned are equal to the previous node.
				pointer = pointer.prev;
				lastReturned = pointer;
			}
			
			//Move index back.
			pointerIndex--;
			
			//return 
			return lastReturned.data;
		}

		@Override
		public int nextIndex() throws UnsupportedOperationException{
			// TODO Auto-generated method stub
			throw new UnsupportedOperationException();
		}

		@Override
		public int previousIndex() throws UnsupportedOperationException{
			// TODO Auto-generated method stub
			throw new UnsupportedOperationException();
		}

		@Override
		public void remove() throws UnsupportedOperationException{
			// TODO Auto-generated method stub
			throw new UnsupportedOperationException();
		}

		@Override
		public void set(T e) throws UnsupportedOperationException{
			// TODO Auto-generated method stub
			throw new UnsupportedOperationException();
		}

		@Override
		public void add(T e) throws UnsupportedOperationException{
			// TODO Auto-generated method stub
			throw new UnsupportedOperationException();
		}
		
	}

}

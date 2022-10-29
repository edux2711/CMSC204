import java.io.IOException;
import java.util.ArrayList;

public class CourseDBStructure<T> implements CourseDBStructureInterface{

	
	
	//Load factor for the assignment
	private final double LOAD_FACTOR = 1.5;
	
	//k variable (4k+3) 
	// k = (p-3) / 4
	private double k, size;
	
	private int   //Represents the number of elements the hashTable can hold
				indexes = 0;//Represents the number of elements the hashTable currently holds
	
	//Array implementation of hashTable
	CourseDBElement[] hashTable;
	
	public CourseDBStructure(int estimateSize) {

		int sizeOverLoadFactor = (int) (estimateSize / LOAD_FACTOR);

		k = find_4kPlus3Prime(sizeOverLoadFactor);
		//k = (h - 3) / 4;
		//System.out.println("kkkkk: " + k);

		//size of the table will be a prime number higher than estimateSize.
		size = k; 
		System.out.println("size: " + size);

		hashTable = new CourseDBElement[(int)size];
	}
	
	public CourseDBStructure(String testing, int size) {
		this.size = size;
		hashTable = new CourseDBElement[size];
	}
	
	
	/** 
	* Adds a CourseDBElement object to the CourseDBStructure using the hashcode
	* of the CourseDatabaseElement object's crn value.
	* If the CourseDatabaseElement already exists, exit quietly
	* @param element the CourseDBElement to be added to CourseDBStructure
	*/
	@Override
	public void add(CourseDBElement element) {
		//If the CourseDatabaseElement already exists, exit quietly
		int index = getIndex(element.getCRN());
		if(hashTable[index] != null) return;
		
		//System.out.println("jgjgjg");

		index = getIndex(element.getCRN());
		hashTable[index] = element;	
		indexes++;
		//System.out.println("ola" + indexes);

	}

	/**
	 * Find a courseDatabaseElement based on the key (crn) of the
	 * courseDatabaseElement If the CourseDatabaseElement is found return it If not,
	 * throw an IOException
	 * 
	 * @param crn crn (key) whose associated courseDatabaseElement is to be returned
	 * @return a CourseDBElement whose crn is mapped to the key
	 * @throws IOException if key is not found
	 */
	@Override
	public CourseDBElement get(int crn) throws IOException {
		// TODO Auto-generated method stub
//		for(CourseDBElement element : hashTable) {
//			if(element != null && element.getCRN() == crn)
//				return element;
//		}
		
		int index = getIndex(crn);
		
		if(hashTable[index] == null)
			throw new IOException();
		System.out.println("kikikiii: " + hashTable[index].toString());
		return hashTable[index];
	}

	/**
	 * @return an array list of string representation of each course in 
	 * the data structure separated by a new line. 
	 * Refer to the following example:
	 * Course:CMSC500 CRN:39999 Credits:4 Instructor:Nobody InParticular Room:SC100
	 * Course:CMSC600 CRN:4000 Credits:4 Instructor:Somebody Room:SC200
	 */
	@Override
	public ArrayList<String> showAll() {
		ArrayList<String> list = new ArrayList<>();
		
		for(CourseDBElement element : hashTable) {
			if(element != null) {
				list.add(element.toString());
			}
		}
		return list;
	}
	

	/**
	* Returns the size of the ConcordanceDataStructure (number of indexes in the array)
	*/
	@Override
	public int getTableSize() {
		// TODO Auto-generated method stub
		return (int)size;
	}
	
	//Takes an integer crn value and returns its index location in the hashTable.
	public int getIndex(int crn) {
		
		int index = hash(crn) % (int)size;
		
		if(index < 0) 
			index += (int)size;
		return index;
	}
	
	//Take and integer crn, convert into String and return its hash code value
	public int hash(int crn) {
		Integer reference = crn;
		String crnStr = reference.toString();
		return crnStr.hashCode();
	}
	
	//takes the n/loadFactor value and return the next 4k+3 prime number based on the value.
	public int find_4kPlus3Prime(int num){
		boolean found = false;
		int k1, 
			n = num;
		double k2;
		
	//	System.out.println("antes de entrar al loop n:" + n);
		
		//If num is not a 4k+3 prime, check the numbers above num
		while (found == false) {
		//	System.out.println("conoooooo");

			if(isPrime(n) == false) {
				n++;
				continue;
			}
			
		//	System.out.println("n:" + n);
			int j = n-3;
			//System.out.println("n-3:" + j);
			
		    
			k1 = (n - 3) / 4;
			k2 = ((double)n - 3) / 4;  
			
		//	System.out.println("k1: " + k1);
		//	System.out.println("k2: " + k2);

			//If the calculation of k as an int is equal to the calculation of k as a double
			//then it means that k must be an integer and therefore it is a 4k+3 prime
			if(k1 == k2) {
				found = true;
				break;
			}
			n++;
			//System.out.println("olallll");
		}
		//System.out.println("Returning n:" + n);
		return n;
	}
	
	boolean isPrime(int n) {
	    //check if n is a multiple of 2
	    if (n%2==0) return false;
	    //if not, then just check the odds
	    for(int i=3;i*i<=n;i+=2) {
	        if(n%i==0)
	            return false;
	    }
	    return true;
	}
	
//	//Inner class node--------------------------------------
//	public class Node{
//		private T data;
//		private Node next;
//		
//		public Node(T data, Node next) {
//			this.data = data;
//			this.next = next;
//		}
//		
//		public Node() {
//			this.data = null;
//			this.next = null;
//		}
//	}

}

//expected: 19, 50
public class CourseDBElement {

	private String courseID, roomNum, instructorName;
	private int crn, credits;
	
	public CourseDBElement(String courseID, int crn, int credits, String roomNum, String instructorName) {
		this.courseID = courseID;
		this.roomNum = roomNum;
		this.instructorName = instructorName;
		this.crn = crn;
		this.credits = credits;
	}
	
	public CourseDBElement() {
		this.courseID = null;
		this.roomNum = null;
		this.instructorName = null;
		this.crn = 0;
		this.credits = 0;
	}
	
	//Normally the CourseDBElement will be an object consisting of 
	//these five attributes and is referred to as a CDE.
	
//	@Override
//	public int compareTo(Object element) {
////		if(element.getClass() == null) {
////			return -1;
////		}
//		element = (CourseDBElement) element;
//		//if(element.ge)
//	
//		return 0;
//	}
	
	public boolean compareTo(CourseDBElement element) {
		
		if(this.equals(element) == true)
			return true;
		return false;
	
	}
	
	//Two elements are the same if they have the same hash code and if they have the same crn value
	public boolean equals(CourseDBElement element) {
		String str1 = "" + this.getCRN(),
			   str2 = "" + element.getCRN();
		
		if(str1.hashCode() == str2.hashCode() && this.getCRN() == element.getCRN()) {
			return true;
		}
		return false;
	}
	//Course:CMSC500 CRN:39999 Credits:4 Instructor:Nobody InParticular Room:SC100
	public String toString() {
		String str = 
		"Course:" + this.getID() + " CRN:" + this.getCRN() + " Credits:" + this.getCredits() + 
		" Intructor:" + this.getInstructorName() + " Room:" + this.getRoomNum();
		
		return str;
		
	}

	public String getID() {
		return courseID;
	}

	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	public String getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}

	public String getInstructorName() {
		return instructorName;
	}

	public void setInstructorName(String instructorName) {
		this.instructorName = instructorName;
	}

	public int getCRN() {
		return crn;
	}

	public void setCRN(int crn) {
		this.crn = crn;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}
		
	
}

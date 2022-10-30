import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class CourseDBManager implements CourseDBManagerInterface{
	
	private CourseDBElement cde;
	private CourseDBStructure cds = new CourseDBStructure(13);

	/**
	 * Adds a course (CourseDBElement) with the given information
	 * to CourseDBStructure.
	 * @param id course id 
	 * @param crn course crn
	 * @param credits number of credits
	 * @param roomNum course room number
	 * @param instructor name of the instructor
	 */
	@Override
	public void add(String id, int crn, int credits, String roomNum, String instructor) {
		cde = new CourseDBElement(id, crn, credits, roomNum, instructor);	
		cds.add(cde);
	}

	/**
	 * finds  CourseDBElement based on the crn key
	 * @param crn course crn (key)
	 * @return a CourseDBElement object
	 * 
	 */
	@Override
	public CourseDBElement get(int crn) {
		try {
			return cds.get(crn);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Reads the information of courses from a text file and adds them
	 * to the CourseDBStructure data structure
	 * @param input file 
	 * @throws FileNotFoundException if file does not exists
	 */
	@Override
	public void readFile(File input) throws FileNotFoundException  {

		Scanner file = null;
		String line1 = "",
			   line2 = "";

		file = new Scanner(input);

		while(file.hasNext()) {
			 line1 = file.nextLine();
			 line2 = file.nextLine();
		}
		file.close();

		int crn     = Integer.parseInt(line1.substring(8, 13)),
			credits = Integer.parseInt(line1.substring(14, 15));
		CourseDBElement cde1 = new CourseDBElement(line1.substring(0, 7), crn, credits, line1.substring(16, 21), line1.substring(22));
		
		crn = Integer.parseInt(line2.substring(8, 13));
		credits = Integer.parseInt(line2.substring(14, 15));
		CourseDBElement cde2 = new CourseDBElement(line2.substring(0, 7), crn, credits, line2.substring(16, 21), line2.substring(22));

		cds.add(cde1);
		cds.add(cde2);

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
		return cds.showAll();
	}

}

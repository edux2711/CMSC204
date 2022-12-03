import java.util.*;

public class Town implements Comparable<Town> {
	
	private String name;
	private ArrayList<Town> adjacentTowns;
	private Town backPath;
	
	
	public Town(String name) {
		this.name = name;
		this.adjacentTowns = new ArrayList<>();
	}
	
	//Copy constructor
	public Town(Town template) {
		this.name = template.name;
		for(Town t : template.adjacentTowns)
			this.adjacentTowns.add(t);
	}
	
	public String getName() {
		return name;
	}
	
	public Town getBackPath() {
		return backPath;
	}
	
	public void setBackPath(Town t) {
//		if(t.getBackPath() == null) {
//			return;
//		}
//		
//		setBackPath(t.getBackPath());
		backPath = t;
	}
	
	public ArrayList<Town> getAdjacentTowns() {
		return adjacentTowns;
	}

	@Override
	public int compareTo(Town o) {
		if(this.equals(o))
			return 0;
		return -1;
	}
	
	@Override
	public String toString() {
		String list = "";
		//for(Town t : adjacentTowns)
			//list += t + " ";
		
		return "Name: " + name;
			   //"Adjacent Towns: " + list;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	//Town objects will be equal if they have the same name.
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		Town other = (Town) obj;
		return Objects.equals(name, other.name);
	}
	
	

}

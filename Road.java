import java.util.Objects;

public class Road implements Comparable<Road> {
	
	private Town source, 
				 destination;
	
	private int distance;
	private String name;
	
	//Constructor initializes every field to a parameter
	public Road(Town source, Town destination, int distance, String name) {
		this.source 	 = source;
		this.destination = destination;
		this.distance 	 = distance;
		this.name 	  	 = name;
	}
	
	//Constructor with weight preset at 1
	public Road(Town source, Town destination, String name) {
		this(source, destination, 1, name);
	}
	
	//Returns true only if the edge contains the given town
	public boolean contains(Town town) {
		
		if(this.source.compareTo(town) == 0 
							|| 
		   this.destination.compareTo(town) == 0)
			
			return true;
		else 
			return false;
	}

	
	@Override
	public int compareTo(Road o) {
		if(this.equals(o))
			return 0;
		return -1;
	}

	@Override
	public String toString() {
		return "Source: "      + source      + "\n" +
			   "Destination: " + destination + "\n" + 
			   "Distance: "    + distance    + "\n" +
			   "Name: "        + name;
	}

	public Town getSource() {
		return source;
	}
	
	public void setSource(Town source) {
		this.source = source;
	}

	public Town getDestination() {
		return destination;
	}
	
	public void setDestination(Town destination) {
		this.destination = destination;
	}

	public int getWeight() {
		return distance;
	}

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(destination, distance, name, source);
	}

	//Road objects will be equal if they have the same distance, and name.
	@Override
	public boolean equals(Object obj) {
		
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		Road other = (Road) obj;
		return distance == other.distance && Objects.equals(name, other.name);
	}

	
	
	

}

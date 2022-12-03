import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class TownGraphManager implements TownGraphManagerInterface {
	
	private Graph graph = new Graph();

	@Override
	public boolean addRoad(String town1, String town2, int weight, String roadName) {
		//if(getTown())
		
		addTown(town1);
		addTown(town2);

		Road road = new Road(getTown(town1), getTown(town2), weight, roadName);
		
		if(graph.containsEdge(getTown(town1), getTown(town2)))
			return true;
		else
			return false;
	}

	@Override
	public String getRoad(String town1, String town2) {
		//graph.edgesOf(null)
		return null;
	}

	@Override
	public boolean addTown(String v) {
		
		Town town = new Town(v);
		graph.addVertex(town);
		
		if(graph.containsVertex(town) == true)
			return true;
		else 
			return false;
	}

	@Override
	public Town getTown(String name) {
		
		for(Town town : graph.vertexSet()) {
			if(town.getName().equals(name));
				return town;
		}
		
		return null;
	}

	@Override
	public boolean containsTown(String v) {
		
		if(getTown(v) == null) 
			return false;
		else
			return true;
		
	}

	@Override
	public boolean containsRoadConnection(String town1, String town2) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Creates an arraylist of all road titles in sorted order by road name
	 * @return an arraylist of all road titles in sorted order by road name
	 */
	@Override
	public ArrayList<String> allRoads() {
		ArrayList<String> result = new ArrayList<>();
//		for(String title : get) {
//			
//		}
		return null;
	}

	@Override
	public boolean deleteRoadConnection(String town1, String town2, String road) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteTown(String v) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<String> allTowns() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> getPath(String town1, String town2) {
		// TODO Auto-generated method stub
		return null;
	}

	public void populateTownGraph(File selectedFile) throws FileNotFoundException{
		// TODO Auto-generated method stub
		
	}

}

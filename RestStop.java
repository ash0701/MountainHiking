import java.util.ArrayList;

/**
 * This is the class responsible for creating a Rest Stop. This class implements
 * the interface Comparable<RestStop>.
 * 
 * @author Ashley Huang
 *
 */
public class RestStop implements Comparable<RestStop> {
	private String nameOfStop;
	private ArrayList<String> suppliesList = new ArrayList<>();
	private ArrayList<String> obstacleList = new ArrayList<>();

	/**
	 * Constructs a new Rest Stop with a specified name, a list of supplies(possibly
	 * empty), and a list of obstacles(possibly empty).
	 * 
	 * @param name      The label of the stop
	 * @param supplies  The list of supplies associated with the given name and
	 *                  obstacles
	 * @param obstacles The list of obstacles associated with the given name and
	 *                  supplies
	 * @throws IllegalArgumentException if name is null(there's no input)
	 */
	public RestStop(String name, ArrayList<String> supplies, ArrayList<String> obstacles)
			throws IllegalArgumentException {
		if (name == null)
			throw new IllegalArgumentException("Please make sure all of your rest stops have labels.");
		nameOfStop = name;
		if (supplies != null)
			suppliesList = supplies;

		if (obstacles != null)
			obstacleList = obstacles;
	}

	/**
	 * Returns the label of a rest stop
	 * 
	 * @return the label of the rest stop
	 */
	public String getLabel() {
		return nameOfStop;
	}

	/**
	 * Return the list of supplies of a rest stop
	 * 
	 * @return the list of supplies of the rest stop
	 */
	public ArrayList<String> getSupplies() {
		if (suppliesList.isEmpty())
			return null;
		return suppliesList;
	}

	/**
	 * Return the list of obstacles of a rest stop
	 * 
	 * @return the list of obstacles of the rest stop
	 */
	public ArrayList<String> getObstacles() {
		if (obstacleList.isEmpty())
			return null;
		return obstacleList;
	}

	/**
	 * Compares two rest stops according to their labels alphanumerically
	 * 
	 * @return 0 if the nodes have the same label, -1 if this node comes before
	 *         other node, otherwise 1 if this node comes after other node
	 */
	@Override
	public int compareTo(RestStop o) {
		return this.getLabel().compareTo(o.getLabel());
	}
}

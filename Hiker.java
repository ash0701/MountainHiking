import java.util.ArrayList;

/**
 * This class represents a hiker.
 * The Hiker consumes and picks up supplies as he treks down the mountain.
 * 
 * @author Ashley Huang
 *
 */
public class Hiker {
	private int countFood;
	private int axes;
	private int rafts;

	/**
	 * Constructs a new Hiker
	 */
	public Hiker() {
		countFood = 0;
		axes = 0;
		rafts = 0;

	}

	/**
	 * Updates supplies given a list of supplies at a specific rest stop
	 * 
	 * @param supp The list of supplies at a specific rest stop
	 * @return true if the list is empty and after successfully updating the hiker's
	 *         supplies.
	 */
	public boolean updateSupplies(ArrayList<String> supp) {

		if (supp == null)
			return true;

		for (int i = 0; i < supp.size(); i++) {
			if (supp.get(i).equals("food"))
				countFood++;
			else if (supp.get(i).equals("axe"))
				axes++;
			else if (supp.get(i).equals("raft"))
				rafts++;
		}
		return true;

	}

	/**
	 * Checks the obstacles given a list of obstacles at a specific rest stop
	 * 
	 * @param obs The list of obstacles at a specific rest stop
	 * @return true if the hiker can pass all of the objects, otherwise return
	 *         false.
	 */
	public boolean checkObstacles(ArrayList<String> obs) {
		if (obs == null)
			return true;
		if (obs != null) {
			ArrayList<String> encounters = obs;
			for (int j = 0; j < encounters.size(); j++) {
				if (!checkObstacle(encounters.get(j)))
					return false;
			}
		}
		return true;
	}

	/**
	 * Checks a specific obstacle from the list of obstacles at the specific rest
	 * stop
	 * 
	 * @param obstacle one obstacle at the rest stop
	 * @return true if hiker can pass, otherwise false.
	 */
	public boolean checkObstacle(String obstacle) {
		if (obstacle.equals("river")) {
			if (rafts <= 0)
				return false;
			else
				rafts--;
		} else if (obstacle.equals("fallen tree")) {
			if (axes <= 0)
				return false;
			else
				axes--;
		}
		return true;
	}

	/**
	 * Representative of the hiker eating food to travel one edge of the mountain
	 * tree.
	 * 
	 * @return true if countFood is greater than or equal to zero, otherwise false
	 */
	public boolean updateFood() {
		countFood--;

		if (countFood < -1)
			return false;

		return true;
	}

	/**
	 * Reset the food count to the given number n
	 * 
	 * @param n The number to set countFood to
	 */
	public void setFoodCount(int n) {
		countFood = n;
	}

	/**
	 * Reset the axe count to the given number n
	 * 
	 * @param n The number to set axes to
	 */
	public void setAxeCount(int n) {
		axes = n;
	}

	/**
	 * Reset the raft count to the given number n
	 * 
	 * @param n The number to set rafts to
	 */
	public void setRaftCount(int n) {
		rafts = n;
	}

	/**
	 * Returns the food count of the hiker
	 * 
	 * @return the food count of the hiker
	 */
	public int getFoodCount() {
		return countFood;
	}

	/**
	 * Returns the axe count of the hiker
	 * 
	 * @return the axe count of the hiker
	 */
	public int getAxes() {
		return axes;
	}

	/**
	 * Returns the raft count of the hiker
	 * 
	 * @return the raft count of the hiker
	 */
	public int getRafts() {
		return rafts;
	}

}

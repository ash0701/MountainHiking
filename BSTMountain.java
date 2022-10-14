import java.util.ArrayList;

/**
 * This class is responsible for creating the mountain and finding the paths
 * that will lead to the bottom. It implements a self-balancing binary tree.
 * 
 * @author Ashley Huang
 *
 */
public class BSTMountain {

	private Node<RestStop> root;
	private ArrayList<String> path;

	/**
	 * Constructs a new, empty tree, sorted according to the natural ordering of its
	 * elements.
	 */
	public BSTMountain() {
		root = null;
	}

	/**
	 * 
	 * Helper method for recursive function Create a new node given a RestStop, and
	 * if root is null, set root to the new node Otherwise, call recursive function
	 * 
	 * Implementation of add method with helper and recursive inspired by Professor Joanna Klukowska
	 * @param data element to be added to this tree
	 * @throws IllegalArgumentException if the rest stop does not exist
	 */
	public void add(RestStop data) throws IllegalArgumentException {
		if (data == null)
			throw new IllegalArgumentException("Please make sure you add an existing rest stop.");
		Node<RestStop> n = new Node<RestStop>(data);
		if (root == null) {// create the first node
			root = n;
			return;
		}
		add(root, n);
	}

	/**
	 * Recursive add method. Adds the specified element, n, to this tree. Duplicates
	 * will be ignored.
	 * 
	 * @param node node at which the recursive function is called on.
	 * @param n    node to be added into the tree
	 * @return node node at which the recursive call is made
	 */
	private Node<RestStop> add(Node<RestStop> node, Node<RestStop> n) {

		if (node == null)
			return n;
		if (node.compareTo(n) > 0)
			node.left = add(node.left, n);
		else if (node.compareTo(n) < 0)
			node.right = add(node.right, n);
		else // Duplicate keys not allowed
			return node;

		node.height = 1 + Math.max(height(node.left), height(node.right));

		int balance = getBalance(node);

		// LL
		if (balance < -1 && node.left.compareTo(n) > 0)
			return balanceLL(node);

		// RR
		if (balance > 1 && node.right.compareTo(n) < 0)
			return balanceRR(node);

		// LR
		if (balance < -1 && node.left.compareTo(n) < 0) {
			return balanceLR(node);
		}

		// RL
		if (balance > 1 && node.right.compareTo(n) > 0) {
			return balanceRL(node);
		}
		return node;
	}

	/**
	 * Performs an RR rotation for a tree with a balance that is greater than 1.
	 * 
	 * @param node1 The unbalanced root of a specific tree
	 * @return The new root of the specific tree
	 */

	private Node<RestStop> balanceRR(Node<RestStop> node1) {

		Node<RestStop> node2 = node1.right;
		node1.right = node2.left;
		// Perform rotation
		node2.left = node1;
		// reset root of entire tree if necessary
		if (node1.compareTo(root) == 0)
			root = node2;
		else
			node1 = node2;
		// Update heights
		updateHeight(node1);
		updateHeight(node2);
		// Return new root
		return node2;
	}

	/**
	 * Performs an LL Rotation for a tree with a balance that is less that -1.
	 * 
	 * @param node1 The unbalanced root of a specific tree
	 * @return the new root of the specific tree
	 */
	private Node<RestStop> balanceLL(Node<RestStop> node1) {
		Node<RestStop> node2 = node1.left;
		node1.left = node2.right;
		// Perform rotation
		node2.right = node1;
		// reset root of entire tree if necessary
		if (node1.compareTo(root) == 0)
			root = node2;
		else
			node1 = node2;
		// Update heights
		updateHeight(node1);
		updateHeight(node2);

		// Return new root
		return node2;
	}

	/**
	 * Performs an LR Rotation in the case that LL Rotation is not sufficient
	 * 
	 * @param node1 The unbalanced root of a specific tree
	 * @return the new root of the specific tree
	 */
	private Node<RestStop> balanceLR(Node<RestStop> node1) {
		Node<RestStop> node2 = node1.left;
		Node<RestStop> node3 = node2.right;

		// perform rotation
		node1.left = node3.right;
		node2.right = node3.left;
		node3.left = node2;
		node3.right = node1;

		updateHeight(node1);
		updateHeight(node2);
		updateHeight(node3);
		// reset root of entire tree if necessary
		if (node1.compareTo(root) == 0)
			root = node3;
		else
			node1 = node3;

		return node3;

	}

	/**
	 * Performs an RL Rotation in the case that RR Rotation is not sufficient
	 * 
	 * @param node1 The unbalanced root of a specific tree
	 * @return the new root of the specific tree
	 */
	private Node<RestStop> balanceRL(Node<RestStop> node1) {
		Node<RestStop> node2 = node1.right;
		Node<RestStop> node3 = node2.left;

		// perform rotation
		node1.right = node3.left;
		node2.left = node3.right;
		node3.left = node1;
		node3.right = node2;

		updateHeight(node1);
		updateHeight(node2);
		updateHeight(node3);

		// reset root of entire tree if necessary
		if (node1.compareTo(root) == 0)
			root = node3;
		else
			node1 = node3;

		return node3;

	}

	/**
	 * Updates the height of the root from the max of its left subtree and right
	 * subtree
	 * 
	 * @param node The root of a specific tree
	 */
	private void updateHeight(Node<RestStop> node) {
		if (node.right == null && node.left == null)
			node.height = 0;
		else if (node.left == null)
			node.height = node.right.height + 1;
		else if (node.right == null)
			node.height = node.left.height + 1;
		else
			node.height = 1 + Math.max(node.left.height, node.right.height);
	}

	/**
	 * Calculates the balance of a node in a tree from its right subtree - left
	 * subtree
	 * 
	 * @param N The root of the specific tree
	 * @return The difference between the height of node N's right subtree and left
	 *         subtree.
	 */
	private int getBalance(Node<RestStop> N) {
		if (N == null)
			return 0;

		int heightRight = 0;
		int heightLeft = 0;
		if (N.right == null)
			heightRight = 0;
		else
			heightRight = N.right.height + 1;
		if (N.left == null)
			heightLeft = 0;
		else
			heightLeft = N.left.height + 1;
		return heightRight - heightLeft;

	}

	/**
	 * Returns the height of a node
	 * 
	 * @param N the node in which we want the height
	 * @return The height of the node
	 */
	public int height(Node<RestStop> N) {
		if (N == null)
			return 0;
		return N.height;
	}

	/**
	 * This is the method responsible for finding the possible paths down the
	 * mountain. Prints all possible paths down mountain. If there is no path,
	 * nothing will be printed.
	 * 
	 */
	public void findPath() {
		if (root == null)
			return;
		int mountainHeight = height(root) + 1;
		if (mountainHeight > 1)
			mountainHeight = mountainHeight - 1;
		Hiker traveler = new Hiker();
		path = new ArrayList<>();
		int index = 0;
		travelTree(root, mountainHeight, traveler, path, index);
	}

	/**
	 * Recursive function responsible for finding the paths in a mountain.
	 * 
	 * @param stop     The current rest stop to be checked
	 * @param height   the height of the mountain. We've reached the bottom if
	 *                 height is 0
	 * @param traveler The hiker that is to travel down the mountain
	 * @param path     Keeps track of the rest stops that passed the check, removes
	 *                 a rest stop if we cannot travel down a rest stop
	 * @param index    index where the next element should be added
	 */
	private void travelTree(Node<RestStop> stop, int height, Hiker traveler, ArrayList<String> path, int index) {

		// check for cliff
		if (stop == null && height >= 0) {
			path.remove(path.size() - 1);
			return;
		}

		traveler.updateSupplies(stop.data.getSupplies());

		if (!traveler.updateFood())
			return;
		int foodAtStop = traveler.getFoodCount(); // food left after consuming for 1 edge

		// If we have no food left and we haven't reached the bottom, return
		if (foodAtStop < 0 && height != 0)
			return;
		if (!traveler.checkObstacles(stop.data.getObstacles()))
			return;

		// we've found path
		if (height == 0) {
			path.add(stop.data.getLabel());
			printPath(path);
			path.remove(stop.data.getLabel());
			return;
		}

		int axesAtStop = traveler.getAxes();
		int raftsAtStop = traveler.getRafts();

		path.add(stop.data.getLabel());
		index++;
		int updatedHeight = height - 1;

		// recursive call
		travelTree(stop.left, updatedHeight, traveler, path, index);

		removeChecked(index);
		// undo acts of left traversal so we can go to right

		if (!path.contains(stop.data.getLabel()))
			path.add(stop.data.getLabel());

		if (traveler.getFoodCount() != foodAtStop) {
			traveler.setFoodCount(foodAtStop);
		}
		if (traveler.getAxes() != axesAtStop) {
			traveler.setAxeCount(axesAtStop);
		}
		if (traveler.getRafts() != raftsAtStop) {
			traveler.setRaftCount(raftsAtStop);
		}

		// recursive call
		travelTree(stop.right, updatedHeight, traveler, path, index);
		removeChecked(index);

	}

	/**
	 * Prints out a possible path down the mountain
	 * 
	 * @param path the list of stops that successfully leads to the bottom of the
	 *             mountain
	 */
	public void printPath(ArrayList<String> path) {
		System.out.print(path.get(0) + " ");
		for (int i = 1; i < path.size(); i++) {
			if (i == path.size() - 1)
				System.out.print(path.get(i));
			else
				System.out.print(path.get(i) + " ");
		}
		System.out.println();
	}

	/**
	 * Removes extra rest stops that have already been checked past the current rest
	 * stop
	 * 
	 * @param index index at which the current rest stop is in the arraylist of rest
	 *              stops
	 */
	public void removeChecked(int index) {
		for (int i = path.size() - 1; path.size() > index; i--) {
			path.remove(i);
		}
	}

	/**
	 * Creates a Node of type RestStop to be implemented by the BSTMountain class
	 * 
	 * @author Ashley Huang
	 *
	 * @param <RestStop> Nodes will be of type RestStop
	 */
	@SuppressWarnings("hiding")
	private class Node<RestStop extends Comparable<RestStop>> implements Comparable<Node<RestStop>> {

		RestStop data;
		Node<RestStop> left;
		Node<RestStop> right;
		int height;

		/**
		 * Constructor for creating new node
		 * 
		 * @param data The data to be stored in the node
		 */
		public Node(RestStop data) {
			this.data = data;
			height = 0;
		}

		/**
		 * Compares the nodes alphabetically
		 * 
		 * @return 0 if the nodes have the same label, -1 if this node comes before
		 *         other node, otherwise 1 if this node comes after other node
		 */
		public int compareTo(Node<RestStop> other) {
			return this.data.compareTo(other.data);
		}

	}

}

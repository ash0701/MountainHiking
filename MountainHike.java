

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * This class expects an input file. Each line of the input file should provide
 * the label of the rest stop, the supplies, and the obstacles, in the
 * respective order. It is fine to have no supplies and/or obstacles at a rest
 * stop. Any supplies that come after obstacles will be ignored. 
 * Valid supplies include: food, axe, raft 
 * Valid obstacles include: river, fallen tree
 * 
 * @author Ashley Huang
 *
 */
public class MountainHike {
	public static void main(String[] args) {
		// check if file is inputed as argument
		if (args.length == 0) {
			System.err.println("Usage Error: the program expects arguments from the command line.");
			System.exit(0);
		}

		// verify that command line argument contains a name of an existing file
		File givenPath = new File(args[0]);
		if (!givenPath.exists()) {
			System.err.println("Error: the file " + givenPath.getAbsolutePath() + " does not exist.\n");
			System.exit(1);
		}
		if (!givenPath.canRead()) {
			System.err.println("Error: the file " + givenPath.getAbsolutePath() + " cannot be opened for reading.\n");
			System.exit(1);
		}

		BSTMountain mountain = new BSTMountain();

		try {

			String line = null;
			String[] arr = new String[20];

			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader(givenPath));

			/*
			 * Read through file and store each line in a Rest Stop and add the rest stop to
			 * the mountain
			 */
			while ((line = br.readLine()) != null) {

				try {
					// break each line into an array
					arr = splitInputLine(line);
				} catch (NoSuchElementException ex) {
					// caused by an incomplete or miss-formatted line in the input file
					System.err.println(line);
					continue;
				}
				try {

					ArrayList<String> givenSupplies = new ArrayList<>();
					ArrayList<String> givenObstacles = new ArrayList<>();
					// read the contents of the line and input accordingly
					for (int i = 1; i < arr.length; i++) {
						String s = arr[i];

						if (s.equals("food"))
							givenSupplies.add(s);
						else if (s.equals("raft"))
							givenSupplies.add(s);
						else if (s.equals("axe"))
							givenSupplies.add(s);
						else if (s.equals("river") || s.equals("fallen")) {
							while (i < arr.length) {
								s = arr[i];
								if (s.equals("river"))
									givenObstacles.add(s);

								else if (s.equals("fallen")) {
									i++;
									if (arr[i].equals("tree")) {
										s += " " + arr[i];
										givenObstacles.add(s);
									} else
										i--;
								}

								i++;
							}
						}
					}
					// create RestStop and add to mountain
					RestStop rest = new RestStop(arr[0], givenSupplies, givenObstacles);
					mountain.add(rest);

				} catch (IllegalArgumentException ex) {
					System.exit(1);
				}

			}
		} catch (IOException ex) {
			System.err.println("Error: the file " + givenPath.getAbsolutePath() + " cannot be opened for reading.\n");
			System.exit(1);
		}
		
		// find the paths down the tree
		mountain.findPath();

	}

	/**
	 * Splits a line into an array separated by spaces
	 * @param textLine the line to be split
	 * @return the string array version of the textLine
	 */
	public static String[] splitInputLine(String textLine) {

		if (textLine == null)
			return null;

		String[] entries = null;

		entries = textLine.split(" ");

		return entries;
	}

}

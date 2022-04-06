package ie.gmit.sw;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Shingler {
	private static Shingler s = null;

	private List<Integer> shingle;

	private Shingler() {

	}
	
	/**
	 * @return instance of Singleton
	 */
	public static Shingler getInstance() {
		if (s == null)
			s = new Shingler();

		return s;
	}
	
	public List<Integer> createShingle(String headline, int size) {
		String[] headlineArray=headline.split(" ");
		
		int amountOfWords = headlineArray.length;

		// create new array. make the size = the amount of words/the size and round up.
		Integer[] result = new Integer[(int) Math.ceil((double) amountOfWords / size)];

		// loops through words[] in increments of passed size. and track number of loops
		// with j
		for (int i = 0, j = 0; i < amountOfWords; i += size, j++) {
			//System.out.println(String.join(" ", Arrays.copyOfRange(headlineArray, i, Integer.min(headlineArray.length, i + size))));
			// copy words[] at position i up to whichever position is smaller, length of
			// words[] or i+size
			// this is necessary so that the for loop doesn't go out of bounds at the end of
			// the file.
			// after this, get the hashcode value of each shingle
			result[j] = String.join(" ", Arrays.copyOfRange(headlineArray, i, Integer.min(headlineArray.length, i + size))).hashCode();
		}

		shingle = new ArrayList<>(Arrays.asList(result));
		//System.out.println("Shingles list created of size: " + shingle.size());
		
		return shingle;
	}
}

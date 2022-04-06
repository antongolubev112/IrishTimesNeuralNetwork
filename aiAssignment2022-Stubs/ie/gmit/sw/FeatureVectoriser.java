package ie.gmit.sw;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class FeatureVectoriser {
	private ArrayList<Integer> vector;

//	public ArrayList<Integer> hashingVectorizer(Headline headline, int vecSize) {
//
//		// create a new arraylist, initialise all values with 0
//		vector = new ArrayList<Integer>(Collections.nCopies(vecSize, 0));
//
//		// hash the date
//		int dateHash = headline.getDate().hashCode();
//		for (Integer shingle : headline.getShingledHeadline()) {
//			// set the index to be the shingle to be the remainder of the hashcode / size of
//			// the vector
//			// increment the value at that index by one
//			System.out.println("shingle: " + shingle);
//			System.out.println("vector value: " + vector.get(Math.abs(shingle) % vecSize));
//			vector.set(Math.abs(shingle) % vecSize, vector.get(Math.abs(shingle) % vecSize) + 1);
//			System.out.println("vector value: " + vector.get(Math.abs(shingle) % vecSize));
//		}
//		// add date to the vector
//		vector.set(Math.abs(dateHash) % vecSize, vector.get(dateHash % vecSize) + 1);
//		
//		return vector;
//	}
	
	public double[] hashVectorizer(Headline headline, int vecSize) {

		// create a new arraylist, initialise all values with 0
		double vector[] = new double [vecSize];
		//Arrays.setAll(vector, 0.0);

		for (Integer shingle : headline.getShingledHeadline()) {
			// set the index to be the shingle to be the remainder of the hashcode / size of
			// the vector
			// increment the value at that index by one
			//System.out.println("vector value: " + vector[Math.abs(shingle) % vecSize]);
			//System.out.println("shingle: " + shingle);
			//vector.set(Math.abs(shingle) % vecSize, vector.get(Math.abs(shingle) % vecSize) + 1);
			vector[Math.abs(shingle) % vecSize]=vector[Math.abs(shingle) % vecSize] + 1;
			//System.out.println("vector value: " + vector[Math.abs(shingle) % vecSize]);
		}
		
		
		vector[Math.abs(headline.getHashedDate()) % vecSize]= vector[headline.getHashedDate() % vecSize]+1;
		
		return vector;
	}

}

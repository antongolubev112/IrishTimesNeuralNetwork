package ie.gmit.sw;

import java.util.Arrays;

public class FeatureVectoriser {
	private static Mapper map=Mapper.getInstance();
	//private ArrayList<Integer> vector;

	public double[] expectedVectorizer(Headline headline, int vecSize) {
		double vector[] = new double[vecSize];
		
		int hash=headline.getExpected().hashCode();
		
		vector[Math.abs(hash) % vecSize] = vector[Math.abs(hash) % vecSize] + 1;
		
		//System.out.println("Incremented value at position "+Math.abs(hash) % vecSize);
		//System.out.println(Arrays.toString(vector));
		
		return vector;
	}

	public double[] hashVectorizer(Headline headline, int vecSize) {
		//System.out.println("feature");
		// create a new arraylist, initialise all values with 0
		double vector[] = new double[vecSize];
		// Arrays.setAll(vector, 0.0);

		for (Integer shingle : headline.getShingledHeadline()) {
			// set the index to be the shingle to be the remainder of the hashcode / size of
			// the vector
			// increment the value at that index by one
			vector[Math.abs(shingle) % vecSize] = vector[Math.abs(shingle) % vecSize] + 1;
			//System.out.println("Incremented value at position "+Math.abs(shingle) % vecSize +"value is: "+vector[Math.abs(shingle) % vecSize]);
			// System.out.println("vector value: " + vector[Math.abs(shingle) % vecSize]);
		}

		vector[Math.abs(headline.getHashedDate()) % vecSize] = vector[Math.abs(headline.getHashedDate()) % vecSize] + 1;
		//System.out.println(Arrays.toString(vector));
		return vector;
	}

	

}

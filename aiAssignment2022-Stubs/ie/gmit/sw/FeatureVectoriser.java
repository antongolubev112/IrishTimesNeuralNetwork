package ie.gmit.sw;

import java.util.Arrays;

public class FeatureVectoriser {
	
	private static Encoder map=Encoder.getInstance();
	//adapted from https://en.wikipedia.org/wiki/Feature_hashing#Feature_vectorization_using_hashing_trick
	
	private int i=0;
	//vectorise the expected value
//	public double[] expectedVectorizer(Headline headline, int vecSize) {
//		//create a new array(vector) of specified size
//		
//		
//		
//		
//		//hash the expected category
//		//int hash=headline.getExpected().hashCode();
//		
//		
//		
//		//map.addToMap(Math.abs(hash % vecSize), headline.getExpected() );
//		
//		//map.addToCategories(headline.getExpected(), i);
//
//		//vector[Math.abs(hash % vecSize)] = vector[Math.abs(hash % vecSize)] + 1;
//		
//		//System.out.println("Incremented value at position "+Math.abs(hash % vecSize));
//		//System.out.println(Arrays.toString(vector));
//		
//		//return vector;
//	}

	public double[] hashVectorizer(Headline headline, int vecSize) {
		double vector[] = new double[vecSize];

		for (Integer shingle : headline.getShingledHeadline()) {
			// set the index to be the shingle to be the remainder of the hashcode / size of
			// the vector increment the value at that index by one
			vector[Math.abs(shingle % vecSize)] = vector[Math.abs(shingle % vecSize)] + 1;
			
			//System.out.println("Incremented value at position "+Math.abs(shingle % vecSize) +"value is: "+vector[Math.abs(shingle % vecSize)]);
			// System.out.println("vector value: " + vector[Math.abs(shingle) % vecSize]);
		}
		//do the same with the date
		vector[Math.abs(headline.getHashedDate() % vecSize)] = vector[Math.abs(headline.getHashedDate() % vecSize)] + 1;
		//System.out.println("Incremented at position: "+vector[Math.abs(headline.getHashedDate() % vecSize)]);
		
		//System.out.println(Arrays.toString(vector));
		return vector;
	}

	

}

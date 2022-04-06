package ie.gmit.sw;

import java.util.ArrayList;

public class TrainingDataFactory {
	private static TrainingDataFactory tdf = null;

	private TrainingDataFactory() {

	}

	public static TrainingDataFactory getInstance() {
		if (tdf == null)
			tdf = new TrainingDataFactory();

		return tdf;
	}
	private double [][] expectedHash;
	private double [][] headlineVectors;
	private FeatureVectoriser fv=new FeatureVectoriser();
	
	public void createHeadlineArray(ArrayList<Headline> headlines, int vecSize) {
		//initialise vector list
		headlineVectors= new double[headlines.size()][vecSize];
		
		int i=0;
		for(Headline h: headlines) {
			headlineVectors[i]=fv.hashVectorizer(h, vecSize);
		}

		//increment counter
		i++;
	}
	
	public void createExpectedArray(ArrayList<Headline> headlines, int vecSize) {
		expectedHash= new double[headlines.size()][1];
		double [] expectedArray=new double[1];
		int i=0;
		for(Headline h: headlines) {
			expectedArray[0]=h.getHashedExpected();
			expectedHash[i]=expectedArray;
		}

		//increment counter
		i++;
	}
	
	public void createNN(ArrayList<Headline> headlines, int vecSize) {
		createHeadlineArray(headlines, vecSize);
		createExpectedArray(headlines, vecSize);

		EncogNN neuralNet= new EncogNN(vecSize, 2, expectedHash, headlineVectors);
		neuralNet.train();
		return ;
	}
}

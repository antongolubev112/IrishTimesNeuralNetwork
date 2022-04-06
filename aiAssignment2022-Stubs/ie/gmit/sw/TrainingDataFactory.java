package ie.gmit.sw;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	
	public double[][] dateVectoriser(ArrayList<Headline> headlines, int vecSize) {
		int i = 0;
		Set<String> set = new HashSet<>();
		for (Headline h : headlines) {
			set.add(h.getExpected());
			i++;
		}
		
		List<String> list=new ArrayList<>(set);
		double[][] dateVectors = new double[headlines.size()][90];
		
		System.out.println("size of map: "+list.size());
		for (int k=0; k<list.size(); k++) {
			for(int j=0; j<headlines.size();j++) {
				if(list.get(k)==headlines.get(j).getExpected()) {
					dateVectors[j][k]=dateVectors[j][k]+1;
				}
				
			}
		}
		
//		for(double [] array: dateVectors) {
//			for(double d: array) {
//				System.out.print(d+" ");
//			}
//			System.out.println("\n");
//		}
		
		expectedHash=dateVectors;
		return dateVectors;
	}
	
}

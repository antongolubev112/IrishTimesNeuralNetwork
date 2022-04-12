package ie.gmit.sw;

import java.util.Arrays;

import org.encog.ml.data.MLDataPair;
import org.encog.ml.data.MLDataSet;

public class Normaliser {
	public MLDataSet normalise(MLDataSet dataset, double minValue, double maxValue) {
		
		double xMin=100;
		double xMax=0;
		
		for (MLDataPair pair: dataset) {
			double [] vector=pair.getInputArray();
			for(int i=0; i< vector.length; i++) {
				if (vector[i] > xMax)
				{
					xMax= vector[i];
				}
				if(vector[i]< xMin) {
					xMin=vector[i];
				}
			}
		}
		
		System.out.println("max : "+xMax);
		System.out.println("min : "+xMin);
		for (MLDataPair pair: dataset) {
			double [] inputVector=pair.getInputArray();
			double [] outputVector=pair.getIdealArray();
			//System.out.println(Arrays.toString(vector));
			for(double d : inputVector) {
				//System.out.println("original: "+d);
				double vec=(d-xMin)/(xMax-xMin);
				d=vec*(maxValue-minValue)+minValue;
				//System.out.println("vectorised: "+d);
			}
			
			for(double d : outputVector) {
				double vec=(d-xMin)/(xMax-xMin);
				d=vec*(maxValue-minValue)+minValue;
			}
			pair.setInputArray(inputVector);
			pair.setIdealArray(outputVector);
			//System.out.println(Arrays.toString(vector));
		}
		return dataset;
	}
}

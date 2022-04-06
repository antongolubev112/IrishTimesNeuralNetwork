package ie.gmit.sw;

import org.encog.ml.data.MLDataSet;

public class NNFactory {
	private static NNFactory nnf = null;
	private ReadCSV read;

	private NNFactory() {

	}

	public static NNFactory getInstance() {
		if (nnf == null)
			nnf = new NNFactory();

		return nnf;
	}

	public EncogNN create(int vecSize,int shingleSize, String file){
		 read= new ReadCSV(vecSize,shingleSize);
		 MLDataSet dataSet= read.readFile(file);
		 
	     EncogNN neuralNet= new EncogNN(vecSize, 2, dataSet);
	     neuralNet.train();
	     return neuralNet;

	   }
	
	public void test(EncogNN nn, int vecSize, int shingleSize, String file) {
		read= new ReadCSV(vecSize,shingleSize);
		MLDataSet dataSet= read.readFile(file);
		nn.test(dataSet);
	}
}

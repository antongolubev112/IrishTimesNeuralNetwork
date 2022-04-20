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
	

	//create neural network
	public EncogNN create(int vecSize,int shingleSize, String file){
		 read= new ReadCSV(vecSize,shingleSize);
		 
	     EncogNN neuralNet= new EncogNN(vecSize, read.readFile(file));
	     neuralNet.train();
	     return neuralNet;

	   }
	
	//test neural network
	public void test(EncogNN nn, int vecSize, int shingleSize, String file) {
		read= new ReadCSV(vecSize,shingleSize);
		nn.test(read.readFile(file));
	}
}

package ie.gmit.sw;

import java.util.Arrays;

import org.encog.Encog;
import org.encog.engine.network.activation.ActivationGaussian;
import org.encog.engine.network.activation.ActivationReLU;
import org.encog.engine.network.activation.ActivationSigmoid;
import org.encog.engine.network.activation.ActivationSoftMax;
import org.encog.ml.data.MLData;
import org.encog.ml.data.MLDataPair;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLData;
import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;
import org.encog.neural.networks.training.propagation.back.Backpropagation;
import org.encog.neural.networks.training.propagation.resilient.ResilientPropagation;

public class EncogNN {
	private BasicNetwork network;

	private int inputNodes;
	private int hiddenNodes=0;
	private final int OUTPUT_NODES = 90;
	private MLDataSet dataSet;
	private boolean trained;
	

	public EncogNN(int inputNodes, MLDataSet dataSet) {
		this.network = new BasicNetwork();
		this.inputNodes = inputNodes;
		this.hiddenNodes = dataSet.size()/(10*(inputNodes+OUTPUT_NODES));
		this.dataSet = dataSet;
		this.trained = false;
		
		System.out.println("input size"+dataSet.getInputSize());
		System.out.println("ideal size"+dataSet.getIdealSize());
		System.out.println("hiddenNodes: "+(2*inputNodes));
		
		// Declare topology
		network.addLayer(new BasicLayer(null, false, this.inputNodes));
		network.addLayer(new BasicLayer(new ActivationReLU(), true, (int)Math.sqrt(inputNodes*OUTPUT_NODES)));
		// output layer, 82 nodes because there are 82 possible outputs
		network.addLayer(new BasicLayer(new ActivationSoftMax(), true, OUTPUT_NODES));
		network.getStructure().finalizeStructure();
		network.reset();
	}

	public void train() {
		Normaliser norm = new Normaliser();

		dataSet=norm.normalise(dataSet,0,1);

		ResilientPropagation train = new ResilientPropagation(network, dataSet);
		double minError = 0.02;
		int epoch = 1;
		System.out.println("[INFO] Training...");
		do {
			train.iteration();
			System.out.println("error: " + train.getError());
			epoch++;
		} while (train.getError() > minError);
		train.finishTraining();
		System.out.println("[INFO] Finished Training in " + epoch + " epochs with e=" + train.getError());
		trained = true;
		System.out.println("[INFO] Shutting down.");
		Encog.getInstance().shutdown();
	}
	
	public int classifyAction(MLData input) {
		return ((int) network.classify(input) + 1);
	}

	public void test(MLDataSet testSet) {
		double correct = 0;
		double total = 0;

		for (MLDataPair pair : testSet) {
			total++;
			MLData output = network.compute(pair.getInput());

			double[] y = output.getData();
			
			//System.out.println(Arrays.toString(y));
//			System.out.println(pair.getInput().getData(0) + "," + pair.getInput().getData(1) + ","
//					+ pair.getInput().getData(2) + ": Y=" + (int) Math.round(output.getData(0)) + ", Yd="
//					+ (int) pair.getIdeal().getData(0));

			// System.out.println(Arrays.toString(y));

			double max = 0;
			int maxPos = 0;
			for (int i = 0; i < y.length; i++) {
				if (y[i] > max) {
					max = y[i];
					maxPos = i;
				}
			}
			
			

			// System.out.println(y.length);
			double[]yd = pair.getIdeal().getData();
			
			double expMax=0;
			double expPos=0;
			for (int i = 0; i < yd.length; i++) {
				if (yd[i] > expMax) {
					expMax = yd[i];
					expPos = i;
				}
			}
			System.out.println("Max position is: " + maxPos + " Value is: " + max);
//			System.out.println("Expected position is: "+expPos+"with a Value of: "+expMax);
//			//System.out.println("classified as: "+classifyAction(pair.getInput()));
//			System.out.println("Value of nn result:" + yd[maxPos]);
			if (yd[maxPos] == 1.0) {
				correct++;
			}
			// System.out.println(Arrays.toString(yd));

//			if(Arrays.equals(y, yd)) {
//				correct++;
//			}		
		}
		System.out.println("[INFO] Testing complete. Acc= " + ((correct / total) * 100));
	}
	

}

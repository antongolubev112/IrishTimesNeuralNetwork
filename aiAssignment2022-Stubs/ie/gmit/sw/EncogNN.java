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
import org.encog.ml.train.strategy.Strategy;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;
import org.encog.neural.networks.training.propagation.back.Backpropagation;
import org.encog.neural.networks.training.propagation.resilient.ResilientPropagation;
import org.encog.neural.networks.training.strategy.SmartLearningRate;

public class EncogNN {
	private BasicNetwork network;
	private static Encoder map=Encoder.getInstance();

	private int inputNodes;
	//90 output nodes because there are 90 categories
	private final int OUTPUT_NODES = 90;
	private MLDataSet dataSet;
	private boolean trained;
	

	public EncogNN(int inputNodes, MLDataSet dataSet) {
		//initialise neural network
		this.network = new BasicNetwork();
		this.inputNodes = inputNodes;
		this.dataSet = dataSet;
		this.trained = false;
		
		System.out.println("input size"+dataSet.getInputSize());
		System.out.println("ideal size"+dataSet.getIdealSize());
		
		// Declare topology
		
		//Input layer
		//number of input nodes is the size of the feature vector
		network.addLayer(new BasicLayer(null, false, this.inputNodes));
		
		//number of hidden nodes is the square root of the input nodes and the output nodes
		network.addLayer(new BasicLayer(new ActivationSigmoid(), true, inputNodes*2));
		
		// output layer, 82 nodes because there are 82 possible outputs
		network.addLayer(new BasicLayer(new ActivationSoftMax(), true, OUTPUT_NODES));
		network.getStructure().finalizeStructure();
		network.reset();
	}

	public void train() {
		Normaliser norm = new Normaliser();

		dataSet=norm.normalise(dataSet,0,1);

		ResilientPropagation train = new ResilientPropagation(network, dataSet);
		train.setBatchSize(0);
		
		//train.
		//desired error rate
		double minError = 0.10;
		int epoch = 1;
		System.out.println("[INFO] Training...");
		//do {
		train.iteration(5);
		System.out.println("error: " + train.getError());
		epoch++;
		//} while (train.getError() > minError);
		train.finishTraining();
		
		System.out.println("[INFO] Finished Training in " + epoch + " epochs with e=" + train.getError());
		System.out.println("[INFO] Shutting down.");
		trained = true;
		Encog.getInstance().shutdown();
	}
	
	public void test(MLDataSet testSet) {
		double correct = 0;
		double total = 0;

		for (MLDataPair pair : testSet) {
			total++;
			
			//classify the input
			MLData output = network.compute(pair.getInput());
			
			//get the output
			double[] outputArray = output.getData();
			
			//System.out.println(Arrays.toString(y));
			
			//check to see what the biggest value is because SoftMax activation returns probabilities of every category
			//the highest probability is the predicted class
			double max = 0;
			int maxPos = 0;
			for (int i = 0; i < outputArray.length; i++) {
				if (outputArray[i] > max) {
					max = outputArray[i];
					maxPos = i;
				}
			}
			
			//get the expected category
			double[]expectedArray = pair.getIdeal().getData();
			// System.out.println(Arrays.toString(yd));
			
			//get the position of the expected value in the expected array
			double expMax=0;
			double expPos=0;
			for (int i = 0; i < expectedArray.length; i++) {
				if (expectedArray[i] > expMax) {
					expMax = expectedArray[i];
					expPos = i;
				}
			}
			
			System.out.println("Neural Network classified the headline as: "+map.getMappedCategories().get(maxPos));
			//java -cp nn.jar;C:\Users\anton\Desktop\College\AI\IrishTimesNeuralNetwork\externalJars\* ie.gmit.sw.NNRunner
//			System.out.println("The outputted classification is: " + maxPos + " Value is: " + max);
//			System.out.println("The expected classification is: "+expPos+" with a Value of: "+expMax);
//			System.out.println("Value of classication in the expected array:" + expectedArray[maxPos]+"\n");
			
			//if the position of the highest value of the output array in the expectedArray is 1
			//then prediction is correct
			if (expectedArray[maxPos] == 1.0) {
				correct++;
			}
			
		}
		System.out.println("[INFO] Testing complete. Acc= " + ((correct / total) * 100));

	}
	

}

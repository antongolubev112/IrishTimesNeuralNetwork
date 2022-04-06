package ie.gmit.sw;

import org.encog.engine.network.activation.ActivationSigmoid;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;
import org.encog.neural.networks.training.propagation.back.Backpropagation;

public class EncogNN {
	private BasicNetwork network; 
	
	private int inputNodes;
	private int hiddenNodes;
	private final int OUTPUT_NODES=1; 
	private double[][] inputs;
	private double [][] expected;
	
	
	
	public EncogNN(int inputNodes, int hiddenNodes, double[][] expected, double [][] inputs) {
		this.network = new BasicNetwork();
		this.inputNodes = inputNodes;
		this.hiddenNodes = hiddenNodes;
		this.expected = expected;
		this.inputs=inputs;
		
		//Declare topology
		network.addLayer(new BasicLayer(null, true, inputNodes));
		network.addLayer(new BasicLayer(new ActivationSigmoid(),true, hiddenNodes));
		//output layer, 82 nodes because there are 82 possible outputs
		network.addLayer(new BasicLayer(new ActivationSigmoid(),false, OUTPUT_NODES));
		network.getStructure().finalizeStructure();
		network.reset();
	}
	
	
	public void train(){
		MLDataSet trainingSet= new BasicMLDataSet(inputs,expected);
		
		Backpropagation train= new Backpropagation(network, trainingSet);
		double minError=0.09;
		int epoch=1;
		System.out.println("[INFO] Training...");
		do {
			train.iteration();
			System.out.println("error: "+train.getError());
			epoch++;
		}while(train.getError()>minError);
		train.finishTraining();
		System.out.println("[INFO] Finished Training in "+ epoch+ " epochs with e="+train.getError());
	}

}

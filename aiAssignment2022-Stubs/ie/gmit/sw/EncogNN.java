package ie.gmit.sw;

import org.encog.engine.network.activation.ActivationSigmoid;
import org.encog.ml.data.MLData;
import org.encog.ml.data.MLDataPair;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;
import org.encog.neural.networks.training.propagation.back.Backpropagation;

public class EncogNN {
	private BasicNetwork network; 
	
	private int inputNodes;
	private int hiddenNodes;
	private final int OUTPUT_NODES=90; 
	private MLDataSet dataSet;
	private boolean trained;
	
	
	
	public EncogNN(int inputNodes, int hiddenNodes, MLDataSet dataSet) {
		this.network = new BasicNetwork();
		this.inputNodes = inputNodes;
		this.hiddenNodes = hiddenNodes;
		this.dataSet=dataSet;
		this.trained=false;
		
		//Declare topology
		network.addLayer(new BasicLayer(null, true, inputNodes));
		network.addLayer(new BasicLayer(new ActivationSigmoid(),true, hiddenNodes));
		//output layer, 82 nodes because there are 82 possible outputs
		network.addLayer(new BasicLayer(new ActivationSigmoid(),false, OUTPUT_NODES));
		network.getStructure().finalizeStructure();
		network.reset();
	}
	
	
	public void train(){
		MLDataSet trainingSet= new BasicMLDataSet(dataSet);
		
		Backpropagation train= new Backpropagation(network, trainingSet);
		double minError=0.01;
		int epoch=1;
		System.out.println("[INFO] Training...");
		do {
			train.iteration();
			System.out.println("error: "+train.getError());
			epoch++;
		}while(train.getError()>minError);
		train.finishTraining();
		System.out.println("[INFO] Finished Training in "+ epoch+ " epochs with e="+train.getError());
		trained=true;
	}
	
	public void test(MLDataSet testSet) {
		double correct=0;
		double total= 0;
		
		for (MLDataPair pair: testSet) {
			total++;
			MLData output= network.compute(pair.getInput());
			
			int y= (int) Math.round(output.getData(0));
			int yd= (int) pair.getIdeal().getData(0);
			
			if(y==yd) {
				correct++;
			}		
		}
		System.out.println("[INFO] Testing complete. Acc= "+((correct/total)*100));
	}

}

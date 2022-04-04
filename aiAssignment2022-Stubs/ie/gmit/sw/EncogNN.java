package ie.gmit.sw;

import org.encog.engine.network.activation.ActivationSigmoid;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;

public class EncogNN {
	
	//Declare topology
	public void go(){
		BasicNetwork network= new BasicNetwork();
		//input layer
		network.addLayer(new BasicLayer(null,true, 4));
		//hidden layer
		network.addLayer(new BasicLayer(new ActivationSigmoid(),true, 2));
		//output layer, 82 nodes because there are 82 possible outputs
		network.addLayer(new BasicLayer(new ActivationSigmoid(),false, 82));
	}
	public static void main(String[] args) {
		new EncogNN().go();
	}
}

public class Organism {
	//Attributes
	Genome dna;
	NeuralNet brain;
	Species spec;
	
	public Organism(Genome g, Species sp){
		dna = g;
		brain = new NeuralNet(g);
		spec = sp;
	}
	
	//Activates the brain and sets the fitness according to the result and the expected result
	//The fitness Gradient changes how close the result has to be to count
	public void activate(LList<Double> in, Double result, Double fitGradient){
		double temp = Math.abs(brain.activate(in) - result);
		if(temp < fitGradient){
			brain.fitness += (fitGradient - temp);
		} else {
			brain.fitness -= temp;
		}


	}
}

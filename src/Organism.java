public class Organism {
	Genome dna;
	NeuralNet brain;
	Species spec;
	
	public Organism(Genome g, Species sp){
		dna = g;
		brain = new NeuralNet(g);
		spec = sp;
	}
	
	public void activate(LList<Double> in, Double result, Double fitGradient){
		double temp = Math.abs(brain.activate(in) - result);
		if(temp < fitGradient){
			brain.fitness += (fitGradient - temp);
		} else {
			brain.fitness -= temp;
		}


	}
}

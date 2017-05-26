public class Organism {
	Genome dna;
	NeuralNet brain;
	Species spec;
	
	public Organism(Genome g, Species sp){
		dna = g;
		brain = new NeuralNet(g);
		spec = sp;
	}
	
	public void activate(LList<Double> in, int result){
		if(result == brain.activate(in)){
			brain.fitness += 1;
		} else {
			brain.fitness -=1;
		}
	}
}

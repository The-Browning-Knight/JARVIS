public class Organism {
	Genome dna;
	NeuralNet brain;
	Species spec;
	
	public Organism(Genome g, Species sp){
		dna = g;
		brain = new NeuralNet(g);
		spec = sp;
	}
	
	public Float activate(LList<Double> in, ){
		return brain.activate(in);
	}
}

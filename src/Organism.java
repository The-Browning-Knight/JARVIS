public class Organism {
	Genome dna;
	NeuralNet brain;
	
	public Organism(Genome g){
		dna = g;
		brain = new NeuralNet(g);
	}
}

public class Population {
	//Keeps track of all the generations
	List<Generation> gens;
	
	public Population(double fg, int inCount, int outCount, double ca1, double ca2, double ca3, double ra1, double ra2, double ra3, double ra4, int cat, double p1, double p2, double im, double mD, double mfg, int ms){
		gens = new LList<Generation>();
		gens.append(new Generation(fg, inCount, outCount, ca1, ca2, ca3, ra1, ra2, ra3, ra4, cat, p1, p2, im, mD, mfg, ms));
	}
	
	//Activates the latest generation
	public void activate(LList<Double> in, Double result){
		gens.append(gens.getValue().activate(in, result));
	}
}

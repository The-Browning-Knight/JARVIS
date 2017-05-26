public class Population {
	List<Generation> gens;
	
	public Population(){
		
	}
	
	public void activate(LList<Double> in, int result){
		gens.append(gens.getValue().activate(in, result));
	}
}

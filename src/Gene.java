public class Gene {
	int hismark;
	boolean enabled;
	float weight;
	int in;
	int out;
	
	Gene(){
		
	}
	
	public boolean equals(Gene g){
		if(g.hismark == hismark && enabled == g.enabled && weight == g.weight){
			return true;
		}
		return false;
	}
}

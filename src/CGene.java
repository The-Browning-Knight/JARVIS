public class CGene extends Gene{
	int hismark; // history mark for reference
	private boolean enabled; // True when connection enabled
	double weight;
	int in;
	int out;
	
	public CGene(int i, int o, double w, boolean e, int h){
		in = i;
		out = o;
		weight = w;
		enabled = e;
		hismark = h;
	}
	
	public int geneType(){
		return 0; // CGene defined as type 0, NGene is type 1
	}
	
	// getters and setters
	
	public int hismark(){
		return hismark;
	}
	
	public boolean enabled(){
		return enabled;
	}
	
	public void setEnabled(boolean a){
		enabled = a;
	}
	
}

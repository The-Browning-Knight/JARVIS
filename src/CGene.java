// implementation class for connection gene

public class CGene extends Gene{
	int hismark;
	private boolean enabled;
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
		return 0;
	}
	
	public int hismark(){
		return hismark;
	}
	
	public boolean enabled(){
		return enabled;
	}
	
	public void setEnabled(boolean a){
		enabled = a;
	}
	
	/**public boolean equals(Gene g){
		if(g.hismark == hismark && enabled == g.enabled && weight == g.weight){
			return true;
		}
		return false;
	}
	*/
}

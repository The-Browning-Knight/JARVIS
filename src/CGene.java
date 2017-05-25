public class CGene extends Gene{
	int hismark;
	boolean enabled;
	double weight;
	int in;
	int out;
	
	public CGene(int i, int o, float w, boolean e, int h){
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
	
	/**public boolean equals(Gene g){
		if(g.hismark == hismark && enabled == g.enabled && weight == g.weight){
			return true;
		}
		return false;
	}
	*/
}

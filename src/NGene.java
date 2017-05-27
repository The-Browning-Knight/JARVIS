public class NGene extends Gene{
	//Attributes
	int name;
	//0-in, 1-hidden, 2-out
	int type;
	int hismark;
	
	public NGene(int n, int t, int h){
		name = n;
		type = t;
		hismark = h;
	}
	
	//Tells that it is a nodeGene(node Gene - 1, connection Gene - 0)
	public int geneType(){
		return 1;
	}
	
	//nodes are always enabled
	public boolean enabled(){
		return true;
	}
	
	//This is just to make it the same as the abstract class
	public void setEnabled(boolean a){
	}
	
	//Returns hismark
	public int hismark(){
		return hismark;
	}
	
}

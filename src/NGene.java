public class NGene extends Gene{
	int name;
	//0-in, 1-hidden, 2-out
	int type;
	int hismark;
	
	public NGene(int n, int t, int h){
		name = n;
		type = t;
		hismark = h;
	}
	
	public int geneType(){
		return 1;
	}
	
	public int hismark(){
		return hismark;
	}
	
}

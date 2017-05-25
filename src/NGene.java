public class NGene extends Gene{
	int name;
	//0-in, 1-hidden, 2-out
	int type;
	
	public NGene(int n, int t){
		name = n;
		type = t;
	}
	
	public int geneType(){
		return 1;
	}
	
	public String toString()
	{
		String str = "Name: " + name + "   Type: " + type;
		return str;
	}
}

public class Node {
	//0-in, 1-hidden, 2-out, 3-head
	int type;
	//going backwards
	LList<Connection> children;
	int name;
	Float value;
	
	public Node(int t, int n){
		type = t;
		name = n;
		children = new LList<Connection>();
	}
	
	public Node(NGene g){
		name = g.name;
		children = new LList<Connection>();
		type = g.type;
	}
}

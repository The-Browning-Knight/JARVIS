public class Node {
	//0-in, 1-hidden, 2-out, 3-head
	int type;
	
	//this refers to the next layer going backwards from the head
	LList<Connection> children;
	int name;
	
	//This is what we use to insert the input value and for the activation function
	Double value;
	
	public Node(int t, int n){
		type = t;
		name = n;
		children = new LList<Connection>();
		value = 0.0;
	}
	
	public Node(NGene g){
		name = g.name;
		children = new LList<Connection>();
		type = g.type;
		value = 0.0;
	}
}

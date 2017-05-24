public class Node {
	//0-in, 1-hidden, 2-out, 3-head
	int type;
	//going backwards
	LList<Connection> children;
	int name;
	
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
	
	public int activate(){
		if(type == 0){
			return value;
		}
		float sum = 0;
		for(int i = 0; i < children.length(); i++){
			sum += (children.getValue().weight * children.getValue().in.activate());
			children.next();
		}
		//Add activation function
		return sum;
	}
}

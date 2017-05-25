public class Connection {
	Node in;
	Node out;
	Float weight;
	boolean enabled;
	
	Connection(Node i, Node o, boolean b, float w){
		in = i;
		out = o;
		enabled = b;
		weight = w;
	}
}

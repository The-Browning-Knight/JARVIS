// connection blueprint for use in CGene

public class Connection {
	Node in;
	Node out;
	double weight;
	boolean enabled;
	
	Connection(Node i, Node o, boolean b, double w){
		in = i;
		out = o;
		enabled = b;
		weight = w;
	}
}

public class NeuralNet {
	LList<Node> nodes;
	double fitness;

	public NeuralNet(Genome g){
		nodes = new LList<Node>();
		for(int i = 0; i < g.genes.length(); i++){
			if(g.genes.getValue().geneType() == 1) {
				nodes.append(new Node((NGene)g.genes.getValue()));
			}
			g.genes.next();
		}
		g.genes.moveToStart();
		for(int i = 0; i < g.genes.length(); i++){
			if(g.genes.getValue().geneType() == 0){
				if(g.genes.getValue().enabled()){
					nodes.moveToStart();
					for(int n = 0; n < nodes.length(); n++){
						if(nodes.getValue().name == ((CGene)g.genes.getValue()).out){
							nodes.getValue().children.append(new Connection(findNode(((CGene)g.genes.getValue()).in), findNode(((CGene)g.genes.getValue()).out), ((CGene)g.genes.getValue()).enabled(), ((CGene)g.genes.getValue()).weight));
						}
						nodes.next();
					}
				}
			}
			g.genes.next();
		}
		Node temp = new Node(3, nodes.length() + 1);
		nodes.append(temp);
		nodes.moveToStart();
		for(int i = 0; i < nodes.length(); i++){
			if(nodes.getValue().type == 2){
				temp.children.append(new Connection(nodes.getValue(), temp, true, 1));
			}
			nodes.next();
		}
	}

	private Node findNode(int a){
		nodes.moveToStart();
		for(int i = 0; i < nodes.length(); i++){
			if(nodes.getValue().name == a){
				return nodes.getValue();
			}
			nodes.next();
		}
		return null;
	}

	public double activate(LList<Float> in){
		nodes.moveToStart();
		Node temp = nodes.getValue();
		//We could speed this up by assuming that it's at the end
		for(int i = 0; i < nodes.length(); i++){
			if(nodes.getValue().type == 3){
				temp = nodes.getValue();
			}
			nodes.next();
		}
		nodes.moveToStart();
		for(int i = 0; i < nodes.length(); i++){
			if(nodes.getValue().type == 0){
				nodes.getValue().value = in.getValue();
				in.next();
			}
			nodes.next();
		}
		return activateHelp(temp); 
	}

	private double activateHelp(Node n){
		if(n.type == 0){
			return n.value;
		}
		double sum = 0f;
		n.children.moveToStart();
		for(int i = 0; i < n.children.length(); i++){
			sum += n.children.getValue().weight * activateHelp(n.children.getValue().in);
		}
		sum = 1 / (1 + java.lang.Math.exp(-1.9 * sum));
		return sum;
	}
}

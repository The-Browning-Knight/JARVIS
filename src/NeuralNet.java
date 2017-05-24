public class NeuralNet {
	LList<Node> nodes;

	public NeuralNet(Genome g){
		for(int i = 0; i < g.genes.length(); i++){
			if(g.genes.getValue().geneType() == 1) {
				nodes.append(new Node(g.genes));
			}
			g.genes.next()
		}
		for(int i = 0; i < g.genes.length(); i++){
			if(g.getValue().geneType() == 0){
				for(int n = 0; n < nodes.length(); n++){
					if(nodes.getValue() == g.getValue().out){
						nodes.getValue().children.append(new Connection(findNode(g.getValue().in), findNode(g.getValue().out), g.genes.getValue().enabled, g.genes.getValue().weight))
					}
					nodes.next();
				}
			}
			g.genes.next();
		}
		temp = new Node(3, nodes.length());
		nodes.append(temp);
		for(int i = 0; i < nodes.length(); i++){
			if(nodes.getValue().type == 2){
				temp.children.append(nodes.getValue());
			}
			nodes.next();
		}
	}
	
	private Node findNode(int a){
		for(int i = 0; i < nodes.length(); i++){
			if(nodes.getValue().name == a){
				return nodes.getValue();
			}
			nodes.next();
		}
	}

	public Float activate(LList<Float> in){
		Node temp;
		//We could speed this up by assuming that it's at the end
		for(int i = 0; i < nodes.length(); i++){
			if(nodes.getValue().type == 3){
				temp = nodes.getValue();
			}
			nodes.next();
		}
		activateHelp(temp); 
	}
	
	private Float activateHelp(Node n){
		Float sum = 0;
		for(int i = 0; i < n.children.length(); i++){
			sum += n.children.getValue().weight * activateHelp(n.children.getValue().in);
		}
		return sum;
	}
}

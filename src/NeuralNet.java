public class NeuralNet {
	LList<LList<Node>> nodes;

	public NeuralNet(int inC, int outC){
		for(int i = 0; i < inC + outC; i++){
			nodes.append(new LList<Node>());
		}
		for(int i = 0; i < inC; i++){
			nodes.moveToPos(i);
			nodes.getValue().append(new Node(0));
		}
		for(int i = inC; i < outC + inC; i++){
			nodes.moveToPos(i);
			nodes.getValue().append(new Node(2));
		}
	}

	public NeuralNet(Genome g){
		LList<Integer> nodeNames = new LList<Integer>();
		boolean tempIn = false;
		boolean tempOut = false;
		for(int i = 0; i < g.genes.length(); i++){
			for(int n = 0; n < nodeNames.length(); n++){
				if(nodeNames.getValue() == g.genes.getValue().in){
					tempIn = true;
				}
				if(nodeNames.getValue() == g.genes.getValue().out){
					tempOut = true;
				}
			}
			if(!tempIn){
				nodeNames.append(g.genes.getValue().in);
			}
			if(!tempIn){
				nodeNames.append(g.genes.getValue().out);	
			}
			g.genes.next();
		}
		for(int i = 0; i < nodeNames.length(); i++){
			nodes.append(new LList<Node>());
			nodes.getValue().append(nodeNames.getValue());
			nodes.next();
		}
	}

}

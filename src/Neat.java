public class Neat {
	Population NYC;
	int popSize;
	int c1;
	int c2;
	int c3;
	int mutProb;
	int disProb;
	int nodeProb;
	int connProb;
	int interspecProb;
	int maxStag;
	int inCount;
	int outCount;
	LList<LList<Double>> inputs;
	LList<Integer> outputs;
	
	public Neat(){
		run();
	}
	
	public void run(){
		for(int i = 0; i < inputs.length(); i++){
			NYC.activate(inputs.getValue(), outputs.getValue());
			inputs.next();
			outputs.next();
		}
	}
}

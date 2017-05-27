public class Run {
	public static void main(String[] args){
		int inCount = 3;
		int outCount = 1;
		double compatibility1 = 1.0;
		double compatibility2 = 1.0;
		double compatibility3 = 0.4;
		double mutChance = 0.8;
		double uniChance = 0.9;
		double disChance = 0.75;
		double noCrossChance = 0.25;
		int cloneCount = 4;
		double nodeMutChance = 0.03;
		double linkMutChance = 0.05;
		double interMateChance = 0.001;
		
		//?
		double maxDis = 100;
		
		//?
		double minFitGain = 0.01;
		int maxStag = 15;
		
		//?
		double fitGradient = 1.0;
		LList<LList<Double>> inputs = new LList<LList<Double>>();
		LList<Double> outputs = new LList<Double>();
		
		LList<Double> temp = new LList<Double>();
		LList<Double> temp1 = new LList<Double>();
		LList<Double> temp2 = new LList<Double>();
		
		temp.append(1.0);
		temp.append(1.0);
		temp.append(1.0);
		inputs.append(temp);
		outputs.append(3.0);
		
		temp1.append(1.0);
		temp1.append(2.0);
		temp1.append(3.0);
		inputs.append(temp1);
		outputs.append(6.0);
		
		/**
		temp2.append(72.0);
		temp2.append(11.0);
		temp2.append(19.0);
		inputs.append(temp2);
		outputs.append(102.0);
		**/
		
		Neat Darwin = new Neat(fitGradient, inCount, outCount, compatibility1, compatibility2, compatibility3, mutChance, uniChance, disChance, noCrossChance, cloneCount, nodeMutChance, linkMutChance, interMateChance, maxDis, minFitGain, maxStag);
		Darwin.run(inputs, outputs);
		
		System.out.println(Darwin.NYC.gens.getValue().members.length());
	}
}

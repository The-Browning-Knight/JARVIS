public class Run {
	public static void main(String[] args){
		int inCount;
		int outCount;
		double compatibility1;
		double compatibility2;
		double compatibility3;
		double mutChance;
		double uniChance;
		double disChance;
		double noCrossChance;
		int cloneCount;
		double nodeMutChance;
		double linkMutChance;
		double interMateChance;
		double maxDis;
		double minFitGain;
		int maxStag;
		double fitGradient;
		LList<LList<Double>> inputs = new LList<LList<Double>>();
		LList<Double> outputs = new LList<Double>();
		
		Neat Darwin = new Neat(fitGradient, inCount, outCount, compatibility1, compatibility2, compatibility3, mutChance, uniChance, disChance, noCrossChance, cloneCount, nodeMutChance, linkMutChance, interMateChance, maxDis, minFitGain, maxStag);
		Darwin.run(inputs, outputs);
	}
}

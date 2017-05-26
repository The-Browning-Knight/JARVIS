public class Neat {
	Population NYC;

	public Neat(int inCount, int outCount, double ca1, double ca2, double ca3, double ra1, double ra2, double ra3, double ra4, int cat, double p1, double p2, double im, double mD, double mfg, int ms){
		NYC = new Population(inCount, outCount, ca1, ca2, ca3, ra1, ra2, ra3, ra4, cat, p1, p2, im, mD, mfg, ms);
	}
	
	public void run(LList<LList<Double>> inputs, LList<Integer> outputs){
		for(int i = 0; i < inputs.length(); i++){
			NYC.activate(inputs.getValue(), outputs.getValue());
			inputs.next();
			outputs.next();
		}
	}
}

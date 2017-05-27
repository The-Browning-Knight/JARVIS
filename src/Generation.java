// group of species
// code for reproduction, both inter and intra species

public class Generation {

	LList<Species> members;
	LList<Species> fullyEvolved;
	LList<Gene> innovList;
	double prob1, prob2;
	double interMate;
	double maxDis;
	double c1,  c2,  c3,  r1,  r2,  r3,  r4;
	int ct;
	double minFitGain;
	int maxStag;
	double fitGradient;

	public Generation (double fg, int inCount, int outCount, double ca1, double ca2, double ca3, double ra1, double ra2, double ra3, double ra4, int cat, double p1, double p2, double im, double mD, double mfg, int ms){
		fullyEvolved = new LList<Species>();
		members = new LList<Species>();
		members.append(new Species(ca1, ca2, ca3, ra1, ra2, ra3, ra4, cat));
		Genome temp = new Genome();
		Genome temp2 = new Genome();
		// Gene temp1;
		for(int i = 0; i < inCount; i++){
			Gene temp1 = new NGene(i,0,i+1);
			temp.genes.append(temp1);
			temp2.genes.append(temp1);
		}
		for(int i = 0; i < outCount; i++){
			Gene temp1 = new NGene(i+inCount,2,i+inCount+1);
			temp.genes.append(temp1);
			temp2.genes.append(temp1);
		}
		members.getValue().members.append(new Organism(temp, members.getValue()));
		members.getValue().prevMember = new Organism(temp2, members.getValue());
		innovList = new LList<Gene>();
		prob1 = p1;
		prob2 = p2;
		c1 = ca1;
		c2 = ca2;
		c3 = ca3;
		r1 = ra1;
		r2 = ra2;
		r3 = ra3;
		r4 = ra4;
		ct = cat;
		fitGradient = fg;
		interMate = im;
		maxDis = mD;
		minFitGain = mfg;
		maxStag = ms;
	}

	int activationCount = 0;

	public Generation activate(LList<Double> in, Double result){
		for(int i = 0; i < members.length(); i++){
			members.getValue().activate(in, result, fitGradient);
		}		
		members.getValue().prevFit.append(members.getValue().getTotalFitness());
		activationCount++;
		return reproduce();
	}

	public Generation reproduce(){

		//Checks for stagnation  and interspecies reproduction
		members.moveToStart();
 
		int initial_length = members.length();

		for(int i = 0; i < initial_length; i++){
			//System.out.println("loop count: " + i);
			members.getValue().prevFit.moveToEnd();
			members.getValue().prevFit.prev();
			double temp8 = members.getValue().prevFit.getValue();
			if(members.getValue().prevFit.length() > maxStag){
				members.getValue().prevFit.moveToPos(members.length() - 1 - maxStag);
			} else {
				members.getValue().prevFit.moveToStart();
			}
			if(temp8 - members.getValue().prevFit.getValue() < minFitGain){
				members.append(members.getValue().reproduce());
			} else {
				members.getValue().enabled = false;
				fullyEvolved.append(members.getValue());
			}
		}


		members.moveToStart();
		for(int i = 0; i < members.length(); i++){

			LList<Organism> curr_members = members.getValue().members;

			curr_members.moveToStart();
			for (int j = 0; j < curr_members.length(); j++) {

				double random = Math.random();

				if (random < prob1) {

					// add new node (innovation)

					NGene mostRecentNGene = null;

					// iterate from end of innov and check type to find most recent NGene
					innovList.moveToPos(innovList.length()-1);
					for (int k = 0; k < innovList.length(); k++) {
						//System.out.
						if (innovList.getValue().geneType() == 1) {
							mostRecentNGene = (NGene) innovList.getValue();
							break;
						}
						innovList.prev();
					}

					if (mostRecentNGene != null) {
						Gene innov = new NGene(mostRecentNGene.name + 1, 1, mostRecentNGene.hismark+1);	
						innovList.append(innov);
						curr_members.getValue().dna.genes.append(innov);
					}					

				}

				random = Math.random();

				if (random < prob2) {
					int nodeCount = 0;

					for(int n = 0; n < curr_members.getValue().dna.genes.length(); n++){
						if(curr_members.getValue().dna.genes.getValue().geneType() == 1){
							nodeCount++;
						}
					}

					int[][] poss = new int[nodeCount][nodeCount];
					for(int n = 0; n < nodeCount; n++){
						for(int m = 0; m < nodeCount; m++){
							poss[n][m] = 1;
						}
					}

					for(int n = 0; n < curr_members.getValue().dna.genes.length(); n++){
						if(curr_members.getValue().dna.genes.getValue().geneType() == 0){
							poss[((CGene)curr_members.getValue().dna.genes.getValue()).in][((CGene)curr_members.getValue().dna.genes.getValue()).out] = 0;
						}
					}

					int sum = 0;

					for(int n = 0; n < nodeCount; n++){
						for(int m = 0; m < nodeCount; m++){
							sum += poss[n][m];
						}
					}

					int temp10 = (int)(Math.floor(sum*Math.random()));

					for(int n = 0; n < nodeCount; n++){
						for(int m = 0; m < nodeCount; m++){
							if(temp10 == 0){
								Gene innov = new CGene(n, m, Math.random(), true, innovList.length() + 1);

								innovList.append(innov);

								curr_members.getValue().dna.genes.append(innov);
							}
							temp10--;
						}
					}
				}

			}

			members.next();
		}

		LList<Organism> all = new LList<Organism>();


		//Insert interspecial reproduction
		members.moveToStart();
		for(int i = 0; i < members.length(); i++){
			members.getValue().members.moveToStart();
			for(int j = 0; j < members.getValue().members.length(); j++){
				all.append(members.getValue().members.getValue());
			}
		}
		Organism temp1;
		for(int i = 0; i < all.length(); i++){
			temp1 = all.getValue();
			all.moveToStart();
			for(int j = 0; j < all.length(); j++){
				if(i != j){
					if(Math.random() < interMate){
						if(all.getValue().spec != null){
							all.append(new Organism(all.getValue().spec.comGene(all.getValue().dna, temp1.dna), null));
						}
					}
				}
				all.next();
			}
			all.moveToPos(i);
		}
		all.moveToStart();
		for(int i = 0; i < all.length(); i++){
			all.getValue().brain = new NeuralNet(all.getValue().dna);
			all.next();
		}

		boolean temp3;
		//Speciation and prevMember
		for(int i = 0; i < members.length(); i++){
			members.getValue().members.clear();
		}
		members.moveToStart();
		Species temp5 = members.getValue();
		double min;
		double temp6;
		all.moveToStart();
		for(int i = 0; i < all.length(); i++){
			min = 0.0;
			temp3 = false;
			members.moveToStart();
			for(int j = 0; j < members.length(); j++){
				if((members.getValue().prevMember != null) && (members.getValue().prevMember.dna != null) && (all.getValue().spec != null)){
					temp6 = all.getValue().spec.findDistance(members.getValue().prevMember.dna);
					if(temp6 < maxDis){
						if(temp6 < min){
							temp5 = members.getValue();
							min = all.getValue().spec.findDistance(members.getValue().prevMember.dna);
							temp3 = true;
						}
					}					
				}

				members.next();
			}
			all.getValue().spec = temp5;
			temp5.members.append(all.getValue());
			if(!temp3){
				Species temp4 = new Species(c1, c2, c3, r1, r2, r3, r4, ct);
				members.append(temp4);
				temp4.members.append(all.getValue());
			}
			all.next();
		}
		for(int i = 0; i < members.length(); i++){
			members.getValue().members.moveToPos((int)Math.floor(Math.random()*members.getValue().members.length()));
			members.getValue().prevMember = members.getValue().members.getValue();
		}
		return this;
	}

}

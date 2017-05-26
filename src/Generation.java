public class Generation {

	LList<Species> members;
	LList<Species> fullyEvolved;
	LList<Gene> innovList;
	double prob1, prob2;
	double interMate;
	double maxDis;
	float c1,  c2,  c3,  r1,  r2,  r3,  r4;
	int ct;
	double minFitGain;
	int maxStag;

	public Generation (LList<Gene> i, LList<Species> m, double p1, double p2, double im, double mD, double mfg, int ms){
		fullyEvolved = new LList<Species>();
		members = m;
		innovList = i;
		prob1 = p1;
		prob2 = p2;
		interMate = im;
		maxDis = mD;
		minFitGain = mfg;
		maxStag = ms;
	}



	public Generation reproduce(){
		LList<Species> temp = new LList<Species>();
		for(int i = 0; i < members.length(); i++){
			if(members.getValue().prevFit.length() > maxStag){
				members.getValue().prevFit.moveToPos(members.length() - 1 - maxStag);
			} else {
				members.getValue().prevFit.moveToStart();
			}
			if(members.getValue().prevFit.getValue() - members.getValue().prevFit.getValue() < minFitGain){
				members.append(members.getValue().reproduce());
			} else {
				members.getValue().enabled = false;
				fullyEvolved.append(members.getValue());
			}
		}

		members = temp;

		for(int i = 0; i < members.length(); i++){

			LList<Organism> curr_members = members.getValue().members;

			for (int j = 0; j < curr_members.length(); j++) {

				double random = Math.random();

				if (random < prob1) {

					// add new node (innovation)

					NGene mostRecentNGene = null;

					// iterate from end of innov and check type to find most recent NGene
					innovList.moveToEnd();
					for (int k = 0; k < innovList.length(); k++) {
						if (innovList.getValue().geneType() == 1) {
							mostRecentNGene = (NGene) innovList.getValue();
							break;
						}
						innovList.prev();
					}

					Gene innov = new NGene(mostRecentNGene.name + 1, 1, mostRecentNGene.hismark+1);

					innovList.append(innov);

				}

				random = Math.random();

				if (random < prob2) {

					LList<Gene> curr_genes = curr_members.getValue().dna.genes;
					int nodeCount = 0;
					for(int n = 0; n < curr_genes.length(); n++){
						if(curr_genes.getValue().geneType() == 1){
							nodeCount++;
						}
					}

					int[][] combos = new int[nodeCount][nodeCount];

					for(int n = 0; n < nodeCount; n++){
						for(int m = 0; m < nodeCount; m++){
							combos[i][j] = 1;
						}
					}

					for(int n = 0; n < curr_genes.length(); n++){
						if(curr_genes.getValue().geneType() == 0){
							combos[((CGene)curr_genes.getValue()).in][((CGene)curr_genes.getValue()).out] = 0;
						}
					}

					int sum = 0;
					for(int n = 0; n < nodeCount; n++){
						for(int m = 0; m < nodeCount; m++){
							if(combos[i][j] == 1){
								sum++;
							}
						}
					}

					int whichConn = (int)Math.floor(sum * Math.random());

					for(int n = 0; n < nodeCount; n++){
						for(int m = 0; m < nodeCount; m++){
							if(whichConn == 0){
								Gene tempGene = new CGene(n,m, Math.random(),true,innovList.length()+1);
								innovList.append(tempGene);
								curr_members.getValue().dna.genes.append(tempGene);
							}
							whichConn--;
						}
					}
				}
				curr_members.next();

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
						all.append(new Organism(all.getValue().spec.comGene(all.getValue().dna, temp1.dna), null));
					}
				}
				all.next();
			}
			all.moveToPos(i);
		}

		boolean temp3;
		//Speciation and prevMember
		for(int i = 0; i < members.length(); i++){
			members.getValue().prevFit.append(members.getValue().getTotalFitness());
			members.getValue().members.clear();
		}
		members.moveToStart();
		Species temp5 = members.getValue();
		double min;
		double temp6;
		for(int i = 0; i < all.length(); i++){
			min = 0.0;
			temp3 = false;
			members.moveToStart();
			for(int j = 0; j < members.length(); j++){
				temp6 = all.getValue().spec.findDistance(members.getValue().prevMember.dna);
				if(temp6 < maxDis){
					if(temp6 < min){
						temp5 = members.getValue();
						min = all.getValue().spec.findDistance(members.getValue().prevMember.dna);
						temp3 = true;
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

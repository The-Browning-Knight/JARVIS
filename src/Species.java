public class Species {
	Organism prevMember;
	LList<Organism> members;
	double distance1;
	double distance2;
	double distance3;
	double mutChance;
	double uniChance;
	double disChance;
	double noCrossChance;
	int champClones;

	Species(float c1, float c2, float c3, float r1, float r2, float r3, float r4, int ct){
		distance1 = c1;
		distance2 = c2;
		distance3 = c3;
		mutChance = r1;
		uniChance = r2;
		disChance = r3;
		noCrossChance = r4;
		champClones = ct;

	}

	public Genome comGene(Genome a, Genome b){
		int temp;
		for(int i = 0; i < a.genes.length(); i++){
			for(int j = 0; j < b.genes.length(); j++){

			}
		}
	}

	public Species reproduce(){
		members.moveToStart();
		double max = members.getValue().brain.fitness;
		Organism champ = members.getValue();
		for(int i = 0; i < members.length(); i++){
			if(members.getValue().brain.fitness > max){
				max = members.getValue().brain.fitness;
				champ = members.getValue();
			}
		}
		for(int i = 0; i < champClones - 1; i ++){
			members.append(champ);
		}
		double temp;
		members.moveToStart();
		for(int i = 0; i < members.length(); i++){
			temp = Math.random();
			if(temp < mutChance){
				temp = Math.random();
				if(temp < uniChance){
					members.getValue().dna.genes.moveToStart();
					for(int j = 0; j < members.getValue().dna.genes.length(); j++){
						if(members.getValue().dna.genes.getValue().geneType() == 0){
							((CGene)members.getValue().dna.genes.getValue()).weight = ((CGene)members.getValue().dna.genes.getValue()).weight * (1 + (Math.random()/100));
						}
					}
				}else{
					members.getValue().dna.genes.moveToPos((int) Math.floor(members.getValue().dna.genes.length() * Math.random()));
					if (members.getValue().dna.genes.getValue().geneType() == 0){
						((CGene)members.getValue().dna.genes.getValue()).weight = Math.random();
					}
				}
			}
			members.next();
		}
		members.moveToStart();
		int temp1;
		for(int i = 0; i < members.length(); i++){
			temp = Math.random();
			if(temp > noCrossChance){
				temp1 = Math.floor(members.length() * Math.random());
				while(temp1 != i){
					
				}
				for(int j = 0; j < getFitness(); j++){
					
				}
			}
			member
		}
		return this;
	}

	public float getFitness(){
		float temp = 0f;
		members.moveToStart();
		for(int i = 0; i < members.length(); i++){
			temp  += members.getValue().brain.fitness/findDistance(members.getValue().dna);
			members.next();
		}
		return temp;
	}

	public double findDistance(Genome g){
		int max;
		int excess = 0;
		int disjoint = 0;
		double weightDiff = 0f;
		g.genes.moveToPos(g.genes.length()-1);
		prevMember.dna.genes.moveToPos(g.genes.length()-1);
		if(g.genes.getValue().hismark() < prevMember.dna.genes.getValue().hismark()){
			max = g.genes.getValue().hismark();
		}else{
			max = prevMember.dna.genes.getValue().hismark();
		}
		boolean temp;
		g.genes.moveToStart();
		for(int i = 0; i < g.genes.length(); i++){
			if(g.genes.getValue().hismark() > max){
				excess++;
			} else {
				prevMember.dna.genes.moveToStart();
				temp = true;
				for(int j = 0; j< prevMember.dna.genes.length(); j++){
					if(prevMember.dna.genes.getValue().hismark() == g.genes.getValue().hismark()){
						if(prevMember.dna.genes.getValue().geneType() == 0){
							weightDiff += Math.abs(((CGene)prevMember.dna.genes.getValue()).weight - ((CGene)g.genes.getValue()).weight);
						}
						temp = false;
					} 
					if(temp){
						disjoint++;
					}
					prevMember.dna.genes.next();
				}
			}
			g.genes.next();
		}
		return ((excess * distance1 + disjoint * distance2)/(prevMember.dna.genes.length() + g.genes.length()) + weightDiff * distance3);
	}
}

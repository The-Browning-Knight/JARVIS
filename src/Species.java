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
		Genome out = new Genome();
		int temp;
		a.genes.moveToPos(a.genes.length() - 1); 
		b.genes.moveToPos(b.genes.length() - 1);
		if(a.genes.getValue().hismark() > b.genes.getValue().hismark()){
			temp = a.genes.getValue().hismark();
		}else{
			temp = b.genes.getValue().hismark();
		}
		Gene temp1 = null;
		Gene temp2 = null;
		for(int i = 0; i < temp; i++){
			for(int j = 0; j < a.genes.length(); j++){
				if(a.genes.getValue().hismark() == i){
					temp1 = a.genes.getValue();
				}
			}
			for(int j = 0; j < b.genes.length(); j++){
				if(b.genes.getValue().hismark() == i){
					temp2 = b.genes.getValue();
				}
			}
			if(temp1 != null && temp2 != null){
				if(temp1.enabled() && temp2.enabled()){
					if(Math.random() > 0.5){
						out.genes.append(temp1);
					} else {
						out.genes.append(temp2);
					}
				} else {
					if(Math.random() > 0.5){
						out.genes.append(temp1);
					} else {
						out.genes.append(temp2);
					}
					if(Math.random() > disChance){
						out.genes.moveToPos(out.genes.length() - 1);
						if(!out.genes.getValue().enabled()){
							out.genes.getValue().setEnabled(!out.genes.getValue().enabled());
						}
					}
				}
			} else {
				if(temp1 != null && temp2 == null){
					out.genes.append(temp1);
				} else {
					if(temp1 == null && temp2 != null){
						out.genes.append(temp2);
					}
				}
			}
			temp1 = null;
			temp2 = null;
		}
		return out;
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
		Genome temp2;
		for(int i = 0; i < members.length(); i++){
			temp = Math.random();
			if(temp > noCrossChance){
				temp1 = (int)Math.floor(members.length() * Math.random());
				while(temp1 == i){
					temp1 = (int)Math.floor(members.length() * Math.random());
				}
				members.moveToPos(temp1);
				temp2  = members.getValue().dna;
				members.moveToPos(i);
				for(int j = 0; j < getFitness(); j++){
					members.append((new Organism(comGene(members.getValue().dna, temp2), this)));
				}
			}
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

public class Generation {
	
	LList<Species> members;
	LList<Gene> innovList;
	double prob1, prob2;
	
	public Generation (LList<Gene> i, LList<Species> m, double p1, double p2){
		
		members = m;
		innovList = i;
		
		prob1 = p1;
		prob2 = p2;
		
	}
	
	public Generation reproduce(){
		
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
					
					
					
				}
				
			}
			
			members.next();
			
		}
	}
	
}

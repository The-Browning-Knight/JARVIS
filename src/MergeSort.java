
public class MergeSort {

	static LList<Organism> sort(LList<Organism> a){

		if(a.length() > 2){
			int i;
			LList<Organism> out1 = new LList<Organism>();
			LList<Organism> out2 = new LList<Organism>();
			a.moveToStart();
			for(i = 0; i < a.length()/2; i++){
				out1.append(a.getValue());
				a.next();
			}
			for(;i<a.length(); i++){
				out2.append(a.getValue());
				a.next();
			}
			return merge(sort(out1),sort(out2));
		}
		if(a.length() == 2){
			a.moveToStart();
			double temp = a.getValue().brain.fitness;
			a.next();
			if(a.getValue().brain.fitness < temp) {
				a.moveToStart();
				a.append(a.remove());
				return a;
			} else {
				return a;
			}
		}else{
			return a;
		}
	}

	static LList<Organism> merge(LList<Organism> a, LList<Organism> b){
		LList<Organism> output = new LList<Organism>();
		a.moveToStart();
		b.moveToStart();

		while(output.length() < a.length() + b.length()){
			if(a.getValue() == null){
				output.append(b.getValue());
				b.next();
			} else {
				if(b.getValue() == null){
					output.append(a.getValue());
					a.next();
				} else {
					if(a.getValue().brain.fitness < b.getValue().brain.fitness){
						output.append(a.getValue());
						a.next();
					} else {
						output.append(b.getValue());
						b.next();
					}
				}
			} 
		}
		return output;
	}
}


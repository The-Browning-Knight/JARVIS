
public class MergeSort {

	static LList<Gene> sort(LList<Gene> a){

		if(a.length() > 2){
			int i;
			LList<Gene> out1 = new LList<Gene>();
			LList<Gene> out2 = new LList<Gene>();
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
			int temp = a.getValue().hismark();
			a.next();
			if(a.getValue().hismark() < temp) {
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

	static LList<Gene> merge(LList<Gene> a, LList<Gene> b){
		LList<Gene> output = new LList<Gene>();
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
					if(a.getValue().hismark() < b.getValue().hismark()){
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


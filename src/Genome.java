public class Genome {
	//Assume in chronological order
	LList<Gene> genes;
	
	public Genome(){
		genes = new LList<Gene>();
	}
	
	public void sort(){
		genes = MergeSort.sort(genes);
	}
}

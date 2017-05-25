public class Run {
	public static void main(String[] args){
		Genome gabe1 = new Genome();
		gabe1.genes.append(new NGene(1, 0));
		gabe1.genes.append(new NGene(2, 0));
		gabe1.genes.append(new NGene(3, 1));
		gabe1.genes.append(new NGene(4, 1));
		gabe1.genes.append(new NGene(5, 1));
		gabe1.genes.append(new NGene(6, 2));
		gabe1.genes.append(new CGene(1, 3, 1, true, 1));
		gabe1.genes.append(new CGene(1, 4, 1, true, 1));
		gabe1.genes.append(new CGene(1, 5, 1, true, 1));
		gabe1.genes.append(new CGene(2, 3, 1, true, 1));
		gabe1.genes.append(new CGene(2, 4, 1, true, 1));
		gabe1.genes.append(new CGene(2, 5, 1, true, 1));
		gabe1.genes.append(new CGene(3, 6, 1, true, 1));
		gabe1.genes.append(new CGene(4, 6, 1, true, 1));
		gabe1.genes.append(new CGene(5, 6, 1, true, 1));
		NeuralNet gabe2 = new NeuralNet(gabe1);
		LList<Float> gabe3 = new LList<Float>();
		gabe3.append(1F);
		gabe3.append(1F);
		System.out.println(gabe2.activate(gabe3));
	}
}

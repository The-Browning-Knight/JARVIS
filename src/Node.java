public class Node {
	//0-in, 1-hidden, 2-out
	int type;
	LList<Connection> children;
	int num;
	
	Node(int t){
		type = t;
	}
}

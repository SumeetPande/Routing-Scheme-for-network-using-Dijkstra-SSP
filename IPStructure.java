
public class IPStructure {
	
	IPNode root;
	
	public IPStructure(){
		root = null;
	}
	
	//Method to insert node in the structure
	public IPNode insertelement(Double ip,int hop){
		root = insertNew(root, ip,hop);
		return root;
	}

	private IPNode insertNew(IPNode root, Double ip,int hop) {
		if(root == null){
			root = new IPNode(ip,hop);
		}
		else{
			if(root.getIp()>ip)
				root.setLeft(insertNew(root.getLeft(),ip,hop));
			else
				root.setRight(insertNew(root.getRight(),ip,hop));
		}
		return root;
	}
	
	//Method to perform preorder travelsal 
	public void preorder(IPNode n){
		if(n!=null){
			n.print();
			preorder(n.getLeft());
			preorder(n.getRight());
		}
	}
	 
	int hop;
	//Method to lookup an element of the structure
	public int lookupElem(IPNode root,Double ip){
		
		int check = Double.compare(root.getIp(), ip);
		if(check==0){
			hop=root.getHop();
			}
		else if(check==1){
			lookupElem(root.getLeft(),ip);
			}
		else if((check==-1)){
			lookupElem(root.getRight(),ip);
			}		
		return hop;
	}
}
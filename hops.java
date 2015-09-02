//This class basically structurally determines the concept of a hop in a class defination.
//This consisit of attributes source , destination and hop which is bydefault set to null for each value. 

class hops{
	public String source;
	public String hop=null;
	public String destination;
	public hops(String source,String hop,String destination){
		this.source = source;
		this.hop = hop;
		this.destination = destination;
	}
}


public class IPNode {
	private IPNode left;
	private IPNode right;
	private Double ip;
	private int hop;
	
	public IPNode(Double ip,int hop){
		this.ip = ip;
		this.hop = hop;
		left = null;
		right = null;
	}
	
	public IPNode getLeft() {
		return left;
	}
	
	public void setLeft(IPNode left) {
		this.left = left;
	}
	
	public IPNode getRight() {
		return right;
	}
	
	public void setRight(IPNode right) {
		this.right = right;
	}
	
		public Double getIp() {
		return ip;
	}

	public void setIp(Double ip) {
		this.ip = ip;
	}

	public int getHop() {
		return hop;
	}

	public void setHop(int hop) {
		this.hop = hop;
	}

	public void print(){
		System.out.println("IP is :"+ip+" & Hop :"+hop);
	}
}

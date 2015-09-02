//This class decribes the the fibonacci heap. It describes the various attributes, constructors
// and various methods for the fibonacci heap.
enum StateDijkstra {
    LABELED, UNLABELED, SCANNED
}

class FHNode implements Comparable<FHNode>{
    // For a node in Fibonacci heap
    private FHNode parent;
    private FHNode leftSibling;
    private FHNode rightSibling;
    private FHNode children;
    private int data;
    private int key;
    private boolean mark;
    private int rank;
  
    StateDijkstra state;

    public FHNode(int data, int key) {
        this.data = data;
        this.key = key;
        this.parent = null;
        this.children = null;
        this.mark = false;
        this.rank = 0;
        this.state = StateDijkstra.UNLABELED;
        this.sibling();
    }
    //sets the sibling value
    private void sibling() {
        this.leftSibling = this.rightSibling = this;
    }
    
    // returns the data for the node
    public int getData() {
        return data;
    }
    // sets the data value for the node
    public void setData(int data) {
        this.data = data;
    }
    // returns key for fibonacci heap node
    public int getKey() {
        return key;
    }
    //sets key for fibonacci heap node
    public void setKey(int key) {
        this.key = key;
    }
    //returns the parent for a node
    public FHNode getParent() {
        return parent;
    }
    //sets the parent parameter for the node
    public void setParent(FHNode parent) {
        this.parent = parent;
    }
    //returns children for the node
    public FHNode getChildren() {
        return children;
    }
    //set the children attribute for a node
    public void setChildren(FHNode children) {
        this.children = children;
    }
    //The below four methods sets and returns the value for the sibling parameter.
    
    public FHNode getLeftSibling() {
        return leftSibling;
    }

    public void setLeftSibling(FHNode leftSibling) {
        this.leftSibling = leftSibling;
    }

    public FHNode getRightSibling() {
        return rightSibling;
    }

    public void setRightSibling(FHNode rightSibling) {
        this.rightSibling = rightSibling;
    }

    public boolean isMark() {
        return mark;
    }

    public void setMark(boolean mark) {
        this.mark = mark;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    

    public boolean isSingle() {
        return (this == this.rightSibling);
    }

    public void addChild(FHNode child) {
        if (this.children != null)
            this.children.addSibling(child);
        else
            this.children = child;
        child.setParent(this);
        child.mark = false;
        this.rank++;
    }
    //addition of new sibling for fibonacci heap
    public void addSibling(FHNode sibling) {
        if (sibling == null)
            return;

        FHNode tLeft = this.leftSibling;
        FHNode sLeft = sibling.getLeftSibling();

        tLeft.setRightSibling(sLeft);
        sLeft.setRightSibling(this);

        this.setLeftSibling(sLeft);
        sibling.setLeftSibling(tLeft);
    }
    
    //removes sibling for fibonacci heap
    public void removeSibling() {
        this.getLeftSibling().setRightSibling(this.rightSibling);
        this.getRightSibling().setLeftSibling(this.leftSibling);
        this.setRightSibling(this);
        this.setLeftSibling(this);
    }

    public void remove() {
        if (this.parent != null) {
            // if the nodes have parent
            if (this == this.rightSibling)
                this.parent.setChildren(null);
            else
                this.parent.setChildren(this.rightSibling);
            this.parent.rank--;
        }

        if (this != this.rightSibling) this.removeSibling();
        this.setParent(null);
        this.mark = false;
    }

	public int compareTo(FHNode o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
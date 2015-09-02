//This class decribes the the fibonacci heap. It describes the various attributes, constructors
// and various methods for the fibonacci heap.

class FiboHeapNode implements Comparable<FiboHeapNode>{
    // For a node in Fibonacci heap
    private FiboHeapNode parent;
    private FiboHeapNode leftSibling;
    private FiboHeapNode rightSibling;
    private FiboHeapNode children;
    private int data;
    private int key;
    private boolean mark;
    private int rank;
  
    State state;

    public FiboHeapNode(int data, int key) {
        this.data = data;
        this.key = key;
        this.parent = null;
        this.children = null;
        this.mark = false;
        this.rank = 0;
        this.state = State.UNLABELED;
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

    public void setKey(int key) {
        this.key = key;
    }

    public FiboHeapNode getParent() {
        return parent;
    }

    public void setParent(FiboHeapNode parent) {
        this.parent = parent;
    }

    public FiboHeapNode getChildren() {
        return children;
    }

    public void setChildren(FiboHeapNode children) {
        this.children = children;
    }
    //The below four methods sets and returns the value for the sibling parameter.
    public FiboHeapNode getLeftSibling() {
        return leftSibling;
    }

    public void setLeftSibling(FiboHeapNode leftSibling) {
        this.leftSibling = leftSibling;
    }

    public FiboHeapNode getRightSibling() {
        return rightSibling;
    }

    public void setRightSibling(FiboHeapNode rightSibling) {
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

    public void addChild(FiboHeapNode child) {
        if (this.children != null)
            this.children.addSibling(child);
        else
            this.children = child;
        child.setParent(this);
        child.mark = false;
        this.rank++;
    }
    //addition of new sibling for fibonacci heap
    public void addSibling(FiboHeapNode sibling) {
        if (sibling == null)
            return;

        FiboHeapNode tLeft = this.leftSibling;
        FiboHeapNode sLeft = sibling.getLeftSibling();

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
            // node have parent
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

	@Override
	public int compareTo(FiboHeapNode o) {
		return 0;
	}
}

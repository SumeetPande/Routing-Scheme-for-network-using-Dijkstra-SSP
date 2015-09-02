import java.util.ArrayList;
public class FibonacciHeap {
	// this class consist of the various methods associated with different operations for the 
	// fibonacci heaps.
    private FiboHeapNode minRoot;
    private int count;
    private int maxRank;

    public FibonacciHeap() {
        this.minRoot = null;
        this.maxRank = 0;
    }

    public FibonacciHeap(FiboHeapNode minRoot) {
        this.minRoot = minRoot;
        this.minRoot.setParent(null);
        this.minRoot.setLeftSibling(null);
        this.minRoot.setRightSibling(null);
        this.minRoot.setChildren(null);
        this.minRoot.setRank(0);
        this.maxRank = 0;
    }

    // method to check if the  Fibonacci heap is empty.
    public boolean isEmpty() {
        return (this.minRoot == null);
    }
    //Insert a new vertex or node in the F heap.
    public boolean insertVertexT(FiboHeapNode fiboHeapNode) {
        if (fiboHeapNode == null)
            return false;
        if (this.minRoot == null) {
        	// No root present in the fibonacci heap
            this.minRoot = fiboHeapNode;
            this.minRoot.setParent(null);
        } else {
            this.minRoot.addSibling(fiboHeapNode);
            if (this.minRoot.getKey() > fiboHeapNode.getKey())
                this.minRoot = fiboHeapNode;
        }
        return true;
    }
    
    public void InsertVertex(GVertex gVertex) {
    	FiboHeapNode fiboHeapNode = new FiboHeapNode(Integer.parseInt(gVertex.name),(int) gVertex.minDistance);
        if (this.minRoot == null) {
        	// No root present in the fibonacci heap
            this.minRoot = fiboHeapNode;
            this.minRoot.setParent(null);
        } else {
            this.minRoot.addSibling(fiboHeapNode);
            if (this.minRoot.getKey() > fiboHeapNode.getKey())
                this.minRoot = fiboHeapNode;
        }
    }

    public void decreaseKey(int delta, FiboHeapNode vertex) {
        vertex.setKey(delta);
        // check position of vertex
        FiboHeapNode parent = vertex.getParent();
        if (parent == null) {
            // right position check new min root
            if (vertex.getKey() < this.minRoot.getKey())
                this.minRoot = vertex;
            return;
        } else if (parent.getKey() < delta)
            return;

        FiboHeapNode fiboHeapNode = vertex;
        while (true) {
            fiboHeapNode.remove();
            this.insertVertexT(fiboHeapNode);
            if (parent.getParent() == null)
                // parent is root
                break;
            else if (!parent.isMark()) {
                // parent is not marked
                parent.setMark(true);
                break;
            } else {
                // parent is marked
                fiboHeapNode = parent;
                parent = parent.getParent();
            }
        }
    }

    public FiboHeapNode findMin() {
        return this.minRoot;
    }

    public FiboHeapNode deleteMin() {
        if (this.minRoot != null)
            count--;
        else
            return null;
        // Make children of minRoot new roots
        if (this.minRoot.getChildren() != null) {
            FiboHeapNode temp = this.minRoot.getChildren();
            while (temp != null) {
                temp.remove();
                this.insertVertexT(temp);
                temp = this.minRoot.getChildren();
            }
        }

        FiboHeapNode min = this.minRoot;
        // deletion of last node
        if (this.minRoot.getRightSibling() == this.minRoot) {
            this.count = 0;
            this.maxRank = 0;
            this.minRoot.remove();
            this.minRoot = null;
            return min;
        }

        // Merging of  root with same rank
        ArrayList<FiboHeapNode> rankRoots = new ArrayList<FiboHeapNode>(this.maxRank + 1);
        for (int i = 0; i < this.maxRank + 1; i++)
            rankRoots.add(null);
        this.maxRank = 0;
        FiboHeapNode curNode = this.minRoot.getRightSibling();
        int curRank;
        do {
            curRank = curNode.getRank();
            FiboHeapNode cur = curNode;
            curNode = curNode.getRightSibling();
            while (rankRoots.get(curRank) != null) {
                // have root with same rank
                FiboHeapNode add = rankRoots.get(curRank);
                if (cur.getKey() > add.getKey()) {
                    FiboHeapNode temp = cur;
                    cur = add;
                    add = temp;
                }
                add.removeSibling();
                cur.addChild(add);
                rankRoots.set(curRank, null);
                curRank++;
                if (curRank >= rankRoots.size())
                    rankRoots.add(null);
            }
            rankRoots.set(curRank, cur);
        } while (curNode != this.minRoot);

        // Remove minRoot and find new minRoot
        this.minRoot.remove();
        this.minRoot = null;
        for (int i = 0; i < rankRoots.size(); i++) {
            FiboHeapNode temp = rankRoots.get(i);
            if (temp != null) {
                temp.setLeftSibling(temp);
                temp.setRightSibling(temp);
                insertVertexT(temp);
                if (i>this.maxRank)
                    this.maxRank = i;
            }
        }
        return min;
    }
}

import java.util.ArrayList;
public class FibonacciHeapDijkstra {
    // this class consist of the various methods associated with different operations for the 
	// fibonacci heaps.
    private FHNode minRoot;
    private int count;
    private int maxRank;

    public FibonacciHeapDijkstra() {
        this.minRoot = null;
        this.maxRank = 0;
    }

    public FibonacciHeapDijkstra(FHNode minRoot) {
        this.minRoot = minRoot;
        this.minRoot.setParent(null);
        this.minRoot.setChildren(null);
        this.minRoot.setLeftSibling(null);
        this.minRoot.setRightSibling(null);
        this.minRoot.setRank(0);
        this.maxRank = 0;
    }

    // method to check if the  Fibonacci heap is empty.
    public boolean isEmpty() {
        return (this.minRoot == null);
    }
    
   //Insert a new vertex or node in the F heap. 
   public boolean insertVertexT(FHNode node) {
        if (node == null)
            return false;
        if (this.minRoot == null) {
            // No root present in the fibonacci heap
            this.minRoot = node;
            this.minRoot.setParent(null);
        } else {
            this.minRoot.addSibling(node);
            if (this.minRoot.getKey() > node.getKey())
                this.minRoot = node;
        }
        return true;
    }
    
    public void InsertVertex(GraphVertex graphVertex) {
    	FHNode node = new FHNode(Integer.parseInt(graphVertex.name),(int) graphVertex.minDistance);
        if (this.minRoot == null) {
        	// No root present in the fibonacci heap
            this.minRoot = node;
            this.minRoot.setParent(null);
        } else {
            this.minRoot.addSibling(node);
            if (this.minRoot.getKey() > node.getKey())
                this.minRoot = node;
        }
    }

    public void decreaseKey(int dkdata, FHNode vertex) {
        vertex.setKey(dkdata);
        // check position of vertex
        FHNode parent = vertex.getParent();
        if (parent == null) {
            // right position check new min root
            if (vertex.getKey() < this.minRoot.getKey())
                this.minRoot = vertex;
            return;
        } else if (parent.getKey() < dkdata)
            return;

        FHNode node = vertex;
        while (true) {
            node.remove();
            this.insertVertexT(node);
            if (parent.getParent() == null)
                // parent is root
                break;
            else if (!parent.isMark()) {
                // parent is not marked
                parent.setMark(true);
                break;
            } else {
                // parent is marked
                node = parent;
                parent = parent.getParent();
            }
        }
    }

    public FHNode findMin() {
        return this.minRoot;
    }

    public FHNode deleteMin() {
        if (this.minRoot != null)
            count--;
        else
            return null;
        // Make children of minRoot new roots
        if (this.minRoot.getChildren() != null) {
        	FHNode temp = this.minRoot.getChildren();
            while (temp != null) {
                temp.remove();
                this.insertVertexT(temp);
                temp = this.minRoot.getChildren();
            }
        }

        FHNode min = this.minRoot;
        // deletion of last node
        if (this.minRoot.getRightSibling() == this.minRoot) {
            this.count = 0;
            this.maxRank = 0;
            this.minRoot.remove();
            this.minRoot = null;
            return min;
        }

        // Merging of  root with same rank
        ArrayList<FHNode> rankRoots = new ArrayList<FHNode>(this.maxRank + 1);
        for (int i = 0; i < this.maxRank + 1; i++)
            rankRoots.add(null);
        this.maxRank = 0;
        FHNode curNode = this.minRoot.getRightSibling();
        int curRank;
        do {
            curRank = curNode.getRank();
            FHNode cur = curNode;
            curNode = curNode.getRightSibling();
            while (rankRoots.get(curRank) != null) {
                // have root with same rank
            	FHNode add = rankRoots.get(curRank);
                if (cur.getKey() > add.getKey()) {
                	FHNode temp = cur;
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
        	FHNode temp = rankRoots.get(i);
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

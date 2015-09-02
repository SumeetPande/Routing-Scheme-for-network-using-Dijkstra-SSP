import java.util.ArrayList;
//This class describes the attributes as well as some methods for the vertex of the graph which will
//be parsed from the input data.
class GVertex implements Comparable<GVertex>
{
	public String name;
    public ArrayList<GEdge> adjList;
    public double minDistance = Double.POSITIVE_INFINITY;
    public GVertex previous;
    public GVertex(String argName) { 
    	name = argName; 
    	adjList = new ArrayList<GEdge>();
    }
    public String toString() { return name; }
    public int compareTo(GVertex other)
    {
        return Double.compare(minDistance, other.minDistance);
    }
}


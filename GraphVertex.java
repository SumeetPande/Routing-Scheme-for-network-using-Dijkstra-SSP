import java.util.ArrayList;

//This class describes the attributes as well as some methods for the vertex of the graph which will
//be parsed from the input data.
class GraphVertex implements Comparable<GraphVertex>
{
	public final String name;
    public ArrayList<GraphEdge> adjList;
    public double minDistance = Double.POSITIVE_INFINITY;
    public GraphVertex previous;
    public GraphVertex(String argName) { 
    	name = argName; 
    	adjList = new ArrayList<GraphEdge>();
    }
    public String toString() { return name; }
    public int compareTo(GraphVertex other)
    {
        return Double.compare(minDistance, other.minDistance);
    }
}
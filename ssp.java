import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

//This is the main program where parsing of the input text file happens to store the data in 
//graphical structure. Post this we apply Djikstra SSP algorith to calculate the shortest path
//for the constructed graph. The underlying data structure in Djikstra SSP is the fibonacci heap.
public class ssp
{
	public static GraphVertex[] vertices;
	
    public static void main(String[] args) throws IOException
    {	
    	//Declaring the arguements for the input text files and vertex position.
    	String s= args[0];
    	int source = Integer.parseInt(args[1]);
    	int destination = Integer.parseInt(args[2]);
    	
    	//Parsing the input text file to create a graph structure.
    	String[] edgeInfo = parsing(s);
    	vertices = new GraphVertex[GraphStructure.noOfNodes];
    	
    	for(int i=0; i<GraphStructure.noOfNodes;i++){
    		GraphVertex v = new GraphVertex(i+"");
    		vertices[i] = v;
    	}
    	
    	for(int i=0; i<GraphStructure.noOfEdges;i++){
    		String[] edges = edgeInfo[i].split(" ");
    		int node = Integer.parseInt(edges[0]);
    		int connectingNode = Integer.parseInt(edges[1]);
    		double weight = Double.parseDouble(edges[2]);
    		GraphEdge newNode1 = new GraphEdge(vertices[connectingNode],weight);
    		GraphEdge newNode2 = new GraphEdge(vertices[node],weight);
    		vertices[node].adjList.add(newNode1);
    		vertices[connectingNode].adjList.add(newNode2);
    	}
    	
    	//Computing the Djikstra's SSP
    	computeShortestPath(vertices[source]);
    	GraphVertex v = vertices[destination];
    	System.out.println(v.minDistance);
	    List<GraphVertex> path = getShortestPathTo(v);
	    System.out.println(path);
	
    }
    
    //Method to parse the input text file.
    static String[] parsing(String name) throws IOException
  	{
  		String fileName = name;
  	    Path path = Paths.get(fileName);
  	    //Extracting the no of vertices and edges.  	    
  	    Scanner scanner = new Scanner(path);
  	    String s = scanner.nextLine();
  	    String[] graphInfo = s.split(" ");
	    GraphStructure.noOfNodes = Integer.parseInt(graphInfo[0]);
	    GraphStructure.noOfEdges = Integer.parseInt(graphInfo[1]);
	    
	    //Extracting the remaining data about edges and edge weights
	    int i=0;
	    String[] tempStrng = new String[GraphStructure.noOfEdges];
  	    
  	    while (scanner.hasNextLine())
  	    {
  	    	s = scanner.nextLine();
  	    	if(!s.isEmpty()){
  	        tempStrng[i]=s;
  	        i++;
  	    	}
  	    }
  	    scanner.close();
  		return tempStrng;
  	}
	
    //Method to implement the Djikstra SSP
    public static void computeShortestPath(GraphVertex source)
    {
        source.minDistance = 0.0;
        FibonacciHeapDijkstra vertexQueue = new FibonacciHeapDijkstra();
        vertexQueue.InsertVertex(source);
        
	while (!vertexQueue.isEmpty()) {
		FHNode n = vertexQueue.deleteMin();
		GraphVertex u = vertices[n.getData()];

            // Visit each edge exiting u
            for (GraphEdge e : u.adjList)
            {
            	GraphVertex v = e.target;
                double weight = e.weight;
                double distanceThroughU = u.minDistance + weight;
		if (distanceThroughU < v.minDistance) {
		    v.minDistance = distanceThroughU ;
		    v.previous = u;
		    vertexQueue.InsertVertex(v);
		}
            }
        }
    }
    //This method will compute the shorest path between the vertexes.
    public static List<GraphVertex> getShortestPathTo(GraphVertex target)
    {
        List<GraphVertex> path = new ArrayList<GraphVertex>();
        for (GraphVertex graphVertex = target; graphVertex != null; graphVertex = graphVertex.previous)
            path.add(graphVertex);
        Collections.reverse(path);
        return path;
    }
}
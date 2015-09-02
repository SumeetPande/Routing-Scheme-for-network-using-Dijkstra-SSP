import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;
//This is the main program where parsing of the input text file happens to store the data in 
//graphical structure. Post this we apply Djikstra SSP algorith to calculate the shortest path
//for the constructed graph. The underlying data structure in Djikstra SSP is the fibonacci heap.
//In addition we also perform parsing of the IP address files, convert them to binary string 
//and store the same in a Hashtable. 
class calcSSP{
	public GVertex[] vertices;
	//int totalnodes=0;
	ArrayList<FibonacciHeap> myList = new ArrayList<FibonacciHeap>();
	FibonacciHeap vertexQueue;
	public void computeShortestPath(GVertex source)
    {
        source.minDistance = 0.0;
        vertexQueue = new FibonacciHeap();
        vertexQueue.InsertVertex(source);
        while (!vertexQueue.isEmpty()) {
        	FiboHeapNode n = vertexQueue.deleteMin();
        	GVertex u = vertices[n.getData()];

            // Visit each edge exiting u
            for (GEdge e : u.adjList)
            {
                GVertex v = e.target;
                double weight = e.weight;
                double distanceThroughU = u.minDistance + weight;
                if (distanceThroughU < v.minDistance) {
				    v.minDistance = distanceThroughU ;
				    v.previous = u;
				    vertexQueue.InsertVertex(v);
                }
            }
        }
        myList.add(vertexQueue);
    }
			//This method will compute the shorest path between the vertexes.
			public List<GVertex> getShortestPathTo(GVertex target)
    {
        List<GVertex> path = new ArrayList<GVertex>();
        for (GVertex gVertex = target; gVertex != null; gVertex = gVertex.previous)
            path.add(gVertex);
        Collections.reverse(path);
        return path;
    }
			public String[] parsing(String name) throws IOException
			{
				String fileName = name;
		  	    Path path = Paths.get(fileName);
		  	    //Extracting the no of vertices and edges.  	    
		  	    Scanner scanner = new Scanner(path);
		  	    String s = scanner.nextLine();
		  	    String[] graphInfo = s.split(" ");
			    GStructure.noOfNodes = Integer.parseInt(graphInfo[0]);
			    GStructure.noOfEdges = Integer.parseInt(graphInfo[1]);
			    //totalnodes=GStructure.noOfNodes;
			    
			    //Extracting the remaining data about edges and edge weights
			    int i=0;
			    String[] tempStrng = new String[GStructure.noOfEdges];
		  	    
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
		//Parsing the input IP address  file to create and store in a Hashtable.
		public Hashtable<Integer, Double> parseIPAddress(String ipname) throws IOException
			{
		    	int i=0;
		    	Double d= null;
		    	String[] strs = new String[50000];
		    	String fileName = ipname;
			    Path path = Paths.get(fileName);
		    	Hashtable<Integer, Double> ipTable = new Hashtable<Integer, Double>();
			    Scanner scanner = new Scanner(path);
			    
			    while (scanner.hasNextLine())
			    {
			    	String s = scanner.nextLine();
			    	if(!s.isEmpty()){
			        strs[i]=s;
			        String[] strsTemp = s.split("\\.");
			        String binconvert = Integer.toBinaryString(Integer.parseInt(strsTemp[0]))+""+Integer.toBinaryString(Integer.parseInt(strsTemp[1]))+""+Integer.toBinaryString(Integer.parseInt(strsTemp[2]))+""+Integer.toBinaryString(Integer.parseInt(strsTemp[3]));
			        d = Double.parseDouble(binconvert);
			    	}
			    	if(!ipTable.containsKey(i/2)){
			       	ipTable.put(i/2,d);
			    	}
			    	i++;
			    }
			    scanner.close();
			    return ipTable;
				}
}
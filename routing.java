import java.io.IOException;
import java.util.Hashtable;
import java.util.List;
import java.util.ArrayList;

public class routing
{
        public static void main(String[] args) throws IOException
        {	
        	//Declaring the arguements for the input text files and vertex position.
        	String name = args[0];
        	String ipname = args[1];
        	int inputNode = Integer.parseInt(args[2]);
    	    int destination = Integer.parseInt(args[3]);
        	calcSSP calcPath = new calcSSP();
        	String[] edgeInfo = calcPath.parsing(name);
        	calcPath.vertices = new GVertex[GStructure.noOfNodes];
    	    
        	//Starting the hop calculation
    	    ArrayList<ArrayList<hops>> vertexHopList = new ArrayList<ArrayList<hops>>();
            for(int k=0;k<GStructure.noOfNodes;k++){
            	for(int i=0; i<GStructure.noOfNodes;i++){
            		GVertex v = new GVertex(i+"");
            		calcPath.vertices[i] = v;
            	}
            	
            	for(int i=0; i<GStructure.noOfEdges;i++){
            		String[] edges = edgeInfo[i].split(" ");
            		int node = Integer.parseInt(edges[0]);
            		int connectingNode = Integer.parseInt(edges[1]);
            		double weight = Double.parseDouble(edges[2]);
            		GEdge x = new GEdge(calcPath.vertices[connectingNode],weight);
            		GEdge y = new GEdge(calcPath.vertices[node],weight);
            		calcPath.vertices[node].adjList.add(x);
            		calcPath.vertices[connectingNode].adjList.add(y);
            	}
            	
            	
            	calcPath.computeShortestPath(calcPath.vertices[k]);
            	ArrayList<hops> hopstable = new ArrayList<hops>();
                for (GVertex v : calcPath.vertices)
                {
        	    List<GVertex> path1 = calcPath.getShortestPathTo(v);
        	    String hop = null;
        	    if(path1.size()>1){
        	    hop = path1.get(1).name;
        	    }
        	    hops hp = new hops(calcPath.vertices[k].name,hop,v.name);
        	    hopstable.add(hp);
        	    }
                vertexHopList.add(hopstable);
            }    	
            //end of hops calculation
            
            //Get and convert ip into binary form
            Hashtable<Integer, Double> ip = calcPath.parseIPAddress(ipname);
            
            IPNode h = null;
            IPStructure bt1 = new IPStructure();
            ArrayList<IPNode> trie = new ArrayList<IPNode>();
            for(int a=0;a<vertexHopList.size();a++){
            	IPStructure bt = new IPStructure();
            	for(int b = 0; b<vertexHopList.get(a).size();b++){
            		Double db = ip.get(Integer.parseInt(vertexHopList.get(a).get(b).destination));
            		int hop;
            		if(vertexHopList.get(a).get(b).hop!=null){
            		hop = Integer.parseInt(vertexHopList.get(a).get(b).hop);
            		}
            		else
            			hop =Integer.MAX_VALUE;
            		h=bt.insertelement(db, hop);
            	}
            	trie.add(h);
            }
            for(int i=0; i<GStructure.noOfNodes;i++){
        		GVertex v = new GVertex(i+"");
        		calcPath.vertices[i] = v;
        	}
        	
        	
        	for(int i=0; i<GStructure.noOfNodes;i++){
        		GVertex v = new GVertex(i+"");
        		calcPath.vertices[i] = v;
        	}
        	
        	for(int i=0; i<GStructure.noOfEdges;i++){
        		String[] edges = edgeInfo[i].split(" ");
        		int node = Integer.parseInt(edges[0]);
        		int connectingNode = Integer.parseInt(edges[1]);
        		double weight = Double.parseDouble(edges[2]);
        		GEdge x = new GEdge(calcPath.vertices[connectingNode],weight);
        		GEdge y = new GEdge(calcPath.vertices[node],weight);
        		calcPath.vertices[node].adjList.add(x);
        		calcPath.vertices[connectingNode].adjList.add(y);
        	}
        	
        	
        	calcPath.computeShortestPath(calcPath.vertices[inputNode]);
        	
         
    	    System.out.println(calcPath.vertices[destination].minDistance);
    	    
    	    
    	    Double ipSource = ip.get(inputNode);
    	    System.out.print(ipSource+" ");
    	    int g = 0;
    	    while(inputNode!=destination){
    	    	Double d = ip.get(destination);
    	    	
    	    	h = trie.get(inputNode);
    	    	g = bt1.lookupElem(h, d);
    	    	if(g==2147483647)
    	    		break;
    	    	System.out.print(ip.get(destination).toString()+" ");
    	    	inputNode=g;
    	    }
    	}      
}
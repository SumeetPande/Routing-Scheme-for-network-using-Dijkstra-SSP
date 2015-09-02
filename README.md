# Routing-Scheme-for-network-using-Dijkstra-SSP
Routing Scheme for network using Dijkstra's SSP

To implement Dijkstra's Single Source Shortest Path (ssp) algorithm for undirected graphs using Fibonacci heaps. To make use the adjacency list representation for graphs.
Structure & Methodology:
The programming for the above part has been implemented in JAVA programming language. Hence the compiler used is the JAVA compiler for the entire code. The main idea behind this implementation is to parse the input text file which happens to store the graphical data and create a graph structure in our project consisting of nodes i.e. vertices and edges and weights for the edges. The graphical representation used is the adjacency list representation. Based on this graphical structure we have applied the Dijkstra’s SSP to calculate the shortest path between any nodes/vertices. The underlying data structure used for the Dijkstra’s SSP algorithm is the Fibonacci heap.
Step 1: The first primary requirement was to create a graph structure and to populate this graph structure by parsing the sample input files.
For this purpose we have created three different classes which are as follows:
GraphVertex: This class describes the attributes as well as some methods for the vertex of the graph which will be parsed from the input data.
GraphEdge: This class describes the attributes of the graph edge of the graph which will be parsed from the input text file.
GraphStructure: This graph in general describes the structure of the graph consisting of nodes and edges.
The main methods used for this are as follows:
static String[] parsing(String name)
This method basically parses the input text file line by line and populates the information of the same into a graphical structure as defined from the above three classes. This actually returns the array of strings which consist of information regarding no of nodes, no of edges and weight for each of these edges. This graph structure is then passed on to the methods responsible for computing the shortest path using Dijkstra’s algorithm.
Step 2: Fibonacci heap implementation of Dijkstra’s SSP.
The underlying data structure for our implementation is the Fibonacci heap structure. For this purpose we have created two classes which are actually implementation of a node, attribute and methods of that node for the Fibonacci heap as well another class which consist of methods for different operations on Fibonacci heap. The classes are as follows:
FHNode: This class basically describes the Fibonacci heap. It describes the various attributes, constructors and node level operation methods for the Fibonacci heap data structure. The various methods used in this class are private void sibling() ,public int getData(),public void setData(int data),public int getKey(),public void setKey(int key),public FHNode getParent(),public void setParent(FHNode parent) and many other methods which perform node level operations for the Fibonacci heap structure.
FibonacciHeapDijkstra : This class was used to implement methods for operation of the Fibonacci heap. These operations are used while executing the shortes path algorithm.
The methods are as follows:
// method to check if the Fibonacci heap is empty.
public boolean isEmpty()
//Perform operations on the min element
public FHNode findMin()
public FHNode deleteMin()
//Insert a new vertex or node in the F heap.
public boolean insertVertexT(FHNode node)
//Performdecrease key operationson the F-Heap
public void decreaseKey(int dkdata, FHNode vertex)
Step 3: Computing the shortest path
This is accomplished by making use of multiple methods in our main class i.e. ssp.java. This main purpose here in this class is to implement methodologies to accomplish the entire program working right from inputting text file and capturing the argument which will be actually graph nodes between which the shortest path has to be calculated , the minimum weight/distance between them has to be output and the path has to be displayed.
The methodology for taking arguments is as follows:
//Declaring the arguements for the input text files and vertex position.
String s= args[0];
int source = Integer.parseInt(args[1]);
int destination = Integer.parseInt(args[2]);
Once we get the input text file and the argument vertices then we implement the program code for parsing this text file and setting up Fibonacci heap data structure as mentioned in step 1 and step 2.
Once we have that up and ready we need to compute the shortest path which as follows:
//Computing the Djikstra's SSP
computeShortestPath(vertices[source]);
//Method to implement the Djikstra SSP
public static void computeShortestPath(GraphVertex source)
The first method here passes on the source argument node to the method which compute shortest path. This method computes the shortest path from the source vertex to all the remaining vertices in our graph and computes the shortest path for each of them as well as update the minimum distance for each of these vertices which is initially by default set to positive infinity.
GraphVertex v = vertices[destination];
System.out.println(v.minDistance);
List<GraphVertex> path = getShortestPathTo(v);
System.out.println(path);
These statement then computes & displays the particular shortest path as well as the minimum distance corresponding between the source and the destination.
The parameters and the output for the same when passed the input_1000_50_part1.txt is as follows.

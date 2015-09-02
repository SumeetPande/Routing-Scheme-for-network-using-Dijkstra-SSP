//This class describes the attributes of the graph edge of the graph which will be parsed from 
//the input text file.
class GraphEdge
{
    public final GraphVertex target;
    public final double weight;
    public GraphEdge(GraphVertex argTarget, double argWeight)
    { target = argTarget; weight = argWeight;}
}
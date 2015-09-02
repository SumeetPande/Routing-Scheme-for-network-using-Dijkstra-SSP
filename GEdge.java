//This class describes the attributes of the graph edge of the graph which will be parsed from 
//the input text file.
class GEdge
{
    public final GVertex target;
    public final double weight;
    public GEdge(GVertex argTarget, double argWeight)
    { target = argTarget; weight = argWeight;}
}
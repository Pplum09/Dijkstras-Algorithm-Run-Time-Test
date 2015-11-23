import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.lang.Math;
 
public class DijkstraAlgorithmSet
{
    private int distances[];
    private Set<Integer> settled;
    private Set<Integer> unsettled;
    private int number_of_nodes;
    private int adjacencyMatrix[][];
    
    public static int[][] generateMatrix(int numOfNodes) {
	
	int[][] matrix = new int[numOfNodes + 1][numOfNodes + 1];
	int dice;
	for (int i = 1; i <= numOfNodes; i++) {
	    for (int j = 1; j <= numOfNodes; j++) {
		
		matrix[i][j] = (int)(Math.random() * 11);
		dice = (int)(Math.random() * 10);
		if (dice >= 4) {
		    matrix[i][j] = Integer.MAX_VALUE;
		    continue;
		}
		
		if (i == j) {
		    matrix[i][j] = 0;
		    continue;
		}
		
		if (matrix[i][j] == 0) {
		    matrix[i][j] = Integer.MAX_VALUE;
		}
	    }
	}
	// debug
	/*
	for (int i = 0; i < matrix.length; i++) {
	    for (int j = 0; j < matrix[i].length; j++) {
		System.out.print(matrix[i][j] + " ");
	    }
	    System.out.println();
	}
	*/
	return matrix;
    }
    
    public DijkstraAlgorithmSet(int number_of_nodes)
    {
        this.number_of_nodes = number_of_nodes;
        distances = new int[number_of_nodes + 1];
        settled = new HashSet<Integer>();
        unsettled = new HashSet<Integer>();
        adjacencyMatrix = new int[number_of_nodes + 1][number_of_nodes + 1];
    }
 
    public void dijkstra_algorithm(int adjacency_matrix[][], int source)
    {
        int evaluationNode;
        for (int i = 1; i <= number_of_nodes; i++)
            for (int j = 1; j <= number_of_nodes; j++)
                adjacencyMatrix[i][j] = adjacency_matrix[i][j];
 
        for (int i = 1; i <= number_of_nodes; i++)
	    {
		distances[i] = Integer.MAX_VALUE;
	    }
 
        unsettled.add(source);
        distances[source] = 0;
        while (!unsettled.isEmpty())
	    {
		evaluationNode = getNodeWithMinimumDistanceFromUnsettled();
		unsettled.remove(evaluationNode);
		settled.add(evaluationNode);
		evaluateNeighbours(evaluationNode);
	    } 
    }
 
    private int getNodeWithMinimumDistanceFromUnsettled()
    {
        int min ;
        int node = 0;
 
        Iterator<Integer> iterator = unsettled.iterator();
        node = iterator.next();
        min = distances[node];
        for (int i = 1; i <= distances.length; i++)
	    {
		if (unsettled.contains(i))
		    {
			if (distances[i] <= min)
			    {
				min = distances[i];
				node = i;
			    }
		    }
	    }
        return node;
    }
 
    private void evaluateNeighbours(int evaluationNode)
    {
        int edgeDistance = -1;
        int newDistance = -1;
 
        for (int destinationNode = 1; destinationNode <= number_of_nodes; destinationNode++)
	    {
		if (!settled.contains(destinationNode))
		    {
			if (adjacencyMatrix[evaluationNode][destinationNode] != Integer.MAX_VALUE)
			    {
				edgeDistance = adjacencyMatrix[evaluationNode][destinationNode];
				newDistance = distances[evaluationNode] + edgeDistance;
				if (newDistance < distances[destinationNode])
				    {
					distances[destinationNode] = newDistance;
				    }
				unsettled.add(destinationNode);
			    }
		    }
	    }
    }
 
    public static void main(String... arg)
    {
        int adjacency_matrix[][];
        int numOfNodes;
        int source = 0;
        Scanner scan = new Scanner(System.in);
	int nodeAmount[] = {10, 100, 1000, 10000, 100000, 1000000};

	for (int i = 0; i < nodeAmount.length; i++) {
	    
	    try
		{
		    
		// manual program
		/*
		System.out.print("Enter number of nodes: ");
		numOfNodes = scan.nextInt();
		System.out.println("generating adjacency matrix...");
 		System.out.print("Enter the source: ");
		source = scan.nextInt();
		*/
		    //source = 2;
		
		    DijkstraAlgorithmSet dijkstrasAlgorithm = new DijkstraAlgorithmSet(nodeAmount[i]);
		
		    // start time and memory keeping
		    long startTime = System.currentTimeMillis();
		    long startMemory = Runtime.getRuntime().freeMemory();
		    
		    // where the algo is called
		    dijkstrasAlgorithm.dijkstra_algorithm(generateMatrix(nodeAmount[i]), source);
		    
		    // end time and memory keeping
		    long stopMemory = Runtime.getRuntime().totalMemory();
		
		    long stopTime = System.currentTimeMillis();
		    long runTime = stopTime - startTime;
		    //System.out.println("Start mem: " + startMemory);
		    //System.out.println("End mem: " + stopMemory);
		    System.out.println("Number of nodes: " + nodeAmount[i]);
		    System.out.println("Total mem of Dijkstra's: " + (stopMemory - startMemory) + " units of memory");

		    //System.out.println("Start time: " + startTime);
		    //System.out.println("End Time: " + stopTime);
		    System.out.println("Total runtime of DJ: " + runTime + "ms\n");
		    
		    // print results
		    /*
		  System.out.println("The Shorted Path to all nodes are ");
		  for (int i = 1; i <= dijkstrasAlgorithm.distances.length - 1; i++)
		  {
		  if (dijkstrasAlgorithm.distances[i] == Integer.MAX_VALUE) {
		  System.out.println(source + " to " + i + " is: none");
		      }
		      
		      else {
			System.out.println(source + " to " + i + " is: "+ dijkstrasAlgorithm.distances[i]);
		      }
		  }
		*/
		} catch (InputMismatchException inputMismatch)
		      {
			  System.out.println("Wrong Input Format");
		      }
	    }
		//scan.close();
    }
}
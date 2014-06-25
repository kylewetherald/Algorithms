/*
 * Kyle Wetherald
 * Project 3
 * BST Optimizer
 * Optimizes binary search tree based on frequency of searches done on each node
 *
**/

import java.util.*;

public class BSTOptimizer
{
	/*---- globals ---------------------------------------------------------------------*/
	public boolean MEMOIZE;	// turns memoization on or off.
	public int CALLS;		// counts recursive calls.
	private Hashtable<String, Integer> freqTable;	// key: node's value value: frequency
	private BinaryTree[][] memo;	// memo of which trees have been optimized
	private ArrayList<String> keys;	// list of all of the nodes
	private static final int MAX_COST = 2147483647; 	// the largest possible int
	
	
	/*---- Init: crate new hastable, init call counter ---------------------------------*/
	public BSTOptimizer()
	{
		freqTable = new Hashtable<String, Integer>();
		MEMOIZE = true;
		CALLS = 0;
	}

	/*---- Allows user to add a node/frequency to freqTable ----------------------------*/
	public void addKey(String key, int frequency) //adds a key.
	{
		freqTable.put(key, new Integer(frequency));
	}
	
	/*---- finds the optimal BST -------------------------------------------------------*/
	public BinaryTree optimize() //returns an optimal BST.
	{
		keys = new ArrayList<String>(freqTable.keySet()); 	// get the list of nodes
		Collections.sort(keys);								// sort list of nodes
		memo = new BinaryTree[keys.size()][keys.size()];	// init memo
		return optimize(0, keys.size()-1);					// call recursive function
	}

	/*---- recursively finds BST of specifeid nodes ------------------------------------*/
	public BinaryTree optimize(int start, int end)
	{
		CALLS++;
			
		// if key is out of range
		if(end < 0 || start >= keys.size() )
			return new BinaryTree();
			
		// check memo first if allowed
		if(MEMOIZE && memo[start][end] != null)
			return memo[start][end];
		
		BinaryTree bestTree = new BinaryTree();

		// if only one key
		if(start == end)
		{
			bestTree = new BinaryTree( keys.get(start) );
			bestTree.cost = ((Integer)freqTable.get(keys.get(start))).intValue();
		}
		
		else
		{
			int bestCost = MAX_COST;	// worst cost = biggest possible integer
			
			BinaryTree thisTree;		// local placeholder
			
			// iterate through each node as root to determine if the cost is best.
			for(int i=start; i<=end; i++)	
			{
				thisTree = new BinaryTree(keys.get(i));
				// find the optimal left side of the tree
				thisTree.leftChild = optimize(start, i-1);
				// find the optimal right side of the tree
				thisTree.rightChild = optimize(i+1, end);

				// determine cost:
				// add left and right children's cost, 
				thisTree.cost += thisTree.leftChild.cost;
				thisTree.cost += thisTree.rightChild.cost;
					
				// determine if best
				if (thisTree.cost < bestCost)
				{
					bestTree = thisTree;
					bestCost = thisTree.cost;
				}
			}
			// frequency of each node is added at each level, to account for depth
			for(int j=start ; j<=end; j++)
				bestTree.cost += ((Integer)freqTable.get(keys.get(j))).intValue();			
		}
		memo[start][end] = bestTree;
		return bestTree;	
	}
}
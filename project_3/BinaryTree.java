/*
 * Kyle Wetherald
 * CS 1501
 * Project 3
 * BinaryTree data structure that holds root of tree, and pointers to childre
**/

import java.util.*;
 
public class BinaryTree
{
	public int cost;		// frequency * depth of each node in tree
	public String value;	// "name" of root node
	public BinaryTree leftChild;	// pointer to left child
	public BinaryTree rightChild;	// pointer to right child

	/*---- init ------------------------------------------------------------------------*/
	public BinaryTree()
	{
		value = "";
		leftChild = null;
		rightChild = null;
		cost = 0;
	}
	public BinaryTree(String v)
	{
		value = v;
		leftChild = null;
		rightChild = null;
	}
	
	/*---- provide meaningful way to print ---------------------------------------------*/
	public String toString()
	{
		if(leftChild == null && rightChild == null)
		{
			return "(" + value +" null null)"; 
		}
		else if(leftChild.value == "")
		{
			return "(" + value +" null "+ rightChild +")";
		}
		else if(rightChild.value == "")
		{
			return "("+ value +" "+ leftChild +" null)";
		}
		else
		{
			return "("+ value +" " + leftChild + " " + rightChild + ")";
		}
	}
}
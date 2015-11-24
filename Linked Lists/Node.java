/**
 * 
 * @author Gregory Grzymski
 *
 */

public class Node<E>
{
	public Object data; // data field stored in the node
	public Node<E> next; // pointer to the next node in the list

	/*
	 * Two Scenarios:
	 * 
	 * 1. Create a blank Node.
	 * 
	 * 2. Create a Node with specific data.
	 */
	
	public Node()
	{
		this.data=null;
		this.next=null;
	}
	
	public Node(Object input, Node<E> connect)
	{
		this.data = input;
		this.next=connect;
	}
	
	// Getters and Setters
	
	public Object getData()
	{
		return this.data;
	}
	
	public void setData(Object info)
	{
		this.data=info;
	}
	
	public void setNext(Node<E> connect)
	{
		this.next= connect;
	}
	
	public Node<E> getNext()
	{
		return this.next;
	}
}


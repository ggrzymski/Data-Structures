import java.util.NoSuchElementException;

/**
 * @author Gregory Grzymski
 */
public class Stack<T> 
{
	private Node<T> front; // top of the stack
	private int n; // counts how many elements are on the stack
	
	public Stack() 
	{
		front = null;
		n = 0;
	}
	
	public void push(T item) 
	{
		front = new Node<T>(item, front);
		n++;
	}
	
	public T pop() throws NoSuchElementException 
	{
		if (front == null) 
		{
			throw new NoSuchElementException();
		}
		
		T hold = front.data;
		front = front.next;
		n--;
		return hold;
	}
	
	public boolean isEmpty() 
	{
		return front == null;
	}
	
	public int size() 
	{
		return n;
	}
	
	public void clear() 
	{
		front = null;
		n = 0;
	}
}

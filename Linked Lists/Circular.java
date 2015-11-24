/**
 * 
 * @author Gregory Grzymski
 *
 */
public class Circular 
{
	// add to front and return the rear pointer of the resulting list
	public static <E> Node<E> addFront(int data, Node<E> rear) 
	{
		Node<E> temp = new Node<E>(data, null);
		
		if (rear == null) 
		{
			temp.next = temp;
			return temp;
		}
		
		temp.next = rear.next;
		rear.next = temp;
		return rear;
	}
	
	// delete from front and return the rear pointer of the resulting list
	public static <E> Node<E> deleteFront(Node<E> rear) 
	{
		if (rear == null || rear.next == rear) 
		{ // empty or single-node list
			return null;
		}
		
		rear.next = rear.next.next;
		return rear;
	}
	
	public static <E> boolean search(Node<E> rear, Object target) {
		if (rear == null) 
		{ 
			return false;
		}
		
		Node<E> ptr=rear;
		do 
		{
			if (ptr.data == target) 
			{
				return true;
			}
			
			ptr = ptr.next;
		} while (ptr != rear);
		
		return false;
	}
}

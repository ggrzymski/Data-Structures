/**
 * 
 * @author Gregory Grzymski
 * 
 *
 */
public class LinkedList<E> 
{
	public Node<E> head; // header (first item in the linked list)
	
	public LinkedList()
	{
		this.head = null;
	}
	
	// Implement Insert and Remove methods
	
	public void addFront(E data)
	{
		// Make a new header
		
		head = new Node<E>(data, head); // point to itself for starters
	}
	
	public Node<E> getHead()
	{
		if(head==null)
		{
			return null;
		}
		
		return head;
	}
	
	public boolean removeHead()
	{
		if(head==null)
		{
			return false;
		}
		
		head = head.next;
		return true;
	}
	
	public void addToEnd(E data)
	{
		if(head==null)
		{
			addFront(data);
			return;
		}
		
		Node<E> temp = head;
		Node<E> prev = head;
		
		while(temp!=null)
		{
			prev = temp;
			temp = temp.next;
		}
		
		prev.next = new Node<E>(data, null);
	}
	
	public Node<E> getLast()
	{
		if (head==null)
		{
			return null;
		}
		
		Node<E> temp = this.head;
		Node<E> prev = this.head;
		
		while(temp!=null)
		{
			prev=temp;
			temp= temp.next;
		}
		
		return prev;
	}
	
	public boolean insertAfter(Node<E> item, Object key)
	{
		Node<E> insert = new Node<E>(key, null);
		
		if(head==null)
		{
			head = new Node<E>(item,null);
			return true;
		}
		
		Node<E> temp = head;
		
		while(temp!=null)
		{
			if(temp.data.equals(key))
			{
				insert.next = temp.next;
				temp.next = insert;
				break;
			}
			
			temp=temp.next;
		}
		
		return true;
	}
	
	public void remove(Object key)
	{
	      if(head == null)
	      {
	         throw new RuntimeException("cannot delete");
	      }

	      if( head.data.equals(key) )
	      {
	         head = head.next;
	         return;
	      }

	      Node<E> cur  = head;
	      Node<E> prev = null;

	      while(cur != null && !cur.data.equals(key) )
	      {
	         prev = cur;
	         cur = cur.next;
	      }

	      if(cur == null)
	      {
	         throw new RuntimeException("cannot delete");
	      }

	      //delete cur node
	      prev.next = cur.next;
	 }
	
	public void insertBefore(E key, Object toInsert)
	{
	      if(head == null) return;

	      if(head.data.equals(key))
	      {
	         addFront(key);
	         return;
	      }

	      Node<E> prev = null;
	      Node<E> cur = head;

	      while(cur != null && !cur.data.equals(key))
	      {
	         prev = cur;
	         cur = cur.next;
	      }
	      //insert between cur and prev
	      if(cur != null)
	      {
	         prev.next = new Node<E>(toInsert, cur);
	      }
	 }
}

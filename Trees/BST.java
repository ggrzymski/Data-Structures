import java.util.ArrayList;
import java.util.NoSuchElementException;

public class BST<T extends Comparable<T>> 
{
		
	private BSTNode<T> root;
	private int size;
	
	public BST() 
	{
		root = null;
		size = 0;
	}
	
	public T search(T target)
	{  
		BSTNode<T> ptr = root;
		
		while (ptr != null) 
		{
			int c = target.compareTo(ptr.data);
			
			if (c == 0) 
			{
				return ptr.data;
			}
			
			ptr = c < 0 ? ptr.left : ptr.right;
		}
		
		return null;
	}
	
	public void insert(T target) throws IllegalArgumentException 
	{
		BSTNode<T> ptr=root, prev=null;
		int c=0;
		
		while (ptr != null) 
		{
			c = target.compareTo(ptr.data);
			
			if (c == 0) 
			{
				throw new IllegalArgumentException("Duplicate key");
			}
			
			prev = ptr;
			ptr = c < 0 ? ptr.left : ptr.right;
		}
		
		BSTNode<T> tmp = new BSTNode<T>(target);
		size++;
		
		if (root == null) 
		{
			root = tmp;
			return;
		}
		
		if (c < 0) 
		{
			prev.left = tmp;
		} 
		else 
		{
			prev.right = tmp;
		}
	}

	public void delete(T key) throws NoSuchElementException 
	{
		BSTNode<T> p = null, x = root;
		int c;
		
		while (x != null) 
		{
			c = key.compareTo(x.data);
			if (c == 0) {
				break;
			}
			p = x;
			x = c < 0 ? x.left : x.right;
		}

		if (x == null) 
		{
			throw new NoSuchElementException();
		}

		if (x.left != null && x.right != null) 
		{
			BSTNode<T> y = x.left; p=x;
			
			while (y.right != null) 
			{  
				p=y;
				y = y.right;
			}
			
			x.data = y.data;   // write y's data over x's
			x = y;   // set x to y in prep for case 1 or case 2 fall through
		}

		size--;
		
		// What if p is null? It means x is the root
		
		if (p == null) 
		{
			root = x.right == null ? x.left : x.right;
			return;
		}

		// Case 1 and 2
		
		if (x == p.left) 
		{
			p.left = x.left != null ? x.left : x.right;
			return;
		}
		
		p.right = x.left != null ? x.left : x.right;
	}
	
	public ArrayList<T> sort() 
	{
		// call inorder traversal method with root as parameter and
		// pass new empty array list to be filled in
		
		ArrayList<T> sortedSequence = new ArrayList<T>();
		
		inorder(root, sortedSequence);
		
		return sortedSequence;
	}

	private void inorder(BSTNode<T> root, ArrayList<T> sortedSequence) 
	{
		if (root == null) 
		{
			return;
		}
		
		inorder(root.left, sortedSequence);
		sortedSequence.add(root.data);
		inorder(root.right, sortedSequence);
	}
}

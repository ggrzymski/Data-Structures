package sort;

public class Mergesort {
	
	// prevent objects from being created
	private Mergesort() { }
	
	private static <T extends Comparable<T>> Node<T> merge(Node<T> L1, Node<T> L2) 
	{
        if (L1 == null && L2 == null) 
        {
                return null;
        }
        
        if (L1 == null) 
        {
                return L2;
        }
        
        if (L2 == null) 
        {
                return L1;
        }
        
        if (L1.data.compareTo(L2.data) < 0) 
        {
                L1.next = merge(L1.next, L2);
                return L1;
        } 
        
        else 
        {
                L2.next = merge(L1, L2.next);
                return L2;
        }
	}
	
	// supposed to be only called on lists of length > 1
    private static <T extends Comparable<T>> Node<T> split(Node<T> L, int n) 
    {
            if (L == null) 
            {
                    return null;
            }
            
            Node<T> ptr = L;
            
            for (int i=1; i < n/2; i++) 
            {
                    ptr = ptr.next;
            }
            
            Node<T> ret = ptr.next;
            ptr.next = null;
            return ret;      
    }

    public static <T extends Comparable<T>>Node<T> sort(Node<T> L, int n) 
    {
        if (L == null) 
        {
                return null;
        }
        
        if (L.next == null) 
        { // only one node
                return L;
        }
        
        Node<T> L2 = split(L,n);
        L = sort(L,n/2);    // recursively sort left half
        L2 = sort(L2,(n-n/2));  // recursively sort right half
        
        return merge(L,L2);
    }
}


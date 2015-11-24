package sort;

class RxNode 
{
	String data;
	RxNode next;
	
	RxNode(String data, RxNode next) 
	{
		this.data = data;
		this.next = next;
	}
}

public class Radixsort 
{
	
	// prevent instantiation
	private Radixsort() { }
	
	// scatters the given LL into buckets, based on value of digit
	// being processed in the current pass - pass = 1 means least significant digit
	// each bucket is stored as a circular linked list for easy gathering
	private static void scatter(RxNode front, RxNode[] buckets, int radix,int pass) 
	{
		RxNode ptr = front;
		
		while (ptr != null) 
		{
			// get the value of current digit
			int val = 0;
			int len = ptr.data.length();
			
			if (len >= pass) 
			{
				val = Character.digit(ptr.data.charAt(len-pass), radix);
			}
			
			RxNode next = ptr.next;
			ptr.next = ptr;
			
			if (buckets[val] == null) 
			{
				buckets[val] = ptr;
			} 
			else 
			{
				ptr.next = buckets[val].next;
				buckets[val].next = ptr;
				buckets[val] = ptr;
			}
			ptr = next;
		}
	}
	
	// gathers the CLLs of all buckets into a single CLL
	// sets each bucket to null after its CLL has been gathered
	// returns result as a non-CLL
	private static RxNode gather(RxNode[] buckets) 
	{
		RxNode rear = null;
		
		for (int i=0; i < buckets.length; i++) 
		{
			if (buckets[i] == null) 
			{
				continue;
			}
			
			if (rear == null) 
			{
				rear = buckets[i];
				buckets[i] = null;
				continue;
			}
			
			// neither rear nor buckets[i] is null
			RxNode front = rear.next;
			rear.next = buckets[i].next;
			buckets[i].next = front;
			rear = buckets[i];
			buckets[i] = null;
		}
		
		// convert to non-CLL and return
		RxNode front = rear.next;
		rear.next = null;
		
		return front;
	}
	
	// sorts a set of numbers represented in a given radix system, stored in
	// a linked list
	// returns the front of the sorted linked list
	public static RxNode sort(RxNode front, int n, int radix) 
	{
		if (n < 2) 
		{
			return front;
		}

		// find maximum length of all strings
		int max = front.data.length();
		RxNode ptr = front.next;
		
		while (ptr != null) 
		{
			if (ptr.data.length() > max) 
			{
				max = ptr.data.length();
			}
			
			ptr = ptr.next;
		}
		
		// set up buckets, length = radix
		RxNode[] buckets = new RxNode[radix];
		
		for (int i=0; i < buckets.length; i++) 
		{
			buckets[i] = null;
		}
		
		// iterate for max number of passes
		for (int pass=1; pass <= max; pass++) 
		{			
			// scatter 
			scatter(front, buckets, radix, pass);
			// gather
			front = gather(buckets);
		}
		
		return front;
	}
}

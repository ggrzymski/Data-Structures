package sort;

public class InsertionSort 
{

	public static <T extends Comparable<T>>void sort(T[] list) 
	{  
        for (int i=1; i < list.length; i++) 
        {
                int index;
                
                for (index=i;index > 0;index--) 
                {
                        if (list[i].compareTo(list[index-1]) > 0) 
                        {
                                break;
                        }
                }
                // we have the index position for insert
                
                T temp = list[i];
                for (int j=i; j > index; j--) 
                {
                        list[j] = list[j-1];
                }
                
                list[index] = temp;
        }
	}
}

import java.util.HashMap;

class Point 
{
	int x,y;
	
	public Point(int x, int y) 
	{
		this.x = x;
		this.y = y;
	}
	
	public String toString() 
	{
		return "(" + x + "," + y + ")";
	}
	
	// implement a hashCode method, that overrides the Object class'
	// hashCode method - the trick is to use the String class' hashCode
	// on a string representation of this object
	
	public int hashCode() 
	{
		return toString().hashCode();
	}
	
}

class Person 
{
	String firstName, lastName;
	String email;
	
	public Person(String firstName, String lastName, String email) 
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	
	public String toString() 
	{
		return "(" + firstName + " " + lastName + " : " + email + ")";
	}
	
	// implement a hashCode method, that overrides the Object class'
	// hashCode method - here we can concatenate the first and last names
	// and call hashCode on that
	
	public int hashCode() 
	{
		return (firstName + lastName).hashCode();
	}
	
}

public class HashUse 
{

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		HashMap<Point,Point> points = new HashMap<Point,Point>(100,0.75f);
		Point point = new Point(2,3);
		points.put(point,point); // the HashMap put method calls the key object's hashCode method
								 // so, it ends up calling the point object's hashCode method
		
		HashMap<Person,Person> people = new HashMap<Person,Person>(500,2.5f);
		Person person = new Person("Eli", "Manning", "emanning@nygiants.com");
		people.put(person,person);
		// the HashMap put method will call the person object's hashCode method
	}

}

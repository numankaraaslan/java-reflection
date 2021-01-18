package reflection.java.model;

public class Person
{
	private Vehicle vehicle;

	public Person(Vehicle vehicle)
	{
		this.vehicle = vehicle;
	}

	public void getInfo()
	{
		System.err.println("This is person");
	}
}

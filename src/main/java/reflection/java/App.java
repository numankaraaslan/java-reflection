package reflection.java;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Set;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import reflection.java.model.Person;

public class App
{
	public static void main(String[] args)
	{
		Person person = getPerson();
		person.getInfo();
	}

	private static Person getPerson()
	{
		Person person;
		try
		{
			Constructor<?> personConstructor = Person.class.getConstructors()[ 0 ];
			String constructorParameterClassName = personConstructor.getParameterTypes()[ 0 ].getName();
			System.err.println("First constructor parameter of Person class is --> " + constructorParameterClassName);
			Class<?> vehicleInterface = Class.forName(constructorParameterClassName);
			System.err.println("Package name of the constructor parameter is --> " + vehicleInterface.getPackage());
			Reflections reflections = new Reflections(vehicleInterface.getPackage(), new SubTypesScanner(true));
			Set<?> vehicleImplementations = reflections.getSubTypesOf(vehicleInterface);
			System.err.println("The number of vehicle implementations is --> " + vehicleImplementations.size());
			Class<?> carClass = (Class<?>) vehicleImplementations.iterator().next();
			System.err.println("Vehicle implementation class is --> " + carClass.getTypeName());
			person = (Person) personConstructor.newInstance(carClass.newInstance());
			Method carClassMethod = carClass.getDeclaredMethods()[ 0 ];
			System.err.println("First method of the car class is --> " + carClassMethod.getName());
			Object carClassMethodResult = carClassMethod.invoke(carClass.newInstance());
			System.err.println("The result of the car class method is --> " + carClassMethodResult);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			person = null;
		}
		return person;
	}
}

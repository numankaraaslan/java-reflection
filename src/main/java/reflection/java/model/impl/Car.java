package reflection.java.model.impl;

import reflection.java.model.Vehicle;

public class Car implements Vehicle
{
	@Override
	public String getInfo()
	{
		return "This is a car";
	}
}

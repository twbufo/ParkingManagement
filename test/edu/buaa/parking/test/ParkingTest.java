package edu.buaa.parking.test;

import junit.framework.Assert;
import junit.framework.TestCase;
import edu.buaa.parking.ParkingManager;
import edu.buaa.parking.bean.Car;
import edu.buaa.parking.bean.ParkCard;

public class ParkingTest extends TestCase {
	
	public void testParking() {
		
		Car car1 = new Car();
		car1.setNumber("京A00001");
		
		Car car2 = new Car();
		car2.setNumber("京A00002");	
		
		Car car3 = new Car();
		car3.setNumber("京A00003");	
		
		Car car4 = new Car();
		car4.setNumber("京A00004");	
		
		ParkingManager pm = new ParkingManager();
		
		try {
			
			//car1停车
			System.out.println("********car1停车*******");
			ParkCard pc1 = pm.park(car1);
			System.out.println();	
			
			
			//car2停车
			System.out.println("********car2停车*******");
			ParkCard pc2 = pm.park(car2);
			System.out.println();
			
			//car3停车
			System.out.println("********car3停车*******");
			ParkCard pc3 = pm.park(car3);
			System.out.println();
			
			//car4停车
			System.out.println("********car4停车*******");
			ParkCard pc4 = pm.park(car4);
			System.out.println();
			
			
			//car1取车
			System.out.println("********car1取车*******");
			pm.drawOut(pc1, car1);
			System.out.println();
			
			//card2取车car1
			System.out.println("********card2取车car1*******");
			pm.drawOut(pc2, car1);
			System.out.println();
			
			Assert.assertNotNull(pc2);
			Assert.assertEquals(pc2.getCar().getNumber(), car2.getNumber());
			Assert.assertEquals(pc2.getCar().getNumber(), car2.getNumber());
			
			//car2取车
			System.out.println("********car2正常取车*******");
			pm.drawOut(pc2, car2);
			System.out.println();
			
			
			//car2重复取车
			System.out.println("********car2重复取车*******");
			pm.drawOut(pc2, car2);
			System.out.println();	
			
			//car3取车
			System.out.println("********car3取车*******");
			pm.drawOut(pc3, car3);
			System.out.println();
			
			//car3重复取车
			System.out.println("********car3重复取车*******");
			pm.drawOut(pc3, car3);
			System.out.println();
			
			
			//car4取车
			System.out.println("********car4取车*******");
			pm.drawOut(pc4, car4);
			System.out.println();
			
		
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}

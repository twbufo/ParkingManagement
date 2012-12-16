package edu.buaa.parking.test;

import junit.framework.Assert;
import junit.framework.TestCase;
import edu.buaa.parking.ParkingManager;
import edu.buaa.parking.bean.Car;
import edu.buaa.parking.bean.ParkCard;

public class ParkingTest extends TestCase {
	
	public void testParking() {
		
		Car car1 = new Car();
		car1.setNumber("��A00001");
		
		Car car2 = new Car();
		car2.setNumber("��A00002");	
		
		Car car3 = new Car();
		car3.setNumber("��A00003");	
		
		Car car4 = new Car();
		car4.setNumber("��A00004");	
		
		ParkingManager pm = new ParkingManager();
		
		try {
			
			//car1ͣ��
			System.out.println("********car1ͣ��*******");
			ParkCard pc1 = pm.park(car1);
			System.out.println();	
			
			
			//car2ͣ��
			System.out.println("********car2ͣ��*******");
			ParkCard pc2 = pm.park(car2);
			System.out.println();
			
			//car3ͣ��
			System.out.println("********car3ͣ��*******");
			ParkCard pc3 = pm.park(car3);
			System.out.println();
			
			//car4ͣ��
			System.out.println("********car4ͣ��*******");
			ParkCard pc4 = pm.park(car4);
			System.out.println();
			
			
			//car1ȡ��
			System.out.println("********car1ȡ��*******");
			pm.drawOut(pc1, car1);
			System.out.println();
			
			//card2ȡ��car1
			System.out.println("********card2ȡ��car1*******");
			pm.drawOut(pc2, car1);
			System.out.println();
			
			Assert.assertNotNull(pc2);
			Assert.assertEquals(pc2.getCar().getNumber(), car2.getNumber());
			Assert.assertEquals(pc2.getCar().getNumber(), car2.getNumber());
			
			//car2ȡ��
			System.out.println("********car2����ȡ��*******");
			pm.drawOut(pc2, car2);
			System.out.println();
			
			
			//car2�ظ�ȡ��
			System.out.println("********car2�ظ�ȡ��*******");
			pm.drawOut(pc2, car2);
			System.out.println();	
			
			//car3ȡ��
			System.out.println("********car3ȡ��*******");
			pm.drawOut(pc3, car3);
			System.out.println();
			
			//car3�ظ�ȡ��
			System.out.println("********car3�ظ�ȡ��*******");
			pm.drawOut(pc3, car3);
			System.out.println();
			
			
			//car4ȡ��
			System.out.println("********car4ȡ��*******");
			pm.drawOut(pc4, car4);
			System.out.println();
			
		
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}

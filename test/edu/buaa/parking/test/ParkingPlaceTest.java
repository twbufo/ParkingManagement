package edu.buaa.parking.test;

import junit.framework.Assert;
import junit.framework.TestCase;
import edu.buaa.parking.bean.Car;
import edu.buaa.parking.bean.ParkCard;
import edu.buaa.parking.bean.ParkingPlace;

public class ParkingPlaceTest extends TestCase {

	/*
	 * ͣ������ Ʊ�ݲ��� ȡ������
	 */
	public void testParkAndTake() {
		ParkingPlace place = new ParkingPlace(2, 10);

		int count = place.getFreeCount();
		Car c = new Car();
		ParkCard card = place.parkCar(c, null);
		// ����Ӧ�÷��سɹ���card����Ϊ��
		Assert.assertNotNull(card);
		// ͣ��һ�����󣬿ճ�λ��1
		Assert.assertEquals(count - 1, place.getFreeCount());
		// ͣ��һ�������Ѿ�ʹ�õĳ�λ��1
		Assert.assertEquals(10 - count + 1, place.getMaxCount()
				- place.getFreeCount());

		Car c1 = new Car();
		place.parkCar(c1, null);

		Car r = place.removeCar(card);
		// ȡ���ĳ�Ӧ���Ƿ���ĳ�
		Assert.assertTrue(c == r);

		Car r1 = place.removeCar(card);
		// ���ʹ��һ��Ʊ��ȡ����Ӧ�÷��ؿ�
		Assert.assertTrue(r1 == null);
	}

	/*
	 * ͣһ����
	 */
	public void testParkOneCar() {
		ParkingPlace place = new ParkingPlace(1, 10);

		int count = place.getFreeCount();

		Car c = new Car();
		ParkCard card = place.parkCar(c, null);

		Assert.assertEquals(count - 1, place.getFreeCount());
		Assert.assertNotNull(card);
	}

	/*
	 * ���Խ�ͣ����ͣ��
	 */
	public void testParkToFull() {
		ParkingPlace place = new ParkingPlace(1, 10);

		int i = 0;
		for (; i < 100; i++) {
			Car c = new Car();
			ParkCard t = place.parkCar(c, null);
			if (t == null)
				break;
		}

		Assert.assertEquals(i, 10);
	}

}

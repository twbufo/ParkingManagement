package edu.buaa.parking.test;

import junit.framework.Assert;
import junit.framework.TestCase;
import edu.buaa.parking.bean.Car;
import edu.buaa.parking.bean.ParkCard;
import edu.buaa.parking.bean.ParkingPlace;

public class ParkingPlaceTest extends TestCase {

	/*
	 * 停车测试 票据测试 取车测试
	 */
	public void testParkAndTake() {
		ParkingPlace place = new ParkingPlace(2, 10);

		int count = place.getFreeCount();
		Car c = new Car();
		ParkCard card = place.parkCar(c, null);
		// 存入应该返回成功，card不能为空
		Assert.assertNotNull(card);
		// 停入一辆车后，空车位减1
		Assert.assertEquals(count - 1, place.getFreeCount());
		// 停入一辆车后，已经使用的车位加1
		Assert.assertEquals(10 - count + 1, place.getMaxCount()
				- place.getFreeCount());

		Car c1 = new Car();
		place.parkCar(c1, null);

		Car r = place.removeCar(card);
		// 取出的车应该是放入的车
		Assert.assertTrue(c == r);

		Car r1 = place.removeCar(card);
		// 多次使用一个票据取车，应该返回空
		Assert.assertTrue(r1 == null);
	}

	/*
	 * 停一辆车
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
	 * 测试将停车场停满
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

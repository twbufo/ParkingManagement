package edu.buaa.parking.test;

import junit.framework.Assert;
import junit.framework.TestCase;
import edu.buaa.parking.BaseParkingBoy;
import edu.buaa.parking.ParkingBoy;
import edu.buaa.parking.ParkingDirector;
import edu.buaa.parking.ParkingManager;
import edu.buaa.parking.bean.Car;
import edu.buaa.parking.bean.ParkCard;
import edu.buaa.parking.bean.ParkingPlace;
import edu.buaa.parking.strategy.MaxParkStrategy;
import edu.buaa.parking.strategy.MaxRateParkStrategy;
import edu.buaa.parking.strategy.SimpleParkStrategy;

public class ParkingBoyTest extends TestCase {

	private BaseParkingBoy init(BaseParkingBoy boy) {
		boy.removeAllParkingPlace();

		ParkingPlace place1 = new ParkingPlace(10 + boy.getId(), 10);
		ParkingPlace place2 = new ParkingPlace(20 + boy.getId(), 10);

		boy.addParkingPlace(place1);
		boy.addParkingPlace(place2);

		return boy;
	}

	/**
	 * 停车、取车
	 * 
	 * @param boy
	 */
	private void parkAndTake(BaseParkingBoy boy) {
		int count = boy.getFreeCount();
		Car c = new Car();
		ParkCard card1 = boy.parkCar(c);
		assertNotNull(card1);
		assertEquals(count - 1, boy.getFreeCount());

		Car c0 = new Car();
		boy.parkCar(c0);

		Car c1 = boy.removeCar(card1);
		assertNotNull(c1);
		assertEquals(c1, c);

		Car c2 = boy.removeCar(card1);
		assertNull(c2);
	}

	/**
	 * 将车位停满然后取出
	 * 
	 * @param boy
	 */
	private void takeCarWithNoSpace(BaseParkingBoy boy) {
		int i = 0;
		int max = boy.getMaxCount();

		ParkCard card[] = new ParkCard[max];
		Car c[] = new Car[max];

		for (; i < 10000; i++) {
			Car tmp = new Car();
			ParkCard parkCard = boy.parkCar(tmp);
			if (parkCard == null)
				break;

			card[i] = parkCard;
			c[i] = tmp;
		}

		Assert.assertEquals(max, i);
		Assert.assertEquals(0, boy.getFreeCount());

		for (i = 0; i < max; i++) {
			Car cc = boy.removeCar(card[i]);
			Assert.assertEquals(cc, c[i]);
		}

		Assert.assertEquals(max, boy.getFreeCount());
	}

	/**
	 * 对主管测试
	 */
	public void testDirector() {
		BaseParkingBoy boy1 = new ParkingBoy(1, "boy",
				new SimpleParkStrategy<ParkingPlace>());
		BaseParkingBoy boy2 = new ParkingBoy(2, "super_boy",
				new MaxParkStrategy<ParkingPlace>());
		BaseParkingBoy boy3 = new ParkingBoy(3, "super_boy",
				new MaxRateParkStrategy<ParkingPlace>());

		init(boy1);
		init(boy2);
		init(boy3);

		ParkingManager manager = new ParkingManager(9, "Manager",
				new MaxParkStrategy<ParkingPlace>());
		manager.addParkingBoy(boy1);
		manager.addParkingBoy(boy2);
		manager.addParkingBoy(boy3);

		parkAndTake(manager);
		parkAndTake(manager);
		parkAndTake(manager);
		parkAndTake(manager);

		init(boy1);
		init(boy2);
		init(boy3);

		manager.removeAllParkingBoy();
		manager.addParkingBoy(boy3);
		manager.addParkingBoy(boy2);
		manager.addParkingBoy(boy1);

		takeCarWithNoSpace(manager);

		ParkingDirector director = new ParkingDirector();
		director.addParkingManager(manager);
		director.printReport();
	}

	/**
	 * 对经理进行测试
	 */
	public void testManager() {
		BaseParkingBoy boy1 = new ParkingBoy(1, "boy",
				new SimpleParkStrategy<ParkingPlace>());
		BaseParkingBoy boy2 = new ParkingBoy(2, "super_boy",
				new MaxParkStrategy<ParkingPlace>());
		BaseParkingBoy boy3 = new ParkingBoy(3, "super_boy",
				new MaxRateParkStrategy<ParkingPlace>());

		init(boy1);
		init(boy2);
		init(boy3);

		ParkingManager manager = new ParkingManager(9, "Manager",
				new MaxParkStrategy<ParkingPlace>());
		manager.addParkingBoy(boy1);
		manager.addParkingBoy(boy2);
		manager.addParkingBoy(boy3);

		parkAndTake(manager);
		parkAndTake(manager);
		parkAndTake(manager);
		parkAndTake(manager);

		init(boy1);
		init(boy2);
		init(boy3);

		manager.removeAllParkingBoy();
		manager.addParkingBoy(boy3);
		manager.addParkingBoy(boy2);
		manager.addParkingBoy(boy1);

		takeCarWithNoSpace(manager);

	}

	/**
	 * 对普通停车仔进行测试
	 */
	public void testSimpleBoy() {
		BaseParkingBoy boy = new ParkingBoy(1, "boy",
				new SimpleParkStrategy<ParkingPlace>());

		init(boy);
		parkAndTake(boy);

		init(boy);
		takeCarWithNoSpace(boy);
	}

	/**
	 * 对聪明停车仔进行测试
	 */
	public void testSmartBoy() {
		BaseParkingBoy boy = new ParkingBoy(2, "smart_boy",
				new MaxParkStrategy<ParkingPlace>());

		init(boy);
		parkAndTake(boy);

		init(boy);
		takeCarWithNoSpace(boy);
	}

	/**
	 * 对超级停车仔进行测试
	 */
	public void testSupperBoy() {
		BaseParkingBoy boy = new ParkingBoy(3, "super_boy",
				new MaxRateParkStrategy<ParkingPlace>());

		init(boy);
		parkAndTake(boy);

		init(boy);
		takeCarWithNoSpace(boy);
	}
}

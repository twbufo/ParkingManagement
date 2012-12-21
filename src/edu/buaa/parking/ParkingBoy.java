package edu.buaa.parking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.buaa.parking.bean.Car;
import edu.buaa.parking.bean.Park;
import edu.buaa.parking.bean.ParkCard;

public class ParkingBoy {

	// 停车场列表
	private List<Park> parkList = new ArrayList<Park>();

	// 停车卡（纸）列表
	private Map<String, ParkCard> parkCardMap = new HashMap<String, ParkCard>();

	public ParkingBoy() {

		this.initial();
	}

	/**
	 * 程序初始化
	 */
	private void initial() {

		Park park1 = new Park("停车场1", 1, 1);
		parkList.add(0, park1);

		Park park2 = new Park("停车场2", 2, 2);
		parkList.add(1, park2);
	}

	/**
	 * 停车
	 * 
	 * @param car
	 * @return
	 * @throws Exception
	 */
	public ParkCard park(Car car) throws Exception {

		ParkCard parkCard = null;

		// 分配停车场
		Park park = this.distributePark();

		if (park == null) {
			System.out.println("对不起，所有停车场都没有空闲停车位！");
		} else {

			// 发卡停车
			parkCard = new ParkCard(park, car);
			this.parkCardMap.put(parkCard.getCardId(), parkCard);
			int parkSpaceNum = park.getSpaceNum();
			park.setSpaceNum(parkSpaceNum - 1);
			System.out.println("车牌号为：" + car.getNumber() + "已成功停车至["
					+ park.getParkName() + "]！");

			this.displaySpaceNum();
		}

		return parkCard;
	}

	/**
	 * 分配停车场
	 */
	private Park distributePark() {
		Park park = null;

		Park parkTmp = null;
		int spaceNumMax = 0;
		for (int i = 0; i < this.parkList.size(); i++) {
			parkTmp = this.parkList.get(i);
			if (parkTmp.hasSpace() && parkTmp.getSpaceNum() > spaceNumMax) {
				park = parkTmp;
				spaceNumMax = parkTmp.getSpaceNum();
			}
		}

		return park;
	}

	/**
	 * 取车
	 */
	public boolean drawOut(ParkCard parkCard, Car car) throws Exception {
		boolean b = false;

		if (parkCard == null) {
			System.out.println("无效的停车卡！");
			return false;
		}

		if (car == null) {
			System.out.println("车辆信息无效！");
			return false;
		}

		if (this.isAllParkEmpty()) {
			System.out.println("非法的取车请求：当前所有停车场均无车！");
			return false;
		}

		String cardId = parkCard.getCardId();
		ParkCard readyCard = this.parkCardMap.get(cardId);

		if (readyCard == null) {
			System.out.println("无效的停车卡！");
			return false;
		}

		Car readyCar = readyCard.getCar();
		if (!car.getNumber().equalsIgnoreCase(readyCar.getNumber())) {
			System.out.println("车卡不一致，不允许取车！");
			return false;
		}

		Park readyPark = readyCard.getPark();
		this.parkCardMap.remove(cardId);
		int parkSpaceNum = readyPark.getSpaceNum();
		readyPark.setSpaceNum(parkSpaceNum + 1);
		System.out.println("车牌号为：" + car.getNumber() + "已成功从["
				+ readyPark.getParkName() + "]取出！");

		return b;
	}

	private boolean isAllParkEmpty() {
		boolean b = true;
		int spaceTotal = 0;
		int spaceNum = 0;
		for (Park parkTmp : parkList) {
			spaceTotal = parkTmp.getSpaceTotal();
			spaceNum = parkTmp.getSpaceNum();

			if (spaceNum != spaceTotal) {
				b = false;
			}
		}

		return b;
	}

	/**
	 * 显示空车位数量
	 */
	public void displaySpaceNum() {
		System.out.println("##    车位显示屏        ##");
		for (Park park : parkList) {
			System.out.println("## " + park.getParkName() + "共有车位："
					+ park.getSpaceTotal() + "个，当前空车位" + park.getSpaceNum()
					+ "个");
		}
	}
}

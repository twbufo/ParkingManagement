package edu.buaa.parking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.buaa.parking.bean.Car;
import edu.buaa.parking.bean.Park;
import edu.buaa.parking.bean.ParkCard;

public class ParkingBoy {

	// ͣ�����б�
	private List<Park> parkList = new ArrayList<Park>();

	// ͣ������ֽ���б�
	private Map<String, ParkCard> parkCardMap = new HashMap<String, ParkCard>();

	public ParkingBoy() {

		this.initial();
	}

	/**
	 * �����ʼ��
	 */
	private void initial() {

		Park park1 = new Park("ͣ����1", 1, 1);
		parkList.add(0, park1);

		Park park2 = new Park("ͣ����2", 2, 2);
		parkList.add(1, park2);
	}

	/**
	 * ͣ��
	 * 
	 * @param car
	 * @return
	 * @throws Exception
	 */
	public ParkCard park(Car car) throws Exception {

		ParkCard parkCard = null;

		// ����ͣ����
		Park park = this.distributePark();

		if (park == null) {
			System.out.println("�Բ�������ͣ������û�п���ͣ��λ��");
		} else {

			// ����ͣ��
			parkCard = new ParkCard(park, car);
			this.parkCardMap.put(parkCard.getCardId(), parkCard);
			int parkSpaceNum = park.getSpaceNum();
			park.setSpaceNum(parkSpaceNum - 1);
			System.out.println("���ƺ�Ϊ��" + car.getNumber() + "�ѳɹ�ͣ����["
					+ park.getParkName() + "]��");

			this.displaySpaceNum();
		}

		return parkCard;
	}

	/**
	 * ����ͣ����
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
	 * ȡ��
	 */
	public boolean drawOut(ParkCard parkCard, Car car) throws Exception {
		boolean b = false;

		if (parkCard == null) {
			System.out.println("��Ч��ͣ������");
			return false;
		}

		if (car == null) {
			System.out.println("������Ϣ��Ч��");
			return false;
		}

		if (this.isAllParkEmpty()) {
			System.out.println("�Ƿ���ȡ�����󣺵�ǰ����ͣ�������޳���");
			return false;
		}

		String cardId = parkCard.getCardId();
		ParkCard readyCard = this.parkCardMap.get(cardId);

		if (readyCard == null) {
			System.out.println("��Ч��ͣ������");
			return false;
		}

		Car readyCar = readyCard.getCar();
		if (!car.getNumber().equalsIgnoreCase(readyCar.getNumber())) {
			System.out.println("������һ�£�������ȡ����");
			return false;
		}

		Park readyPark = readyCard.getPark();
		this.parkCardMap.remove(cardId);
		int parkSpaceNum = readyPark.getSpaceNum();
		readyPark.setSpaceNum(parkSpaceNum + 1);
		System.out.println("���ƺ�Ϊ��" + car.getNumber() + "�ѳɹ���["
				+ readyPark.getParkName() + "]ȡ����");

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
	 * ��ʾ�ճ�λ����
	 */
	public void displaySpaceNum() {
		System.out.println("##    ��λ��ʾ��        ##");
		for (Park park : parkList) {
			System.out.println("## " + park.getParkName() + "���г�λ��"
					+ park.getSpaceTotal() + "������ǰ�ճ�λ" + park.getSpaceNum()
					+ "��");
		}
	}
}

package edu.buaa.parking.bean;

public class Park {
	
	/**
	 * 此停车场的名称
	 */
	private String parkName;
	
	/**
	 * 此停车场停车位总数
	 */
	private int spaceTotal;
	
	/**
	 * 此停车场空车位的个数
	 */
	private int spaceNum;

	
	
	public Park() {
		super();
	}


	public Park(String parkName, int spaceTotal, int spaceNum) {
		super();
		this.parkName = parkName;
		this.spaceTotal = spaceTotal;
		this.spaceNum = spaceNum;
	}


	public String getParkName() {
		return parkName;
	}


	public void setParkName(String parkName) {
		this.parkName = parkName;
	}


	public int getSpaceTotal() {
		return spaceTotal;
	}


	public void setSpaceTotal(int spaceTotal) {
		this.spaceTotal = spaceTotal;
	}


	public int getSpaceNum() {
		return spaceNum;
	}


	public void setSpaceNum(int spaceNum) {
		this.spaceNum = spaceNum;
	}
	
	public boolean hasSpace(){

		if (this.spaceNum > 0) {
			return true;
		} else {
			return false;
		}
	}
	
}

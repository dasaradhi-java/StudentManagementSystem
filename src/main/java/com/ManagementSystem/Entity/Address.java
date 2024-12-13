package com.ManagementSystem.Entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {
	@Override
	public String toString() {
		return "Address [doorNo=" + doorNo + ", area=" + area + ", city=" + city + ", district=" + district + ", state="
				+ state + ", pincode=" + pincode + "]";
	}
	private String doorNo;
	private String area;
	private String city;
	private String district;
	private String state;
	private String pincode;
	public String getDoorNo() {
		return doorNo;
	}
	public void setDoorNo(String doorNo) {
		this.doorNo = doorNo;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	
	
	

}

package com.dongnesari.comm.test.vo;

public class AddressVO {
	private int addrId;
	private String zipcode;
	private String baseAddr;
	private String plusAddr;
	private String isUsing;

	public AddressVO() {}
	
	public AddressVO(String zipcode, String baseAddr, String plusAddr) {
		this.zipcode = zipcode;
		this.baseAddr = baseAddr;
		this.plusAddr = plusAddr;
	}

	public int getAddrId() {		return addrId;	}
	public void setAddrId(int addrId) {		this.addrId = addrId;	}
	public String getZipcode() {		return zipcode;	}
	public void setZipcode(String zipcode) {		this.zipcode = zipcode;	}
	public String getBaseAddr() {		return baseAddr;	}
	public void setBaseAddr(String baseAddr) {		this.baseAddr = baseAddr;	}
	public String getPlusAddr() {		return plusAddr;	}
	public void setPlusAddr(String plusAddr) {		this.plusAddr = plusAddr;	}
	public String getIsUsing() {		return isUsing;	}
	public void setIsUsing(String isUsing) {		this.isUsing = isUsing;	}
	
	public String toLogString() {
	    return "Addr{addrId=" + addrId + ", zipcode='" + zipcode + '\'' + ", baseAddr='" + baseAddr + '\'' + ", plusAddr='" + plusAddr + '\'' + ", isUsing='" + isUsing + '\'' + '}';
	}
}
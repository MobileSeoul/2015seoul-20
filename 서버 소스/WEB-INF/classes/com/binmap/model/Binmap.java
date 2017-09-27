package com.binmap.model;

public class Binmap {
	private int bin_id;
	private String gu;
	private String latitude;
	private String longitude;
	private int status;
	private int recycle;
	private int useRate;

	public int getBin_id() {
		return bin_id;
	}

	public void setBin_id(int bin_id) {
		this.bin_id = bin_id;
	}

	public String getGu() {
		return gu;
	}

	public void setGu(String gu) {
		this.gu = gu;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getRecycle() {
		return recycle;
	}

	public void setRecycle(int recycle) {
		this.recycle = recycle;
	}

	public int getUseRate() {
		return useRate;
	}

	public void setUseRate(int useRate) {
		this.useRate = useRate;
	}
	
	
}

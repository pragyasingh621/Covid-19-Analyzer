package com.pragya.coronavirustracker.models;

public class locationStats {
	private String states;
	private String country;
	private int latestTotalCase;
	private int differenceFromPrevDay;
	public int getDifferenceFromPrevDay() {
		return differenceFromPrevDay;
	}
	public void setDifferenceFromPrevDay(int differenceFromPrevDay) {
		this.differenceFromPrevDay = differenceFromPrevDay;
	}
	public String getStates() {
		return states;
	}
	public void setStates(String states) {
		this.states = states;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getLatestTotalCase() {
		return latestTotalCase;
	}
	public void setLatestTotalCase(int latestTotalCase) {
		this.latestTotalCase = latestTotalCase;
	}
	@Override
	public String toString() {
		return "locationStats [states=" + states + ", country=" + country + ", latestTotalCase=" + latestTotalCase
				+ ", getStates()=" + getStates() + ", getCountry()=" + getCountry() + ", getLatestTotalCase()="
				+ getLatestTotalCase() + "]";
	}
	

}

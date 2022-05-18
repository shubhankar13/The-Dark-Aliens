package com.darkaliens.darkaliens.FlightSearchResults;

public class FlightSearchData {
  private static FlightSearchData instance = null;

  public String from = "DEB";
  public String to = "VIE";
  public String date;
  public String cabinClass = "Premium economy";

  public void setFrom(String from) {
    this.from = from;
  }

  public void setTo(String to) {
    this.to = to;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public void setCabinClass(String cabinClass) {
    this.cabinClass = cabinClass;
  }

  public static FlightSearchData getInstance() {
    if (instance == null) {
      instance = new FlightSearchData();
    }
    return instance;
  }
}
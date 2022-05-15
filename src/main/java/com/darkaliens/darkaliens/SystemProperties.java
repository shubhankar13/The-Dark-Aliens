package com.darkaliens.darkaliens;

public class SystemProperties {
  public static final String tokenKeyName = "dark-aliens-token";
  public static final String uidKeyName = "dark-aliens-uid";
  public static final String refreshTokenKeyName = "dark-aliens-refresh-token";

  public void clearProperties() {
    System.clearProperty(tokenKeyName);
    System.clearProperty(uidKeyName);
    System.clearProperty(refreshTokenKeyName);
  }

  public void setProperties(String token, String uid, String refreshToken) {
    System.setProperty(tokenKeyName, token);
    System.setProperty(uidKeyName, uid);
    System.setProperty(refreshTokenKeyName, refreshToken);
  }
}

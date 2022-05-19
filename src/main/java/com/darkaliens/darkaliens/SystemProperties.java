package com.darkaliens.darkaliens;

import java.io.Serializable;

public class SystemProperties implements Serializable {
  public String uid = "uid";

  public String getUid() {
    return uid;
  }

  public void setUid(String uid) {
    this.uid = uid;
  }
}

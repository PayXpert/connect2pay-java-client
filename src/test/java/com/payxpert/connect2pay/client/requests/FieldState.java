package com.payxpert.connect2pay.client.requests;

public class FieldState {

  public String name;
  public String group;
  public boolean isMandatory;

  FieldState(String group, String name, boolean isMandatory) {
    this.group = group;
    this.name = name;
    this.isMandatory = isMandatory;
  }

}

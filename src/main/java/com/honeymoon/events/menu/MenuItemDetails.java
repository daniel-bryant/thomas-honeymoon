package com.honeymoon.events.menu;

public class MenuItemDetails {

  private String id;
  private String name;
  
  private String description;
  
  private int cost;

  public MenuItemDetails() {
	id = null;
  }

  public MenuItemDetails(String id) {
    this.id = id;
  }
  
  public MenuItemDetails(String id, String name, String description, int cost) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.cost = cost;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getCost() {
    return cost;
  }

  public void setCost(int cost) {
    this.cost = cost;
  }

  public String getdescription() {
    return description;
  }

  public void setdescription(String description) {
    this.description = description;
  }
}

package com.honeymoon.persistence.domain;

import com.honeymoon.events.menu.MenuItemDetails;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "menu_items")
public class MenuItem {

  @Id
  private String id;
  
  @Field("name")
  private String name;

  @Field("description")
  private String description;
  
  @Field("cost")
  private int cost;
  
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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public MenuItemDetails toStatusDetails() {
    return new MenuItemDetails(id, name, description, cost);
  }

  public static MenuItem fromStatusDetails(MenuItemDetails menuItemDetails) {
    MenuItem item = new MenuItem();

    item.cost = menuItemDetails.getCost();
    item.description = menuItemDetails.getdescription();
    item.name = menuItemDetails.getName();

    return item;
  }
}

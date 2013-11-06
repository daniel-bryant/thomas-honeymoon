package com.honeymoon.web.domain;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.BeanUtils;

import com.honeymoon.events.menu.MenuItemDetails;

public class MenuItem {

  private String id;
  
  @NotNull
  @NotEmpty
  private String name;

  @NotNull
  @NotEmpty
  private String description;

  @NotNull
  @Min(0)
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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getCost() {
    return cost;
  }

  public void setCost(int cost) {
    this.cost = cost;
  }
 
  public static MenuItemDetails toMenuDetails(MenuItem menuItem) {
	  MenuItemDetails menuItemDetails = new MenuItemDetails();
	  BeanUtils.copyProperties(menuItem, menuItemDetails);
	  return menuItemDetails;
  }
  
  public static MenuItem fromMenuDetails(MenuItemDetails menuItemDetails) {
	MenuItem menuItem = new MenuItem();
	BeanUtils.copyProperties(menuItemDetails, menuItem);
	return menuItem;
  }
  
}
